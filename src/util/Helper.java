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

    public static String toPercentageFormat( double d )
    {
        String str = String.valueOf( d * 100 );
        if( str.length() > 5 )
        {
            return str.substring( 0, 5 ) + "%";
        }
        return str + "%";
    }

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
            return parseStringToDouble( str.split( CURRENCY_UNIT )[1] );
        }
        return parseStringToDouble( str );
    }

    public static double parseStringToDouble( String str )
    {
        try
        {
            return Double.valueOf( str );
        }
        catch( NumberFormatException ex )
        {
            return 0;
        }
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
