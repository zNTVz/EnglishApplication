package dictionary.main_server;

import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextToSpeech {

    private static final Logger logger = LogManager.getLogger(TextToSpeech.class);


    /**
     * Convert English input {@code text} to voice and play it with Google Translator TTS API
     *
     * @param text The text to be converted to voice in English
     */
    public static void playSoundGoogleTranslateEnToVi(String text) {
        try {
            String api =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + "en"
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream audio = con.getInputStream();
            new Player(audio).play();
            con.disconnect();
        } catch (Exception e) {
            logger.error("An error occurred:", e);
            System.err.println("Error in getting voices");
        }
    }
}


