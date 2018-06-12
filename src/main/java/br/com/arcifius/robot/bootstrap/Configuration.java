package br.com.arcifius.robot.bootstrap;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will offer support to establish configuration defaults.
 * We leave all configuration details here.
 * 
 * @author Augusto Russo
 */
public class Configuration {
    public static Map<String, String> configuration = new HashMap<>();

    // Disable constructor
    private Configuration() {
    }

    /**
     * Updates configuration.<br>
     * OPTIONS LIST:<br>
     * FBBOT_PUBLICATION_TYPE: TEXT_AND_IMAGE, TEXT_ONLY, IMAGE_ONLY<br>
     * FBBOT_ROBOT_MODE: POOLING<br>
     * FB_TEXT_TEMPLATE will resolve variables: %NUMBER_OF_COURSES%, %NEW_COURSE_TITLE%, %SCHOOL_NAME%
     */
    public static void update() {
        Map<String, String> env = System.getenv();

        // Facebook
        configuration.put("FB_PAGE_ID", env.getOrDefault("FBBOT_FB_PAGE_ID", ""));
        configuration.put("FB_PAGE_TOKEN", env.getOrDefault("FBBOT_FB_PAGE_TOKEN", ""));
        configuration.put("FB_PUBLICATION_TYPE", env.getOrDefault("FBBOT_FB_PUBLICATION_TYPE", "TEXT_ONLY"));
        configuration.put("FB_TEXT_TEMPLATE", env.getOrDefault("FBBOT_FB_TEXT_TEMPLATE", "Confira nosso novo curso: %NEW_COURSE_TITLE%"));

        // Robot
        configuration.put("ROBOT_MODE", env.getOrDefault("FBBOT_ROBOT_MODE", "POOLING"));
        configuration.put("DATABASE_URI", env.getOrDefault("FBBOT_DATABASE_URI", "mongodb://database:27017"));

        // Scheduler
        configuration.put("SCHEDULER_SCHOOL", env.getOrDefault("FBBOT_SCHEDULER_SCHOOL", ""));
        configuration.put("SCHEDULER_INTERVAL", env.getOrDefault("FBBOT_SCHEDULER_INTERVAL", "0/60 * * * * ?"));
    }

    /**
     * Gets a specific configuration.
     * 
     * @return configuration value or null if it does not exist.
     */
    public static String get(String key) {
        if (configuration.size() == 0) {
            update();
        }

        return configuration.get(key);
    }
}
