import service.GlobalServices;
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

        GoldMineWindowControl app = GlobalServices.activeService( GoldMineWindowControl.class );
        app.run();
        app = null;
        GlobalServices.stopAllServices();
    }

}
