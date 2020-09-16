/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anime.bot;



import java.io.IOException;
import twitter4j.TwitterException;


/**
 *
 * @author Utilisateur */
public class AnimeBot {

    

    /**
     * @param args the command line arguments
     * @throws twitter4j.TwitterException
     */
    
   

    

    public static void main(String[] args) throws TwitterException, InterruptedException, IOException, Exception {
        TweetConfig config = new TweetConfig();
//        Classe permettant la création des tweets
        config.TweetGif();

    }

}
