package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SampleWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1313795092045031657L;

	/**
	 * 
	 */
	public SampleWindow() {
		super();
		this.setSize(300, 200);
		this.getContentPane().setLayout(null);
		this.add(getJLabel(), null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
		this.setTitle("HelloWorld");

	}

	/*
	 * ==========================================================================
	 * Private Methods
	 * ==========================================================================
	 */
	private javax.swing.JLabel getJLabel() {

		JLabel jLabel = null;
		if (jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setBounds(34, 49, 53, 18);
			jLabel.setText("Name:");
		}
		return jLabel;
	}

	private javax.swing.JTextField getJTextField() {
		JTextField jTextField = null;
		if (jTextField == null) {
			jTextField = new javax.swing.JTextField();
			jTextField.setBounds(96, 49, 160, 20);
		}
		return jTextField;
	}

	private javax.swing.JButton getJButton() {
		JButton jButton = null;
		if (jButton == null) {
			jButton = new javax.swing.JButton();
			jButton.setBounds(103, 110, 71, 27);
			jButton.setText("OK");
		}
		return jButton;
	}
}