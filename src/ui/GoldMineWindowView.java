/**
 * 
 */
package ui;

import java.awt.Container;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.miginfocom.swing.MigLayout;

/**
 * @author tuya
 * 
 */
public class GoldMineWindowView extends BaseWindowView implements
		TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6943326077921559372L;

	private JTable myTable;
	private TradeRecordTable myTradeRecordTableModel;

	public GoldMineWindowView() {
		super();
	}

	/**
	 * 
	 */
	@Override
	protected void buildContentArea() {
		this.setTitle("Gold Mine");
		this.setSize(800, 600);
		Container mainPane = getContentPane();
		mainPane.setLayout(new MigLayout("", "12[]6[]"));
		mainPane.add(buildTable(), "wrap");
	}

	@Override
	public void clear() {
		myTable = null;
		myTradeRecordTableModel = null;
	}

	/**
	 * 
	 * @return <code>true</code> if window is closed.
	 */
	public boolean isClosed() {
		return !this.isVisible();
	}

	/**
	 * ========================================================================
	 * Private Methods
	 */

	private JTable buildTable() {
		myTable = new JTable();
		myTradeRecordTableModel = new TradeRecordTable(myTable);
		myTradeRecordTableModel.addTrade(System.currentTimeMillis(), 10, 356.3);
		myTradeRecordTableModel.addTrade(System.currentTimeMillis(), 10, 352.3);
		myTradeRecordTableModel
				.addTrade(System.currentTimeMillis(), 10, -350.4);
		myTradeRecordTableModel.addTrade(System.currentTimeMillis(), 10, -366);
		myTradeRecordTableModel.addTableModelListener(this);

		return myTable;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println("TableChanged");
	}
}
