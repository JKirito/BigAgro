package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class CamposCSVWritable implements WritableComparable<CamposCSVWritable> {
	private DoubleWritable latitud;
	private DoubleWritable longitud;
	private Text campaña;
	private Text producto;
	private DoubleWritable masa;


	public CamposCSVWritable(Double latitud, Double longitud, String campaña, String producto,
			Double masa) {
		super();
		this.latitud = new DoubleWritable(latitud);
		this.longitud = new DoubleWritable(longitud);
		this.campaña = new Text(campaña);
		this.producto = new Text(producto);
		this.masa = new DoubleWritable(masa);
	}

	public CamposCSVWritable() {
		latitud = new DoubleWritable();
		longitud = new DoubleWritable();
		campaña = new Text();
		producto = new Text();
		masa = new DoubleWritable();
	}

	public DoubleWritable getLatitud() {
		return latitud;
	}

	public DoubleWritable getLongitud() {
		return longitud;
	}

	public Text getCampaña() {
		return campaña;
	}

	public Text getProducto() {
		return producto;
	}

	public DoubleWritable getMasa() {
		return masa;
	}

	public void setLatitud(DoubleWritable latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(DoubleWritable longitud) {
		this.longitud = longitud;
	}

	public void setCampaña(Text campaña) {
		this.campaña = campaña;
	}

	public void setProducto(Text producto) {
		this.producto = producto;
	}

	public void setMasa(DoubleWritable masa) {
		this.masa = masa;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		latitud.readFields(in);
		longitud.readFields(in);
		campaña.readFields(in);
		producto.readFields(in);
		masa.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		latitud.write(out);
		longitud.write(out);
		campaña.write(out);
		producto.write(out);
		masa.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaña == null) ? 0 : campaña.hashCode());
		result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
		result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
		result = prime * result + ((masa == null) ? 0 : masa.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CamposCSVWritable other = (CamposCSVWritable) obj;
		if (campaña == null) {
			if (other.campaña != null)
				return false;
		} else if (!campaña.equals(other.campaña))
			return false;
		if (latitud == null) {
			if (other.latitud != null)
				return false;
		} else if (!latitud.equals(other.latitud))
			return false;
		if (longitud == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		if (masa == null) {
			if (other.masa != null)
				return false;
		} else if (!masa.equals(other.masa))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return producto + ";" + campaña + ";" + longitud + ";" + latitud + ";" + masa;
	}

	@Override
	public int compareTo(CamposCSVWritable cw) {
		//TODO: creo que no lo uso!!
		return 0;
	}

}
