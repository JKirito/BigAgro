package mr_0_poda_masa;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import entities.CamposCSVWritable;
import entities.CoordenadaMasaWritable;
import entities.MasaProductoCampañaWritable;
import funciones.OperadorRend;

/**
 * Se hace la poda por los valores de la masa. Se emite los mismos valores que el csv original
 * para poder usar ese como input del mr_1_grilla
 *
 * @author pruebahadoop
 *
 */
public class Reducer_PodaMasa extends
		Reducer<MasaProductoCampañaWritable, CoordenadaMasaWritable, CamposCSVWritable, NullWritable> {

	public void reduce(MasaProductoCampañaWritable _key, Iterable<CoordenadaMasaWritable> values, Context context)
			throws IOException, InterruptedException {

		OperadorRend op = new OperadorRend(values, false);
		@SuppressWarnings("unchecked")
		List<CamposCSVWritable> camposCSV = (List<CamposCSVWritable>) op.getListWithoutOutliers(15.0, 90.0);

//		System.out.println("REDUCER DE KEY ("+camposCSV.size()+")"+": "+_key.toString());
		for (CamposCSVWritable C : camposCSV) {
			//Tengo que agregar el producto y campaña de la clave (no lo agrego en el value para disminuir cantidad de datos)
			C.setProducto(_key.getProdCamp().getProducto());
			C.setCampaña(_key.getProdCamp().getCampaña());
			context.write(C, NullWritable.get());
		}

	}
}
