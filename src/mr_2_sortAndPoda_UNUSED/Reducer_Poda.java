package mr_2_sortAndPoda_UNUSED;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import entities.CeldaWritable;
import entities.CeldaWritableConRindeAndCantPuntosxCelda;
import entities.RindeValueCantxCelda;
import funciones.OperadorRend;

public class Reducer_Poda extends
		Reducer<DoubleWritable, CeldaWritableConRindeAndCantPuntosxCelda, CeldaWritable, RindeValueCantxCelda> {

	public void reduce(DoubleWritable _key, Iterable<CeldaWritableConRindeAndCantPuntosxCelda> values, Context context)
			throws IOException, InterruptedException {

		List<CeldaWritableConRindeAndCantPuntosxCelda> celdasConRinde = new ArrayList<CeldaWritableConRindeAndCantPuntosxCelda>();

		// TODO: esta forma de copia, copia los N valores iguales al ultimo objeto :/
		// List<CoordenadaConRendWritable> list = Lists.newArrayList(values);

		// Tengo que crear una nueva instacia de cada objeto para crear una nueva CeldaWritableConRinde
		// y agregarlo a una nueva lista
		for (CeldaWritableConRindeAndCantPuntosxCelda C : values) {
			CeldaWritable c = new CeldaWritable();
			c.setFila(new IntWritable(C.getCelda().getFila().get()));
			c.setColumna(new IntWritable(C.getCelda().getColumna().get()));
			c.getProdCamp().setProducto(new Text(C.getCelda().getProdCamp().getProducto().toString()));
			c.getProdCamp().setCampaña(new Text(C.getCelda().getProdCamp().getCampaña().toString()));
			DoubleWritable rinde = C.getRindeValueCantxCelda().getRinde();
			Integer cantPuntosxCelda = C.getRindeValueCantxCelda().getPuntosxCelda().get();
			CeldaWritableConRindeAndCantPuntosxCelda celdaRinde = new CeldaWritableConRindeAndCantPuntosxCelda(c, rinde.get(), cantPuntosxCelda);
			celdasConRinde.add(celdaRinde);
		}

		OperadorRend oper = new OperadorRend(celdasConRinde);
		List<CeldaWritableConRindeAndCantPuntosxCelda> celdas = (List<CeldaWritableConRindeAndCantPuntosxCelda>) oper.getListWithoutOutliers(15.0, 90.0);
		for (CeldaWritableConRindeAndCantPuntosxCelda C : celdas) {
			context.write(C.getCelda(), C.getRindeValueCantxCelda());
		}

	}
}
