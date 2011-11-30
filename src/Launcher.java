import service.GlobalServices;
import ui.GoldMineWindowControl;
import file.XMLFileHandler;

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
        XMLFileHandler.writeSampleXml( ".\\data\\save.xml" );
        app = null;
        GlobalServices.stopAllServices();
    }

}
