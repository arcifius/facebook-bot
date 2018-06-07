package br.com.arcifius.robot.state;

import br.com.arcifius.robot.models.Course;
import java.util.List;

/**
 * IState
 * 
 * @author Augusto Russo
 */
public interface IState {

    public boolean update(String school, List<Course> courses);

    public List<Course> retrieve(String school);

}