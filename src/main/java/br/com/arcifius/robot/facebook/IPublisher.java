/*
 * Publisher interface
 * 
 */
package br.com.arcifius.robot.facebook;

import br.com.arcifius.robot.facebook.models.FacebookImage;

/**
 *
 * @author Augusto Russo
 */
public interface IPublisher {

    public String publishImage(FacebookImage image);

    public String publishImage(FacebookImage image, String postMessage);

    public String publish(String postMessage);

}
