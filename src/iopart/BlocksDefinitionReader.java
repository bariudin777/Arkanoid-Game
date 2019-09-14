package iopart;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        // this is the line i will work on
        String lineToRead;
        // create the reader for blocks symbols
        BlocksFromSymbolsFactory symbolsFactory = new BlocksFromSymbolsFactory();
        //create a line reader
        LineNumberReader reader1 = new LineNumberReader(reader);
        int numberOfLine = reader1.getLineNumber();
        try {
            //map of blocks
            Map<String, String> mainMap = new HashMap<String, String>();
            //sub map
            Map<String, String> subMap = new HashMap<String, String>();
            //temp map
            Map<String, String> tempMap = new HashMap<String, String>();
            while ((lineToRead = reader1.readLine()) != null) {
                if (lineToRead.startsWith("#") || lineToRead.compareTo("") == 0) {
                    continue;
                }
                if (lineToRead.startsWith("default")) {
                    //read the line of the default and return a string without the wightespace
                    lineToRead = lineToRead.substring("default".length()).trim();
                    subMap = analyzeTheLine(lineToRead, numberOfLine);
                } else if (lineToRead.startsWith("bdef")) {
                    lineToRead = lineToRead.substring("bdef".length()).trim();
                    //clears the map
//                        tempMap.clear();
                    //add the line and the line number to the temp map
                    mainMap = analyzeTheLine(lineToRead, numberOfLine);
                    mainMap.putAll(subMap);
                    //clear the main map and put the 2 maps inside
                    //if the file has this strings create a block
                    BlockCreator newBlock = new BlockCreator();
                    String theSmb = mainMap.get("symbol");
                    try {
                        if (Integer.valueOf(mainMap.get("width")) > 0) {
                            newBlock.setWidth(Integer.valueOf(mainMap.get("width")));
                        }
                        if (Integer.valueOf(mainMap.get("hit_points")) > 0) {
                            newBlock.setHitPoints(Integer.valueOf(mainMap.get("hit_points")));
                        }
                        if (Integer.valueOf(mainMap.get("height")) > 0) {
                            newBlock.setHeight(Integer.valueOf(mainMap.get("height")));
                        }
                    } catch (NumberFormatException k) {
                        throw new NumberFormatException("Error with reading the file");
                    }

                    Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
                    Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
                    if (mainMap.containsKey("stroke")) {
                        // to ask the teacher why only the static garam she yeru et ha metoda?
                        colorMap.put(-1, ColorsParser.colorFromString(mainMap.get("stroke")));
                    }
                    if (mainMap.containsKey("fill")) {
                        mainMap.put("fill-1", mainMap.get("fill"));
                        mainMap.remove("fill");
                    }
                    int len = Integer.valueOf(mainMap.get("hit_points"));
                    for (int k = 0; k < len; k++) {
                        if (mainMap.containsKey("fill-" + (k + 1))) {
                            String fill = mainMap.get("fill-" + (k + 1));
                            if (fill.startsWith("image(")) {
                                fill = fill.substring("image(".length());
                                fill = fill.replace(")", "");
                                try {
                                    Image image = ImageIO.read(ClassLoader.getSystemClassLoader().
                                            getResourceAsStream(fill));
                                    imageMap.put(k, image);
                                } catch (Exception e) {
                                    System.out.println("Exception Image");

                                }
                            } else if (fill.startsWith("color(")) {
                                Color color = ColorsParser.colorFromString(fill);
                                colorMap.put(k, color);
                            }
                        }
                    }
                    newBlock.setStringImageMap(imageMap);
                    newBlock.setStringColorMap(colorMap);
                    symbolsFactory.putB(theSmb, newBlock);
                } else if (lineToRead.startsWith("sdef")) {
                    Map<String, String> stringMap = new HashMap<String, String>();
                    lineToRead = lineToRead.substring("sdef".length()).trim();
                    stringMap = analyzeTheLine(lineToRead, numberOfLine);
                    String str = stringMap.get("symbol");
                    Integer width = Integer.valueOf(stringMap.get("width"));
                    symbolsFactory.putSpace(str, width);
                } else {
                    System.out.println("Error- reading sdef");
                    return null;
                }
            }

        } catch (IOException e) {
            throw new NumberFormatException("Error");
        } finally {
            if (reader1 != null) {
                try {
                    reader1.close();
                } catch (IOException e) {
                    throw new NumberFormatException("Error");
                }
            }
        }
        return symbolsFactory;
    }


    /**
     * Analyze the line map.
     *
     * @param string     the string
     * @param lineNumber the line number
     * @return the map
     */
    public static Map<String, String> analyzeTheLine(String string, int lineNumber) {
        String[] add = string.split(" ");
        Map<String, String> mapToReturn = new HashMap<String, String>();
        for (String toAdd : add) {
            String[] partOf = toAdd.split(":");
            if (partOf.length != 2) {
                continue;
            }
            mapToReturn.put(partOf[0], partOf[1]);
        }
        return mapToReturn;
    }
}
