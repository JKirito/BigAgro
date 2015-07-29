package mr_superficiexproductoYcampaña;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

import entities.ProductoCampañaWritable;

/**
 * Suma las superficies x producto. Emite (Producto, superficieTotal)
 *
 * @author pruebahadoop
 *
 */
public class Reducer_superficiexProductoYCampaña extends Reducer<ProductoCampañaWritable, DoubleWritable, ProductoCampañaWritable, DoubleWritable> {

	public void reduce(ProductoCampañaWritable _key, Iterable<DoubleWritable> values, Context context) throws IOException,
			InterruptedException {

		Double sumaSuperficie = 0.0;
		for (DoubleWritable masa : values) {
			sumaSuperficie += masa.get();
		}

		context.write(_key, new DoubleWritable(sumaSuperficie));
	}
}
