/**
 * 
 */
package util;

/**
 * @author tuya
 */
public class Helper
{
    public final static String CURRENCY_UNIT = "￥";

    public static String getCurrencyString( double money )
    {
        return CURRENCY_UNIT + money;
    }

    public static double getCurrencyValue( String str )
    {
        if( str.contains( CURRENCY_UNIT ) )
        {
            return Double.valueOf( str.split( CURRENCY_UNIT )[1] );
        }
        return Double.valueOf( str );
    }
}
