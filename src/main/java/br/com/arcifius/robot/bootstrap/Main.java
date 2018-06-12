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
 * Here we will start our robot based on provided mode.
 *
 * @author Augusto Russo
 */
public class Main {

    public static void main(String[] args) {
        String stringMode = Configuration.get("ROBOT_MODE");
        RobotMode mode = RobotMode.valueOf(stringMode);

        // Post job initial parameters
        Map<String, Object> postJobParams = new HashMap<>();
        postJobParams.put("publisher", new FacebookPublisher());

        switch (mode) {
            case POOLING:
                // Verify job initial parameters
                Map<String, Object> verifyJobParams = new HashMap<>();
                verifyJobParams.put("fetcher", new WebFetcher());
                verifyJobParams.put("state", new MongoState(Configuration.get("DATABASE_URI")));
                verifyJobParams.put("schoolName", Configuration.get("SCHEDULER_SCHOOL"));

                // Starting the scheduler and registrying jobs
                SundialJobScheduler.startScheduler("br.com.arcifius.robot.jobs");
                SundialJobScheduler.addJob("VerifyJob", "br.com.arcifius.robot.jobs.VerifyJob", verifyJobParams, false);
                SundialJobScheduler.addCronTrigger("VerifyJob-Cron-Trigger", "VerifyJob", Configuration.get("SCHEDULER_INTERVAL"));
                SundialJobScheduler.addJob("PostJob", "br.com.arcifius.robot.jobs.PostJob", postJobParams, true);
                break;

            default:
                System.err.println("Provided mode isnt supported. We only support POOLING at the moment.");
        }
    }
}
