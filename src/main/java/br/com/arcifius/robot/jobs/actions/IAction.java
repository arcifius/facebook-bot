package br.com.arcifius.robot.jobs.actions;

/**
 * Action interface
 * Jobs must fire actions to fulfill their objectives.
 * 
 * @author Augusto Russo
 */
public interface IAction {

    public Object act() throws Exception;

}