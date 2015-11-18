package funciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.CamposCSVWritable;
import entities.CeldaWritableConRindeAndCantPuntosxCelda;
import entities.CoordenadaMasaWritable;

public class OperadorRend {

	private List<CeldaWritableConRindeAndCantPuntosxCelda> sortCeldasRindeValues;
	private List<CamposCSVWritable> sortCamposCSV;

	// private List<Double> valuesWithoutOutliers;

	public OperadorRend(List<CeldaWritableConRindeAndCantPuntosxCelda> celdasRinde) {
		this.sortCeldasRindeValues = celdasRinde;
	}

//	public OperadorRend(Iterable<CamposCSVWritable> celdasCSV) {
//		this.sortCamposCSV = new ArrayList<CamposCSVWritable>();
//		for(CamposCSVWritable C : celdasCSV) {
//			double latitud = C.getLatitud().get();
//			double longitud = C.getLongitud().get();
//			double masa = C.getMasa().get();
//			String campaña = C.getCampaña().toString();
//			String producto = C.getProducto().toString();
//			CamposCSVWritable campo = new CamposCSVWritable(latitud, longitud, campaña, producto, masa);
//			this.sortCamposCSV.add(campo);
//		}
//	}

	public OperadorRend(Iterable<CoordenadaMasaWritable> coordenadaMasa, boolean sortMasa) {
		this.sortCamposCSV = new ArrayList<CamposCSVWritable>();
		for(CoordenadaMasaWritable C : coordenadaMasa) {
			double latitud = C.getCoordenada().getLatitud().get();
			double longitud = C.getCoordenada().getLongitud().get();
			double masa = C.getMasa().get();
			CamposCSVWritable campo = new CamposCSVWritable(latitud, longitud, "", "", masa);
			this.sortCamposCSV.add(campo);
		}
		if(sortMasa)
			Collections.sort(this.sortCamposCSV, new Comparator<CamposCSVWritable>() {
				  public int compare(CamposCSVWritable c1,CamposCSVWritable c2){
                      return c1.getMasa().compareTo(c2.getMasa());
                }});
	}


	/**
	 * Devuelve una nueva lista, más adelante eliminar los outliers sin crear
	 * una nueva lista para ahorrar RAM
	 *
	 * @param percentilMenor ES %: 10% debe ser 10. Incluye al valor "x" (percentil menor)
	 * @param percentilMayor ES %: 90% debe ser 90. Luego de eliminar el percentil menor, dejo HASTA el percentil mayor (inclusive)
	 * @return una NUEVA lista eliminando los percentiles de los parametros (de la lista que recibe el constructor)
	 */
//	public List<CeldaWritableConRinde> getListWithoutOutliers(double percentilMenor, double percentilMayor) {
//
//		// resto 1 porque las listas comienzan en la pos cero.
//		int posPercentilMenor = cantEliminar(this.sortCeldasRindeValues.size(), percentilMenor) - 1;
//
//		// Quito a la lista original el percentil menor
//		this.sortCeldasRindeValues = this.sortCeldasRindeValues.subList(posPercentilMenor, sortCeldasRindeValues.size());
//
//		// Quito el percentil mayor a la lista a la cual ya quite el percentil menor
//		int posPercentilMayor = cantEliminar(this.sortCeldasRindeValues.size(), percentilMayor) - 1;
//		this.sortCeldasRindeValues = this.sortCeldasRindeValues.subList(0, posPercentilMayor + 1);
//
//		return this.sortCeldasRindeValues;
//	}

	/**
	 * Devuelve una nueva lista, más adelante eliminar los outliers sin crear
	 * una nueva lista para ahorrar RAM
	 *
	 * @param percentilMenor ES %: 10% debe ser 10. Incluye al valor "x" (percentil menor)
	 * @param percentilMayor ES %: 90% debe ser 90. Luego de eliminar el percentil menor, dejo HASTA el percentil mayor (inclusive)
	 * @return una NUEVA lista eliminando los percentiles de los parametros (de la lista que recibe el constructor)
	 */
	public List<?> getListWithoutOutliers(double percentilMenor, double percentilMayor, List<?> valores) {

		// resto 1 porque las listas comienzan en la pos cero.
		int posPercentilMenor = cantEliminar(valores.size(), percentilMenor) - 1;

		// Quito a la lista original el percentil menor
		valores = valores.subList(posPercentilMenor, valores.size());

		// Quito el percentil mayor a la lista a la cual ya quite el percentil menor
		int posPercentilMayor = cantEliminar(valores.size(), percentilMayor) - 1;
		valores = valores.subList(0, posPercentilMayor + 1);

		return valores;
	}

	public List<?> getListWithoutOutliers(double percentilMenor, double percentilMayor)
	{
		if(this.sortCamposCSV != null)
		{
			return this.getListWithoutOutliers(percentilMenor, percentilMayor, sortCamposCSV);
		}
		else if(this.sortCeldasRindeValues != null)
		{
			return this.getListWithoutOutliers(percentilMenor, percentilMayor, sortCeldasRindeValues);
		}
		return null;
	}

	/**
	 *
	 * @param size
	 *            : cantidad de valores
	 * @param percentil
	 *            : valor que se eliminará (ES %: 10% debe ser 10)
	 * @return
	 */
	private int cantEliminar(int size, double percentilPorcentaje) {
		Double percentil = new Double((size * percentilPorcentaje) / 100);
		return new Double(Math.ceil(percentil)).intValue();
	}

}
