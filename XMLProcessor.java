// Justin Chan
// July 18th, 2015
// XMLProcessor.java
// This class will utilize the XMLReader class to read an XML file. The data
// this class will extract from the file are lists of latitudes and longitudes.

package src.justinchanlab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLProcessor {
    private ArrayList<Double> pointset;
    
    public XMLProcessor()
    {
        pointset = new ArrayList<Double>();
    }
    
    /*
        This routine opens an takes the ArrayList created from the loadXML()
        routine and extracts the latitudal or longitudal element text of each
        node and puts those values in an ArrayList.
    */
    public ArrayList<Double> extractPoints(String type)
    {
        ArrayList<Node> nlist = loadXML();
        pointset = new ArrayList<Double>();
        
        for (int i = 0; i < nlist.size(); i++)
        {
            String data = ((Element) nlist.get(i)).getElementsByTagName(type).item(0).getTextContent();
            pointset.add(Double.parseDouble(data));
        }
        return pointset;
    }
    
    /*
        This routine reads Coordinates.xml and extracts the nodes "Location"
        and places all nodes in an ArrayList.
    */
    public ArrayList<Node> loadXML()
    {
        XMLReader reader = new XMLReader();
        Document doc = null;
        
        try
        {
            doc = reader.ReadXML(System.getProperty("user.dir") + File.separator + "Coordinates.xml");
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        
        NodeList nlist = reader.GetNodes(doc, "Location");
        ArrayList<Node> alist = new ArrayList<Node>();
        
        for (int i = 0; i < nlist.getLength(); i++) 
        {
            Node n = nlist.item(i);
            alist.add(n);
        }
        
        return alist;
    }
    
    /*
        Test method. Please Ignore.
    */
    public static void main(String[] args)
    {
        XMLProcessor xml = new XMLProcessor();
        ArrayList<Double> a = xml.extractPoints("Longitude");
        for (Double d : a)
        {
            System.out.println(d);
        }
        System.out.println(a.size());
        ArrayList<Double> b = xml.extractPoints("Latitude");
        for (Double o : b)
        {
            System.out.println(o);
        }
        System.out.println(b.size());
    }
}
