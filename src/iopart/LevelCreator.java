package iopart;

import levels.AnitialBackGround;
import collections.Sprite;
import levels.LevelInformation;
import mathgeometry.Block;
import mathgeometry.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level creator.
 */
public class LevelCreator implements LevelInformation {

    private List<Velocity> startBallVel;
    private Sprite backG;
    private List<Block> blockList;
    private Integer paddleS;
    private Integer paddleW;
    private Integer paddleH;
    private Integer blockX;
    private Integer blockY;
    private Integer numOfB;
    private Integer rowH;
    private Integer numberOfBlockToRemove;
    private String name;


    /**
     * Instantiates a new Level creator.
     */
    public LevelCreator() {
        this.backG = new AnitialBackGround();
        this.startBallVel = new ArrayList<Velocity>();
        this.blockList = new ArrayList<Block>();
        this.paddleH = 10;
        this.paddleS = null;
        this.paddleW = null;
        this.numOfB = null;
        this.name = null;
        this.blockX = null;
        this.blockY = null;
        this.numberOfBlockToRemove = null;
        this.rowH = null;

    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return this.numOfB;
    }

    /**
     * Sets number of balls.
     *
     * @param numberOfBalls the number of balls
     */
    public void setNumberOfBalls(int numberOfBalls) {
        this.numOfB = numberOfBalls;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.startBallVel;
    }

    /**
     * Sets start ball vel.
     *
     * @param vel the vel
     */
    public void setStartBallVel(List<Velocity> vel) {
        this.startBallVel = vel;
    }

    /**
     * Add start ball vel.
     *
     * @param vel the vel
     */
    public void addStartBallVel(Velocity vel) {
        this.startBallVel.add(vel);
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return this.paddleS;
    }

    /**
     * Sets paddle speed.
     *
     * @param speed the speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleS = speed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return this.paddleW;
    }

    /**
     * Sets paddle width.
     *
     * @param width the width
     */
    public void setPaddleWidth(int width) {
        this.paddleW = width;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public int paddleHeight() {
        return this.paddleH;
    }

    /**
     * Sets paddleheight.
     *
     * @param hieght the hieght
     */
    public void setPaddleheight(int hieght) {
        this.paddleH = hieght;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return this.name;
    }

    /**
     * Sets level name.
     *
     * @param levelName the name
     */
    public void setLevelName(String levelName) {
        this.name = levelName;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return this.backG;
    }

    /**
     * Sets back ground.
     *
     * @param backGround the back ground
     */
    public void setBackGround(Sprite backGround) {
        this.backG = backGround;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * Sets blocks list.
     *
     * @param blocksList the blocks list
     */
    public void setBlocksList(List<Block> blocksList) {
        this.blockList = blocksList;
    }

    /**
     * Add block.
     *
     * @param block the block
     */
    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlockToRemove;
    }

    /**
     * Sets number of block to remove.
     *
     * @param number the number
     */
    public void setNumberOfBlockToRemove(int number) {
        this.numberOfBlockToRemove = number;
    }

    /**
     * Block x int.
     *
     * @return the int
     */
    public int blockX() {
        return this.blockX;
    }

    /**
     * Sets block x.
     *
     * @param number the number
     */
    public void setBlockX(int number) {
        this.blockX = number;
    }

    /**
     * Block y int.
     *
     * @return the int
     */
    public int blockY() {
        return this.blockY;
    }

    /**
     * Sets block y.
     *
     * @param number the number
     */
    public void setBlockY(int number) {
        this.blockY = number;
    }

    /**
     * Row height int.
     *
     * @return the int
     */
    public int rowHeight() {
        return this.rowH;
    }

    /**
     * Sst row h.
     *
     * @param number the number
     */
    public void setRowH(int number) {
        this.rowH = number;
    }
}
