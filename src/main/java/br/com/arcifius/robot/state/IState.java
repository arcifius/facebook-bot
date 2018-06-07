package br.com.arcifius.robot.state;

import br.com.arcifius.robot.models.Course;
import java.util.List;

/**
 * IState
 * 
 * @author Augusto Russo
 */
public interface IState {

    public boolean save(Course course);

    public List<Course> retrieve(String school);

}