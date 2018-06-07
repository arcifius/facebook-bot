package br.com.arcifius.robot.facebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import br.com.arcifius.robot.facebook.models.FacebookImage;

public class PublisherTest {
    private final String postID = "123456";
    private String publishResult;

    @Test
    public void successfulPublication() throws Exception {
        IPublisher publisher = new MockPublisher(postID, true);
        FacebookImage fbImage = new FacebookImage("faker", "http://validurl.com.br/fakeimage.jpg");

        publishResult = publisher.publish("message");
        assertEquals(postID, publishResult, "Successful publication should return publication ID.");

        publishResult = publisher.publishImage(fbImage);
        assertEquals(postID, publishResult, "Successful image publication should return publication ID.");

        publishResult = publisher.publishImage(fbImage, "message");
        assertEquals(postID, publishResult, "Successful image and text publication should return publication ID.");
    }

    @Test
    public void failingPublication() throws Exception {
        IPublisher publisher = new MockPublisher(postID, false);
        FacebookImage fbImage = new FacebookImage("faker", "http://validurl.com.br/fakeimage.jpg");

        publishResult = publisher.publish("message");
        assertEquals(null, publishResult, "Failed publication should return null.");

        publishResult = publisher.publishImage(fbImage);
        assertEquals(null, publishResult, "Failed image publication should return null.");

        publishResult = publisher.publishImage(fbImage, "message");
        assertEquals(null, publishResult, "Failed image and text publication should return null.");
    }
}