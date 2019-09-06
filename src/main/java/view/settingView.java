package view;

import Controller.ClientSetting;
import Model.AlertControls;
import client.ChatClient;
import client.Resource;
import client.Vars;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is for the settings GUI that toggles on/off incoming
 * message notifications. When an alarm is disabled for a specific
 * user it will be marked with a crossed out bell.
 */
public class settingView {

    private Stage setting_stage;
    private BorderPane setting_pane = new BorderPane();

    public CheckBox notification_box = new CheckBox("Notification");

    private Button ok_btn;

    public String alarm_content = new String();

    public boolean confirm_flag = false;
    
    public AlertControls user_cell;

    // Initializes the settings view window.
    public void initView() {
        // Set the check box ID and add listener for "checked" actions.
        notification_box.setId("checkbox");
        notification_box.setSelected(ClientSetting.noti);
        notification_box.selectedProperty().addListener(new ChangeListener<Boolean>() {
            // Process changes to the active users list when sound settings for a specific user is modified.
            // Displays a crossed out bell icon if incoming sound message is disabled for a specific user.
            // When re-activating incoming sound message for a specific user a normal bell will appear.
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                ClientSetting.noti = newValue;
                ClientSetting.user_noti.put(user_cell.username, newValue);
                int index = Vars.chat_view.user_list.getSelectionModel().getSelectedIndex();
                if(!newValue){
                    AlertControls updated_cell = new AlertControls(user_cell.username, false, Vars.logo.getRingDisableIcon());
                    Vars.chat_view.user_list.getItems().set(index, updated_cell);
                }else{
                    AlertControls updated_cell = new AlertControls(user_cell.username, true, Vars.logo.getRingIcon());
                    Vars.chat_view.user_list.getItems().set(index, updated_cell);
                }
            }
        });

        // Creates alarm box with given alignment.
        VBox alarmBox = new VBox();
        alarmBox.setAlignment(Pos.CENTER);
        alarmBox.getChildren().addAll(notification_box);

        // Main setting box with given dimensions.
        GroupBox mainBox = new GroupBox("Setting", alarmBox, Resource.RIGHT_AREA_WIDTH - 60, 25);

        initBtns();

        // Creates an "OK" button with given spacing and alignment.
        HBox btnBox = new HBox();
        btnBox.setSpacing(15);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().addAll(ok_btn);

        // Creates the main area with given dimension settings.
        BorderPane blue_pane = new BorderPane();
        blue_pane.setId("main_area");
        BorderPane.setMargin(mainBox, new Insets(20, 10, 10, 10));
        blue_pane.setCenter(mainBox);
        BorderPane.setMargin(btnBox, new Insets(10, 10, 25, 10));
        blue_pane.setBottom(btnBox);

        // Creates a black border around the main area.
        setting_pane.setId("black_area");
        BorderPane.setMargin(blue_pane, new Insets(20, 20, 20, 20));
        setting_pane.setCenter(blue_pane);

        // Scene settings that uses set styles from "rootStyles.css".
        Scene scene = new Scene(setting_pane);
        scene.getStylesheets().add(getClass().getResource("/rootStyles.css").toExternalForm());

        // Creates a new stage with heading title and has a setting
        // that doesn't permit for resizing the settings stage.
        setting_stage = new Stage();
        setting_stage.setScene(scene);
        setting_stage.setResizable(false);
        setting_stage.setTitle("Setting");

        setting_stage.initOwner(ChatClient.clientStage);
        setting_stage.initModality(Modality.WINDOW_MODAL);
    }

    // Display the setting window and wait user interaction.
    public void show() {
        setting_stage.showAndWait();
    }

    // Initialize the "OK" button with the given
    // text alignment and width dimension.
    private void initBtns() {
        ok_btn = new Button("OK");
        ok_btn.setId("normalbtn");
        ok_btn.setTextAlignment(TextAlignment.CENTER);
        ok_btn.setPrefWidth(180);

        // When the "Ok" button is pressed
        // close the settings window.
        ok_btn.setOnAction((evt) -> {
            setting_stage.close();
        });
        ok_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setting_stage.close();
            }
        });
    }
}
