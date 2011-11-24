package service;

/**
 * GMService provide service for user and other components, 
 * it can be a window or a background component or something else.
 * GMService should be created from GlobalServices.activeService(),
 * and destoried by GlobalServices.stopService(). In other words,
 * every GMService is singleton.
 * 
 * e.g.
 * Class MyService implements GMService
 * 
 * MyService service = GlobalServices.activeService(MyService.class);
 *  :
 * GlobalServices.stopService(MyService.class);
 * service = null;
 * 
 * Every active GMService should be registered in GlobalServices 
 * by it's constructor.
 * Every GMService should implement destory(), unregister itself 
 * in this method, or call super.destory() from this method.
 * 
 */

/**
 * @author tuya
 */
public interface GMService
{
    void activeNotify();

    void refresh();

    /**
     * Destory itself, unregister itself from GlobalServices
     */
    void destory();
}
