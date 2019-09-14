package menu;

import animation.AnimationRunner;
import animation.GameAnimation;

/**
 * The type Start game task.
 */
public class StartGameTask implements Task<Void> {

    private AnimationRunner runner;
    private GameAnimation gameAnimation;


    /**
     * Instantiates a new Start game task.
     *
     * @param runner        the runner
     * @param gameAnimation the game animation
     */
    public StartGameTask(AnimationRunner runner, GameAnimation gameAnimation) {
        this.runner = runner;
        this.gameAnimation = gameAnimation;
    }

    /**
     * Run t.
     *
     * @return the t
     */
    @Override
    public Void run() {
        runner.run(gameAnimation);
        return null;
    }
}


//
//
//import animation.Animation;
//import animation.AnimationRunner;
//import screen.HighScoresAnimation;
//import biuoop.DialogManager;
//import biuoop.GUI;
//import biuoop.KeyboardSensor;
//import game.GameFlow;
//import levels.LevelInformation;
//import score.HighScoresTable;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * The type Start game task.
// */
// {
//
//    //    private AnimationRunner animationRunner;
////    private HighScoresAnimation highScoresAnimation;
////    private GUI gui;
//    private HighScoresTable table;
//    private File file;
//    private List<LevelInformation> levels;
//    private GameFlow gameFlow;
//
//
////    public StartGameTask(AnimationRunner animationRunner, GUI gui, HighScoresTable table,
////                         File file, List<LevelInformation> levels, HighScoresAnimation highScoresAnimation) {
////        this.animationRunner = animationRunner;
////        this.gui = gui;
////        this.file = file;
////        this.levels = levels;
////        this.highScoresAnimation = highScoresAnimation;
////
////    }
//
//    public StartGameTask(GameFlow gameFlow, List<LevelInformation> levels,HighScoresTable table,File file) {
//        this.gameFlow = gameFlow;
//        this.table = table;
//        this.levels = levels;
//        this.file=file;
//    }
//
//    @Override
//    public Void run() {
//
//        gameFlow.runLevels(levels);
//        try{
//            this.table.save(this.file);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
