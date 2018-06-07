package br.com.arcifius.robot.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.arcifius.robot.models.Course;

public class StateTest {
    private LinkedList<Course> courses;

    @BeforeEach
    public void prepare() {
        this.courses = new LinkedList<>();
        courses.add(new Course());
        courses.add(new Course());
        courses.add(new Course());
        courses.add(new Course());
    }

    @Test
    public void retrieve() throws Exception {
        IState state = new MockState(this.courses, true);

        List<Course> fetechedCourses = state.retrieve("school");
        assertEquals(fetechedCourses.size(), courses.size(), "Fetched courses should contain 4 elements");
    }


    @Test
    public void successfulCourseSave() throws Exception {        
        IState state = new MockState(null, true);

        boolean saved = state.update("school", this.courses);
        assertTrue(saved, "Successfully saved course should return true");
    }

    @Test
    public void failingCourseSave() throws Exception {        
        IState state = new MockState(null, false);

        boolean saved = state.update("school", this.courses);
        assertFalse(saved, "Failed course save should return false");
    }

}