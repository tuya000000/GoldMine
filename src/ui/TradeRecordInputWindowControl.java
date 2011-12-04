/*
 *  Copyright (c) 2008 Nokia Siemens Networks. All rights reserved.
 *
 *  Revision History:
 *
 *  DATE/AUTHOR         COMMENT
 *  ---------------------------------------------------------------------
 *  xx.xx.xxxx/ESe                            
 */
package ui;

import java.util.ArrayList;
import java.util.List;

import model.DataRoot;
import model.TradeRecord;
import model.TradeRecordParser;
import service.GlobalServices;

/**
 * @author tuya
 */
public class TradeRecordInputWindowControl extends BaseWindowControl
{
    List<TradeRecord> myInputTradeRecords = new ArrayList<TradeRecord>();

    /**
     * 
     */
    public TradeRecordInputWindowControl()
    {
        super();

        setView( new TradeRecordInputWindowView() );

        getView().setVisible( true );
    }

    public void activeNotify()
    {
        getView().setVisible( true );
        refresh();
    }

    @Override
    public void destory()
    {
        super.destory();
    }

    @Override
    public void refresh()
    {
        getView().setModel( myInputTradeRecords );
    }

    public boolean hasValidInput()
    {
        return !myInputTradeRecords.isEmpty();
    }

    public void saveTradeRecordInput()
    {
        GoldMineWindowControl mainWindow = GlobalServices.findRegisteredService( GoldMineWindowControl.class );
        mainWindow.addTradeRecordsFromInputWindow( myInputTradeRecords );
        myInputTradeRecords.clear();
        hide();
    }

    /**
     * @param inputStr
     */
    public void parseTradeRecordInput( String inputStr )
    {
        String[] lines = inputStr.split( "\n" );
        for( String line : lines )
        {
            try
            {
                String oneLineStr = remakeFormat( line );
                TradeRecord tr = TradeRecordParser.parseFormatedString( oneLineStr, "!" );
                if( isValidInput( tr ) )
                {
                    tr.setObjectId( "TR-" );
                    myInputTradeRecords.add( tr );
                }
            }
            catch( Throwable ex )
            {
                System.out.println( "无效输入:" + line );
                continue;
            }
        }
        refresh();
    }

    private String remakeFormat( String origStr )
    {
        if( origStr.split( "  " )[0].length() == 4 )
        {
            return remakeBuyFormat( origStr );
        }
        else
        {
            return remakeSellFormat( origStr );
        }
    }

    private boolean isValidInput( TradeRecord tr )
    {
        for( TradeRecord dtr : myInputTradeRecords )
        {
            if( dtr.getTime() == tr.getTime() )
            {
                return false;
            }
        }
        for( TradeRecord dtr : DataRoot.inst().getTradeRecords() )
        {
            if( dtr.getTime() == tr.getTime() )
            {
                return false;
            }
        }

        return true;
    }

    /**
     * @param origStr
     * @return
     */
    private String remakeBuyFormat( String origStr )
    {
        String formattedStr = origStr.replaceAll( "  ", "!" );
        formattedStr = formattedStr.replaceAll( "! ", "!!" );
        formattedStr = formattedStr.replaceAll( "!- ", "!-!" );
        return formattedStr;
    }

    /**
     * @param origStr
     * @return
     */
    private String remakeSellFormat( String origStr )
    {
        String formattedStr = origStr.replaceAll( "  ", "!" );
        formattedStr = formattedStr.replaceAll( "!-!", "!-!!" );
        return formattedStr;
    }
}
