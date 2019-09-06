package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This class is the controller for sound notifications.
 */

public class Alert {

    public static Media media;
    public static MediaPlayer alert_player;

    public static void AlertNotification(String path) {
        try {
            media = new Media(new File(path).toURI().toURL().toString());
            alert_player = new MediaPlayer(media);
            alert_player.setOnError(()
                    -> System.out.println("media error" + alert_player.getError().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alert_player.play();
    }
}
