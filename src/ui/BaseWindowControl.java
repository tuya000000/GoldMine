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
	 * Interface method.Refresh the page with models
	 */
	public void refresh() {

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
		view.setControl(this);
	}

	public void clear() {
		if (myView != null) {
			myView.clear();
			myView = null;
		}
	}
}
