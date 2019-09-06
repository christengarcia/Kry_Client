package client;

import javafx.application.Application;
import javafx.stage.Stage;
import view.chatView;
import view.loginView;
import view.mainView;
import view.rootView;

/**
 * This class initiates the loginView, chatView and mainView
 * instances. All the related classes are located in the
 * view package.
 */
public class ChatClient extends Application {
    
    public static Stage clientStage;
    @Override
    public void start(Stage primaryStage) {
        clientStage = primaryStage;
        Resource.initWindowSize();
        Vars.chat_view = new chatView();
        Vars.chat_view.initView();
        
        Vars.login_view = new loginView();
        Vars.login_view.initView();
        
        Vars.main_view = new mainView();
        Vars.main_view.initView();

        clientStage.setTitle(Resource.TITLE);
        clientStage.setScene(rootView.root_ui_scene);
        clientStage.show();
    }

    /**
     * Initiate main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
