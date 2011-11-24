/**
 * 
 */
package ui.analysis;

import ui.BaseWindowView;

/**
 * @author tuya
 */
public class TradePairAnalysisWindowView extends BaseWindowView
{
    private static final String myTitle = "交易配对分析";

    /**
     * 
     */
    private static final long serialVersionUID = 8523346382284976784L;

    public TradePairAnalysisWindowView()
    {
        super();
    }

    @Override
    protected void buildContentArea()
    {
        this.setTitle( myTitle );
        this.setSize( 800, 600 );
    }
}
