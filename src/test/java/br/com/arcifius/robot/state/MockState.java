package br.com.arcifius.robot.state;

import java.util.List;

import br.com.arcifius.robot.models.Course;

public class MockState implements IState {
    private List<Course> courses;
    private boolean saveShouldSucceed;

    MockState(List<Course> courses, boolean saveShouldSucceed) {
        this.courses = courses;
        this.saveShouldSucceed = saveShouldSucceed;
    }

    @Override
    public List<Course> retrieve(String school) {
        return this.courses;
    }

    @Override
    public boolean update(String school, List<Course> courses) {
        return this.saveShouldSucceed;
    }
}
