package Controller;

import Model.UserCell;
import client.Vars;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import packets.*;
import view.AlertMessage;
import view.AnimationEffect;
import view.mainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles the client-side server connection operations.
 */
public class ClientSetting {

    public static Client client;
    public static boolean noti = true;

    public static HashMap<String, Boolean> user_noti = new HashMap<String, Boolean>();

    public static boolean initializeClient(String username) {
        // Creates a new client connection and then starts
        // a connection through the given port address.
        client = new Client();
        client.start();
        try {
            client.connect(5000, "127.0.0.1", 23900, 23901);
        } catch (Exception e) {
            AlertMessage.message("Could not connect to server !");
            return false;
        }

        // Register all the Packet classes required to run client.
        client.getKryo().register(Packet.class);
        client.getKryo().register(Packet1Connect.class);
        client.getKryo().register(Packet2ClientConnected.class);
        client.getKryo().register(Packet3ClientDisconnect.class);
        client.getKryo().register(Packet4Chat.class);
        client.getKryo().register(PacketSameUser.class);
        client.getKryo().register(ArrayList.class);
        client.getKryo().register(PacketUserList.class);

        // Adds a listener to print out the response.
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Packet) {
                    Text text;
                    if (object instanceof PacketSameUser) {
                        client.close();
                        Platform.runLater(() -> {
                            AlertMessage.message("Same username already exists!");
                        });

                      // Add client to user_list.
                    } else if (object instanceof PacketUserList) {
                        Platform.runLater(() -> {
                            PacketUserList users = (PacketUserList) object;
                            ArrayList<String> user_list = users.user_list;
                            // Add all user_item in the UserCell to each username in user_list.
                            for (String user_item : user_list) {
                                UserCell user_cell = new UserCell(user_item, true, Vars.logo.getRingIcon());
                                Vars.chat_view.user_list.getItems().add(user_cell);
                                user_noti.put(user_item, true);
                            }
                            AnimationEffect.animation_show(Vars.chat_view.chat_pane);
                            mainView.mainArea.setCenter(Vars.chat_view.chat_pane);
                        });
                      // When a client connects successfully attach "connected" to
                      // their username.
                    } else if (object instanceof Packet2ClientConnected) {
                        Packet2ClientConnected p2 = (Packet2ClientConnected) object;
                        text = new Text(p2.clientName + " connected.\n");
                        Platform.runLater(() -> {
                            UserCell user_cell = new UserCell(p2.clientName, true, Vars.logo.getRingIcon());
                            Vars.chat_view.user_list.getItems().add(user_cell);
                            user_noti.put(p2.clientName, true);
                            Vars.chat_view.message_area.getChildren().addAll(text);
                        });
                      // When the client disconnects attach "disconnected" to
                      // their username.
                    } else if (object instanceof Packet3ClientDisconnect) {
                        Packet3ClientDisconnect p3 = (Packet3ClientDisconnect) object;
                        text = new Text(p3.clientName + " disconnected.\n");
                        Platform.runLater(() -> {
                            Vars.chat_view.message_area.getChildren().addAll(text);
                        });
                      // When a client sends a message attach his username: to
                      // the message.
                    } else if (object instanceof Packet4Chat) {
                        Packet4Chat p4 = (Packet4Chat) object;
                        Text username_text = new Text(p4.username + ": ");
                        Platform.runLater(() -> {
                            Vars.chat_view.message_area.getChildren().addAll(username_text);
                        });
                        // Sends chat message to parserMessage.
                        String chat_message = p4.message + "\n";
                        Platform.runLater(() -> {
                            parserMessage(chat_message);
                        });
                        // Play incoming sound alerts if it's set to receive from
                        // a specific username.
                        if (user_noti.containsKey(p4.username)) {
                            if (user_noti.get(p4.username)) {
                                Alarm.AlarmNotification("src/main/resources/messageAlert.mp3");
                            }
                        }
                    }
                }
            }
        });

        // Create a new connection packet and set username
        // then
        Packet1Connect p1 = new Packet1Connect();
        p1.username = username;
        client.sendTCP(p1);
        return true;
    }

    // Message parser for emoji string representations.
    private static void parserMessage(String message) {
        String parse_msg = message;
        List<String> emo_list = EmoInfo.getEmoList();
        String[] split_message = parse_msg.split("\\:");
        HBox message_box = new HBox();
        message_box.setAlignment(Pos.CENTER_LEFT);
        for (String item : split_message) {
            if (item == null || item.isEmpty()) {
                continue;
            }
            if (!emo_list.contains(item)) {
                Text text = new Text(item);
                Vars.chat_view.message_area.getChildren().addAll(text);
            } else {
                for (String emo_str : emo_list) {
                    if (item.equals(emo_str)) {
                        Vars.chat_view.message_area.getChildren().addAll(generateEmoji(emo_str));
                    }
                }
            }
        }
    }

    // Generates ImageView of a chosen emoji.
    private static ImageView generateEmoji(String style) {
        ImageView image_view = new ImageView();
        if (style.equals(EmoInfo.slightly_smile)) {
            image_view.setImage(Vars.logo.getSlightlySmile());
        } else if (style.equals(EmoInfo.smile)) {
            image_view.setImage(Vars.logo.getSmile());
        } else if (style.equals(EmoInfo.smiley)) {
            image_view.setImage(Vars.logo.getSmiley());
        } else if (style.equals(EmoInfo.upside)) {
            image_view.setImage(Vars.logo.getUpside());
        } else if (style.equals(EmoInfo.stuck_tongue)) {
            image_view.setImage(Vars.logo.getStuckout());
        } else if (style.equals(EmoInfo.sun_glass)) {
            image_view.setImage(Vars.logo.getSunglass());
        } else if (style.equals(EmoInfo.think_face)) {
            image_view.setImage(Vars.logo.getThinking());
        } else if (style.equals(EmoInfo.frowning)) {
            image_view.setImage(Vars.logo.getFrowining());
        } else if (style.equals(EmoInfo.cry)) {
            image_view.setImage(Vars.logo.getCry());
        } else if (style.equals(EmoInfo.sob)) {
            image_view.setImage(Vars.logo.getSob());
        } else if (style.equals(EmoInfo.angry)) {
            image_view.setImage(Vars.logo.getAngry());
        } else if (style.equals(EmoInfo.rage)) {
            image_view.setImage(Vars.logo.getRage());
        } else if (style.equals(EmoInfo.ok)) {
            image_view.setImage(Vars.logo.getOk());
        } else if (style.equals(EmoInfo.thumbs)) {
            image_view.setImage(Vars.logo.getThumsup());
        } else if (style.equals(EmoInfo.clap)) {
            image_view.setImage(Vars.logo.getClap());
        } else if (style.equals(EmoInfo.heart)) {
            image_view.setImage(Vars.logo.getHeart());
        }
        image_view.setFitWidth(18);
        image_view.setFitHeight(18);

        return image_view;
    }
}
