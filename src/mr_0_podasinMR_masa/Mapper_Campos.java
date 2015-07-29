package mr_0_podasinMR_masa;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CamposCSVWritable;
import entities.CoordenadaMasaWritable;
import entities.ProductoCampañaWritable;
import funciones.Csv2Values;

/**
 * parsea el input (texto) y emite k(producto, campaña), List(v(lat,long, masa))
 * @author pruebahadoop
 *
 */
public class Mapper_Campos extends Mapper<LongWritable, Text, ProductoCampañaWritable, CoordenadaMasaWritable> {

	private static final String SEPARATOR_SYMBOL = ";";
	private static final String REPLACE_SYMBOL = "\"";
	private Pattern patterReplace;

	protected void setup(Context context) throws IOException {
		patterReplace = Pattern.compile(REPLACE_SYMBOL);
	}

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		Csv2Values csvValues = new Csv2Values(ivalue.toString(), patterReplace, SEPARATOR_SYMBOL);

		CamposCSVWritable campos = csvValues.getCSVCampos();

		if(campos == null)
			return;

		ProductoCampañaWritable prodCamp = new ProductoCampañaWritable(campos.getProducto().toString(), campos.getCampaña().toString());
		CoordenadaMasaWritable C = new CoordenadaMasaWritable(campos.getLatitud(), campos.getLongitud(), campos.getMasa());
		context.write(prodCamp, C);
	}

}
