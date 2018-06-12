package unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.arcifius.robot.facebook.IPublisher;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.facebook.models.FacebookPublication;
import br.com.arcifius.robot.models.School;
import br.com.arcifius.robot.facebook.MockPublisher;
import br.com.arcifius.robot.jobs.actions.PostAction;

public class PostActionTest {
    private List<Course> coursesFromState;

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
        coursesFromState.add(c2);
        coursesFromState.add(c3);
    }

    @Test
    @DisplayName("Should be able to post")
    public void successfulPost() {
        IPublisher publisher = new MockPublisher("555555");
        School school = new School("School", coursesFromState);
        Course course = new Course();
        course.setTitle("Test Course");
        course.setLogo_url("https://i.pinimg.com/originals/7d/a3/c8/7da3c8ea479c5db09a097a95229e109f.jpg");

        PostAction post = new PostAction(publisher, school, course, FacebookPublication.TEXT_AND_IMAGE.toString());

        try {
            String postID = post.act();
            assertEquals("555555", postID, "post ID should be 555555.");
        } catch (Exception ex) {
            // Failed
        }
    }

    @Test
    @DisplayName("Should throw MalformedURLException when a invalid url is provided as course logo url")
    public void badUrlProvided() {
        assertThrows(MalformedURLException.class, () -> {
            IPublisher publisher = new MockPublisher("555555");
            School school = new School("School", coursesFromState);
            Course course = new Course();
            course.setTitle("Test Course");
            course.setLogo_url("this should throw an exception");

            PostAction post = new PostAction(publisher, school, course, FacebookPublication.TEXT_AND_IMAGE.toString());
            post.act();
        });
    }

    @Test
    @DisplayName("Should accept null image only when mode is TEXT_ONLY")
    public void nullImageURLWillBeAcceptedInTextOnly() throws Exception {
        assertThrows(MalformedURLException.class, () -> {
            IPublisher publisher = new MockPublisher("555555");
            School school = new School("School", coursesFromState);
            Course course = new Course();
            course.setTitle("Test Course");

            PostAction post = new PostAction(publisher, school, course, FacebookPublication.TEXT_AND_IMAGE.toString());
            post.act();
        });


        assertThrows(MalformedURLException.class, () -> {
            IPublisher publisher = new MockPublisher("555555");
            School school = new School("School", coursesFromState);
            Course course = new Course();
            course.setTitle("Test Course");

            PostAction post = new PostAction(publisher, school, course, FacebookPublication.IMAGE_ONLY.toString());
            post.act();
        });
        
        IPublisher publisher = new MockPublisher("555555");
        School school = new School("School", coursesFromState);
        Course course = new Course();
        course.setTitle("Test Course");

        PostAction post = new PostAction(publisher, school, course, FacebookPublication.TEXT_ONLY.toString());
        post.act();        
    }
}