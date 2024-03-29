package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * This class handles the GUI for the GroupBox.
 */
public class GroupBox extends StackPane {

    public GroupBox(String titleString, Node content, double width, int top_margin) {
        Label title = new Label(" " + titleString + " ");
        title.setId("title_label");
        title.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane contentPane = new StackPane();
        content.getStyleClass().add("bordered-titled-content");
        StackPane.setMargin(content, new Insets(top_margin, 15, 15, 15));
        contentPane.getChildren().add(content);
        setMaxWidth(width);
        getStyleClass().add("bordered-titled-border");
        getChildren().addAll(title, contentPane);
    }
}
