/*
 *                          
 */
package model;

/**
 * @author tuya
 */
public class TradePair extends TradePairable
{

    /**
     * 
     */
    public TradePair()
    {
    }

    @Override
    public boolean isPairable()
    {
        return false;
    }

    /**
     * Checks if is full pair.<br>
     * Definition of full pair: buy trade and sell trade has the same amount
     * 
     * @return
     */
    public boolean isFullPair()
    {
        if( getBuyTrade().getAmount() == getSellTrade().getAmount() )
        {
            return true;
        }
        return false;
    }

    /**
     * Splits to 2 TradePairables. <br>
     * ~[0] is full TradePair <br>
     * ~[1] is TradePairable
     * 
     * @return TradePairable[]
     */
    public TradePairable[] splitToFullPair()
    {
        TradePairable[] trpbs = new TradePairable[ isFullPair() ? 1 : 2 ];
        if( isFullPair() )
        {
            TradePair clonePair = new TradePair();
            clonePair.setBuyTrade( getBuyTrade().clone() );
            clonePair.setSellTrade( getSellTrade().clone() );
            trpbs[0] = clonePair;
        }
        else
        {
            if( this.getBuyAmount() > this.getSellAmount() )
            {
                trpbs[0] = new TradePair();
                TradeRecord[] splitBuyTrades = splitTrade( getBuyTrade(), getSellAmount() );
                trpbs[0].setSellTrade( getSellTrade().clone() );
                trpbs[0].setBuyTrade( splitBuyTrades[0] );
                trpbs[1] = new TradePairable();
                trpbs[1].setBuyTrade( splitBuyTrades[1] );
            }
            else
            {
                trpbs[0] = new TradePair();
                TradeRecord[] splitSellTrades = splitTrade( getSellTrade(), getBuyAmount() );
                trpbs[0].setBuyTrade( getBuyTrade().clone() );
                trpbs[0].setSellTrade( splitSellTrades[0] );
                trpbs[1] = new TradePairable();
                trpbs[1].setSellTrade( splitSellTrades[1] );
            }
        }
        return trpbs;
    }

    public TradePair clone()
    {
        TradePair clonePair = new TradePair();
        return clonePair;
    }

    private TradeRecord[] splitTrade( TradeRecord tr, int... amounts )
    {
        TradeRecord[] splitTrs = new TradeRecord[ amounts.length + 1 ];
        int totalAmount = tr.getAmount();
        for( int i = 0; i < splitTrs.length; i++ )
        {
            splitTrs[i] = new TradeRecord();
            splitTrs[i].setPrise( tr.getPrise() );
            splitTrs[i].setTime( tr.getTime() );
            if( i < amounts.length )
            {
                splitTrs[i].setAmount( amounts[i] );
                totalAmount -= amounts[i];
            }
            else
            {
                splitTrs[i].setAmount( totalAmount );
            }
        }
        return splitTrs;
    }
}
