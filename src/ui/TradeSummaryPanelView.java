/**
 * 
 */
package ui;

import java.awt.Component;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import util.Helper;

/**
 * @author tuya
 */
public class TradeSummaryPanelView extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 8026487324209481947L;

    protected LabelValuePane myTotalBuyAmount;

    protected LabelValuePane myTotalSellAmount;

    protected LabelValuePane myCarryAmount;

    protected LabelValuePane myTotalBuyMoney;

    protected LabelValuePane myTotalSellMoney;

    protected LabelValuePane myCarryCost;

    protected LabelValuePane myAvgCarryPrise;

    protected TradeSummaryPanelControl myControl;

    public TradeSummaryPanelView()
    {
        build();
    }

    protected void build()
    {
        myControl = new TradeSummaryPanelControl( this );
    }

    /**
     * @return
     */
    public Component buildContentArea()
    {
        setLayout( new MigLayout( "", "12[]6[]" ) );
        myTotalBuyAmount = new LabelValuePane();
        myTotalBuyAmount.setLabelText( "购入总量：" );
        add( myTotalBuyAmount, "wrap" );

        myTotalSellAmount = new LabelValuePane();
        myTotalSellAmount.setLabelText( "卖出总量：" );
        add( myTotalSellAmount, "wrap" );

        myTotalBuyMoney = new LabelValuePane();
        myTotalBuyMoney.setLabelText( "购入总金额：" );
        add( myTotalBuyMoney, "wrap" );

        myTotalSellMoney = new LabelValuePane();
        myTotalSellMoney.setLabelText( "卖出总金额：" );
        add( myTotalSellMoney, "wrap" );

        myCarryAmount = new LabelValuePane();
        myCarryAmount.setLabelText( "持有总量：" );
        add( myCarryAmount, "wrap" );

        myCarryCost = new LabelValuePane();
        myCarryCost.setLabelText( "成本：" );
        add( myCarryCost, "wrap" );

        myAvgCarryPrise = new LabelValuePane();
        myAvgCarryPrise.setLabelText( "均价：" );
        add( myAvgCarryPrise, "wrap" );

        return this;
    }

    public void refresh()
    {
        TradeSummary summary = myControl.generateSummary();

        myTotalBuyAmount.setValueText( Helper.getAmountString( summary.getTotalBuyAmount() ) );

        myTotalSellAmount.setValueText( Helper.getAmountString( summary.getTotalSellAmount() ) );

        myCarryAmount.setValueText( Helper.getAmountString( summary.getCarryAmount() ) );

        myTotalBuyMoney.setValueText( Helper.getCurrencyString( summary.getTotalBuyMoney() ) );

        myTotalSellMoney.setValueText( Helper.getCurrencyString( summary.getTotalSellMoney() ) );

        myCarryCost.setValueText( Helper.getCurrencyString( summary.getCarryCost() ) );

        myAvgCarryPrise.setValueText( Helper.getCurrencyString( summary.getAvgCarryPrise() ) );
    }
}
