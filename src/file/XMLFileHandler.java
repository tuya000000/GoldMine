/**
 * 
 */
package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.DataRoot;
import model.TradeRecord;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author tuya
 */
public class XMLFileHandler
{

    /**
	 * 
	 */
    public XMLFileHandler()
    {
        // TODO Auto-generated constructor stub
    }

    public static void writeSampleXml( String fileName )
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.newDocument();
            Element root = doc.createElement( "root" );
            Element trList = doc.createElement( "TradeRecordList" );
            root.appendChild( trList );
            doc.appendChild( root );
            for( TradeRecord tr : DataRoot.inst().getTradeRecords() )
            {
                trList.appendChild( createTradeRecordNode( tr, doc ) );
            }
            FileOutputStream outStream = new FileOutputStream( fileName );
            OutputStreamWriter outWriter = new OutputStreamWriter( outStream );
            callWriteXMLFile( doc, outWriter, "UTF-8" );
            outWriter.close();
            outStream.close();

        }
        catch( ParserConfigurationException e )
        {

            e.printStackTrace();
        }
        catch( FileNotFoundException e )
        {

            e.printStackTrace();
        }
        catch( IOException e )
        {

            e.printStackTrace();
        }
    }

    private static Element createTradeRecordNode( TradeRecord tr, Document doc )
    {
        Element trNode = doc.createElement( "TradeRecord" );
        Date date = new Date( tr.getTime() );
        trNode.setAttribute( "Time", date.toLocaleString() );
        trNode.setAttribute( "Prise", String.valueOf( tr.getPrise() ) );
        trNode.setAttribute( "Amount", String.valueOf( tr.getAmount() ) );
        return trNode;
    }

    private static void callWriteXMLFile( Document doc, OutputStreamWriter w, String encoding )
    {
        try
        {
            Source source = new DOMSource( doc );
            Result ret = new StreamResult( w );
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty( OutputKeys.ENCODING, encoding );
            xformer.transform( source, ret );
        }
        catch( TransformerConfigurationException e )
        {
            e.printStackTrace();
        }
        catch( TransformerException e )
        {
            e.printStackTrace();
        }
    }

    public static void readXml( String fileName )
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.parse( new File( fileName ) );
            Element elmtInfo = doc.getDocumentElement();
            NodeList nodes = elmtInfo.getChildNodes();
            int m = 1;
            for( int i = 0; i < nodes.getLength(); i++ )
            {
                Node result = nodes.item( i );
                if( result.getNodeType() == Node.ELEMENT_NODE )
                {
                    NodeList ns = result.getChildNodes();

                    for( int j = 0; j < ns.getLength(); j++ )
                    {
                        Node record = ns.item( j );

                        if( record.getNodeType() == Node.ELEMENT_NODE )
                        {
                            System.out.println( m + ": " + record.getTextContent() );
                            m++;
                        }
                    }
                }
            }
        }
        catch( ParserConfigurationException e )
        {
            e.printStackTrace();
        }
        catch( SAXException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }
}
