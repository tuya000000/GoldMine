/**
 * 
 */
package ui.analysis;

import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import ui.LabelValuePane;
import ui.TradeSummaryPanelView;
import util.Helper;

/**
 * @author tuya
 */
public class TradePairAnalysisSummaryPanelView extends TradeSummaryPanelView
{
    /**
     * 
     */
    private static final long serialVersionUID = 3911314939329552227L;

    private LabelValuePane myPairedTradeProfit;

    public TradePairAnalysisSummaryPanelView()
    {
    }

    @Override
    protected void build()
    {
        myControl = new TradePairAnalysisSummaryPanelControl( this );
    }

    /**
     * @return
     */
    @Override
    public Component buildContentArea()
    {
        setLayout( new MigLayout( "", "12[]6[]" ) );
        myTotalBuyAmount = new LabelValuePane();
        myTotalBuyAmount.setLabelText( "购入总量：" );
        add( myTotalBuyAmount, "wrap" );

        myTotalSellAmount = new LabelValuePane();
        myTotalSellAmount.setLabelText( "卖出总量：" );
        add( myTotalSellAmount, "wrap" );

        myCarryAmount = new LabelValuePane();
        myCarryAmount.setLabelText( "持有总量：" );
        add( myCarryAmount, "wrap" );

        myTotalBuyMoney = new LabelValuePane();
        myTotalBuyMoney.setLabelText( "购入总金额：" );
        add( myTotalBuyMoney, "wrap" );

        myTotalSellMoney = new LabelValuePane();
        myTotalSellMoney.setLabelText( "卖出总金额：" );
        add( myTotalSellMoney, "wrap" );

        myPairedTradeProfit = new LabelValuePane();
        myPairedTradeProfit.setLabelText( "已配对交易收益：" );
        add( myPairedTradeProfit, "wrap" );

        myCarryCost = new LabelValuePane();
        myCarryCost.setLabelText( "未配对交易成本：" );
        add( myCarryCost, "wrap" );

        myAvgCarryPrise = new LabelValuePane();
        myAvgCarryPrise.setLabelText( "未配对交易均价：" );
        add( myAvgCarryPrise, "wrap" );

        return this;
    }

    @Override
    public void refresh()
    {
        super.refresh();
        TradePairAnalysisSummary summary = ( TradePairAnalysisSummary ) myControl.getSummary();
        myPairedTradeProfit.setValueText( Helper.getCurrencyString( summary.getPairedTradeProfit() ) );
    }

}
