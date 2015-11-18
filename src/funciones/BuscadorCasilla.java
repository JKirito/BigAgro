package funciones;

import entities.CoordenadaWritable;

public class BuscadorCasilla {
	double anchoCelda;
	// double latitudMin;
	// double longitudMin;
	double latitud;
	double longitud;

	public BuscadorCasilla(CoordenadaWritable coordenada, double anchoCelda) {
		this.latitud = Utils.redondear(coordenada.getLatitud().get(),4);
		this.longitud = Utils.redondear(coordenada.getLongitud().get(), 4);
		this.anchoCelda = anchoCelda;
	}

	public Integer getFila() {
//		if(Integer.valueOf(Utils.redondear(this.latitud / this.anchoCelda, 4).intValue()).toString().substring(0,6))
//			System.out.println("uffff: "+this.latitud);
		return Integer.valueOf(Utils.redondearAEntero(this.latitud / this.anchoCelda, 4).toString().substring(0,7));
	}

	public Integer getColumna() {
		return Integer.valueOf(Utils.redondearAEntero(this.longitud / this.anchoCelda, 4).toString().substring(0,7));
	}

}
