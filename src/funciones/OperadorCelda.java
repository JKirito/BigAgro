package funciones;

import entities.CeldaWritable;

public class OperadorCelda {

	private CeldaWritable celda;
	private final int superficieHectarea = 10000;
	private double masaSuma;
	private double anchoCelda;

	// Si necesito obtener el rinde por celda
	public OperadorCelda(CeldaWritable celda, double masaSuma, double anchoCelda) {
		this.celda = celda;
		this.masaSuma = masaSuma;
		this.anchoCelda = anchoCelda;
	}

	// Si necesito la superficie por celda
	public OperadorCelda(CeldaWritable celda, double anchoCelda) {
		this.celda = celda;
		this.anchoCelda = anchoCelda;
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

	public double getSuperficie() {
		double x1 = celda.getColumna().get() * this.anchoCelda;
		double y1 = celda.getFila().get() * this.anchoCelda;
		double x2 = (celda.getColumna().get() + 1) * this.anchoCelda;

		// (x,y) --> (x+1, y)
		double base = distance(y1, y1, x1, x2, 0, 0);
		base = Utils.redondear(base, 4);

		x1 = celda.getColumna().get() * this.anchoCelda;
		y1 = celda.getFila().get() * this.anchoCelda;
		double y2 = (celda.getFila().get() + 1) * this.anchoCelda;

		// (x,y) --> (x, y+1)
		double altura = distance(y1, y2, x1, x1, 0, 0);
		altura = Utils.redondear(altura, 4);

		return Utils.redondear(base * altura, 4);
	}

	public Double getRinde() {
		double sup = getSuperficie();
		return Utils.redondear((this.masaSuma / sup) * this.superficieHectarea, 4);
	}

}
