package client;

import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * This class manages the accessors for emoji's and icons.
 * @author
 */
public class LogoManager {
    public Image getIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("client/icon.png");
        return new Image(icon_stream);
    }
    public Image getSendIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("chatIcons/send_icon.png");
        return new Image(icon_stream);
    }
    public Image getSettingIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("chatIcons/set_icon.png");
        return new Image(icon_stream);
    }
    public Image getEmoIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("chatIcons/emo_logo.png");
        return new Image(icon_stream);
    }
    public Image getRingIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("chatIcons/ring_icon.png");
        return new Image(icon_stream);
    }
    public Image getRingDisableIcon(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("chatIcons/ring_disable.png");
        return new Image(icon_stream);
    }
    
    public String getURL(String location){
        String path = getClass().getClassLoader().getResource(location).toExternalForm();
        return path;
    }

    //get Emoji icons
    public Image getSlightlySmile(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/slightly_smiling_face.png");
        return new Image(icon_stream);
    }
    public Image getSmile(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/smile.png");
        return new Image(icon_stream);
    }
    public Image getSmiley(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/smiley.png");
        return new Image(icon_stream);
    }
    public Image getUpside(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/upside_down_face.png");
        return new Image(icon_stream);
    }
    public Image getStuckout(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/stuck_out_tongue.png");
        return new Image(icon_stream);
    }
    public Image getSunglass(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/sunglasses.png");
        return new Image(icon_stream);
    }
    public Image getThinking(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/thinking_face.png");
        return new Image(icon_stream);
    }
    public Image getFrowining(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/white_frowning_face.png");
        return new Image(icon_stream);
    }
    public Image getCry(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/cry.png");
        return new Image(icon_stream);
    }
    public Image getSob(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/sob.png");
        return new Image(icon_stream);
    }
    public Image getAngry(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/angry.png");
        return new Image(icon_stream);
    }
    public Image getRage(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/rage.png");
        return new Image(icon_stream);
    }
    public Image getOk(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/ok.png");
        return new Image(icon_stream);
    }
    public Image getThumsup(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/thumbsup.png");
        return new Image(icon_stream);
    }
    public Image getClap(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/clap.png");
        return new Image(icon_stream);
    }
    public Image getHeart(){
        InputStream icon_stream = getClass().getClassLoader().getResourceAsStream("emos/heart.png");
        return new Image(icon_stream);
    }
}
