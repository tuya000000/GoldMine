/*
 * 
 */
package model;

import model.TradeRecord.TradeType;

/**
 * @author tuya
 */
public class TradePairable extends VirtualModelObject
{
    private TradeRecord myBuyTrade;

    private TradeRecord mySellTrade;

    /**
     * 
     */
    public TradePairable()
    {
    }

    public TradePairable( TradeRecord tr )
    {
        if( tr.getType() == TradeType.BUY )
        {
            myBuyTrade = tr;
        }
        else if( tr.getType() == TradeType.SELL )
        {
            mySellTrade = tr;
        }
    }

    public boolean hasBuyTradeRef()
    {
        return myBuyTrade != null;
    }

    public boolean hasSellTradeRef()
    {
        return mySellTrade != null;
    }

    public boolean isPairable()
    {
        return !hasBuyTradeRef() || !hasSellTradeRef();
    }

    /**
     * @return the buyTrade
     */
    public TradeRecord getBuyTrade()
    {
        return myBuyTrade;
    }

    /**
     * @return the sellTrade
     */
    public TradeRecord getSellTrade()
    {
        return mySellTrade;
    }

    /**
     * @param buyTrade the buyTrade to set
     */
    public void setBuyTrade( TradeRecord buyTrade )
    {
        myBuyTrade = buyTrade;
    }

    /**
     * @param sellTrade the sellTrade to set
     */
    public void setSellTrade( TradeRecord sellTrade )
    {
        mySellTrade = sellTrade;
    }

    public int getBuyAmount()
    {
        return myBuyTrade != null ? myBuyTrade.getAmount() : 0;
    }

    public double getBuyPrise()
    {
        return myBuyTrade != null ? myBuyTrade.getPrise() : 0;
    }

    public int getSellAmount()
    {
        return mySellTrade != null ? mySellTrade.getAmount() : 0;
    }

    public double getSellPrise()
    {
        return mySellTrade != null ? mySellTrade.getPrise() : 0;
    }

    public double getBuyMoney()
    {
        if( myBuyTrade != null )
        {
            return myBuyTrade.getMoney();
        }
        return 0;
    }

    public double getSellMoney()
    {
        if( mySellTrade != null )
        {
            return mySellTrade.getMoney();
        }
        return 0;
    }
}
