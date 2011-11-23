import ui.GoldMineWindowControl;

/**
 * 
 */

/**
 * @author tuya
 */
public class Launcher
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        GoldMineWindowControl app = new GoldMineWindowControl();
        app.run();
        app.destroy();
        app = null;
    }
}
