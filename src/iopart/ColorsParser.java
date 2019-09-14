package iopart;

import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {


    /**
     * Color from string color.
     *
     * @param word the word
     * @return the color
     */
    public static Color colorFromString(String word) {
        // if the word starts with color
        if (word.startsWith("color(")) {
            word = word.substring("color(".length());
            //if the word startsb with RGB
            word = word.replace(")", "");
            if (word.startsWith("RGB(")) {
                word = word.substring("RGB(".length());
                // here i split the settings of the color
                String[] strings = word.split(",");
                //if the value dont have 3 parts that makes the color
                if (!(strings.length == 3)) {
                    return null;
                    // get out and after that i will catch an exception
                }
                // i will saporate the parts of the colors settings ---> (part1,part2,part3)
                int part1 = Integer.valueOf(strings[0]);
                int part2 = Integer.valueOf(strings[1]);
                int part3 = Integer.valueOf(strings[2]);
                //now i will return this color
                return new Color(part1, part2, part3);
            } else {
                if (word.startsWith("black")) {
                    return Color.BLACK;
                }
                if (word.startsWith("gray")) {
                    return Color.GRAY;
                }
                if (word.startsWith("white")) {
                    return Color.WHITE;
                }
                if (word.startsWith("blue")) {
                    return Color.BLUE;
                }
                if (word.startsWith("red")) {
                    return Color.RED;
                }
                if (word.startsWith("orange")) {
                    return Color.ORANGE;
                }
                if (word.startsWith("yellow")) {
                    return Color.YELLOW;
                }
                if (word.startsWith("megenta")) {
                    return Color.MAGENTA;
                }
                if (word.startsWith("cyan")) {
                    return Color.CYAN;
                }
                if (word.equals("green")) {
                    return Color.green;
                }
                if (word.startsWith("pink")) {
                    return Color.pink;
                }
                if (word.startsWith("lightGray")) {
                    return Color.LIGHT_GRAY;
                }
                if (word.startsWith("darkGray")) {
                    return Color.DARK_GRAY;
                } else {
                    // here i want to throw exception
                    return null;
                }

            }
        }
        // here i want to throw exception
        return null;
    }
}
