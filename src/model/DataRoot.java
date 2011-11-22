/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuya
 * 
 */
public class DataRoot extends ModelObject {

	private static DataRoot myInstance;

	/**
	 * 
	 */
	private DataRoot() {
		// TODO Auto-generated constructor stub
	}

	public static void initialize() {
		myInstance = new DataRoot();
	}

	public static DataRoot inst() {
		return myInstance;
	}

	public static void destory() {
		myInstance = null;
	}

	public boolean hasInstance() {
		return myInstance != null;
	}

	// ======================================================================
	// Members to manage Trade records
	// ======================================================================
	private final List<TradeRecord> myTradeRecords = new ArrayList<TradeRecord>();

	public List<TradeRecord> getTradeRecords() {
		return myTradeRecords;
	}

	public void addTradeRecord(TradeRecord tr) {
		myTradeRecords.remove(tr);
		myTradeRecords.add(tr);
	}

	public void addTradeRecords(List<TradeRecord> trs) {
		for (TradeRecord tr : trs) {
			addTradeRecord(tr);
		}
	}

	public void clearTradeRecords() {
		myTradeRecords.clear();
	}
}
