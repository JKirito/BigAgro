package test;

import java.util.HashSet;
import java.util.Set;

import uk.me.jstott.jcoord.LatLng;
import entities.CeldaWritable;
import funciones.OperadorCelda;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CeldaWritable c = new CeldaWritable(-620933, -365115, "soja", "2009-2010");

		OperadorCelda op = new OperadorCelda(c, 12.23, 0.0001);
		double lat1 = -36.503611;
		double lat2 = -36.503576;
		double long1 = -62.084938;
		double long2 = -62.084903;

		double d = distance(lat1, lat2, long1, long2, 0, 0);

		System.out.println("dist: "+ d);

		LatLng latlng1 = new LatLng(lat1, long1);
		LatLng latlng2 = new LatLng(lat2, long2);
		double dist = latlng1.distance(latlng2);
		System.out.println("dist2: "+dist);
		System.out.println("dist2: "+dist/1000.0);
		System.out.println("LLL "+latlng1.toString());

		System.out.println("UTM: "+latlng1.toUTMRef());
		double ll = latlng1.toUTMRef().getEasting() - latlng2.toUTMRef().getEasting();
		double ll2 = latlng1.toUTMRef().getNorthing() - latlng2.toUTMRef().getNorthing();
		System.out.println(ll);
		System.out.println(ll2);
//		latlng1.toWGS84();
//		System.out.println("UTM: "+latlng1.toUTMRef());
//		latlng1.toWGS84();
//		System.out.println("UTM: "+latlng1.toUTMRef());

	}
	private static Set<String> distancias = new HashSet<String>();
	public static void addDistancias(String dist){
		if(!distancias.contains(dist)) {
			distancias.add(dist);
			System.out.println(dist);
		}
	}

	/*
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 *
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 */
	private static double distance(double lat1, double lat2, double lon1, double lon2,
	        double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;
	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    return Math.sqrt(distance);
	}

	private static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}

}
