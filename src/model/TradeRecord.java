/**
 * 
 */
package model;

import java.sql.Date;

import util.Helper;

/**
 * @author tuya
 */
public class TradeRecord extends ModelObject
{

    public enum TradeType
    {
        BUY, // In a BUY trade, the prise should above 0
        SELL, // In a SELL trade, the prise should below 0
        NOT_A_TRADE
    }

    private long time;

    private int amount;

    private double prise;

    /**
	 * 
	 */
    public TradeRecord()
    {

    }

    /**
	 * 
	 */
    public TradeRecord( long time, int amount, double prise )
    {
        setTime( time );
        setAmount( amount );
        setPrise( prise );
    }

    /**
     * @return
     * @see java.lang.Object#clone()
     */
    public TradeRecord clone()
    {
        TradeRecord cloneRecord = new TradeRecord();
        cloneRecord.amount = amount;
        cloneRecord.prise = prise;
        cloneRecord.time = time;
        return cloneRecord;
    }

    /**
     * @return
     */
    public TradeType getType()
    {
        if( prise > 0 )
        {
            return TradeType.BUY;
        }
        else if( prise < 0 )
        {
            return TradeType.SELL;
        }
        return TradeType.NOT_A_TRADE;
    }

    /**
     * @return
     */
    public double getMoney()
    {
        return Math.abs( prise ) * amount;
    }

    /**
     * @return the time
     */
    public long getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime( long time )
    {
        this.time = time;
    }

    /**
     * @return the amount
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount( int amount )
    {
        this.amount = amount;
    }

    /**
     * @return the prise
     */
    public double getPrise()
    {
        return prise;
    }

    /**
     * @param prise the prise to set
     */
    public void setPrise( double prise )
    {
        this.prise = prise;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append( "价格:" + Helper.getCurrencyString( Math.abs( prise ) ) + "\n" );
        sb.append( ( prise > 0 ? "买入:" : "卖出:" ) + Helper.getAmountString( amount ) + "\n" );
        Date date = new Date( time );
        sb.append( date.toLocaleString() + "\n" );
        return sb.toString();
    }
}
