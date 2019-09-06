package Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the emoji conversions i.e. matching string
 * representations with the equivalent emoji images.
 */
public class EmojiInfo {
    public static String smile = "smile";
    public static String smiley = "smiley";
    public static String slightly_smile = "slightly_smile";
    public static String upside = "upside";
    public static String sun_glass = "sun_glass";
    public static String think_face = "think_face";
    public static String stuck_tongue = "stuck_tongue";
    public static String heart = "heart";
    public static String clap = "clap";
    public static String ok = "ok";
    public static String thumbs = "thumbs";
    public static String frowning = "frowning";
    public static String sob = "sob";
    public static String cry = "cry";
    public static String angry = "angry";
    public static String rage = "rage";

    // Adds each emoji to an ArrayList.
    public static List<String> getEmoList(){
        List<String> emoji_list = new ArrayList<String>();
        emoji_list.add(smile);
        emoji_list.add(smiley);
        emoji_list.add(slightly_smile);
        emoji_list.add(upside);
        emoji_list.add(sun_glass);
        emoji_list.add(think_face);
        emoji_list.add(stuck_tongue);
        emoji_list.add(heart);
        emoji_list.add(clap);
        emoji_list.add(ok);
        emoji_list.add(thumbs);
        emoji_list.add(frowning);
        emoji_list.add(sob);
        emoji_list.add(cry);
        emoji_list.add(angry);
        emoji_list.add(rage);
        return emoji_list;
    }
}
