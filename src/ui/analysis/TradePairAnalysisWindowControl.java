/**
 * 
 */
package ui.analysis;

import model.DataRoot;
import model.TradePairAnalysisResult;
import model.TradePairable;
import model.TradeRecord;
import ui.BaseWindowControl;

/**
 * @author tuya
 */
public class TradePairAnalysisWindowControl extends BaseWindowControl
{

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
        for( TradeRecord tr : DataRoot.inst().getTradeRecords() )
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

}
