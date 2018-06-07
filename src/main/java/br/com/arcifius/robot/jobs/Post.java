package br.com.arcifius.robot.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.exceptions.JobInterruptException;

public class Post extends Job {
    @Override
    public void doRun() throws JobInterruptException {
        // String school = this.getJobContext().get("school");
        // Course course = this.getJobContext().get("course");

        System.out.println("I should post on facebook");

        // System.out.println("SCHOOL: " + school);
        // System.out.println("NEW COURSE: " + course.getTitle());
        // System.out.println("IMAGE: " + course.getLogo_url());
    }
}