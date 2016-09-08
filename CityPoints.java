// Justin Chan
// July 18th, 2015
// CityPoints.java
/*
    PLEASE READ!!!
    This is the class you should run to get the full functionality of this 
    project. This class sets up a JFrame and a JPanel so that a GUI will pop up
    containing the points of cities and lines of Coordinates.xml. 
*/

package src.justinchanlab2;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class CityPoints 
{
    JFrame cityframe;
    CityPanel citypanel;
    
    public void createFrame()
    {
        cityframe = new JFrame("City Points");
        cityframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        citypanel = new CityPanel();
        
        cityframe.getContentPane().add(citypanel);
        
        citypanel.setPreferredSize(new Dimension(1000, 1000));
        
        /*
            The size is set up according to the outermost points of latitude and
            longitude in Coordinates.xml. The scale allows for a small amount of
            room on the edges.
        */
        cityframe.setSize(701, 365);
        cityframe.setVisible(true);
        cityframe.setResizable(false);
    }
    
    public static void main (String[] args)
    {
        CityPoints run = new CityPoints();
        run.createFrame();
    }
}

