package funciones;

public class Utils {

	/**
	 *
	 * @param n: número decimal a redondear
	 * @param cantDecimales: números que van a quedar despues de la ","
	 *
	 * @return n pero con cantDecimales despues de las ","
	 */
	public static Double redondear(Double n, int cantDecimales) {
		return Math.round(n * Math.pow(10, cantDecimales)) / Math.pow(10, cantDecimales);
	}

}
