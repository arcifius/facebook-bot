/*
 * Publisher interface
 * 
 */
package br.com.arcifius.robot.interfaces;

import br.com.arcifius.robot.models.FacebookImage;

/**
 *
 * @author Augusto Russo
 */
public interface IPublisher {

    public String publishImage(FacebookImage image);

    public String publishImage(FacebookImage image, String postMessage);

    public String publish(String postMessage);

}
