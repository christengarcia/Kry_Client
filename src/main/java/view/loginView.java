package view;

import Controller.ClientSetting;
import Controller.UserInfo;
import client.Resource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class handles the GUI for the initial login window.
 */
public class loginView {

    public BorderPane setting_pane = new BorderPane();

    public Label username_label = new Label("UserName: ");
    public TextField username_filed = new TextField();

    private Button login_btn;
    private Button cancel_btn;

    // Initializes the login view window with the given dimensions and settings.
    public void initView() {
        // Username ID and text field width dimension.
        username_label.setId("roundLabel");
        username_filed.setId("round");
        username_filed.setPrefWidth(300);
        HBox alarmBox = new HBox();
        alarmBox.setAlignment(Pos.CENTER);
        alarmBox.getChildren().addAll(username_label, username_filed);

        // Box to contain text heading.
        GroupBox mainBox = new GroupBox("User Information", alarmBox, Resource.WINDOW_WIDTH - 60, 25);

        initBtns();

        // Username input box actions and settings.
        HBox chatBox = new HBox();
        chatBox.setSpacing(15);
        chatBox.setAlignment(Pos.CENTER);
        chatBox.getChildren().addAll(login_btn, cancel_btn);

        // Box to contain text above the login and cancel button.
        GroupBox btnGroup = new GroupBox("Select Required Option", chatBox, Resource.WINDOW_WIDTH - 60, 30);

        // Main area settings and dimensions.
        BorderPane main_pane = new BorderPane();
        main_pane.setId("main_area");
        BorderPane.setMargin(mainBox, new Insets(20, 10, 10, 10));
        main_pane.setCenter(mainBox);
        BorderPane.setMargin(btnGroup, new Insets(10, 10, 25, 10));
        main_pane.setBottom(btnGroup);

        // Black border settings and dimensions.
        setting_pane.setId("black_area");
        BorderPane.setMargin(main_pane, new Insets(20, 20, 20, 20));
        setting_pane.setCenter(main_pane);
    }

    private void setImageToBtn(Button button, Image image) {
        button.setContentDisplay(ContentDisplay.CENTER);
        ImageView btn_img = new ImageView(image);
        double width = 30;
        double height = 30;
        btn_img.setFitWidth(width);
        btn_img.setFitHeight(height);
        button.setGraphic(btn_img);
    }

    // Initializes the buttons.
    private void initBtns() {
        // Creates login button.
        login_btn = new Button("Login");
        login_btn.setId("normalbtn");
        login_btn.setPrefWidth(150);
        login_btn.setCursor(Cursor.HAND);

        // Creates cancel button.
        cancel_btn = new Button("Cancel");
        cancel_btn.setId("normalbtn");
        cancel_btn.setPrefWidth(150);
        cancel_btn.setCursor(Cursor.HAND);

        // If a user triggers the login button but hasn't
        // entered a username an alert message will be displayed.
        login_btn.setOnAction((evt) -> {
            String username = username_filed.getText();
            if (username.isEmpty()) {
                AlertMessage.message("Please enter username !");
                return;
            }
            UserInfo.username = username;
            if (!ClientSetting.initializeClient(username)) {
                return;
            }
        });
        login_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String username = username_filed.getText();
                if (username.isEmpty()) {
                    AlertMessage.message("Please enter username !");
                    return;
                }
                UserInfo.username = username;
                if (!ClientSetting.initializeClient(username)) {
                    return;
                }
            }
        });

        // When the cancel button is triggered the
        // login window will be closed.
        cancel_btn.setOnAction((evt) -> {
            System.exit(0);
        });
        cancel_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.exit(0);
            }
        });

    }
}
