package br.com.arcifius.robot.jobs.actions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;
import br.com.arcifius.robot.network.IFetcher;

public class VerifyAction implements IAction {
    private final IFetcher fetcher;
    private final School school;

    public VerifyAction(IFetcher fetcher, School school) {
        this.fetcher = fetcher;
        this.school = school;
    }

    @Override
    public List<Course> act() {
        List<Course> newCourses = new LinkedList<>();

        try {
            newCourses = this.fetcher.fetch(this.school.getName());
        } catch (IOException ex) {
            System.out.println("IO Exception:" + ex.getMessage());
        }

        // Filtering
        newCourses.removeIf(course -> this.alreadyRegistered(course.getCourse_id()));

        return newCourses;
    }

    private boolean alreadyRegistered(String courseID) {
        List<Course> courses = this.school.getCourses();
        return courses.stream().anyMatch((course) -> (course.getCourse_id().equals(courseID)));
    }
}