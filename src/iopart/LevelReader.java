package iopart;


import levels.LevelInformation;


import java.io.Reader;
import java.util.ArrayList;

import java.util.List;

/**
 * The type Level reader.
 */
public class LevelReader {
    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public static List<LevelInformation> fromReader(Reader reader) {

        if (reader == null) {
            return new ArrayList<>();
        }

        List<LevelInformation> levelInformations = new ArrayList<>();

        levelInformations = LevelSpecificationReader.fromReader(reader);

        return levelInformations;
    }
}

