package menu;

import levels.LevelInformation;

import java.util.List;

/**
 * The type Menu manager.
 */
public class MenuManager {
    private String k;
    private String m;
    private List<LevelInformation> list;


    /**
     * Instantiates a new Menu manager.
     *
     * @param key  the key
     * @param mess the mess
     * @param list the list
     */
    public MenuManager(String key, String mess, List<LevelInformation> list) {
        this.k = key;
        this.m = mess;
        this.list = list;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return this.k;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.m;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<LevelInformation> getList() {
        return this.list;
    }

}
