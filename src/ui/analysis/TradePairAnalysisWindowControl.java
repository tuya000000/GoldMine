/**
 * 
 */
package ui.analysis;

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
    public void destory()
    {
        super.destory();
    }

    @Override
    public void refresh()
    {

    }

}
