package mr_0_podasinMR_masa;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import entities.CamposCSVWritable;
import entities.CoordenadaMasaWritable;
import entities.ProductoCampañaWritable;
import funciones.OperadorRend;

/**
 * Se hace la poda por los valores de la masa. Se emite los mismos valores que el csv original
 * para poder usar ese como input del mr_1_grilla
 *
 * @author pruebahadoop
 *
 */
public class Reducer_PodaMasa extends
		Reducer<ProductoCampañaWritable, CoordenadaMasaWritable, CamposCSVWritable, NullWritable> {

	public void reduce(ProductoCampañaWritable _key, Iterable<CoordenadaMasaWritable> values, Context context)
			throws IOException, InterruptedException {

		OperadorRend op = new OperadorRend(values, true);
		@SuppressWarnings("unchecked")
		List<CamposCSVWritable> camposCSV = (List<CamposCSVWritable>) op.getListWithoutOutliers(15.0, 90.0);

//		System.out.println("REDUCER DE KEY ("+camposCSV.size()+")"+": "+_key.toString());
		for (CamposCSVWritable C : camposCSV) {
			//Tengo que agregar el producto y campaña de la clave (no lo agrego en el value para disminuir cantidad de datos)
			C.setProducto(_key.getProducto());
			C.setCampaña(_key.getCampaña());
			context.write(C, NullWritable.get());
		}

	}
}
