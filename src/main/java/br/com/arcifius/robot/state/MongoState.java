package br.com.arcifius.robot.state;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import br.com.arcifius.robot.models.Course;
import com.mongodb.client.FindIterable;

/**
 * Mongo Implementation to manage state
 *
 * @author Augusto Russo
 */
public class MongoState implements IState {

    private MongoClient mongo;

    private MongoDatabase connect() {
        MongoClientURI uri = new MongoClientURI(System.getenv().get("FACEBOOKMANAGER_DATABASE_URI"));
        this.mongo = new MongoClient(uri);
        MongoDatabase database = this.mongo.getDatabase("robotState");
        return database;
    }

    private void disconnect() {
        this.mongo.close();
        this.mongo = null;
    }

    @Override
    public List<Course> retrieve(String school) {
        MongoDatabase database = this.connect();
        MongoCollection<Document> schools = database.getCollection("schools");
        BasicDBObject fields = new BasicDBObject("name", school);
        FindIterable<Document> result = schools.find(fields);
        Document target = null;

        if (result.first() != null) {
            target = result.first();
        } else {
            // If the school isnt present, create an entry for it
            target = new Document();
            List<Course> courses = new LinkedList<>();
            target.append("name", school);
            target.append("courses", courses);
            schools.insertOne(target);
        }

        // Dispose mongo connection
        this.disconnect();

        return (List<Course>) target.get("courses");
    }

    @Override
    public boolean update(String school, List<Course> courses) {
        MongoDatabase database = this.connect();
        MongoCollection<Document> schools = database.getCollection("schools");
        Gson gson = new Gson();

        List<BasicDBObject> formattedCourses = new ArrayList<>();
        for (Course course : courses) {
            formattedCourses.add(BasicDBObject.parse(gson.toJson(course)));
        }

        Document result = schools.findOneAndUpdate(Filters.eq("name", school),
                Updates.set("courses", formattedCourses));

        // Dispose mongo connection
        this.disconnect();

        return !result.isEmpty();
    }
}
