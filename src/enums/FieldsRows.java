package enums;

public enum FieldsRows {
	PRODUCTO(0),
	CAMPAÑA (1),
	LATITUD(2),
	LONGITUD(3),
	MASA(4);

	private int pos;

	FieldsRows(int pos){
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}

}
