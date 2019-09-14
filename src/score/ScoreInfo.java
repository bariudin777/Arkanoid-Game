package score;

import java.io.Serializable;

/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreInfo diffrent) {
        if (this.getScore() < diffrent.getScore()) {
            return 1;
        } else if (this.getScore() < diffrent.getScore()) {
            return 0;
        } else {
            return -1;
        }
    }
}
