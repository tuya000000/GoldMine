/**
 * 
 */
package ui.analysis;

import model.DataRoot;
import model.TradePair;
import model.TradePairable;
import ui.TradeSummaryPanelControl;

/**
 * @author tuya
 */
public class TradePairAnalysisSummaryPanelControl extends TradeSummaryPanelControl
{
    TradePairAnalysisSummary mySummary = new TradePairAnalysisSummary();

    public TradePairAnalysisSummaryPanelControl( TradePairAnalysisSummaryPanelView view )
    {
        myView = view;
    }

    /* =========================================================
     * Functional Methods
     */
    /**
     * @return
     */
    @Override
    public TradePairAnalysisSummary generateSummary()
    {
        int totalBuyAmount = 0;
        int totalSellAmount = 0;
        double totalBuyMoney = 0;
        double totalSellMoney = 0;
        double pairedProfit = 0;
        double unpairedCarryCost = 0;
        int unpairedCarryAmount = 0;
        for( TradePairable trpb : DataRoot.inst().getTradePairAnalysisResult().getResultList() )
        {
            totalBuyAmount += trpb.getBuyAmount();
            totalSellAmount += trpb.getSellAmount();
            totalBuyMoney += trpb.getBuyMoney();
            totalSellMoney += trpb.getSellMoney();
            if( !trpb.isPairable() )
            {
                TradePair trpa = ( TradePair ) trpb;
                pairedProfit += ( trpa.getSellMoney() - trpa.getBuyMoney() );
            }
            else
            {
                unpairedCarryCost += trpb.getBuyMoney();
                unpairedCarryAmount += trpb.getBuyAmount();
            }
        }
        mySummary.setTotalBuyAmount( totalBuyAmount );
        mySummary.setTotalSellAmount( totalSellAmount );
        mySummary.setTotalBuyMoney( totalBuyMoney );
        mySummary.setTotalSellMoney( totalSellMoney );
        mySummary.setCarryAmount( totalBuyAmount - totalSellAmount );
        mySummary.setPairedTradeProfit( pairedProfit );
        mySummary.setCarryCost( totalBuyMoney - totalSellMoney );
        mySummary.setAvgCarryPrise( unpairedCarryCost / unpairedCarryAmount );
        return mySummary;
    }

    @Override
    public TradePairAnalysisSummary getSummary()
    {
        return mySummary;
    }
}
