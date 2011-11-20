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
		// TODO Auto-generated constructor stub
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
