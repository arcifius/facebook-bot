package br.com.arcifius.robot.models;

/**
 * Represents an image that could be sent to Facebook.
 *
 * @author Augusto Russo
 */
public class FacebookImage {

    private String name;
    private String mime;
    private byte[] byteArray;

    public FacebookImage(String name, String mime, byte[] byteArray) {
        this.name = name;
        this.mime = mime;
        this.byteArray = byteArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

}
