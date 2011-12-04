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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.TradeRecord;
import net.miginfocom.swing.MigLayout;

/**
 * @author tuya
 */
public class TradeRecordInputWindowView extends BaseWindowView implements TableModelListener, ActionListener,
    KeyListener
{
    private static final String myTitle = "输入交易记录";

    private JTextArea myInputArea;

    private JTable myTable;

    private JButton myParseButton;

    private JButton mySaveButton;

    private TradeRecordTableModel myTradeRecordTableModel;

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
        // Main Layout
        mainPane.setLayout( new MigLayout( "", "12[]6[]" ) );
        JPanel inputArea = new JPanel();
        inputArea.setLayout( new MigLayout( "", "0[]" ) );
        mainPane.add( inputArea );
        mainPane.add( buildTablePanel() );

        // Input Area Layout
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout( new MigLayout( "", "0[]6[]" ) );
        inputArea.add( buildInputArea(), "wrap" );
        inputArea.add( buttonArea );
        buttonArea.add( buildParseButton() );
        buttonArea.add( buildSaveButton() );
    }

    @Override
    public void setModel( Object... objs )
    {
        @SuppressWarnings( "unchecked" )
        List<TradeRecord> trs = ( List<TradeRecord> ) objs[0];
        refreshTableModel( trs );

        myInputArea.setText( "" );

        updateGuiState();
    }

    public void updateGuiState()
    {
        mySaveButton.setEnabled( ( ( TradeRecordInputWindowControl ) getControl() ).hasValidInput() );
        myParseButton.setEnabled( !myInputArea.getText().isEmpty() );
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
        scrollPane.setSize( 310, 200 );
        tablePanel.add( scrollPane, "wrap" );

        return scrollPane;
    }

    private JPanel buildInputArea()
    {
        JPanel inputPanel = new JPanel( new MigLayout( "ins 0 0 0 0" ) );
        myInputArea = new JTextArea();
        myInputArea.setRows( 20 );
        myInputArea.setColumns( 30 );
        myInputArea.addKeyListener( this );
        JScrollPane scrollPane = new JScrollPane( myInputArea );
        scrollPane.setViewportBorder( BorderFactory.createEmptyBorder() );
        scrollPane.setSize( 310, 400 );
        inputPanel.add( scrollPane, "wrap" );
        return inputPanel;
    }

    private JButton buildParseButton()
    {
        myParseButton = new JButton();
        myParseButton.setText( "输入解析" );
        myParseButton.addActionListener( this );
        return myParseButton;
    }

    private JButton buildSaveButton()
    {
        mySaveButton = new JButton();
        mySaveButton.setText( "保存输入" );
        mySaveButton.addActionListener( this );
        return mySaveButton;
    }

    /**
     * 
     */
    private void parseInput()
    {
        ( ( TradeRecordInputWindowControl ) getControl() ).parseTradeRecordInput( myInputArea.getText() );
    }

    /**
     * 
     */
    private void saveInput()
    {
        ( ( TradeRecordInputWindowControl ) getControl() ).saveTradeRecordInput();
    }

    private void refreshTableModel( List<TradeRecord> trs )
    {
        if( myTradeRecordTableModel == null )
        {
            myTradeRecordTableModel = new TradeRecordTableModel( myTable );
        }
        myTradeRecordTableModel.setModel( trs );
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
        if( evt.getSource().equals( myParseButton ) )
        {
            parseInput();
        }
        else if( evt.getSource().equals( mySaveButton ) )
        {
            saveInput();
        }
        updateGuiState();
    }

    /**
     * @param arg0
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed( KeyEvent arg0 )
    {
    }

    /**
     * @param arg0
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased( KeyEvent arg0 )
    {
        updateGuiState();
    }

    /**
     * @param arg0
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped( KeyEvent arg0 )
    {
    }

}
