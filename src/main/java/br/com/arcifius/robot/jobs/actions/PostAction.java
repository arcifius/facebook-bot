package br.com.arcifius.robot.jobs.actions;

import org.knowm.sundial.exceptions.JobInterruptException;

import br.com.arcifius.robot.bootstrap.Configuration;
import br.com.arcifius.robot.facebook.IPublisher;
import br.com.arcifius.robot.facebook.models.FacebookImage;
import br.com.arcifius.robot.facebook.models.FacebookPublication;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.models.School;

/**
 * PostAction
 * This action will handle facebook promotions (posts).
 * 
 * @author Augusto Russo
 */
public class PostAction implements IAction {
    private final IPublisher publisher;
    private final Course course;
    private final School school;
    private final String mode;

    public PostAction(IPublisher publisher, School school, Course course, String mode) {
        this.publisher = publisher;
        this.school = school;
        this.course = course;
        this.mode = mode;
    }

    @Override
    public String act() throws Exception {
        FacebookImage fbImage;
        FacebookPublication publicationMode = FacebookPublication.valueOf(this.mode);
        String postMessage = Configuration.get("FB_TEXT_TEMPLATE");
        String postID = null;

        switch (publicationMode) {
            case TEXT_AND_IMAGE:
                fbImage = new FacebookImage("Course image", course.getLogo_url());
                postMessage = this.processMessage(postMessage);
                postID = publisher.publishImageWithText(fbImage, postMessage);
                break;

            case TEXT_ONLY:
                postMessage = this.processMessage(postMessage);
                postID = publisher.publish(postMessage);
                break;

            case IMAGE_ONLY:
                fbImage = new FacebookImage("Course image", course.getLogo_url());
                postID = publisher.publishImage(fbImage);
                break;

            default:            
                System.err.println("Post wasnt performed because you provided an invalid post mode."
                        + "You must provide one of: TEXT_AND_IMAGE, TEXT_ONLY or IMAGE_ONLY");
                throw new JobInterruptException();
        }

        return postID;
    }

    /**
     * Replaces reserved words from template with real data.
     */
    public String processMessage(String message) {
        String processed;
        processed = message.replace("%SCHOOL_NAME%", this.school.getName());
        processed = processed.replace("%NEW_COURSE_TITLE%", this.course.getTitle());
        processed = processed.replace("%NUMBER_OF_COURSES%", String.valueOf(this.school.getCourses().size()));
        return processed;
    }
}