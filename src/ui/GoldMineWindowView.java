/**
 * 
 */
package ui;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuya
 * 
 */
public class GoldMineWindowView extends BaseWindowView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6943326077921559372L;

	public GoldMineWindowView() {
		super();
		buildContentArea();
	}

	/**
	 * 
	 */
	@Override
	protected void buildContentArea() {
		this.setSize(800, 600);
		this.getContentPane().setLayout(new MigLayout());
		this.setTitle("Gold Mine");
	}

	@Override
	public void clear() {

	}

	/**
	 * 
	 * @return <code>true</code> if window is closed.
	 */
	public boolean isClosed() {
		return !this.isVisible();
	}

}
