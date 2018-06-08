package br.com.arcifius.robot.bootstrap;

import java.util.HashMap;
import java.util.Map;

import org.knowm.sundial.SundialJobScheduler;

import br.com.arcifius.robot.network.WebFetcher;
import br.com.arcifius.robot.state.MongoState;

/**
 * Main class.
 *
 * @author Augusto Russo
 */
public class Main {

    public static void main(String[] args) {
        Map<String, Object> verifyParams = new HashMap<>();
        verifyParams.put("fetcher", new WebFetcher());
        verifyParams.put("state", new MongoState());
        verifyParams.put("school", System.getenv().get("FACEBOOKMANAGER_SCHOOL"));

        SundialJobScheduler.startScheduler("br.com.arcifius.robot.jobs");
        SundialJobScheduler.addJob("VerifyJob", "br.com.arcifius.robot.jobs.VerifyJob", verifyParams, false);
        SundialJobScheduler.addJob("PostJob", "br.com.arcifius.robot.jobs.PostJob", null, true);
    }

}
