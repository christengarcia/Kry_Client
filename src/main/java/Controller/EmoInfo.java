package Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the emoji conversions i.e. matching string
 * representations with the equivalent emoji images.
 */
public class EmoInfo {
    public static String slightly_smile = "slightly_smile";
    public static String smile = "smile";
    public static String smiley = "smiley";
    public static String upside = "upside";
    public static String stuck_tongue = "stuck_tongue";
    public static String sun_glass = "sun_glass";
    public static String think_face = "think_face";
    public static String frowning = "frowning";
    public static String cry = "cry";
    public static String sob = "sob";
    public static String angry = "angry";
    public static String rage = "rage";
    public static String ok = "ok";
    public static String thumbs = "thumbs";
    public static String clap = "clap";
    public static String heart = "heart";

    // Adds each emoji to an ArrayList.
    public static List<String> getEmoList(){
        List<String> emo_list = new ArrayList<String>();
        emo_list.add(slightly_smile);
        emo_list.add(smile);
        emo_list.add(smiley);
        emo_list.add(upside);
        emo_list.add(stuck_tongue);
        emo_list.add(sun_glass);
        emo_list.add(think_face);
        emo_list.add(frowning);
        emo_list.add(cry);
        emo_list.add(sob);
        emo_list.add(angry);
        emo_list.add(rage);
        emo_list.add(ok);
        emo_list.add(thumbs);
        emo_list.add(clap);
        emo_list.add(heart);
        return emo_list;
    }
}
