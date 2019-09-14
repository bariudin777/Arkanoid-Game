package game;

import animation.AnimationRunner;
import biuoop.DialogManager;

import biuoop.KeyboardSensor;
import blockremover.Counter;
import levels.AnitialBackGround;
import levels.LevelInformation;
import score.HighScoresTable;
import score.ScoreInfo;
import screen.GameOver;
import screen.HighScoresAnimation;
import screen.KeyPressStoppableAnimation;
import screen.YouWin;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    private DialogManager dialogManager;
    private AnitialBackGround backGround;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar       the ar
     * @param ks       the ks
     * @param hTable   the h table
     * @param dManager the d manager
     * @param file     the file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable hTable,
                    DialogManager dManager, File file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        highScoresTable = hTable;
        dialogManager = dManager;
        backGround = new AnitialBackGround();
        this.file = file;

    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter lives = new Counter(7);
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
// run the game level with the parameters until there is no more lives
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, score, lives);

            level.initialize();
            while (level.getLives() > 0 && level.numOfBlocks() > 0) {
                level.playOneTurn();
            }
            if (level.getLives() <= 0) {
                break;
            }

        }

        if (lives.getValue() > 0) {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", new YouWin(score.getValue())));

        } else {
            highScoresTable = HighScoresTable.loadFromFile(file);
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                    new GameOver(score.getValue())));

        }
        String name = dialogManager.showQuestionDialog("Name", "What is your name", "");
        highScoresTable.add(new ScoreInfo(name, score.getValue()));
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable, backGround);
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", highScoresAnimation));

        try {

            this.highScoresTable.save(file);
        } catch (IOException e) {
            System.out.println("Error");
        }


    }
}