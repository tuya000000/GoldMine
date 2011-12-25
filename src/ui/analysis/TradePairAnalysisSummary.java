/*                         
 */
package ui.analysis;

import ui.TradeSummary;

/**
 * @author tuya
 */
public class TradePairAnalysisSummary extends TradeSummary
{

    private double myPairedTradeProfit;

    /**
     * 
     */
    public TradePairAnalysisSummary()
    {
    }

    /**
     * @param pairedTradeProfit the pairedTradeProfit to set
     */
    public void setPairedTradeProfit( double pairedTradeProfit )
    {
        myPairedTradeProfit = pairedTradeProfit;
    }

    /**
     * @return the pairedTradeProfit
     */
    public double getPairedTradeProfit()
    {
        return myPairedTradeProfit;
    }

}
