package br.com.arcifius.robot.network;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.arcifius.robot.models.Course;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Web Fetcher
 * Try to fetch network resource 5 times
 * 
 * @author Augusto Russo
 */
public class WebFetcher implements IFetcher {
    private final int maxTries = 5;
    private int currentTries = 0;

    @Override
    public List<Course> fetch(String school) throws MalformedURLException, IOException {
        URL url = new URL("https://" + school + ".eadbox.com/api/courses");
        List<Course> courses = null;

        do {
            courses = get(url);
            currentTries++;
        } while (courses == null && currentTries < maxTries);

        currentTries = 0;
        return courses;
    }

    public List<Course> get(URL url) {
        try {
            InputStreamReader reader = new InputStreamReader(url.openStream());
            Type listType = new TypeToken<List<Course>>() {
            }.getType();
            List<Course> courses = new Gson().fromJson(reader, listType);
            return courses;
        } catch (IOException ex) {
            return null;
        }
    }
}