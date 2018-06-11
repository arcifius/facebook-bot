package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.arcifius.robot.jobs.actions.VerifyAction;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;
import br.com.arcifius.robot.network.IFetcher;
import br.com.arcifius.robot.network.MockFetcher;

public class VerifyActionTest {
    private List<Course> coursesFromState;
    private List<Course> coursesFromSource;

    @BeforeEach
    public void prepare() {
        Course c1 = new Course();
        c1.setCourse_id("123456");

        Course c2 = new Course();
        c2.setCourse_id("123456");

        Course c3 = new Course();
        c3.setCourse_id("654321");

        coursesFromState = new ArrayList<>();
        coursesFromState.add(c1);

        coursesFromSource = new ArrayList<>();
        coursesFromSource.add(c2);
        coursesFromSource.add(c3);
    }

    @Test
    @DisplayName("Should be able to detect new courses")
    public void verifyTest() throws Exception {
        IFetcher fetcher = new MockFetcher(coursesFromSource);
        School school = new School("school", coursesFromState);

        VerifyAction verify = new VerifyAction(fetcher, school);
        List<Course> diff = verify.act();

        assertEquals(1, diff.size(), "Verify result should be 1.");
    }
}