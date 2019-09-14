package game;
/**
 * name- daniel bariudin
 * id- 307758334
 */

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import ballremover.BallRemover;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import blockremover.BlockRemover;
import blockremover.Counter;
import collections.Collidable;
import collections.Sprite;
import collections.SpriteCollection;
import levels.LevelInformation;
import mathgeometry.Paddle;
import mathgeometry.Point;
import mathgeometry.Rectangle;
import mathgeometry.Ball;
import mathgeometry.Block;
import score.LevelNameIndicator;
import score.LivesIndicator;
import score.ScoreIndicator;
import score.ScoreTrackingListener;


import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type game.GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter liveCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private LevelInformation information;
    private Map<Integer, Color> mapOfColor;
    private Map<Integer, Image> mapOfImage;

    /**
     * Instantiates a new game.GameLevel.
     *
     * @param levelInformation the level information
     * @param ar               the ar
     * @param ks               the ks
     * @param score            the score
     * @param lives            the lives
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks,
                     Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.score = score;
        this.liveCounter = lives;
        this.runner = ar;
        keyboard = ks;
        this.information = levelInformation;
        this.mapOfColor = new TreeMap<Integer, Color>();
        this.mapOfImage = new TreeMap<Integer, Image>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Paint row color.
     *
     * @param i the
     * @return the color
     */
    public Color paintRow(int i) {
        if (i == 1) {
            return Color.RED;
        }
        if (i == 2) {
            return Color.BLUE;
        }
        if (i == 3) {
            return Color.YELLOW;
        }
        if (i == 4) {
            return Color.MAGENTA;
        }
        if (i == 5) {
            return Color.ORANGE;
        } else {
            return Color.GREEN;
        }
    }

    /**
     * Make paddle.
     */
    public void makePaddle() {
        int gameWidth = 800;
        int borderSize = 10;
        double blockHeight = 20;

        if (paddle != null) {
            paddle.removeFromGame(this);
        }

        paddle = new Paddle(new Rectangle(new Point(350, 570), information.paddleWidth(), blockHeight),
                Color.GRAY, information.paddleSpeed(), borderSize,
                gameWidth - borderSize, keyboard);
//        paddle.timePassed();
        paddle.addToGame(this);


    }

    /**
     * Make balls.
     */
    public void makeBalls() {

        int gameWidth = 800;
        int gameHeight = 600;
        int borderSize = 10;

        Point ballCenter = new Point(400, 500);
        int radius = 4;
        Color ballColor = Color.WHITE;

        double blockWidth = 100, blockHeight = 20;
        double blockFirstX = 200, blockFirstY = 200;
        int blocksNum = 7;
        int ballSpeed = 5;

        for (int i = 0; i < information.numberOfBalls(); ++i) {
            Ball ball = new Ball(ballCenter, radius, ballColor);
            ball.setVelocity(information.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            ballCounter.increase(1);
        }
    }

    /**
     * Create borders.
     */
    public void createBorders() {
        int gameWidth = 800;
        int gameHeight = 600;
        int borderSize = 10;

        Point ballCenter = new Point(400, 500);
        int radius = 4;
        Color ballColor = Color.WHITE;

        double blockWidth = 100, blockHeight = 20;
        double blockFirstX = 200, blockFirstY = 200;
        int blocksNum = 7;
        int ballSpeed = 5;

        //TODO - set colors
        Block borderOne = new Block(new Point(0, 0), gameHeight, borderSize);
        mapOfColor.put(0, Color.GRAY);
        borderOne.setColor(mapOfColor);
        borderOne.setLive(0);
        borderOne.addToGame(this);
        /////////////////////////////////////////////////////////////////////////////
        Block borderTow = new Block(new Point(gameWidth - borderSize, 0), gameHeight, borderSize);
        borderTow.setColor(mapOfColor);
        borderTow.setLive(0);
        borderTow.addToGame(this);
        ///////////////////////////////////////////////////////////////////////////
        Block borderThree = new Block(new Point(borderSize, 18), borderSize,
                gameWidth - 2 * borderSize);
        mapOfColor.put(0, Color.gray);
        borderThree.setLive(0);
        borderThree.setColor(mapOfColor);
        borderThree.addToGame(this);
        ////////////////////////////////////////////////////////////////////////////////////
        Block borderF = new Block(new Point(borderSize, gameHeight - (borderSize - 11)), borderSize,
                gameWidth - 2 * borderSize);
        borderF.setColor(mapOfColor);
        borderF.setLive(0);
        borderF.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        borderF.addHitListener(ballRemover);
    }

    /**
     * Make blocks.
     */
    public void makeBlocks() {
        int gameWidth = 800;
        int gameHeight = 600;
        int borderSize = 10;

        Point ballCenter = new Point(400, 500);
        int radius = 4;
        Color ballColor = Color.WHITE;

        double blockWidth = 100, blockHeight = 20;
        double blockFirstX = 200, blockFirstY = 200;
        int blocksNum = 7;
        int ballSpeed = 5;
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);

        for (int i = 0; i < information.numberOfBlocksToRemove(); ++i) {
            Block block = information.blocks().get(i).copy();
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            block.addToGame(this);
            blockCounter.increase(1);
        }

    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and mathgeometry.Ball (and mathgeometry.Paddle)
    // and add them to the game.
    public void initialize() {
        mapOfColor.put(0, Color.YELLOW);
        int gameWidth = 800;
        int gameHeight = 600;
        int borderSize = 10;
//
//        Point ballCenter = new Point(400, 500);
//        int radius = 4;
//        Color barColor = Color.YELLOW;
//
//        double blockWidth = 100, blockHeight = 20;
//        double blockFirstX = 200, blockFirstY = 200;
//        int blocksNum = 7;
//        int ballSpeed = 5;
        this.addSprite(information.getBackground());

        // TODO - set color

        Block bar = new Block(new Point(borderSize, 0), borderSize + 25,
                gameWidth + borderSize);
        bar.setColor(mapOfColor);
        bar.setLive(0);
        this.addSprite(bar);
        this.addSprite(new ScoreIndicator(score));
        this.addSprite(new LivesIndicator(liveCounter));
        this.addSprite(new LevelNameIndicator(information.levelName()));

        createBorders();
        makeBlocks();
    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        makeBalls();
        makePaddle();
        this.runner.run(new CountdownAnimation(2, 3, sprites));

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

        if (blockCounter.getValue() <= 0) {
            score.increase(100);
        }

        if (ballCounter.getValue() <= 0) {
            liveCounter.decrease(1);
        }
    }

    /**
     * The entry point of application.
     */
    public void run() {
        while (liveCounter.getValue() > 0) {
            playOneTurn();
            if (ballCounter.getValue() == 0) {
                liveCounter.decrease(1);
            }
        }
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        // the `return` or `break` statements should be replaced with
        if (keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(keyboard));
        }

        if (blockCounter.getValue() == 0) {
            this.running = false;
        }

        if (ballCounter.getValue() == 0) {
            this.running = false;

        }
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return liveCounter.getValue();
    }

    /**
     * Num of blocks int.
     *
     * @return the int
     */
    public int numOfBlocks() {
        return blockCounter.getValue();
    }
}

