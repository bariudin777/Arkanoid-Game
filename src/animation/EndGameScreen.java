//package animation;
//
//import biuoop.DrawSurface;
//import biuoop.KeyboardSensor;
//
///**
// * The type End game screen.
// */
//public class EndGameScreen implements Animation {
//
//
//    private KeyboardSensor keyboard;
//    private boolean stop;
//    private String msg;
//    private int score;
//
//    /**
//     * Instantiates a new End game screen.
//     *
//     * @param k     the k
//     * @param msg   the msg
//     * @param score the score
//     */
//    public EndGameScreen(KeyboardSensor k, String msg, int score) {
//        this.keyboard = k;
//        this.stop = false;
//        this.msg = msg;
//        this.score = score;
//    }
//
//    @Override
//    public void doOneFrame(DrawSurface d,double dt) {
//        d.drawText(10, d.getHeight() / 2, msg + " Your score is " + score, 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
//    }
//
//    @Override
//    public boolean shouldStop() {
//        return this.stop;
//    }
//}
