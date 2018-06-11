package br.com.arcifius.robot.facebook;

import br.com.arcifius.robot.facebook.IPublisher;
import br.com.arcifius.robot.facebook.models.FacebookImage;

public class MockPublisher implements IPublisher {
    private String postID;
    private boolean shouldSucceed;

    public MockPublisher(String postID, boolean shouldSucceed) {
        this.postID = postID;
        this.shouldSucceed = shouldSucceed;
    }

    public MockPublisher(String postID) {
        this.postID = postID;
        this.shouldSucceed = true;
    }

    @Override
    public String publish(String postMessage) {
        if (shouldSucceed) {
            return this.postID;
        } else {
            return null;
        }
    }

    @Override
    public String publishImage(FacebookImage image) {
        if (shouldSucceed) {
            return this.postID;
        } else {
            return null;
        }
    }

    @Override
    public String publishImageWithText(FacebookImage image, String postMessage) {
        if (shouldSucceed) {
            return this.postID;
        } else {
            return null;
        }
    }
}
