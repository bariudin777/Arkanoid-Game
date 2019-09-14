package menu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
// to create a map to save the key and the massage
    // menu saves the data that i need, add data if he needed to,
    // and in the doOneFrame mathod knows how to print himself-
    // and how? ---> knows what massage to print
    // the map is a good selution
    // getStatus- only if key is pressed

    private List<T> returnVals;
    private List<String> masseges;
    private List<String> keys;
    private List<Menu<T>> menu;
    private List<Menu<T>> sMenu;
    private String key;
    private String mT;
    private Boolean end;
    //    private Map<String, String> menuMap;
    private T status;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;


    /**
     * Instantiates a new Menu animation.
     *
     * @param menuTitle the menu title
     * @param kb        the kb
     * @param ar        the ar
     */
// need to add more shit here...
    public MenuAnimation(String menuTitle, KeyboardSensor kb, AnimationRunner ar) {

//        menuMap = new TreeMap<String, String>();
        masseges = new ArrayList<String>();
        keys = new ArrayList<String>();
        this.returnVals = new ArrayList<T>();
        this.menu = new ArrayList<Menu<T>>();
        this.sMenu = new ArrayList<Menu<T>>();
        this.end = false;
        this.mT = menuTitle;
        this.animationRunner = ar;
        this.keyboardSensor = kb;

    }

    /**
     * Add menu.
     *
     * @param message the message
     * @param keyOf     the key
     * @param subMenu the sub menu
     */
    public void addMenu(String message, String keyOf, Menu<T> subMenu) {
        this.keys.add(keyOf);
        this.masseges.add(message);
        this.sMenu.add(null);
        this.returnVals.add(null);
        this.menu.add(subMenu);

    }

    // add the key and the strings and create a mapping of them
    // the put
    @Override
    public void addSelection(String s, String message, T returnVal) {
//        this.menuMap.put(key, message);
        this.keys.add(s);
        this.menu.add(null);
        this.masseges.add(message);
        this.returnVals.add(returnVal);
        this.sMenu.add(null);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(10, 150, 250));
        d.drawText(300, 100, mT, 50);
        int j = 200;
        for (int i = 0; i < returnVals.size(); i++) {
            d.setColor(Color.BLACK);
            d.drawText(100, j, ("(" + this.keys.get(i) + ")"
                    + " -----------------------------------------> " + this.masseges.get(i)), 30);
            j += 50;
        }

        //if key is pressed what to get out the massage, and change the status
        for (int k = 0; k < keys.size(); k++) {
            if (keyboardSensor.isPressed(keys.get(k))) {
                // if the action is to open the sub menu
                if (this.menu.get(k) != null) {
                    Menu<T> sub = this.menu.get(k);
                    animationRunner.run(sub);
                    status = sub.getStatus();

//                    this.animationRunner.run(sMenu.get(k));
//                    this.status= this.sMenu.get(k).getStatus();
                    end = true;
                } else {
                    status = returnVals.get(k);
                    end = true;
                }
            }
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        if (end) {
            end = false;
            return true;
        }
        return false;
    }
}
