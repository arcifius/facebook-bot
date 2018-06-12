package br.com.arcifius.robot.facebook.models;

import java.io.Serializable;

/**
 * This ENUM defines all available publication types for Facebook.
 * 
 * @author Augusto Russo
 */
public enum FacebookPublication implements Serializable {
    TEXT_AND_IMAGE, TEXT_ONLY, IMAGE_ONLY;
}