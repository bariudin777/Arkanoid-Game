package score;

//import java.

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private int topSize;
    private List<ScoreInfo> scores;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        topSize = size;
        scores = new ArrayList<>();
    }

    /**
     * Add.
     *
     * @param addS the add s
     */
// Add a high-score.
    public void add(ScoreInfo addS) {
        // if there is a space in the list
        if (scores.size() < topSize) {
            scores.add(addS);
            Collections.sort(scores);
            // if there is a higher score- remove the lower score and add the new one
        } else if (addS.compareTo(scores.get(size() - 1)) < 0) {
            scores.remove(size() - 1);
            scores.add(addS);
            Collections.sort(scores);
        }

    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return scores.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return scores;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (int k = 0; k < scores.size(); k++) {
            if (score > scores.get(k).getScore()) {
                return ++k;
            }
        }
        return size() + 1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        scores.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable tempo;
        ObjectInputStream objectReader = null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(filename);
            objectReader = new ObjectInputStream(file);
            tempo = (HighScoresTable) objectReader.readObject();
            this.topSize = tempo.topSize;
            this.scores = tempo.scores;
        } catch (ClassNotFoundException p) {
            p.getException();
        } finally {
            if (file != null) {
                file.close();
            }

            if (objectReader != null) {
                objectReader.close();
            }
        }

    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        FileOutputStream file = null;
        ObjectOutput objectOutput = null;
        try {
            file = new FileOutputStream(filename);
            objectOutput = new ObjectOutputStream(file);
            objectOutput.writeObject(this);
        } finally {
            if (file != null) {
                file.close();
            }
            if (objectOutput != null) {
                objectOutput.close();
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
// If the file does not exist, or there is a problem with
// reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(10);
        try {
            highScoresTable.load(filename);
        } catch (Exception e) {
            return new HighScoresTable(10);
        }
        return highScoresTable;
    }
}
