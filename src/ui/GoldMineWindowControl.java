/**
 * 
 */
package ui;

import java.io.File;
import java.io.IOException;

import model.DataRoot;
import file.CSVReader;

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
        DataRoot.initialize();

        setView( new GoldMineWindowView() );
    }

    public void run()
    {
        getView().setVisible( true );
        while( !isClosed() )
        {

        }
    }

    public boolean isClosed()
    {
        return ( ( GoldMineWindowView ) getView() ).isClosed();
    }

    /**
     * ======================================================================== Interface Methods
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
}
