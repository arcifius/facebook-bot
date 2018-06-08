package br.com.arcifius.robot.state;

import br.com.arcifius.robot.models.School;

public class MockState implements IState {
    private School school;
    private boolean updateShouldSucceed;

    public MockState(School school, boolean updateShouldSucceed) {
        this.school = school;
        this.updateShouldSucceed = updateShouldSucceed;
    }

    public MockState(School school) {
        this.school = school;
        this.updateShouldSucceed = false;
    }

    @Override
    public School retrieve(String school) {
        return this.school;
    }

    @Override
    public boolean update(School school) {
        return this.updateShouldSucceed;
    }
}
