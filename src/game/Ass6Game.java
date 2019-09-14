package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.GameAnimation;
import menu.MenuAnimation;
import menu.Menu;
import menu.ExitGameTask;
import menu.StartGameTask;
import levels.AnitialBackGround;
import menu.ShowHiScoresTask;
import menu.Task;
import screen.HighScoresAnimation;
import screen.KeyPressStoppableAnimation;
import biuoop.GUI;
import levels.LevelInformation;
import iopart.LevelReader;
import score.HighScoresTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


/**
 * this game running the game.
 */
public class Ass6Game {

    /**
     * main that running the game.
     *
     * @param args empty for now.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        File fileForH = new File("high scores");
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(fileForH);
        HighScoresAnimation highScoresA = new HighScoresAnimation(highScoresTable, new AnitialBackGround());

        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor(),
                highScoresTable, gui.getDialogManager(), new File("Arkonid"));

        Menu<Task<Void>> subMenu = new MenuAnimation<>("Level Sets", gui.getKeyboardSensor(), ar);

        if (args.length != 0) {
            readLevels(subMenu, new InputStreamReader(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(args[0])), gameFlow, ar);
        } else {

            readLevels(subMenu, new InputStreamReader(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("level_sets.txt")), gameFlow, ar);
        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkonid", gui.getKeyboardSensor(), ar);
        Animation highScoresAnimation = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space", highScoresA);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // want to add the sub - menu to the menu
        // the sub - menu have the level sets
        menu.addMenu("Strat-Game", "s", subMenu);
        menu.addSelection("t", "Hi-Scores", new ShowHiScoresTask(ar, highScoresAnimation));
        menu.addSelection("e", "Exit", new ExitGameTask());

        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();

        }
    }

    /**
     * Read levels.
     *
     * @param menu     the menu
     * @param reader   the reader
     * @param gameFlow the game flow
     * @param ar       the ar
     */
    private static void readLevels(Menu<Task<Void>> menu, Reader reader, GameFlow gameFlow, AnimationRunner ar) {
        BufferedReader br = new BufferedReader(reader);
        String currLine;
        try {
            while ((currLine = br.readLine()) != null) {
                if (!currLine.contains(":")) {
                    throw new Exception("error in line");
                }
                String[] parts = currLine.trim().split(":");
                currLine = br.readLine();
                Reader pathToLevel = new InputStreamReader(ClassLoader.getSystemClassLoader().
                        getResourceAsStream(currLine));

                List<LevelInformation> levels = LevelReader.fromReader(pathToLevel);

                GameAnimation gameAnimation = new GameAnimation(gameFlow, levels);

                menu.addSelection(parts[0], parts[1], new StartGameTask(ar, gameAnimation));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
