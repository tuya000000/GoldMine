/**
 * 
 */
package ui;

/**
 * @author tuya
 */
public class TradeSummary
{
    private int myTotalBuyAmount;

    private int myTotalSellAmount;

    private int myCarryAmount;

    private double myTotalBuyMoney;

    private double myTotalSellMoney;

    private double myCarryCost;

    private double myAvgCarryPrise;

    /**
     * @param totalBuyAmount the totalBuyAmount to set
     */
    public void setTotalBuyAmount( int totalBuyAmount )
    {
        myTotalBuyAmount = totalBuyAmount;
    }

    /**
     * @return the totalBuyAmount
     */
    public int getTotalBuyAmount()
    {
        return myTotalBuyAmount;
    }

    /**
     * @param totalSellAmount the totalSellAmount to set
     */
    public void setTotalSellAmount( int totalSellAmount )
    {
        myTotalSellAmount = totalSellAmount;
    }

    /**
     * @return the totalSellAmount
     */
    public int getTotalSellAmount()
    {
        return myTotalSellAmount;
    }

    /**
     * @param carryAmount the carryAmount to set
     */
    public void setCarryAmount( int carryAmount )
    {
        myCarryAmount = carryAmount;
    }

    /**
     * @return the carryAmount
     */
    public int getCarryAmount()
    {
        return myCarryAmount;
    }

    /**
     * @param totalBuyMoney the totalBuyMoney to set
     */
    public void setTotalBuyMoney( double totalBuyMoney )
    {
        myTotalBuyMoney = totalBuyMoney;
    }

    /**
     * @return the totalBuyMoney
     */
    public double getTotalBuyMoney()
    {
        return myTotalBuyMoney;
    }

    /**
     * @param totalSellMoney the totalSellMoney to set
     */
    public void setTotalSellMoney( double totalSellMoney )
    {
        myTotalSellMoney = totalSellMoney;
    }

    /**
     * @return the totalSellMoney
     */
    public double getTotalSellMoney()
    {
        return myTotalSellMoney;
    }

    /**
     * @param carryCost the carryCost to set
     */
    public void setCarryCost( double carryCost )
    {
        myCarryCost = carryCost;
    }

    /**
     * @return the carryCost
     */
    public double getCarryCost()
    {
        return myCarryCost;
    }

    /**
     * @param avgCarryPrise the avgCarryPrise to set
     */
    public void setAvgCarryPrise( double avgCarryPrise )
    {
        myAvgCarryPrise = avgCarryPrise;
    }

    /**
     * @return the avgCarryPrise
     */
    public double getAvgCarryPrise()
    {
        return myAvgCarryPrise;
    }

}
