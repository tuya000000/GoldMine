/**
 * 
 */
package ui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author tuya
 * 
 */
public class BaseWindowView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6187945577835791998L;

	/**
	 * @throws HeadlessException
	 */
	public BaseWindowView() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public BaseWindowView(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public BaseWindowView(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param gc
	 */
	public BaseWindowView(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	/*
	 * ==================================================================== Base
	 * Methods
	 */

	protected void buildContentArea() {
	}

	public void clear() {

	}
}
