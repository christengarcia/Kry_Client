package client;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * This class handles the resources for the chat window's dimensions.
 * @author
 */
public class Resource {
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static int SIDE_AREA_WIDTH;
    public static int RIGHT_AREA_WIDTH;
    public static int MAIN_VIEW_WIDTH;
    
    public static String TITLE = "Chat Client";

    // Initializes the window size with the given settings.
    public static void initWindowSize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        WINDOW_WIDTH = (int)primaryScreenBounds.getWidth();
        WINDOW_HEIGHT = (int)primaryScreenBounds.getHeight();
        MAIN_VIEW_WIDTH = WINDOW_WIDTH / 2;
        SIDE_AREA_WIDTH = (int)(WINDOW_WIDTH * 0.15);
        RIGHT_AREA_WIDTH = (int)(WINDOW_WIDTH * 0.85);
    }
}
