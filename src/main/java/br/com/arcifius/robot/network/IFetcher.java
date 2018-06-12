package br.com.arcifius.robot.network;

import br.com.arcifius.robot.models.Course;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Fetcher interface
 * 
 * @author Augusto Russo
 */
public interface IFetcher {

    public List<Course> fetch(String school) throws MalformedURLException, IOException;

}
