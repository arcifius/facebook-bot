package br.com.arcifius.robot.facebook.models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Represents an image that could be sent to Facebook.
 *
 * @author Augusto Russo
 */
public class FacebookImage {

    private String name;
    private URL url;

    public FacebookImage(String name, String url) throws MalformedURLException {
        this.name = name;
        this.url = new URL(url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return this.url;
    }

    /**
     * Downloads image from URL and convert it to a byte array.
     * 
     * @return byte array relative to the image URL or null if it fails
     */
    public byte[] getBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            HttpResponse<InputStream> stream = Unirest.get(this.url.toString()).asBinary();

            byte[] chunk = new byte[4096];
            int bytesRead;

            while ((bytesRead = stream.getBody().read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace(System.err);
            return null;
        }

        return outputStream.toByteArray();
    }

}
