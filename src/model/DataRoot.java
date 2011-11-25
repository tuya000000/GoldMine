/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Helper;

/**
 * @author tuya
 */
public class DataRoot extends VirtualModelObject
{
    private static Map<String, Class<? extends ModelObject>> myObjectIdtoClassMap =
        new HashMap<String, Class<? extends ModelObject>>();

    private static DataRoot myInstance;

    static
    {
        myObjectIdtoClassMap.put( "TR", TradeRecord.class );
        myObjectIdtoClassMap.put( "TRPA", TradePair.class );
    }

    /**
	 * 
	 */
    private DataRoot()
    {
        // TODO Auto-generated constructor stub
    }

    public static void initialize()
    {
        myInstance = new DataRoot();
    }

    public static DataRoot inst()
    {
        return myInstance;
    }

    public static void destory()
    {
        myInstance = null;
    }

    public boolean hasInstance()
    {
        return myInstance != null;
    }

    @SuppressWarnings( "unchecked" )
    public <T extends ModelObject> T getModelObject( String objectId )
    {
        Class<? extends ModelObject> objectType = myObjectIdtoClassMap.get( Helper.getObjectPrefix( objectId ) );
        if( objectType == TradeRecord.class )
        {
            return ( T ) getTradeRecord( objectId );
        }
        return null;
    }

    // ======================================================================
    // Members to manage Trade records
    // ======================================================================
    private final List<TradeRecord> myTradeRecords = new ArrayList<TradeRecord>();

    public List<TradeRecord> getTradeRecords()
    {
        return myTradeRecords;
    }

    public void addTradeRecord( TradeRecord tr )
    {
        myTradeRecords.remove( tr );
        myTradeRecords.add( tr );
    }

    public void addTradeRecords( List<TradeRecord> trs )
    {
        for( TradeRecord tr : trs )
        {
            addTradeRecord( tr );
        }
    }

    public void clearTradeRecords()
    {
        myTradeRecords.clear();
    }

    public TradeRecord getTradeRecord( String objectId )
    {
        for( TradeRecord tr : myTradeRecords )
        {
            if( tr.getObjectId().equals( objectId ) )
            {
                return tr;
            }
        }
        return null;
    }

    // ======================================================================
    // Members to manage Trade pair analysis
    // ======================================================================
    private static TradePairAnalysisResult myTradePairAnalysisResult;

    /**
     * @param myTradePairAnalysisResult the myTradePairAnalysisResult to set
     */
    public static void setTradePairAnalysisResult( TradePairAnalysisResult myTradePairAnalysisResult )
    {
        DataRoot.myTradePairAnalysisResult = myTradePairAnalysisResult;
    }

    /**
     * @return the myTradePairAnalysisResult
     */
    public static TradePairAnalysisResult getTradePairAnalysisResult()
    {
        return myTradePairAnalysisResult;
    }

}
