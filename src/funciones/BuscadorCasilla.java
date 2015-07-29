package funciones;

import entities.CoordenadaWritable;

public class BuscadorCasilla {
	double anchoCelda;
	// double latitudMin;
	// double longitudMin;
	double latitud;
	double longitud;

	public BuscadorCasilla(CoordenadaWritable coordenada, double anchoCelda) {
		this.latitud = coordenada.getLatitud().get();
		this.longitud = coordenada.getLongitud().get();
		this.anchoCelda = anchoCelda;
	}

	public Integer getFila() {
		return (int) (this.latitud / this.anchoCelda);
	}

	public Integer getColumna() {
		return (int) (this.longitud / this.anchoCelda);
	}

}
