package br.com.arcifius.robot.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.exceptions.JobInterruptException;

import br.com.arcifius.robot.bootstrap.Configuration;
import br.com.arcifius.robot.facebook.IPublisher;
import br.com.arcifius.robot.jobs.actions.PostAction;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;

/**
 * PostJob will forward relevant information from job context to PostAction
 * 
 * @author Augusto Russo
 */
public class PostJob extends Job {
    @Override
    public void doRun() throws JobInterruptException {
        // Expected context variables
        IPublisher publisher = this.getJobContext().get("publisher");
        School school = this.getJobContext().get("school");
        Course course = this.getJobContext().get("course");

        if (publisher == null || school == null || course == null) {
            System.err.println("Post job expects the job context to contain (IPublisher publisher, School school, Course course)");
            throw new JobInterruptException();
        }
        
        try {
            PostAction postAction = new PostAction(publisher, school, course, Configuration.get("FB_PUBLICATION_TYPE"));
            postAction.act();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}