package br.com.arcifius.robot.bootstrap;

import java.util.HashMap;
import java.util.Map;

import org.knowm.sundial.SundialJobScheduler;

import br.com.arcifius.robot.facebook.FacebookPublisher;
import br.com.arcifius.robot.models.RobotMode;
import br.com.arcifius.robot.network.WebFetcher;
import br.com.arcifius.robot.state.MongoState;

/**
 * Main class.
 *
 * @author Augusto Russo
 */
public class Main {

    public static void main(String[] args) {
        Configuration.update();

        String stringMode = Configuration.get("ROBOT_MODE");
        RobotMode mode = RobotMode.valueOf(stringMode);

        switch (mode) {
        case POOLING:
            Map<String, Object> verifyJobParams = new HashMap<>();
            verifyJobParams.put("fetcher", new WebFetcher());
            verifyJobParams.put("state", new MongoState(Configuration.get("DATABASE_URI")));
            verifyJobParams.put("schoolName", Configuration.get("SCHEDULER_SCHOOL"));

            Map<String, Object> postJobParams = new HashMap<>();
            postJobParams.put("publisher", new FacebookPublisher());

            SundialJobScheduler.startScheduler("br.com.arcifius.robot.jobs");
            SundialJobScheduler.addJob("VerifyJob", "br.com.arcifius.robot.jobs.VerifyJob", verifyJobParams, false);
            SundialJobScheduler.addCronTrigger("VerifyJob-Cron-Trigger", "VerifyJob", Configuration.get("SCHEDULER_INTERVAL"));
            SundialJobScheduler.addJob("PostJob", "br.com.arcifius.robot.jobs.PostJob", postJobParams, true);
            break;

        case WEBHOOK:
            // Not implemented yet
            System.err.println(mode + " mode isnt available.");
            break;

        default:
            System.err.println("Provided mode isnt supported. You must use POOLING or WEBHOOK");
        }
    }
}
