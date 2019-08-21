package Model;

import javafx.scene.image.Image;

/**
 * This class manages the operations for
 * the username, notification and ring image
 * displayed in the active user list.
 */
public class UserCell {
    public String username;
    public boolean noti;
    public Image ring_image;
    
    public UserCell(String name, boolean noti, Image ring_image){
        this.username = name;
        this.noti = noti;
        this.ring_image = ring_image;
    }
}
