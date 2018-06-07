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
        SundialJobScheduler.addJob("Verify", "br.com.arcifius.robot.jobs.Verify", verifyParams, false);
        SundialJobScheduler.addJob("Post", "br.com.arcifius.robot.jobs.Post", null, true);
    }

}
