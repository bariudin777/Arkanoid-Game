
package iopart;

import levels.LevelInformation;
import mathgeometry.Block;
import mathgeometry.Velocity;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    private static BlocksFromSymbolsFactory blocksFromSymbolsFactory;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public static List<LevelInformation> fromReader(java.io.Reader reader) {

        // create the list of level information type
        List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
        //create a line reader that gets a reader from "from Reader"
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        String line = "";
        LevelCreator level = null;
        boolean inTheSec = true;
        int lineIs = 0;
        int counter = 0;
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                line = line.trim();
                // return the string without thw whitespaces
                lineIs = lineNumberReader.getLineNumber();
                // if the line is a comment or starts with " " -  do not enter the loop
                if (line.startsWith("#") || line.compareTo("") == 0) {
                    continue;
                }
                // if the reader is in the block section
                if (inTheSec) {
                    // if we read the start of the level=="START_LEVEL"
                    if (line.startsWith("START_LEVEL")) {
                        // now create a new level
                        level = new LevelCreator();
                    } else if (line.startsWith("END_LEVEL")) {
                        //if we in the end of the section=
                        // please check the following
                        if (level.levelName() == null
                                || level.numberOfBalls() == 0
                                || level.getBackground() == null
                                || level.numberOfBlocksToRemove() == 0
                                || level.paddleSpeed() == 0
                                || level.paddleWidth() == 0
                                || level.paddleHeight() == 0
                                || level.blockX() == 0
                                || level.blockY() == 0
                                || level.rowHeight() == 0) {
                            // throw error exeption
                            throw new Exception("Error");
                        } // don't know what to do??!! to fix!!!
                        // add the level to the list, and make him null( for new ones...)
                        levelInformations.add(level);
                        //if the sectionn starts with "START_BLOCKS"
                    } else if (line.startsWith("START_BLOCKS")) {
                        // if it is th begging of the block
                        inTheSec = false;
                        counter = 0;
                        //do the parsing...
                    } else {
                        // its not the begging of the line an for that:
                        // here i want to parse the line
                        analyzeLine(line, counter, level);
                    }
                    // if the end of the section "END_BLOCKS"
                } else if (line.equals("END_BLOCKS")) {
                    // it is the end of the block , and for thst i want to:
                    inTheSec = true;
                    // the end of the block
                    blocksFromSymbolsFactory = null;
                } else if (line.startsWith("END_LEVEL")) { //if we in the end of the section= please check the following
                    if (level.levelName() == null
                            || level.numberOfBalls() == 0
                            || level.getBackground() == null
                            || level.numberOfBlocksToRemove() == 0
                            || level.paddleSpeed() == 0
                            || level.paddleWidth() == 0
                            || level.paddleHeight() == 0
                            || level.blockX() == 0
                            || level.blockY() == 0
                            || level.rowHeight() == 0) {
                        // throw error exeption
                        throw new Exception("Error");
                    } // don't know what to do??!! to fix!!!
                    // add the level to the list, and make him null( for new ones...)
                    levelInformations.add(level);
                    //if the sectionn starts with "START_BLOCKS"
                } else {

                    analyzeBlocSec(line, counter, level);
                    counter++;
                }

            }
        } catch (Exception i) {
            i.printStackTrace();
        }
        //return the list
        return levelInformations;
    }


    /**
     * Analyze line.
     *
     * @param line    the line
     * @param lineNum the line num
     * @param level   the level
     * @throws Exception the exception
     */
    public static void analyzeLine(String line, int lineNum, LevelCreator level) throws Exception {

        String[] theParts = line.trim().split(":");
        if (!(theParts.length == 2)) {
            // get out of this--- throw Exeption
            throw new Exception("Error");
        }

        String key = theParts[0];
        String value = theParts[1];
        if (key.equals("level_name")) {
            level.setLevelName(value);
        }

        /////////////////// ball velocities "reading"/////////////////////////////
        if (key.equals("ball_velocities")) {
            int nOb = 0; // number of balls
            String[] speedAndAngle = value.split(" "); //separatede by " "
            for (String velocity : speedAndAngle) {
                // separated by ,
                String[] veloCom = velocity.split(",");
                if ((veloCom.length != 2)) {
                    throw new Exception("Error");
                }
                String angels = veloCom[0];
                String speeds = veloCom[1];

                try {
                    // if the speed is lower/equal to 0 , throw exeption
                    if (Double.valueOf(speeds) < 0) {
                        throw new NumberFormatException();
                        //number of balls goes up
                    }
                    // here i want to anitialize the velocity( by From angle to speed method)
                    level.addStartBallVel(Velocity.fromAngleAndSpeed(Double.valueOf(angels), Double.valueOf(speeds)));
                    nOb++;
                } catch (NumberFormatException e) {
                    throw new Exception(e);
                }
            }
            level.setNumberOfBalls(nOb);
        }

        /////////////////// background "reading"/////////////////////////////
        // if the key == background
        if (key.equals("background")) {
            //if the value == "image("
            if (value.startsWith("image(")) {
                //switch the value by this way:
                value = value.substring("image(".length());
                value = value.replace(")", "");
                try {
                    //resd the image file by ImageIO reader
                    Image image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(value));
                    BackGround backGround = new ImageBackGround(image);
                    level.setBackGround(backGround);
                } catch (IOException e) {
                    throw new Exception("Error");
                }
            } else if (value.startsWith("color")) {
                Color color = new ColorsParser().colorFromString(value);
                BackGround backGround = new ColorBackGround(color);
                level.setBackGround(backGround);
            } else {
                throw new Exception("Error");
            }
        }
        if (key.equals("paddle_speed")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    // if there is no speed- throw exception
                    throw new NumberFormatException("Error- no speed");
                }
                //else set the speed(after you read it
                level.setPaddleSpeed(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");
            }
        }
        if (key.equals("paddle_width")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    // if there is no width in the section
                    throw new FontFormatException("Error- no width");
                }
                //else set width after you read it
                level.setPaddleWidth(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");
            }
        }
        if (key.equals("block_definitions")) {
            Reader bReader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(value));
            //now i want to read the blockFromSymbolsFactory...
            blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(bReader);
        }
        if (key.equals("blocks_start_x")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new NumberFormatException("Error- no number here");
                }
                level.setBlockX(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");
            }
        }
        if (key.equals("blocks_start_y")) {
            try {
                if (Integer.valueOf(value) < 0) {
                    throw new NumberFormatException("Error- no number here");
                }
                level.setBlockY(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");

            }
        }
        if (key.equals("row_height")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException("Error in row height");
                }
                level.setRowH(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");
            }
        }
        if (key.equals("num_blocks")) {
            try {
                if (Integer.valueOf(value) <= 0) {
                    throw new NumberFormatException("Error in num blocks");
                }
                level.setNumberOfBlockToRemove(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new Exception("Error");
            }
        }
    }

    /**
     * Analyze bloc sec.
     *
     * @param line         the line
     * @param counterOfRow the counter of row
     * @param level        the level
     * @throws Exception the exception
     */
    public static void analyzeBlocSec(String line, int counterOfRow, LevelCreator level)
            throws Exception {

        if (
                level.levelName() == null
                        || level.numberOfBalls() == 0
                        || level.getBackground() == null
                        || level.numberOfBlocksToRemove() == 0
                        || level.paddleSpeed() == 0
                        || level.paddleWidth() == 0
                        || level.paddleHeight() == 0
                        || level.blockX() == 0
                        || level.blockY() == 0
                        || level.rowHeight() == 0) {
            throw new Exception("Error in analyzing block section row");
        }
        //anitialize a new (cuurent) x and y of block
        int x = level.blockX();
        //the y == the blocks y' +number(counter( of the cuur row * row height
        int y = level.blockY() + counterOfRow * level.rowHeight();
        for (int k = 0; k < line.length(); k++) {
            String smb = String.valueOf(line.charAt(k));
            if (blocksFromSymbolsFactory.isSpaceSymbol(smb)) {
                x = x + blocksFromSymbolsFactory.getSpaceWidth(smb);
            } else if (blocksFromSymbolsFactory.isBlockSymbol(smb)) {
                Block block = blocksFromSymbolsFactory.getBlock(smb, x, y);
                x = (int) (x + block.getCollisionRectangle().getWidth());
                level.addBlock(block);
            } else {
                throw new Exception("Error in reading block section line");
            }
        }
    }
}


