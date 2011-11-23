/**
 * 
 */
package ui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author tuya
 */
public class BaseWindowView extends JFrame
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 6187945577835791998L;

    private BaseWindowControl myControl;

    /**
     * @throws HeadlessException
     */
    public BaseWindowView() throws HeadlessException
    {
        buildContentArea();
    }

    /**
     * @param gc
     */
    public BaseWindowView( GraphicsConfiguration gc )
    {
        super( gc );
        buildContentArea();
    }

    /**
     * @param title
     * @throws HeadlessException
     */
    public BaseWindowView( String title ) throws HeadlessException
    {
        super( title );
        buildContentArea();
    }

    /**
     * @param title
     * @param gc
     */
    public BaseWindowView( String title, GraphicsConfiguration gc )
    {
        super( title, gc );
        buildContentArea();
    }

    /*
     * ========================================================================
     * Base Methods
     */

    protected void buildContentArea()
    {
    }

    public void clear()
    {

    }

    /**
     * Disposes of the resources associated with the panel.
     * 
     * @todo Remove this quickfix when Platypus fixes it for real.
     */
    @Override
    public void dispose()
    {
        myControl = null;
        super.dispose();
    }

    public void setModel( Object... objs )
    {

    }

    /**
     * @return the myControl
     */
    public BaseWindowControl getControl()
    {
        return myControl;
    }

    /**
     * @param myControl the myControl to set
     */
    public void setControl( BaseWindowControl myControl )
    {
        this.myControl = myControl;
    }
}
