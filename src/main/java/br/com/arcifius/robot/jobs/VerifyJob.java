package br.com.arcifius.robot.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.arcifius.robot.jobs.actions.VerifyAction;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;
import br.com.arcifius.robot.network.IFetcher;
import br.com.arcifius.robot.state.IState;

@CronTrigger(cron = "0/10 * * * * ?")
public class VerifyJob extends Job {

    @Override
    public void doRun() throws JobInterruptException {
        System.out.println("Looking for new courses...");

        IFetcher fetcher = this.getJobContext().get("fetcher");
        IState state = this.getJobContext().get("state");
        String schoolName = this.getJobContext().get("school");

        // School from state
        School school = state.retrieve(schoolName);

        // Looking for new courses
        VerifyAction verify = new VerifyAction(fetcher, school);
        List<Course> newCourses = verify.act();
        System.out.println("Found " + newCourses.size() + " new course(s) for " + school.getName());

        // Fires a Post job for each new course
        // Jobs run asynchronously, we cant assure the post will be sent successfully
        for (Course course : newCourses) {
            Map<String, Object> params = new HashMap<>();
            params.put("course", course);
            params.put("school", school);
            SundialJobScheduler.startJob("PostJob", params);
        }

        // The state must be updated to avoid duplicated posts
        // NOTE: Assuming the post and state implementation ALWAYS works.
        school.getCourses().addAll(newCourses);
        state.update(school);
    }
}