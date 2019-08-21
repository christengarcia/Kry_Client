package view;

import client.Resource;
import client.Vars;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * This class is for the mainView GUI with the given dimensions and settings.
 */
public class mainView {
    public static BorderPane root_pane;

    public static BorderPane mainArea;
    
    public void initView() {
        mainArea = new BorderPane();
        mainArea.setPrefWidth(Resource.WINDOW_WIDTH);
        mainArea.setId("rightArea");
        
        mainArea.setCenter(Vars.login_view.setting_pane);

        root_pane = new BorderPane();
        BorderPane.setMargin(mainArea, new Insets(0, 0, 0, 0));
       
        root_pane.setCenter(mainArea);

        rootView.root_ui_pane = new BorderPane();
        rootView.root_ui_scene = new Scene(root_pane, 1000, 600);
        rootView.root_ui_scene.getStylesheets().add(getClass().getResource("/rootStyles.css").toExternalForm());
    }
}
