/**
 * 
 */
package ui.analysis;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.TradePairAnalysisResult;
import model.TradePairable;
import net.miginfocom.swing.MigLayout;
import ui.BaseWindowView;
import ui.LabelValuePane;
import util.Helper;

/**
 * @author tuya
 */
public class TradePairAnalysisWindowView extends BaseWindowView implements TableModelListener, ActionListener
{
    private static final String myTitle = "交易配对分析";

    private JTable myTable;

    private LabelValuePane myTotalBuyAmount;

    private LabelValuePane myTotalSellAmount;

    private LabelValuePane myCarryAmount;

    private LabelValuePane myTotalBuyMoney;

    private LabelValuePane myTotalSellMoney;

    private LabelValuePane myPairedTradeProfit;

    private LabelValuePane myCarryCost;

    private LabelValuePane myAvgCarryPrise;

    private TradePairTableModel myTradePairTableModel;

    private JButton myAnalysisButton;

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
        Container mainPane = getContentPane();
        mainPane.setLayout( new MigLayout( "", "12[]6[]" ) );
        mainPane.add( buildTablePanel(), "" );
        mainPane.add( buildSummaryPanel(), "wrap" );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new MigLayout( "", "0[]12[]" ) );
        buttonPanel.add( buildAnalysisButton(), "" );
        mainPane.add( buttonPanel, "wrap" );
    }

    /**
     * @return
     */
    private Component buildSummaryPanel()
    {
        JPanel summaryPane = new JPanel();
        summaryPane.setLayout( new MigLayout( "", "12[]6[]" ) );
        myTotalBuyAmount = new LabelValuePane();
        myTotalBuyAmount.setLabelText( "购入总量：" );
        summaryPane.add( myTotalBuyAmount, "wrap" );

        myTotalSellAmount = new LabelValuePane();
        myTotalSellAmount.setLabelText( "卖出总量：" );
        summaryPane.add( myTotalSellAmount, "wrap" );

        myCarryAmount = new LabelValuePane();
        myCarryAmount.setLabelText( "持有总量：" );
        summaryPane.add( myCarryAmount, "wrap" );

        myTotalBuyMoney = new LabelValuePane();
        myTotalBuyMoney.setLabelText( "购入总金额：" );
        summaryPane.add( myTotalBuyMoney, "wrap" );

        myTotalSellMoney = new LabelValuePane();
        myTotalSellMoney.setLabelText( "卖出总金额：" );
        summaryPane.add( myTotalSellMoney, "wrap" );

        myPairedTradeProfit = new LabelValuePane();
        myPairedTradeProfit.setLabelText( "已配对交易收益：" );
        summaryPane.add( myPairedTradeProfit, "wrap" );

        myCarryCost = new LabelValuePane();
        myCarryCost.setLabelText( "未配对交易成本：" );
        summaryPane.add( myCarryCost, "wrap" );

        myAvgCarryPrise = new LabelValuePane();
        myAvgCarryPrise.setLabelText( "未配对交易均价：" );
        summaryPane.add( myAvgCarryPrise, "wrap" );

        return summaryPane;
    }

    @Override
    public void setModel( Object... objs )
    {
        TradePairAnalysisResult tpar = ( TradePairAnalysisResult ) objs[0];
        refreshTableModel( tpar.getResultList() );
    }

    public void refreshSummaryPanel()
    {
        TradePairAnalysisSummary summary = ( ( TradePairAnalysisWindowControl ) getControl() ).getSummary();

        myTotalBuyAmount.setValueText( Helper.getAmountString( summary.getTotalBuyAmount() ) );

        myTotalSellAmount.setValueText( Helper.getAmountString( summary.getTotalSellAmount() ) );

        myCarryAmount.setValueText( Helper.getAmountString( summary.getCarryAmount() ) );

        myTotalBuyMoney.setValueText( Helper.getCurrencyString( summary.getTotalBuyMoney() ) );

        myTotalSellMoney.setValueText( Helper.getCurrencyString( summary.getTotalSellMoney() ) );

        myPairedTradeProfit.setValueText( Helper.getCurrencyString( summary.getPairedTradeProfit() ) );

        myCarryCost.setValueText( Helper.getCurrencyString( summary.getCarryCost() ) );

        myAvgCarryPrise.setValueText( Helper.getCurrencyString( summary.getAvgCarryPrise() ) );
    }

    /**
     * @param arg0
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged( TableModelEvent arg0 )
    {
    }

    /**
     * @param arg0
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent evt )
    {
        if( evt.getSource().equals( myAnalysisButton ) )
        {
            makePair();
        }
    }

    /*
     * ========================================================================
     * Private Methods
     */

    /**
     * 
     */
    private void makePair()
    {
        List<TradePairable> trpas = myTradePairTableModel.getPairs();
        ( ( TradePairAnalysisWindowControl ) getControl() ).makePair( trpas );
        ( ( TradePairAnalysisWindowControl ) getControl() ).generateSummary();
        myTradePairTableModel.refresh();
        refreshSummaryPanel();
    }

    private JComponent buildTablePanel()
    {
        JPanel tablePanel = new JPanel( new MigLayout( "ins 0 0 0 0" ) );
        myTable = new JTable();
        myTradePairTableModel = new TradePairTableModel( myTable );
        myTradePairTableModel.addTableModelListener( this );
        tablePanel.add( myTable.getTableHeader(), "wrap" );
        JScrollPane scrollPane = new JScrollPane( myTable );
        scrollPane.setViewportBorder( BorderFactory.createEmptyBorder() );
        scrollPane.setSize( 300, 400 );
        tablePanel.add( scrollPane, "wrap" );

        return scrollPane;
    }

    private JButton buildAnalysisButton()
    {
        myAnalysisButton = new JButton();
        myAnalysisButton.setText( "开始分析" );
        myAnalysisButton.addActionListener( this );
        return myAnalysisButton;
    }

    private void refreshTableModel( List<TradePairable> trpbs )
    {
        if( myTradePairTableModel == null )
        {
            myTradePairTableModel = new TradePairTableModel( myTable );
        }
        myTradePairTableModel.setModel( trpbs );
    }

}
