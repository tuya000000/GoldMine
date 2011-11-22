/**
 * 
 */
package file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import model.TradeRecord;
import model.TradeRecordParser;

/**
 * @author tuya
 * 
 */
public class CSVReader {

	public static List<TradeRecord> readTradeRecordsFromFile(File file)
			throws IOException {
		List<TradeRecord> trs = new ArrayList<TradeRecord>();
		FileReader reader = new FileReader(file);
		LineNumberReader lnr = new LineNumberReader(reader);
		for (String oneLineString = lnr.readLine(); oneLineString != null; oneLineString = lnr
				.readLine()) {
			trs.add(TradeRecordParser.parseCSVFormatString(oneLineString));
		}
		return trs;
	}
}
