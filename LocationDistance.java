// Justin Chan
// July 18th, 2015
// LocationDistance.java
// This class serves as an object for the Haversine method so that it may be 
// used multiple times efficiently

package src.justinchanlab2;

import java.lang.Math;

public class LocationDistance
{
	final int EARTH_RADIUS = 3959;	
	double dlatitude1, dlongitude1, dlatitude2, dlongitude2;

	public LocationDistance(double dla1, double dlo1, double dla2, double dlo2)
	{
		dlatitude1 = dla1;
		dlongitude1 = dlo1;
		dlatitude2 = dla2;
		dlongitude2 = dlo2;
	}
	
	public double Haversine()
	{
		double dLatitude = Math.toRadians( dlatitude2-dlatitude1 );
		double dLongitude = Math.toRadians( dlongitude2-dlongitude1 );
		double nA = Math.pow ( Math.sin( dLatitude/2.0 ), 2.0 ) + Math.cos( Math.toRadians(dlatitude1) ) * Math.cos( Math.toRadians(dlatitude2) ) * Math.pow ( Math.sin( dLongitude/2.0), 2.0 );
		double nC = 2.0 * Math.atan2( Math.sqrt(nA), Math.sqrt( 1.0 - nA ));
		double distance = EARTH_RADIUS * nC;
		return distance; 
	}
	
        /*
            Test method. Please Ignore.
        */
	public static void main (String[] args)
	{
		LocationDistance ld = new LocationDistance(35, 10, 40, 10);
        LocationDistance ld2 = new LocationDistance(35, 0, 35.1, 0);
		System.out.println(ld.Haversine());
        System.out.println(ld2.Haversine());
        LocationDistance ld3 = new LocationDistance(0, -107, 0, -104.5);
        System.out.println(ld3.Haversine());
	}
}
