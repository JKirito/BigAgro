package mr_3_smoothing;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CeldaWritable;
import entities.CeldaWritableConRinde;
import funciones.GaussianSmoothing;
import funciones.IFilter;
import funciones.ParseadorOutput;

public class SmoothingMapper extends Mapper<LongWritable, Text, CeldaWritable, DoubleWritable> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		final String linea = ivalue.toString();

		ParseadorOutput p = new ParseadorOutput(linea);
		CeldaWritableConRinde cr = p.getCeldaWritableConRinde();

		//SI quiero filtrar x algun producto-campaña
//		if(!cr.getCelda().getProdCamp().getCampaña().toString().equals("2003-2004") && !cr.getCelda().getProdCamp().getProducto().toString().equals("cebada"))
//			return;

		CeldaWritable celda = cr.getCelda();
		IFilter smoothing = new GaussianSmoothing(celda, cr.getRinde().get());
		List<CeldaWritableConRinde> celdasSuavizadas = smoothing.getSmoothingCells();

		for(CeldaWritableConRinde C : celdasSuavizadas)
		{
			context.write(C.getCelda(), C.getRinde());
		}

	}

}
