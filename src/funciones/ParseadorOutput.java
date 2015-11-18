package funciones;

import entities.CeldaWritable;
import entities.CeldaWritableConRinde;
import entities.CeldaWritableConRindeAndCantPuntosxCelda;

/**
 * convierte el output (texto) de un reduce al objeto que necesito. Ejemplo:
 * 2009-2010,soja,-365115,-620933 2010.8090665312488,4
 * (campaña)(producto)(columna,fila) (((rinde))),(cantPuntosxCelda)
 */
public class ParseadorOutput {
	private String linea;

	public ParseadorOutput(String linea) {
		this.linea = linea;
	}

	// keys [0] {campaña,producto,c,f}
	// value [1] {rinde,cantPuntosxCelda}
	public CeldaWritableConRindeAndCantPuntosxCelda getCeldaWritableConRindeAndCantPuntosxCelda() {
		Integer cantPuntosxCelda = Integer.valueOf(this.linea.split("\t")[1].split(",")[1]);
		CeldaWritableConRinde celda = getCeldaWritableConRinde();
		CeldaWritableConRindeAndCantPuntosxCelda cr = new CeldaWritableConRindeAndCantPuntosxCelda(celda.getCelda(),
				celda.getRinde().get(), cantPuntosxCelda);
		return cr;
	}

	// keys [0] {campaña,producto,c,f}
	// value [1] {rinde,cantPuntosxCelda}
	public CeldaWritableConRinde getCeldaWritableConRinde() {
		String[] key = this.linea.split("\t")[0].split(",");
		String rinde = this.linea.split("\t")[1].split(",")[0];
		Integer columna = Integer.valueOf(key[2]);
		Integer fila = Integer.valueOf(key[3]);
		String producto = key[1];
		String campaña = key[0];
		CeldaWritable celda = new CeldaWritable(fila, columna, producto, campaña);
		CeldaWritableConRinde cr = new CeldaWritableConRinde(celda, Double.valueOf(rinde));
		return cr;
	}

}
