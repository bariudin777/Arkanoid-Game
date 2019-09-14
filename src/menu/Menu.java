package menu;

import animation.Animation;

/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {

    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Add menu.
     *
     * @param message the message
     * @param key     the key
     * @param subMenu the sub menu
     */
    void addMenu(String message, String key, Menu<T> subMenu);

    /**
     * Gets status.
     *
     * @return the status
     */
     T getStatus();
}
