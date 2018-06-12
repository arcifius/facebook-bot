package br.com.arcifius.robot.state;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import com.google.gson.Gson;

import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;

/**
 * Mongo Implementation to manage state
 *
 * @author Augusto Russo
 */
public class MongoState implements IState {
    
    private final Gson gson = new Gson();
    private final String mongoURI;
    private MongoClient mongo;

    public MongoState(String mongoURI) {
        this.mongoURI = mongoURI;
    }

    private MongoDatabase connect() {
        MongoClientURI uri = new MongoClientURI(this.mongoURI);
        this.mongo = new MongoClient(uri);
        MongoDatabase database = this.mongo.getDatabase("robotState");
        return database;
    }

    private void disconnect() {
        this.mongo.close();
        this.mongo = null;
    }

    @Override
    public School retrieve(String school) {
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
            List<Course> courses = new ArrayList<>();
            target.append("name", school);
            target.append("courses", courses);
            schools.insertOne(target);
        }

        // Dispose mongo connection
        this.disconnect();

        return gson.fromJson(target.toJson(), School.class);
    }

    @Override
    public boolean update(School school) {
        MongoDatabase database = this.connect();
        MongoCollection<Document> schools = database.getCollection("schools");

        List<BasicDBObject> jsonCourses = new ArrayList<>();
        for (Course course : school.getCourses()) {
            jsonCourses.add(BasicDBObject.parse(gson.toJson(course)));
        }

        Document result = schools.findOneAndUpdate(Filters.eq("name", school.getName()),
                Updates.set("courses", jsonCourses));

        // Dispose mongo connection
        this.disconnect();

        return !result.isEmpty();
    }
}
