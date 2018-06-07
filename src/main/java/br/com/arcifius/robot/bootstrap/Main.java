package br.com.arcifius.robot.bootstrap;

import br.com.arcifius.robot.interfaces.*;
import br.com.arcifius.robot.models.Course;
import br.com.arcifius.robot.network.WebFetcher;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 *
 * @author Augusto Russo
 */
public class Main {

    // private final static String TOKEN_DE_ACESSO_DA_PAGINA = "EAADAetkDqMIBAKaZC5zYfcCPZAN5mmhoQwsD6NpqfB5UhF9y2onXMhtpwjwSM2OCWhPvR1xqmLSBjfT702NuFf1TfyZAnPMlMeVHGziuf3AlYDNzAyVHGRS8k1C9NZCaWyZCux6CdFhzwALRc0AvNcvo0lZBqVdwV6nwRAuFcwKwZDZD";
    // private final static String ID_DA_PAGINA = "arcifiustestes";    

    public static void main(String[] args) {
        // IPublisher publisher = new FacebookPublisher(ID_DA_PAGINA, TOKEN_DE_ACESSO_DA_PAGINA);
        IFetcher fetcher = new WebFetcher();
        
        try {
            List<Course> courses = fetcher.fetch("arcifius");
            Gson gson = new Gson();
            System.out.println(gson.toJson(courses));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
