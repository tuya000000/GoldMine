/**
 * 
 */
package ui;

import model.DataRoot;
import model.TradeRecord;
import model.TradeRecord.TradeType;

/**
 * @author tuya
 */
public class TradeSummaryPanelControl
{
    TradeSummary mySummary = new TradeSummary();

    protected TradeSummaryPanelView myView;

    public TradeSummaryPanelControl()
    {

    }

    public TradeSummaryPanelControl( TradeSummaryPanelView view )
    {
        myView = view;
    }

    /* =========================================================
     * Functional Methods
     */
    /**
     * @return
     */
    public TradeSummary generateSummary()
    {
        int totalBuyAmount = 0;
        int totalSellAmount = 0;
        int carryAmount = 0;
        double totalBuyMoney = 0;
        double totalSellMoney = 0;
        double carryCost = 0;
        for( TradeRecord tr : DataRoot.inst().getTradeRecords() )
        {
            if( tr.getType().equals( TradeType.BUY ) )
            {
                totalBuyAmount += tr.getAmount();
                totalBuyMoney += tr.getMoney();
            }
            else if( tr.getType().equals( TradeType.SELL ) )
            {
                totalSellAmount += tr.getAmount();
                totalSellMoney += tr.getMoney();
            }
        }
        carryAmount = totalBuyAmount - totalSellAmount;
        carryCost = totalBuyMoney - totalSellMoney;
        mySummary.setTotalBuyAmount( totalBuyAmount );
        mySummary.setTotalSellAmount( totalSellAmount );
        mySummary.setTotalBuyMoney( totalBuyMoney );
        mySummary.setTotalSellMoney( totalSellMoney );
        mySummary.setCarryAmount( carryAmount );
        mySummary.setCarryCost( carryCost );
        mySummary.setAvgCarryPrise( carryCost / carryAmount );
        return mySummary;
    }

    public TradeSummary getSummary()
    {
        return mySummary;
    }

}
