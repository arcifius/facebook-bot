/*
 * Represents an eadbox course
 */
package br.com.arcifius.robot.models;

/**
 *
 * @author Augusto Russo
 */
public class Course {

    private String course_id;
    private String course_slug;
    private String title;
    private String description;
    private boolean is_paid;
    private float price;
    private String objective;
    private String certification;
    private String target_audience;
    private int workload;
    private String time_available;
    private String topics;
    private String logo_url;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_slug() {
        return course_slug;
    }

    public void setCourse_slug(String course_slug) {
        this.course_slug = course_slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getTarget_audience() {
        return target_audience;
    }

    public void setTarget_audience(String target_audience) {
        this.target_audience = target_audience;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public String getTime_available() {
        return time_available;
    }

    public void setTime_available(String time_available) {
        this.time_available = time_available;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

}
