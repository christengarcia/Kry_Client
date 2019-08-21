package view;

import client.ChatClient;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * This class handles the alert messages at login.
 */
public final class AlertMessage
{
    // Makes the alert message appear and then slowly fade out.
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay,
                                int fadeInDelay, int fadeOutDelay)
    {
        // Creates a new toastStage i.e. popup and then fade away.
        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        // Text settings for the message.
        Text text = new Text(toastMsg);
        text.setFont(Font.font("Times New Roman", 30));
        text.setFill(Color.RED);

        // Root dimension and color settings.
        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 10; -fx-background-color: " +
                "rgba(255, 255, 255, 0.7); -fx-padding: 50px;");
        root.setOpacity(0);

        // Creates a new transparent Scene for the toastStage display.
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        // Fade in/out operations and settings.
        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay),
                new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);   
        fadeInTimeline.setOnFinished((ae) -> 
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay),
                            new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);   
                    fadeOutTimeline.setOnFinished((aeb) -> toastStage.close()); 
                    fadeOutTimeline.play();
            }).start();
        }); 
        fadeInTimeline.play();
    }
    public static void message(String msg){
        makeText(ChatClient.clientStage, msg, 1000, 500, 1000);
    }
}