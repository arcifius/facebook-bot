package br.com.arcifius.robot.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.network.IFetcher;
import br.com.arcifius.robot.state.IState;

@CronTrigger(cron = "0/10 * * * * ?")
public class Verify extends Job {

    @Override
    public void doRun() throws JobInterruptException {
        System.out.println("Looking for new courses...");

        String school = this.getJobContext().get("school");
        IFetcher fetcher = this.getJobContext().get("fetcher");
        IState state = this.getJobContext().get("state");

        // Looking for new courses
        List<Course> newCourses = new LinkedList<>();
        List<Course> oldCourses = new LinkedList<>();
        
        try {            
            oldCourses = state.retrieve(school);
            newCourses = fetcher.fetch(school);
        } catch (IOException ex) {
            System.out.println("IO Exception:" + ex.getMessage());
        }

        // Filtering
        newCourses.removeAll(oldCourses);

        System.out.println(oldCourses.size());
        System.out.println(newCourses.size());
        System.out.println(school + " have " + newCourses.size() + " new courses");

        // Fires a Post job for each new course
        // Jobs run asynchronously, we cant assure the post will be sent successfully
        for (Course course : newCourses) {
            Map<String, Object> params = new HashMap<>();
            params.put("course", course);
            params.put("school", school);
            SundialJobScheduler.startJob("Post", params);
        }

        // The state must be updated to avoid duplicated posts
        // IMPORTANT: Trusting the post and state implementation ALWAYS works.
        newCourses.addAll(oldCourses);
        state.update(school, newCourses);
    }
}