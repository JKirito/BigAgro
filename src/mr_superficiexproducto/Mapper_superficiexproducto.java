package mr_superficiexproducto;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CeldaWritableConRindeAndCantPuntosxCelda;
import funciones.OperadorCelda;
import funciones.ParseadorOutput;

/**
 * Segunda fase: el input de este job es el output de un job anterior, donde
 * cada celda "pertenece" a un producto. Entonces para cada producto, emito la
 * sup en m2 de cada celda con ESE producto. Emito (Producto, superficieCelda). En el reduce sumo las superficies
 *
 * @author pruebahadoop
 */
public class Mapper_superficiexproducto extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	private double anchoCelda = 0.0001;

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		final String linea = ivalue.toString();
		ParseadorOutput p = new ParseadorOutput(linea);
		CeldaWritableConRindeAndCantPuntosxCelda cr = null;
		try {
			cr = p.getCeldaWritableConRindeAndCantPuntosxCelda();
		} catch (Exception e) {
			System.out.println("LINEA: '"+linea+"'");
			e.printStackTrace();
		}


		// Sólo me interea el producto
		Text producto = cr.getCelda().getProdCamp().getProducto();
		OperadorCelda oc = new OperadorCelda(cr.getCelda(), anchoCelda);
		DoubleWritable sup = new DoubleWritable(oc.getSuperficie());

		context.write(producto, sup);
	}

}
