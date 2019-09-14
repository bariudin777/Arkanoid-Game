package iopart;

import mathgeometry.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {

    //    private String spaceSym;
//    private String blockSym;
    private Map<String, BlockCreator> mapOfBlocks;
    private Map<String, Integer> mapOfSpaces;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param mapOfBlocks the map of blocks
     * @param mapOfSpaces the map of spaces
     */
    public BlocksFromSymbolsFactory(Map<String, BlockCreator> mapOfBlocks, Map<String, Integer> mapOfSpaces) {
        this.mapOfBlocks = mapOfBlocks;
        this.mapOfSpaces = mapOfSpaces;

    }

    /**
     * Instantiates a new Blocks from symbols factory.
     */
    public BlocksFromSymbolsFactory() {
        this.mapOfBlocks = new HashMap<String, BlockCreator>();
        this.mapOfSpaces = new HashMap<String, Integer>();

    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        if (mapOfSpaces.containsKey(s)) {
            return true;
        }
        return false;
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        if (mapOfBlocks.containsKey(s)) {
            return true;
        }
        return false;

    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
// Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return this.mapOfBlocks.get(s).create(xpos, ypos);
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
// Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        return this.mapOfSpaces.get(s);
    }

    /**
     * Put b.
     *
     * @param string the string
     * @param block  the block
     */
    public void putB(String string, BlockCreator block) {
        this.mapOfBlocks.put(string, block);
    }

    /**
     * Put space.
     *
     * @param string the string
     * @param n      the n
     */
    public void putSpace(String string, int n) {
        this.mapOfSpaces.put(string, n);
    }
}
