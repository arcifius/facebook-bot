package br.com.arcifius.robot.models;

import java.util.List;

/**
 * Represents an eadbox school
 * 
 * @author Augusto Russo
 */
public class School {

    private final String name;
    private final List<Course> courses;

    public School(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
