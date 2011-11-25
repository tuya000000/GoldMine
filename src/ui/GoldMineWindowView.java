/**
 * 
 */
package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.TradeRecord;
import net.miginfocom.swing.MigLayout;
import service.GlobalServices;
import ui.analysis.TradePairAnalysisWindowControl;

/**
 * @author tuya
 */
public class GoldMineWindowView extends BaseWindowView implements TableModelListener, ActionListener
{
    /**
	 * 
	 */
    private static final long serialVersionUID = -6943326077921559372L;

    private JTable myTable;

    private TradeRecordTableModel myTradeRecordTableModel;

    private JButton myReadFileButton;

    private JButton myTradePairAnalysisButton;

    public GoldMineWindowView()
    {
        super();
    }

    /**
     * @return <code>true</code> if window is closed.
     */
    public boolean isClosed()
    {
        return !this.isVisible();
    }

    /*
     * ========================================================================
     * Interface Methods
     */
    /**
	 * 
	 */
    @Override
    protected void buildContentArea()
    {
        this.setTitle( "Gold Mine" );
        this.setSize( 800, 600 );
        Container mainPane = getContentPane();
        mainPane.setLayout( new MigLayout( "", "12[]6[]" ) );
        mainPane.add( buildTablePanel(), "wrap" );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new MigLayout( "", "0[]12[]" ) );
        buttonPanel.add( buildReadFileButton(), "" );
        buttonPanel.add( buildTradePairAnalysisButton(), "wrap" );
        mainPane.add( buttonPanel, "wrap" );
    }

    /**
	 * 
	 */
    @Override
    public void clear()
    {
        if( myTradeRecordTableModel != null )
        {
            myTradeRecordTableModel.clear();
        }
    }

    @Override
    public void setModel( Object... objs )
    {
        @SuppressWarnings( "unchecked" )
        List<TradeRecord> trs = ( List<TradeRecord> ) objs[0];
        refreshTableModel( trs );
    }

    /**
     * Destructor for this class. Clears object references and removes listeners.
     */
    @Override
    public void dispose()
    {
        clear();
        if( myTradeRecordTableModel != null )
        {
            myTradeRecordTableModel.destroy();
        }
        myTradeRecordTableModel = null;
        super.dispose();
    }

    @Override
    public void tableChanged( TableModelEvent e )
    {
        // TODO Auto-generated method stub
        System.out.println( "TableChanged" );
    }

    @Override
    public void actionPerformed( ActionEvent evt )
    {
        if( evt.getSource().equals( myReadFileButton ) )
        {
            readTradeRecordsFromFile();
        }
        else if( evt.getSource().equals( myTradePairAnalysisButton ) )
        {
            openTradePairAnalysisWindow();
        }
    }

    /*
     * ========================================================================
     * Private Methods
     */

    private JComponent buildTablePanel()
    {
        JPanel tablePanel = new JPanel( new MigLayout( "ins 0 0 0 0" ) );
        myTable = new JTable();
        myTradeRecordTableModel = new TradeRecordTableModel( myTable );
        myTradeRecordTableModel.addTableModelListener( this );
        tablePanel.add( myTable.getTableHeader(), "wrap" );
        JScrollPane scrollPane = new JScrollPane( myTable );
        scrollPane.setViewportBorder( BorderFactory.createEmptyBorder() );
        scrollPane.setSize( 300, 400 );
        tablePanel.add( scrollPane, "wrap" );

        return scrollPane;
    }

    private void refreshTableModel( List<TradeRecord> trs )
    {
        if( myTradeRecordTableModel == null )
        {
            myTradeRecordTableModel = new TradeRecordTableModel( myTable );
        }
        myTradeRecordTableModel.setModel( trs );
    }

    private JButton buildReadFileButton()
    {
        myReadFileButton = new JButton();
        myReadFileButton.setText( "Read Trade Records..." );
        myReadFileButton.addActionListener( this );
        return myReadFileButton;
    }

    private JButton buildTradePairAnalysisButton()
    {
        myTradePairAnalysisButton = new JButton();
        myTradePairAnalysisButton.setText( "交易配对分析..." );
        myTradePairAnalysisButton.addActionListener( this );
        return myTradePairAnalysisButton;
    }

    private void openTradePairAnalysisWindow()
    {
        GlobalServices.activeService( TradePairAnalysisWindowControl.class );
    }

    private void readTradeRecordsFromFile()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "TradeRecords in CSV", "csv" );
        chooser.setFileFilter( filter );
        int returnVal = chooser.showOpenDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            File csvFile = chooser.getSelectedFile();
            ( ( GoldMineWindowControl ) getControl() ).readTradeRecordsFromCSVFile( csvFile );
        }
    }

}
