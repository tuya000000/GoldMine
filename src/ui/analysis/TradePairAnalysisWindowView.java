/**
 * 
 */
package ui.analysis;

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

/**
 * @author tuya
 */
public class TradePairAnalysisWindowView extends BaseWindowView implements TableModelListener, ActionListener
{
    private static final String myTitle = "交易配对分析";

    private JTable myTable;

    private TradePairTableModel myTradePairTableModel;

    private JButton myAnalysisButton;

    private TradePairAnalysisSummaryPanelView mySummaryView;

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
        mySummaryView = new TradePairAnalysisSummaryPanelView();
        mainPane.add( mySummaryView.buildContentArea(), "wrap" );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new MigLayout( "", "0[]12[]" ) );
        buttonPanel.add( buildAnalysisButton(), "" );
        mainPane.add( buttonPanel, "wrap" );
    }

    @Override
    public void setModel( Object... objs )
    {
        TradePairAnalysisResult tpar = ( TradePairAnalysisResult ) objs[0];
        refreshTableModel( tpar.getResultList() );
    }

    /**
     * @param arg0
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    @Override
    public void tableChanged( TableModelEvent arg0 )
    {
    }

    /**
     * @param arg0
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
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
        myTradePairTableModel.refresh();
        myAnalysisButton.setText( "继续分析" );
        mySummaryView.refresh();
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
