/**
 * 
 */
package ui;

/**
 * @author tuya
 * 
 */
public class BaseWindowControl {

	private BaseWindowView myView;

	/**
	 * 
	 */
	public BaseWindowControl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the myView
	 */
	public BaseWindowView getView() {
		return myView;
	}

	/**
	 * @param myView
	 *            the myView to set
	 */
	public void setView(BaseWindowView view) {
		this.myView = view;
	}

	public void clear() {
		if (myView != null) {
			myView.clear();
			myView = null;
		}
	}
}
