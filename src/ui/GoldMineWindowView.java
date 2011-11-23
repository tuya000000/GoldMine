/**
 * 
 */
package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.TradeRecord;
import net.miginfocom.swing.MigLayout;

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

    private TradeRecordTable myTradeRecordTableModel;

    private JButton myReadFileButton;

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

    /**
     * ======================================================================== Interface Methods
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
        mainPane.add( buildReadFileButton(), "wrap" );

    }

    /**
	 * 
	 */
    @Override
    public void clear()
    {
        myTable = null;
        myTradeRecordTableModel = null;
    }

    @Override
    public void setModel( Object... objs )
    {
        @SuppressWarnings( "unchecked" )
        List<TradeRecord> trs = ( List<TradeRecord> ) objs[0];
        refreshTableModel( trs );
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
    }

    /**
     * ======================================================================== Private Methods
     */

    private JPanel buildTablePanel()
    {
        JPanel tablePanel = new JPanel( new MigLayout( "ins 0 0 0 0" ) );
        myTable = new JTable();
        myTradeRecordTableModel = new TradeRecordTable( myTable );
        myTradeRecordTableModel.addTableModelListener( this );
        myTradeRecordTableModel.addTrade( System.currentTimeMillis(), 19, -330 );
        tablePanel.add( myTable.getTableHeader(), "wrap" );
        tablePanel.add( myTable, "wrap" );
        return tablePanel;
    }

    private void refreshTableModel( List<TradeRecord> trs )
    {
        if( myTradeRecordTableModel == null )
        {
            myTradeRecordTableModel = new TradeRecordTable( myTable );
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
