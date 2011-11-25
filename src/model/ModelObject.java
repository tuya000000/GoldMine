/**
 * 
 */
package model;

/**
 * @author tuya
 */
public class ModelObject
{

    private String myObjectId;

    /**
	 * 
	 */
    public ModelObject()
    {

    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId( String objectId )
    {
        myObjectId = objectId;
    }

    /**
     * @return the objectId
     */
    public String getObjectId()
    {
        return myObjectId;
    }
}
