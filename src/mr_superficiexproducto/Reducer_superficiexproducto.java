package mr_superficiexproducto;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Suma las superficies x producto. Emite (Producto, superficieTotal)
 *
 * @author pruebahadoop
 *
 */
public class Reducer_superficiexproducto extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	public void reduce(Text _key, Iterable<DoubleWritable> values, Context context) throws IOException,
			InterruptedException {

		Double sumaSuperficie = 0.0;
		for (DoubleWritable masa : values) {
			sumaSuperficie += masa.get();
		}

		context.write(_key, new DoubleWritable(sumaSuperficie));
	}
}
