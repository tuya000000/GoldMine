/**
 * 
 */
package ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.DataRoot;
import model.TradeRecord;
import file.CSVReader;
import file.XMLFileHandler;

/**
 * @author tuya
 */
public class GoldMineWindowControl extends BaseWindowControl
{

    /**
	 * 
	 */
    public GoldMineWindowControl()
    {
        super();
        DataRoot.initialize();

        setView( new GoldMineWindowView() );
    }

    public void run()
    {
        while( !isClosed() )
        {

        }
    }

    public boolean isClosed()
    {
        return ( ( GoldMineWindowView ) getView() ).isClosed();
    }

    /*
     * ========================================================================
     * Interface Methods
     */
    /**
     * Interface method.Refresh the page with models
     */
    @Override
    public void clear()
    {
        DataRoot.destory();
        super.clear();
    }

    @Override
    public void refresh()
    {
        getView().setModel( DataRoot.inst().getTradeRecords() );
    }

    public void addTradeRecordsFromInputWindow( List<TradeRecord> trs )
    {
        DataRoot.inst().addTradeRecords( trs );
        refresh();
    }

    public void readTradeRecordsFromCSVFile( File csvFile )
    {

        try
        {
            DataRoot.inst().addTradeRecords( CSVReader.readTradeRecordsFromFile( csvFile ) );
        }
        catch( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        refresh();
    }

    public void readTradeRecordsFromXMLFile( File xmlFile ) throws IOException
    {

        XMLFileHandler.readFromXML( xmlFile );

        refresh();
    }

}
