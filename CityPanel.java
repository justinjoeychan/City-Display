/*
    Justin Chan
    July 18th, 2015
    CityPanel.java

    This class sets up the panel that contains the points and lines required for
    this assignment.
*/

package src.justinchanlab2;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class CityPanel extends JPanel {
    private ArrayList<Double> latitudeset;  // contains the double values of the latitudal points in Coordinates.xml
    private ArrayList<Double> longitudeset; // contains the double values of teh longitdal points in Coordinates.xml
    
    public CityPanel()
    {
        latitudeset = new ArrayList<Double>();
        longitudeset = new ArrayList<Double>();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setLoad();
        
        setBackground(Color.WHITE);
        
        // Drawing the lines
        g.setColor(Color.GREEN);
        double dist = 0;
        double min = 0;
        int mindex = -1;
        for (int i = 0; i < latitudeset.size() - 1; i++)
        {
            dist = 0;
            min = 0;
            mindex = -1;
            for (int j = i + 1; j < latitudeset.size(); j++)
            {
                dist = distanceCalc(latitudeset.get(i), longitudeset.get(i), latitudeset.get(j), longitudeset.get(j));
                if (mindex == -1)
                {
                    mindex = j;
                    min = dist;
                }
                else if (min > dist)
                {
                    mindex = j;
                    min = dist;
                }
            }
            g.drawLine(distanceConvert(latitudeset.get(i), "Latitude") * 2, distanceConvert(longitudeset.get(i), "Longitude") * 2, distanceConvert(latitudeset.get(mindex), "Latitude") * 2, distanceConvert(longitudeset.get(mindex), "Longitude") * 2);
        }
        
        // Drawing the points
        g.setColor(Color.RED);
        for (int i = 0; i < latitudeset.size(); i++)
        {
            g.fillOval(distanceConvert(latitudeset.get(i), "Latitude") * 2 - 2, distanceConvert(longitudeset.get(i), "Longitude") * 2 - 2, 4, 4);
        }
    }
    
    /*
        This method uses the XMLProcessor class to set up the lists of 
        laditudal and longitudal values.
    */
    public void setLoad()
    {
        XMLProcessor xmlp = new XMLProcessor();
        latitudeset = xmlp.extractPoints("Latitude");
        longitudeset = xmlp.extractPoints("Longitude");
    }
    
    /*
        This routine is uses the Haversine method to determine position of a
        given latitudal or longitudal point.
    */
    public int distanceConvert(double unit, String type)
    {
        if (type.equals("Latitude"))
        {
            LocationDistance ld = new LocationDistance(34.9, 0, unit, 0);
            return (int) (ld.Haversine());
        }
        else if (type.equals("Longitude"))
        {
            LocationDistance ld = new LocationDistance(0, -107, 0, unit);
            return (int) (ld.Haversine());
        }
        return -1;
    }
    
    /*
        This method creates a LocationDistance object and calculates the
        distance of the input points.
    */
    public double distanceCalc(double d1, double d2, double d3, double d4)
    {
        LocationDistance distcalc = new LocationDistance(d1, d2, d3, d4);
        return distcalc.Haversine();
    }
}
