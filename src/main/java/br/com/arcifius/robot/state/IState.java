package br.com.arcifius.robot.state;

import br.com.arcifius.robot.models.School;

/**
 * IState
 * 
 * @author Augusto Russo
 */
public interface IState {

    public boolean update(School school);

    public School retrieve(String school);

}