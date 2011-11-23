/**
 * 
 */
package ui;

/**
 * @author tuya
 */
public class BaseWindowControl
{

    private BaseWindowView myView;

    /**
	 * 
	 */
    public BaseWindowControl()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * Interface method.Refresh the page with models
     */
    public void refresh()
    {

    }

    /**
     * @return the myView
     */
    public BaseWindowView getView()
    {
        return myView;
    }

    /**
     * @param myView the myView to set
     */
    public void setView( BaseWindowView view )
    {
        this.myView = view;
        view.setControl( this );
    }

    public void clear()
    {
        if( getView() != null )
        {
            getView().clear();
        }
    }

    /**
     * "Destructor" that destroys the controller. This implementation disposes the view that the controller is
     * controlling, sets the flag that tells the controller has been destroyed. After destroying the controller can't be
     * used anymore. Using a destroyed controller has unpredictable consequences.
     */
    public void destroy()
    {
        if( getView() != null )
        {
            getView().dispose();
        }
        myView = null;
    }
}
