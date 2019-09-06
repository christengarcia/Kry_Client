package client;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * This class manages the accessors for emoji's and icons.
 */
public class LogoManager {
    public Image getIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("client/icon.png");
        return new Image(icon_stream);
    }
    public Image getSendIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("alert/send_icon.png");
        return new Image(icon_stream);
    }
    public Image getSettingIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("alert/set_icon.png");
        return new Image(icon_stream);
    }
    // Get popup emoji keyboard icon
    public Image getEmoIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/slightly_smiling_face.png");
        return new Image(icon_stream);
    }
    // Get sound notification status on icon
    public Image getRingIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("alert/ring_icon.png");
        return new Image(icon_stream);
    }
    // Get sound notification status off icon
    public Image getRingDisableIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("alert/ring_disable.png");
        return new Image(icon_stream);
    }
    // Get URL location
    public String getURL(String location){
        String path = getClass().getClassLoader().getResource(location).toExternalForm();
        return path;
    }

    // Getters for emoji's.
    public Image getSmile(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/smile.png");
        return new Image(icon_stream);
    }

    public Image getSmiley(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/smiley.png");
        return new Image(icon_stream);
    }

    public Image getSlightlySmile(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/slightly_smiling_face.png");
        return new Image(icon_stream);
    }

    public Image getUpside(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/upside_down_face.png");
        return new Image(icon_stream);
    }

    public Image getSunglass(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/sunglasses.png");
        return new Image(icon_stream);
    }

    public Image getThinking(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/thinking_face.png");
        return new Image(icon_stream);
    }

    public Image getStuckout(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/stuck_out_tongue.png");
        return new Image(icon_stream);
    }

    public Image getHeart(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/heart.png");
        return new Image(icon_stream);
    }

    public Image getClap(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/clap.png");
        return new Image(icon_stream);
    }

    public Image getOk(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/ok.png");
        return new Image(icon_stream);
    }

    public Image getThumbsup(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/thumbsup.png");
        return new Image(icon_stream);
    }

    public Image getFrowning(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/white_frowning_face.png");
        return new Image(icon_stream);
    }

    public Image getSob(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/sob.png");
        return new Image(icon_stream);
    }

    public Image getCry(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/cry.png");
        return new Image(icon_stream);
    }

    public Image getAngry(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/angry.png");
        return new Image(icon_stream);
    }

    public Image getRage(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emoji/rage.png");
        return new Image(icon_stream);
    }
}