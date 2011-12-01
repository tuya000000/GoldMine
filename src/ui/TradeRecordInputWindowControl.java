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

/**
 * @author tuya
 */
public class TradeRecordInputWindowControl extends BaseWindowControl
{

    /**
     * 
     */
    public TradeRecordInputWindowControl()
    {
        super();

        setView( new TradeRecordInputWindowView() );

        getView().setVisible( true );
    }

    @Override
    public void destory()
    {
        super.destory();
    }

    @Override
    public void refresh()
    {

    }
}
