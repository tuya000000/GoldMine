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

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuya
 */
public class TradeRecordInputWindowView extends BaseWindowView
{
    private static final String myTitle = "输入交易记录";

    private JTextArea myInputArea;

    /**
     * @throws HeadlessException
     */
    public TradeRecordInputWindowView() throws HeadlessException
    {
    }

    /**
     * @param gc
     */
    public TradeRecordInputWindowView( GraphicsConfiguration gc )
    {
        super( gc );

    }

    /**
     * @param title
     * @throws HeadlessException
     */
    public TradeRecordInputWindowView( String title ) throws HeadlessException
    {
        super( title );

    }

    /**
     * @param title
     * @param gc
     */
    public TradeRecordInputWindowView( String title, GraphicsConfiguration gc )
    {
        super( title, gc );

    }

    @Override
    protected void buildContentArea()
    {
        this.setTitle( myTitle );
        this.setSize( 640, 480 );
        Container mainPane = getContentPane();
        mainPane.setLayout( new MigLayout( "", "12[]6[]" ) );
        mainPane.add( buildInputArea() );
    }

    /*
     * ========================================================================
     * Private Methods
     */

    private JTextArea buildInputArea()
    {
        myInputArea = new JTextArea();
        myInputArea.setRows( 15 );
        myInputArea.setColumns( 60 );
        return myInputArea;
    }
}
