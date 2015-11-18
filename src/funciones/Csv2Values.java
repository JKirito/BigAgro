package funciones;

import java.util.regex.Pattern;

import entities.CamposCSVWritable;
import enums.FieldsRows;

public class Csv2Values {
	final String[] values;

	public Csv2Values(String linea, Pattern patterReplace, String separatorSymbol) {
		 values = patterReplace.matcher(linea).replaceAll("").split(separatorSymbol);
	}

	/**
	 *
	 * @return null si hay algún campo que falta
	 */
	public CamposCSVWritable getCSVCampos() {
		String latitudCampo;
		String longitudCampo;
		String productoCampo;
		String masaCampo;
		String campañaCampo;

		try {
			latitudCampo = values[FieldsRows.LATITUD.getPos()];
			longitudCampo = values[FieldsRows.LONGITUD.getPos()];
			productoCampo = values[FieldsRows.PRODUCTO.getPos()];
			masaCampo = values[FieldsRows.MASA.getPos()];
			campañaCampo = values[FieldsRows.CAMPAÑA.getPos()];
		} catch (Exception e) {
			return null;
		}

		// Salteo el header
		if (productoCampo.equalsIgnoreCase(FieldsRows.PRODUCTO.name())) {
			return null;
		}

		return new CamposCSVWritable(Double.valueOf(latitudCampo), Double.valueOf(longitudCampo), campañaCampo, productoCampo, Double.valueOf(masaCampo));
	}

}
