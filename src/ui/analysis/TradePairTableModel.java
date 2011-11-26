/*
 */
package ui.analysis;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.TradePairable;
import ui.DefaultHeaderRenderer;
import util.Helper;

/**
 * @author tuya
 */
public class TradePairTableModel extends DefaultTableModel
{
    /**
     * 
     */
    private static final long serialVersionUID = -6129475757339665575L;

    private List<TradePairable> myPairs = new ArrayList<TradePairable>();

    private final int COL_BUY_AMOUNT = 0;

    private final int COL_SELL_AMOUNT = 1;

    private final int COL_BUY_PRISE = 2;

    private final int COL_SELL_PRISE = 3;

    private final int COL_PROFIT = 4;

    private final int COL_PROFIT_RATE = 5;

    private final int COL_MAX = 6;

    private final TableColumn[] myColumns = new TableColumn[ COL_MAX ];

    private JTable myTable;

    private final String myColumnHeaders[] = {
                                              // COL_BUY_AMOUNT
                                              "买入数量",
                                              // COL_SELL_AMOUNT
                                              "卖出数量",
                                              // COL_BUY_PRISE
                                              "买入价格",
                                              // COL_SELL_PRISE
                                              "卖出价格",
                                              // COL_PROFIT
                                              "收益",
                                              // COL_PROFIT_RATE
                                              "收益率" };

    public TradePairTableModel( JTable table )
    {
        setTable( table );
        table.setModel( this );
        initColumns();
    }

    /**
     * 
     */
    private void initColumns()
    {
        DefaultHeaderRenderer rightHeaderRenderer = new DefaultHeaderRenderer();
        for( int col = 0; col < COL_MAX; col++ )
        {
            if( myColumns[col] == null )
            {
                myColumns[col] = new TableColumn();
                myColumns[col].setModelIndex( col );
                myColumns[col].setHeaderValue( myColumnHeaders[col] );
                myColumns[col].setHeaderRenderer( rightHeaderRenderer );
                myTable.getColumnModel().addColumn( myColumns[col] );
            }
        }
    }

    /**
     * @param table
     */
    private void setTable( JTable table )
    {
        myTable = table;
    }

    @Override
    public boolean isCellEditable( int row, int column )
    {
        return false;
    }

    @SuppressWarnings( "deprecation" )
    @Override
    public Object getValueAt( int row, int col )
    {
        TradePairable trpb = myPairs.get( row );

        switch( col )
        {
            case COL_BUY_AMOUNT:
                return trpb.getBuyAmount();
            case COL_SELL_AMOUNT:
                return trpb.getSellAmount();
            case COL_BUY_PRISE:
                return Helper.getCurrencyString( trpb.getBuyPrise() );
            case COL_SELL_PRISE:
                return Helper.getCurrencyString( trpb.getSellPrise() );
            case COL_PROFIT:
                return Helper.getCurrencyString( trpb.getSellMoney() - trpb.getBuyMoney() );
            case COL_PROFIT_RATE:
                return "-";
            default:
        }
        return "";
    }

    public void setModel( List<TradePairable> pList )
    {
        myPairs.clear();
        myPairs.addAll( pList );
        refresh();
    }

    /**
     * Clears the TableModel data
     */
    public void clear()
    {
        myPairs.clear();
        fireTableDataChanged();
    }

    /**
     * Destructor for this class.
     */
    public void destroy()
    {
        clear();
        myPairs = null;
    }

    /**
     * 
     */
    private void refresh()
    {
        this.setRowCount( myPairs.size() );
    }

}
