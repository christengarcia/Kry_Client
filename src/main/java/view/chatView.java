package view;

import Controller.ClientSetting;
import Controller.EmojiInfo;
import Controller.UserInfo;
import Model.AlertControls;
import client.Resource;
import client.Vars;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import packets.Packet4Chat;

/**
 * This class handles the GUI resources for the chatView
 */
public class chatView {

    public BorderPane chat_pane = new BorderPane();

    public TextFlow message_area = new TextFlow();
    public CustomTextField chat_message = new CustomTextField();
    public ImageView emo_icon;

    public ListView<AlertControls> user_list = new ListView<AlertControls>();

    public ContextMenu emoPopup;

    private Button send_btn;
    private Button setting_btn;

    // Initializes the View.
    public void initView() {
        emoPopup = new ContextMenu();
        initEmoList();

        user_list.prefWidthProperty().bind(chat_pane.widthProperty().multiply(0.25));
        user_list.setCursor(Cursor.HAND);
        user_list.setCellFactory(new Callback<ListView<AlertControls>, ListCell<AlertControls>>() {
            @Override
            public ListCell<AlertControls> call(ListView<AlertControls> listView) {
                return new UserListCell();
            }
        });

        // Handles the events related to any mouse clicks on the active users list being displayed.
        user_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AlertControls user_cell = user_list.getSelectionModel().getSelectedItem();
                settingView setting_view = new settingView();
                setting_view.user_cell = user_cell;
                setting_view.initView();
                setting_view.show();
            }
        });

        // Message area dimension settings.
        message_area.setId("round");
        message_area.setPrefWidth(Resource.WINDOW_WIDTH - 80);
        message_area.prefWidthProperty().bind(chat_pane.widthProperty().multiply(0.75));

        message_area.setPadding(new Insets(5));
        message_area.setLineSpacing(5);

        // Retrieves emoji from the LogoManager then displays
        // them with the given dimension settings.
        emo_icon = new ImageView(Vars.logo.getEmoIcon());
        emo_icon.setCursor(Cursor.HAND);
        emo_icon.setFitWidth(30);
        emo_icon.setFitHeight(30);

        // Chat message dimensions.
        chat_message.setId("round");
        chat_message.setPadding(new Insets(5));
        // Places emoji after the message.
        chat_message.setRight(emo_icon);
        chat_message.setPrefWidth(Resource.WINDOW_WIDTH - 80);
        // When the enter button is pressed the message is
        // retrieved from text input area then sent.
        chat_message.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String message_str = chat_message.getText();
                // If the text box is empty and the user
                // presses the enter button will be displayed.
                if (message_str.isEmpty()) {
                    return;
                }
                send_message(message_str);
            }
        });

        // Creates box to contain the alarm image dimensions.
        HBox alarmBox = new HBox();
        alarmBox.setAlignment(Pos.CENTER);
        alarmBox.getChildren().addAll(message_area);

        // Creates a box to contain the messages with the given dimensions.
        GroupBox messageBox = new GroupBox("Message", alarmBox, Resource.WINDOW_WIDTH - 60, 25);

        // Creates a box to contain the active users list with the given dimensions.
        GroupBox userListBox = new GroupBox("Users", user_list, Resource.WINDOW_WIDTH - 60, 25);

        // Creates a primary box.
        HBox mainBox = new HBox();
        mainBox.setSpacing(10);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(messageBox, userListBox);

        initBtns();

        // Creates a box for the chat with the given dimensions and settings.
        HBox chatBox = new HBox();
        chatBox.setSpacing(15);
        chatBox.setAlignment(Pos.CENTER);
        chatBox.getChildren().addAll(chat_message, send_btn);

        // The BorderPane dimensions for the main chat area.
        BorderPane main_pane = new BorderPane();
        main_pane.setId("main_area");
        BorderPane.setMargin(mainBox, new Insets(20, 10, 10, 10));
        main_pane.setCenter(mainBox);
        BorderPane.setMargin(chatBox, new Insets(10, 10, 25, 10));
        main_pane.setBottom(chatBox);

        // Creates a black border around the the entire chat window with the given settings.
        chat_pane.setId("black_area");
        BorderPane.setMargin(main_pane, new Insets(20, 20, 20, 20));
        chat_pane.setCenter(main_pane);
    }

    private void setImageToBtn(Button button, Image image) {
        button.setContentDisplay(ContentDisplay.CENTER);
        ImageView btn_img = new ImageView(image);
        double width = 30;
        //double height = image.getHeight() * (width / image.getWidth());
        double height = 30;
        btn_img.setFitWidth(width);
        btn_img.setFitHeight(height);
        button.setGraphic(btn_img);
    }

    // Initializes the send button and emoji button (located next the text chat area).
    private void initBtns() {
        // Creates a round button for send.
        send_btn = new Button();
        String url = Vars.logo.getURL("alert/send_icon.png");
        send_btn.setStyle("-fx-background-image: url('" + url + "'); ");
        send_btn.setId("roundbtn");
        send_btn.setCursor(Cursor.HAND);

        // Creates a round button to pop up the emoji box keyboard.
        setting_btn = new Button();
        url = Vars.logo.getURL("alert/set_icon.png");
        setting_btn.setStyle("-fx-background-image: url('" + url + "'); ");
        setting_btn.setId("roundbtn");
        setting_btn.setCursor(Cursor.HAND);

        // When send button is pressed get all the text written
        // in the text box and send it.
        send_btn.setOnAction((evt) -> {
            String message_str = chat_message.getText();
            // If text box is empty when user presses send
            // no action will be taken.
            if (message_str.isEmpty()) {
                return;
            }
            send_message(message_str);
        });

        // When the user presses the "enter" on the keyboard
        // get all the text contained in the text input box
        // and then send it.
        send_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String message_str = chat_message.getText();
                // No action is taken when text input box is empty.
                if (message_str.isEmpty()) {
                    return;
                }
                send_message(message_str);
            }
        });

        // Setting button actions
        setting_btn.setOnAction((evt) -> {
            settingView set_view = new settingView();
            set_view.initView();
            set_view.show();
        });
        setting_btn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                settingView set_view = new settingView();
                set_view.initView();
                set_view.show();
            }
        });

        // Action settings (pop-up) for displaying emoji box keyboard.
        emo_icon.setOnMouseClicked((evt) -> {
            if (emoPopup.isShowing()) {
                emoPopup.hide();
            } else {
                Bounds boundsInScreen = chat_message.localToScreen(chat_message.getBoundsInLocal());
                double pos_x = boundsInScreen.getMaxX();
                double pos_y = boundsInScreen.getMaxY();
                emoPopup.show(chat_message.getScene().getWindow(), pos_x, pos_y);
            }
        });
    }

    // Creates a new Packet4Chat with username and message
    // being sent over the server.
    private void send_message(String message) {
        Packet4Chat p4 = new Packet4Chat();
        p4.username = UserInfo.username;
        p4.message = message;
        ClientSetting.client.sendTCP(p4);
        chat_message.clear();
    }

    // Initializes the emoji's
    private void initEmoList() {
        // Creates a box containing the emoji's.
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        // The emoji image is retrieved from the LogoManager.
        ImageView smile = new ImageView(Vars.logo.getSmile());
        smile.setCursor(Cursor.HAND);
        // Action: when an emoji is chosen (by mouse click) the matching string/image
        // emoji representation is appended to the message.
        smile.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.smile + ":");
        });

        ImageView smiley = new ImageView(Vars.logo.getSmiley());
        smiley.setCursor(Cursor.HAND);
        smiley.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.smiley + ":");
        });

        ImageView slightly_smile = new ImageView(Vars.logo.getSlightlySmile());
        slightly_smile.setCursor(Cursor.HAND);
        slightly_smile.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.slightly_smile + ":");
        });

        ImageView upside = new ImageView(Vars.logo.getUpside());
        upside.setCursor(Cursor.HAND);
        upside.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.upside + ":");
        });

        ImageView sun_glass = new ImageView(Vars.logo.getSunglass());
        sun_glass.setCursor(Cursor.HAND);
        sun_glass.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.sun_glass + ":");
        });

        ImageView think_face = new ImageView(Vars.logo.getThinking());
        think_face.setCursor(Cursor.HAND);
        think_face.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.think_face + ":");
        });

        ImageView stuck_tongue = new ImageView(Vars.logo.getStuckout());
        stuck_tongue.setCursor(Cursor.HAND);
        stuck_tongue.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.stuck_tongue + ":");
        });

        ImageView heart = new ImageView(Vars.logo.getHeart());
        heart.setCursor(Cursor.HAND);
        heart.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.heart + ":");
        });

        ImageView clap = new ImageView(Vars.logo.getClap());
        clap.setCursor(Cursor.HAND);
        clap.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.clap + ":");
        });

        ImageView ok = new ImageView(Vars.logo.getOk());
        ok.setCursor(Cursor.HAND);
        ok.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.ok + ":");
        });

        ImageView thumbs = new ImageView(Vars.logo.getThumbsup());
        thumbs.setCursor(Cursor.HAND);
        thumbs.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.thumbs + ":");
        });

        ImageView frowning = new ImageView(Vars.logo.getFrowning());
        frowning.setCursor(Cursor.HAND);
        frowning.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.frowning + ":");
        });

        ImageView sob = new ImageView(Vars.logo.getSob());
        sob.setCursor(Cursor.HAND);
        sob.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.sob + ":");
        });

        ImageView cry = new ImageView(Vars.logo.getCry());
        cry.setCursor(Cursor.HAND);
        cry.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.cry + ":");
        });

        ImageView angry = new ImageView(Vars.logo.getAngry());
        angry.setCursor(Cursor.HAND);
        angry.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.angry + ":");
        });

        ImageView rage = new ImageView(Vars.logo.getRage());
        rage.setCursor(Cursor.HAND);
        rage.setOnMouseClicked((evt) -> {
            chat_message.appendText(":" + EmojiInfo.rage + ":");
        });

        // Grid placement positions for the emoji's on emoji keyboard.
        // First row
        grid.add(smile, 0,0,1,1);
        grid.add(smiley, 1,0,1,1);
        grid.add(slightly_smile, 2,0,1,1);
        grid.add(upside, 3,0,1,1);
        // Second row
        grid.add(sun_glass, 0,1,1,1);
        grid.add(think_face, 1,1,1,1);
        grid.add(stuck_tongue, 2,1,1,1);
        grid.add(heart, 3,1,1,1);
        // Third row
        grid.add(clap, 0,3,1,1);
        grid.add(ok, 1,3,1,1);
        grid.add(thumbs, 2,3,1,1);
        grid.add(frowning, 3,3,1,1);
        // Fourth row
        grid.add(sob, 0,4,1,1);
        grid.add(cry, 1,4,1,1);
        grid.add(angry, 2,4,1,1);
        grid.add(rage, 3,4,1,1);

        emoPopup.getScene().setRoot(grid);
    }
}
