package mr_2_sortAndPoda_UNUSED;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import entities.CeldaWritableConRindeAndCantPuntosxCelda;
import funciones.ParseadorOutput;

/**
 * Segunda fase: el input de este job es el output del anterior (texto). Acá lo parseo: armo de nuevo la Celda y rinde.
 * La idea es ordenar todas las celdas segun el valor de su rinde, y a eso eliminar outliers (en el reduce)
 * Necesito que ordene todas las celdas en una única lista según el valor del rinde. Para eso tiene que agrupar las celdas
 * como si fuesen todas iguales (ver GroupComparator.java)
 *
 * @author pruebahadoop
 */
public class Mapper_GroupCeldaRend extends Mapper<LongWritable, Text, DoubleWritable, CeldaWritableConRindeAndCantPuntosxCelda> {


	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		final String linea = ivalue.toString();
		ParseadorOutput p = new ParseadorOutput(linea);
		CeldaWritableConRindeAndCantPuntosxCelda cr = p.getCeldaWritableConRindeAndCantPuntosxCelda();

		// TODO: Sólo me interea un producto y campaña particular (debería
		// tenerlo en un parámetro)
//		String campaña = System.getProperty("campaña");
//		String producto = System.getProperty("producto");
//		String filtrar = System.getProperty("filtrar");
//		if(filtrar.equals("S") && (!campaña.equals(cr.getCelda().getCampaña().toString()) || !producto.equals(cr.getCelda().getProducto().toString()))) {return;}

		context.write(cr.getRindeValueCantxCelda().getRinde(), cr);
	}

}
