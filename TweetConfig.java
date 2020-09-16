/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anime.bot;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Utilisateur
 */
public class TweetConfig {

//    Variable
    public static TwitterFactory recupconfig;
//    public String user;

    //    Configuration pour accéder a notre compte
    public void TweetConfig() throws TwitterException {

        //        Configuration des clés tokens Twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("*")
                .setOAuthConsumerSecret("*")
                .setOAuthAccessToken("*")
                .setOAuthAccessTokenSecret("*");
        recupconfig = new TwitterFactory(cb.build());

    }
//    Méthode permettant le post des gifs

    public void TweetGif() throws TwitterException, InterruptedException, MalformedURLException, IOException, GiphyException {

//      Configuration de l'api Giphy
        Giphy giphy = new Giphy("*");

//      Récupération d'un gif random dans le thème des studio ghibli
        SearchRandom giphyData = giphy.searchRandom("anime");

//      Récupération de l'URL du GIF
        String urlgif = giphyData.getData().getImageUrl();

//      Path de notre gif a tweeter
        File mongif = new File("ghibliGIF.gif");

//      Installation du GIF 
////    ligne de commande shell permettant la sauvegarde de notre gif récupérer sur giphy.com
        String commande = "curl -o ghibliGIF.gif " + urlgif;
        ProcessBuilder processBuilder = new ProcessBuilder(commande.split(" "));

////    Répertoire ou on récuperera le gif installer    
        processBuilder.directory(new File(mongif.getAbsoluteFile().getParent()));
        
////    Lancement du script shell
        Process process = processBuilder.start();

//      Appel à la méthode permettant la config de notre compte twitter
        TweetConfig();

//        Préparation du status
        Twitter twitter = recupconfig.getInstance();
//        Corps manuscrit du tweet
        StatusUpdate status = new StatusUpdate("");
//        Ajout du GIF dans notre tweet
        status.setMedia("mongif", new FileInputStream(mongif));
//        Envoi du tweet
        twitter.updateStatus(status);
//        Check de débogage
        System.out.println("Successfully updated the status to [" + status.toString() + "].");
    }
}
