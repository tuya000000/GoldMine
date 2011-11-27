/**
 * 
 */
package util;

import org.apache.log4j.Logger;

/**
 * @author tuya
 */
public class Helper
{
    static Logger logger = Logger.getLogger( "Util" );

    public final static String CURRENCY_UNIT = "￥";

    public final static String AMOUNT_UNIT = "克";

    public static String getCurrencyString( double money )
    {
        if( money >= 0 )
            return CURRENCY_UNIT + money;
        return "-" + CURRENCY_UNIT + Math.abs( money );
    }

    public static String getAmountString( double amount )
    {
        return String.valueOf( amount ) + AMOUNT_UNIT;
    }

    public static double getCurrencyValue( String str )
    {
        if( str.contains( CURRENCY_UNIT ) )
        {
            return Double.valueOf( str.split( CURRENCY_UNIT )[1] );
        }
        return Double.valueOf( str );
    }

    public static String getObjectPrefix( String objectId )
    {
        if( objectId == null )
        {
            logger.error( "Invalid object id: null" );
            return null;
        }
        String[] idParts = objectId.split( "-" );
        if( idParts[0] == null || idParts[0].isEmpty() )
        {
            logger.error( "Invalid object id:" + objectId );
        }
        return idParts[0];
    }
}
