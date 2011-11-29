/**
 * 
 */
package ui.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.DataRoot;
import model.TradePair;
import model.TradePairAnalysisResult;
import model.TradePairable;
import model.TradeRecord;
import ui.BaseWindowControl;

/**
 * @author tuya
 */
public class TradePairAnalysisWindowControl extends BaseWindowControl
{
    TradePairAnalysisSummary mySummary = new TradePairAnalysisSummary();

    /**
     * 
     */
    public TradePairAnalysisWindowControl()
    {
        super();
        setView( new TradePairAnalysisWindowView() );
        getView().setVisible( true );
    }

    /*
     * ========================================================================
     * Interface Methods
     */
    @Override
    public void activeNotify()
    {
        if( DataRoot.inst().getTradePairAnalysisResult() == null )
        {
            DataRoot.inst().setTradePairAnalysisResult( new TradePairAnalysisResult() );
        }
        TradePairAnalysisResult result = DataRoot.inst().getTradePairAnalysisResult();
        List<TradeRecord> trs = DataRoot.inst().getTradeRecords();
        Collections.sort( trs, new Comparator<TradeRecord>()
        {
            public int compare( TradeRecord tr1, TradeRecord tr2 )
            {
                return ( int ) ( tr1.getTime() - tr2.getTime() );
            }
        } );
        for( TradeRecord tr : trs )
        {
            analysisTrade( tr, result );
        }
        getView().setModel( result );
        getView().setVisible( true );
    }

    @Override
    public void destory()
    {
        super.destory();
    }

    @Override
    public void refresh()
    {

    }

    /* =========================================================
     * Functional Methods
     */
    /**
     * @return
     */
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

    /**
     * @return
     */
    public TradePairAnalysisSummary getSummary()
    {
        return mySummary;
    }

    /**
     * @param trpas
     */
    public void makePair( List<TradePairable> trpbs )
    {
        List<TradePairable> tempList = new ArrayList<TradePairable>();
        tempList.addAll( trpbs );

        for( TradePairable trpb : trpbs )
        {
            if( trpb.isPairable() && trpb.hasBuyTradeRef() )
            {
                //Pick it out from tempList
                tempList.remove( trpb );
                //Find sell Pair
                TradePairable sellPair = null;
                for( TradePairable checkTrpb : tempList )
                {
                    if( checkTrpb.isPairable() && checkTrpb.hasSellTradeRef() )
                    {
                        if( checkPairViaPrise( trpb, checkTrpb ) )
                        {
                            sellPair = checkTrpb;
                            break;
                        }
                    }
                }
                if( sellPair != null )
                {
                    //Pick it out from tempList
                    tempList.remove( sellPair );

                    //Create new Pair
                    TradePair trpa = new TradePair();
                    trpa.setBuyTrade( trpb.getBuyTrade() );
                    trpa.setSellTrade( sellPair.getSellTrade() );

                    TradePairable[] splitPairs = trpa.splitToFullPair();
                    for( TradePairable pair : splitPairs )
                    {
                        //Add new pair to tempList
                        tempList.add( pair );
                    }
                }
                else
                {
                    //Pair not found, put back to tempList
                    tempList.add( trpb );
                }

            }

        }
        //Refresh original list
        trpbs.clear();
        trpbs.addAll( tempList );

        //Save to Model;
        DataRoot.inst().getTradePairAnalysisResult().clearResults();
        DataRoot.inst().getTradePairAnalysisResult().addResults( tempList );
    }

    /* =========================================================
     * Private methods
     */
    private void analysisTrade( TradeRecord tr, TradePairAnalysisResult result )
    {
        if( result.getLastAnalysisedRecordTime() < tr.getTime() )
        {
            result.addResult( new TradePairable( tr ) );
            result.setLastAnalysisedRecordTime( tr.getTime() );
        }
    }

    private boolean checkPairViaPrise( TradePairable buyTrpb, TradePairable sellTrpb )
    {
        if( buyTrpb.getBuyPrise() <= sellTrpb.getSellPrise() )
            return true;
        return false;
    }
}
