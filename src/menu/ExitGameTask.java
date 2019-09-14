package menu;

/**
 * The type Exit game task.
 */
public class ExitGameTask implements Task<Void> {
    @Override
    public Void run() {

        System.exit(0);
        return null;
    }
}
