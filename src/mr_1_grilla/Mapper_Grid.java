package mr_1_grilla;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CamposCSVWritable;
import entities.CeldaWritable;
import entities.CoordenadaWritable;
import funciones.BuscadorCasilla;
import funciones.Csv2Values;

/**
 * parsea el input (texto) y emite k(producto,campaña,columna,fila), v(masa)
 *
 * @author pruebahadoop
 *
 */
public class Mapper_Grid extends Mapper<LongWritable, Text, CeldaWritable, DoubleWritable> {

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

		// TODO: Sólo me interea un producto y campaña particular (debería tenerlo en un parámetro)
//		String campaña = System.getProperty("campaña");
//		String producto = System.getProperty("producto");
//		String filtrar = System.getProperty("filtrar");
//		if(filtrar.equals("S") && (!campaña.equals(campañaCampo) || !producto.equals(productoCampo))){return;}

		CoordenadaWritable coordenada = new CoordenadaWritable(campos.getLatitud(), campos.getLongitud());
		double anchoCelda = Double.valueOf(System.getProperty("anchoCelda"));
		BuscadorCasilla buscadorC = new BuscadorCasilla(coordenada, anchoCelda);
		CeldaWritable celda = new CeldaWritable(buscadorC.getFila(), buscadorC.getColumna(), campos.getProducto().toString(), campos.getCampaña().toString());

		context.write(celda, campos.getMasa());
	}

}
