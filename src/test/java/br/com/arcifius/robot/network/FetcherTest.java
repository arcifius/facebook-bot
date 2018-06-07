package br.com.arcifius.robot.network;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import br.com.arcifius.robot.models.Course;

public class FetcherTest {

    @Test
    public void fetch() throws Exception {
        LinkedList<Course> courses = new LinkedList<>();
        courses.add(new Course());
        courses.add(new Course());
        courses.add(new Course());
        courses.add(new Course());
        IFetcher fetcher = new MockFetcher(courses);

        List<Course> fetechedCourses = fetcher.fetch("school");

        assertEquals(fetechedCourses.size(), courses.size(), "Fetched courses should contain 4 elements");
    }

}