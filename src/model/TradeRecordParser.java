/**
 * 
 */
package model;

import java.util.Date;

/**
 * @author tuya
 */
public class TradeRecordParser
{

    private static final String AMOUNT_UNIT = "克";

    private static final int CALENDAR_YEAR_BASE = 1900;

    @SuppressWarnings( "deprecation" )
    public static TradeRecord parseCSVFormatString( String str )
    {
        TradeRecord tr = new TradeRecord();
        String[] words = str.split( "," );
        String timeStr = words[5];
        if( !timeStr.isEmpty() )
        {
            tr.setTime( Date.parse( parseDate( timeStr ).toGMTString() ) );
        }
        String priseStr = words[4];
        double prise = 0;
        if( !priseStr.isEmpty() )
        {
            prise = Double.valueOf( priseStr );
        }

        // BUT
        if( words[3].isEmpty() )
        {
            tr.setAmount( parseAmount( words[2] ) );
            tr.setPrise( prise );
        }// SELL
        else
        {
            tr.setAmount( parseAmount( words[3] ) );
            tr.setPrise( -prise );
        }

        return tr;
    }

    private static int parseAmount( String amountStr )
    {
        int unitIndex = amountStr.indexOf( AMOUNT_UNIT );
        return Integer.valueOf( amountStr.substring( 0, unitIndex ) );
    }

    /**
     * Parses date string in "yyyy-mm-dd hh:mm:ss" format
     * 
     * @param dateStr
     * @return Date
     */
    @SuppressWarnings( "deprecation" )
    private static Date parseDate( String dateStr )
    {
        Date date = new Date();
        String[] strs = dateStr.split( " " );
        // yyyy-mm-dd
        {
            String[] dateParts = strs[0].split( "-" );

            date.setYear( Integer.valueOf( dateParts[0] ) - CALENDAR_YEAR_BASE );
            date.setMonth( Integer.valueOf( dateParts[1] ) - 1 );
            date.setDate( Integer.valueOf( dateParts[2] ) );
        }

        // hh:mm:ss
        {
            String[] timeParts = strs[1].split( ":" );

            date.setHours( Integer.valueOf( timeParts[0] ) );
            date.setMinutes( Integer.valueOf( timeParts[1] ) );
            date.setSeconds( Integer.valueOf( timeParts[2] ) );
        }
        return date;
    }
}
