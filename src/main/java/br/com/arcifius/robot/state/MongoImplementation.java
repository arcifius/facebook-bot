package br.com.arcifius.robot.state;

import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import java.util.LinkedList;
import java.util.List;
import br.com.arcifius.robot.models.Course;
import com.mongodb.client.FindIterable;

/**
 * Mongo Implementation to manage state
 *
 * @author Augusto Russo
 */
public class MongoImplementation implements IState {

    private MongoClient mongo;

    private MongoDatabase connect() {        
        this.mongo = new MongoClient("localhost", 27017);        
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
            // If the school isnt present we will create an entry for it
            target = new Document();
            List<Course> courses = new LinkedList<>();
            target.append("name", school);
            target.append("courses", courses);
            schools.insertOne(target);
        }

        this.disconnect();
        
        return (List<Course>) target.get("courses");
    }

    @Override
    public boolean save(Course course) {
        // TODO: save a course
        return false;
    }
}
