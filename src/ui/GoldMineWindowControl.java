/**
 * 
 */
package ui;

/**
 * @author tuya
 * 
 */
public class GoldMineWindowControl extends BaseWindowControl {

	/**
	 * 
	 */
	public GoldMineWindowControl() {
		setView(new GoldMineWindowView());
	}

	public void run() {
		getView().setVisible(true);
		while (!isClosed()) {

		}
	}

	public boolean isClosed() {
		return ((GoldMineWindowView) getView()).isClosed();
	}
}
