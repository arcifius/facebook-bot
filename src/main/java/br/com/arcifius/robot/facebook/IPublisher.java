package br.com.arcifius.robot.facebook;

import br.com.arcifius.robot.facebook.models.FacebookImage;

/**
 * Publisher interface
 * 
 * @author Augusto Russo
 */
public interface IPublisher {

    public String publish(String postMessage);

    public String publishImage(FacebookImage image);

    public String publishImageWithText(FacebookImage image, String postMessage);

}
