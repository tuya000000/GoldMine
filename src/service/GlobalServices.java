/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuya
 */
public class GlobalServices
{
    static List<GMService> myServices = new ArrayList<GMService>();

    public static void registerService( GMService service )
    {
        myServices.remove( service );
        myServices.add( service );
    }

    public static void unregisterService( GMService service )
    {
        myServices.remove( service );
    }

    @SuppressWarnings( "unchecked" )
    public static <T extends GMService> T findRegisteredService( Class<? extends GMService> T )
    {
        for( GMService service : myServices )
        {
            if( service.getClass() == T )
            {
                return ( T ) service;
            }
        }
        return null;
    }

    /**
     * @param <T>
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public static <T extends GMService> T activeService( Class<? extends GMService> T )
    {
        try
        {
            GMService service = findRegisteredService( T );
            if( service == null )
            {
                service = T.newInstance();
            }
            service.activeNotify();
            return ( T ) service;
        }
        catch( InstantiationException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( IllegalAccessException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Stop a service
     * 
     * @param T service to stop
     */
    public static void stopService( Class<? extends GMService> T )
    {
        GMService service = findRegisteredService( T );
        if( service != null )
        {
            service.destory();
        }
    }

    public static void stopAllServices()
    {
        List<GMService> activeServices = new ArrayList<GMService>();
        activeServices.addAll( myServices );
        for( GMService service : activeServices )
        {
            service.destory();
        }
    }
}
