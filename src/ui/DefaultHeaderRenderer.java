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

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * @author <a href="mailto:esa.seppala@nsn.com">Esa Seppälä</a>
 */
public class DefaultHeaderRenderer extends DefaultTableCellRenderer
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -8257369080900362667L;

    /**
     * Constructor. Sets horizontal alignment to center and border to
     * <code>UIManager.getBorder( "TableHeader.cellBorder" )</code>.
     */
    public DefaultHeaderRenderer()
    {
        setHorizontalAlignment( JLabel.CENTER );
    }

    /**
     * Configures the renderer appropriately before drawing. After configuration, <code>this</code> component is
     * returned and used by <code>JTable</code> to draw the cell header.
     * 
     * @param table The <code>JTable</code> that is asking the renderer to draw; can be <code>null</code>.
     * @param value The value of the cell header to be rendered.
     * @param isSelected True if the cell header is to be rendered with the selection highlighted; otherwise false. Is
     *        not used in this implementation
     * @param hasFocus If true, render cell appropriately. Is not used in this implementation
     * @param row The row index of the cell being drawn. When drawing the header, the value of <code>row</code> is -1.
     * @param column The column index of the cell being drawn.
     * @return Component used for drawing the cell.
     */

    @Override
    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                    int row, int column )
    {
        if( table != null )
        {
            JTableHeader header = table.getTableHeader();
            if( header != null )
            {
                // To make iconheader look like original
                setForeground( header.getForeground() );
                setBackground( header.getBackground() );
                setFont( header.getFont() );
            }
            setBorder( UIManager.getBorder( "TableHeader.cellBorder" ) );
        }

        if( value instanceof Icon )
        {
            setIcon( ( Icon ) value );
        }
        else if( value instanceof ImageIcon )
        {
            setIcon( ( ImageIcon ) value );
        }
        else
        {
            setText( value.toString() );
        }

        return this;
    }
}
