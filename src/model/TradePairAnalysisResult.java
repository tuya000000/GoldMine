/*
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tuya
 */
public class TradePairAnalysisResult extends VirtualModelObject
{
    private List<TradePairable> myResultList = new ArrayList<TradePairable>();

    private long myLastPairedRecordTime;

    private long myLastAnalysisedRecordTime;

    /**
     * 
     */
    public TradePairAnalysisResult()
    {
    }

    public List<TradePair> getPairedList()
    {
        List<TradePair> pairedList = new ArrayList<TradePair>();
        for( TradePairable trpb : myResultList )
        {
            if( trpb instanceof TradePair )
            {
                pairedList.add( ( TradePair ) trpb );
            }
        }
        return pairedList;
    }

    public List<TradePairable> getUnpairedList()
    {
        List<TradePairable> unpairedList = new ArrayList<TradePairable>();
        for( TradePairable trpb : myResultList )
        {
            if( trpb.isPairable() )
            {
                unpairedList.add( trpb );
            }
        }
        return unpairedList;
    }

    public List<TradePairable> getResultList()
    {
        return myResultList;
    }

    public void clearResults()
    {
        myResultList.clear();
    }

    public void addResult( TradePairable trpa )
    {
        myResultList.add( trpa );
    }

    public void addResults( List<TradePairable> trpas )
    {
        myResultList.addAll( trpas );
    }

    public void removeResults( List<TradePairable> trpas )
    {
        myResultList.removeAll( trpas );
    }

    /**
     * @param lastPairedRecordTime the lastPairedRecordTime to set
     */
    public void setLastPairedRecordTime( long lastPairedRecordTime )
    {
        myLastPairedRecordTime = lastPairedRecordTime;
    }

    /**
     * @return the lastPairedRecordTime
     */
    public long getLastPairedRecordTime()
    {
        return myLastPairedRecordTime;
    }

    /**
     * @param lastAnalysisedRecordTime the lastAnalysisedRecordTime to set
     */
    public void setLastAnalysisedRecordTime( long lastAnalysisedRecordTime )
    {
        myLastAnalysisedRecordTime = lastAnalysisedRecordTime;
    }

    /**
     * @return the lastAnalysisedRecordTime
     */
    public long getLastAnalysisedRecordTime()
    {
        return myLastAnalysisedRecordTime;
    }

}
