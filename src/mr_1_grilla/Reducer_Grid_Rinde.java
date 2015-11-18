package mr_1_grilla;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

import entities.CeldaWritable;
import entities.RindeValueCantxCelda;
import funciones.OperadorCelda;
import funciones.Utils;
/**
 * Suma las masas de los puntos que hay en una celda, y calcula el rinde para esa celda.
 * Emite k(Celda), v(rinde,cantPuntosxCelda)
 * @author pruebahadoop
 *
 */
public class Reducer_Grid_Rinde extends Reducer<CeldaWritable, DoubleWritable, CeldaWritable, RindeValueCantxCelda> {

	public void reduce(CeldaWritable _key, Iterable<DoubleWritable> values, Context context) throws IOException,
			InterruptedException {

		Double suma = 0.0;
		int cantPuntos = 0;
		for (DoubleWritable masa : values) {
			suma += Utils.redondear(masa.get(), 4);
			cantPuntos++;
		}

		double anchoCelda = Double.valueOf(System.getProperty("anchoCelda"));
		OperadorCelda opCelda = new OperadorCelda(_key, suma, anchoCelda);
		RindeValueCantxCelda rinde = new RindeValueCantxCelda(opCelda.getRinde(), cantPuntos);
		context.write(_key, rinde);
	}
}
