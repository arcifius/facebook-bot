package br.com.arcifius.robot.facebook;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;

import br.com.arcifius.robot.bootstrap.Configuration;
import br.com.arcifius.robot.facebook.models.FacebookImage;

/**
 * This class manages Facebook publications.
 *
 * @author Augusto Russo
 *
 */
public class FacebookPublisher implements IPublisher {

    private final FacebookClient facebookClient;
    private final String pageID;

    /**
     * Initializes Facebook client.
     */
    public FacebookPublisher() {
        this.pageID = Configuration.get("FB_PAGE_ID");
        facebookClient = new DefaultFacebookClient(Configuration.get("FB_PAGE_TOKEN"), Version.VERSION_2_7);
    }

    /**
     * Publishes an image in the main feed.
     *
     * @param image image that will be sent within the post.
     * @return post identification or null if fails.
     */
    @Override
    public String publishImage(FacebookImage image) {
        return this.publishImageWithText(image, "");
    }

    /**
     * Publishes an image with text in the main feed
     *
     * @param image       image that will be sent within the post.
     * @param postMessage text that will be sent within the post.
     * @return post identification or null if fails.
     */
    @Override
    public String publishImageWithText(FacebookImage image, String postMessage) {
        try {
            FacebookType publication = facebookClient.publish(pageID + "/photos", FacebookType.class,
                    BinaryAttachment.with(image.getName(), image.getBytes()), Parameter.with("message", postMessage));

            return publication.getId();
        } catch (FacebookException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    /**
     * Publishes a text in the main feed
     *
     * @param postMessage text that will be sent within the post.
     * @return post identification or null if fails.
     */
    @Override
    public String publish(String postMessage) {
        try {
            FacebookType publication = facebookClient.publish(pageID + "/feed", FacebookType.class,
                    Parameter.with("message", postMessage));

            return publication.getId();
        } catch (FacebookException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }
}
