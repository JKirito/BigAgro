package mr_3_smoothing;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

import entities.CeldaWritable;
import funciones.GaussianSmoothing;

public class SmoothingReducer extends Reducer<CeldaWritable, DoubleWritable, CeldaWritable, DoubleWritable> {

	public void reduce(CeldaWritable _key, Iterable<DoubleWritable> values, Context context) throws IOException,
			InterruptedException {

		double total = 0.0;
//		boolean hayRindeNegativo = false;
		for (DoubleWritable rend : values)
		{
			// Si no hay un rinde negativo, entonces esta celda sólo existe por sus vecinos, NO la emito!
			// Esto es SÓLO PARA NO RELLENAR AGUJEROS!!!
			//El rinde negativo lo emite una celda para si misma!
//			if (rend.get() < 0)
//			{
//				hayRindeNegativo = true;
//				rend = new DoubleWritable(rend.get() * -1);
//			}
			total += rend.get();
		}

//		if(!hayRindeNegativo)
//		{
//			return;
//		}

		double mediaRinde = total / GaussianSmoothing.KERNEL_VALUES_SUM;

		context.write(_key, new DoubleWritable(mediaRinde));
	}
}
