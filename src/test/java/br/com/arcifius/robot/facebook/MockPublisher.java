package br.com.arcifius.robot.facebook;

import br.com.arcifius.robot.facebook.models.FacebookImage;

public class MockPublisher implements IPublisher {
    private String postID;
    private boolean shouldSucceed;

    MockPublisher(String postID, boolean shouldSucceed) {
        this.postID = postID;
        this.shouldSucceed = shouldSucceed;
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
    public String publishImage(FacebookImage image, String postMessage) {
        if (shouldSucceed) {
            return this.postID;
        } else {
            return null;
        }
    }
}
