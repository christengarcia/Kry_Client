package view;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * This class handles the animations for the toast messages.
 * @author
 */
public class AnimationEffect {
    public static void animation_show(Node node){
        FadeTransition ft = new FadeTransition(Duration.millis(700), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
