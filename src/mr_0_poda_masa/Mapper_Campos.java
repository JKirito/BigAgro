package mr_0_poda_masa;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CamposCSVWritable;
import entities.CoordenadaMasaWritable;
import entities.MasaProductoCampañaWritable;
import funciones.Csv2Values;

/**
 * parsea el input (texto) y emite k(masa, producto, campaña), List(v(lat,long, masa))
 * --en el value, en el reduce, le agrego producto-campaña (no lo agregao ahora para mandar menos datos)
 * @author pruebahadoop
 *
 */
public class Mapper_Campos extends Mapper<LongWritable, Text, MasaProductoCampañaWritable, CoordenadaMasaWritable> {

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

		MasaProductoCampañaWritable masaProdCamp = new MasaProductoCampañaWritable(campos.getMasa(), campos.getProducto().toString(), campos.getCampaña().toString());
		CoordenadaMasaWritable C = new CoordenadaMasaWritable(campos.getLatitud(), campos.getLongitud(), campos.getMasa());
		context.write(masaProdCamp, C);
	}

}
