package br.com.arcifius.robot.network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.network.IFetcher;

public class MockFetcher implements IFetcher {
    private List<Course> courses;

    public MockFetcher(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public List<Course> fetch(String school) throws MalformedURLException, IOException {
        return this.courses;
    }
}
