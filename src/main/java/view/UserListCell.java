package view;

import Model.AlertControls;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class is for the active users GUI. It contains a
 * list of all users logged on and also displays a bell
 * icon next to their name. The bell can be selected to
 * turn on/off the incoming sound messages for that
 * specific user.
 */
public class UserListCell extends ListCell<AlertControls> {
    private HBox content;
    private Text name;
    private ImageView ring_view;

    // Dimensions and settings for the user list box.
    public UserListCell() {
        super();
        name = new Text();
        ring_view = new ImageView();
        ring_view.setFitWidth(20);
        ring_view.setFitHeight(20);
        VBox vBox = new VBox(name);
        content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(ring_view, vBox);
    }

    // Updates the the list by adding and displaying
    // a bell next to new active username.
    @Override
    protected void updateItem(AlertControls item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            name.setText(item.username);
            ring_view.setImage(item.ring_image);
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}