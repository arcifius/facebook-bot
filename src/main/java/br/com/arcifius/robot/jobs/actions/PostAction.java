package br.com.arcifius.robot.jobs.actions;

import br.com.arcifius.robot.facebook.IPublisher;
import br.com.arcifius.robot.models.Course;

public class PostAction implements IAction {
    private IPublisher publisher;
    private Course course;

    public PostAction(IPublisher publisher, Course course) {
        this.publisher = publisher;
        this.course = course;
    }

    @Override
    public String act() {
        return null;
    }
}