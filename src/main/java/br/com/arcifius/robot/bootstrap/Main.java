package br.com.arcifius.robot.bootstrap;

import br.com.arcifius.robot.state.IState;
import br.com.arcifius.robot.network.IFetcher;
import br.com.arcifius.robot.facebook.FacebookPublisher;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.facebook.models.FacebookImage;
import br.com.arcifius.robot.models.School;
import br.com.arcifius.robot.network.WebFetcher;
import br.com.arcifius.robot.state.MongoImplementation;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 *
 * @author Augusto Russo
 */
public class Main {

    public static void main(String[] args) {
        /*
         * try { IPublisher publisher = new FacebookPublisher();
         * publisher.publishImage( new FacebookImage("image",
         * "https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg"));
         * publisher.publishImage( new FacebookImage("image",
         * "https://www.codeproject.com/KB/GDI-plus/ImageProcessing2/img.jpg"),
         * "Image and text"); publisher.publish("Post with text only"); } catch
         * (MalformedURLException ex) { System.err.println("Invalid URL:" +
         * ex.getMessage()); }
         */

        // TODO: Move this code to br.com.arcifius.robot.jobs.Verify
        //       Pass interfaces on job constructor so we can mock later...
        IFetcher fetcher = new WebFetcher();
        IState state = new MongoImplementation();
        String schoolName = "arcifius";
        List<Course> newCourses;
        List<Course> oldCourses;

        try {
            newCourses = fetcher.fetch(schoolName);
            oldCourses = state.retrieve(schoolName);

            // Check new courses
            newCourses.removeAll(oldCourses);

            System.out.println("SCHOOL "+ schoolName +" HAS " + newCourses.size() + " NEW COURSE(S)");
        } catch (IOException ex) {
            System.err.println("IO Exception:" + ex.getMessage());
        }
    }

}
