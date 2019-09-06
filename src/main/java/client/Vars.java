package client;

import view.chatView;
import view.loginView;
import view.mainView;

/**
 * This class creates instances of the LogoManager,
 * mainView, chatView and loginView classes.
 */
public class Vars {
    public static LogoManager logo = new LogoManager();
    public static mainView main_view = new mainView();
    public static chatView chat_view = new chatView();
    public static loginView login_view = new loginView();
}
