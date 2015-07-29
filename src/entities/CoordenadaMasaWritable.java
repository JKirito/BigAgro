package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;

public class CoordenadaMasaWritable implements WritableComparable<CoordenadaMasaWritable> {
	private CoordenadaWritable coordenada;
	private DoubleWritable masa;

	public CoordenadaMasaWritable(CoordenadaWritable coordenada, Double masa) {
		this.masa = new DoubleWritable(masa);
		this.coordenada = coordenada;
	}

	public CoordenadaMasaWritable(CoordenadaWritable coordenada, DoubleWritable masa) {
		this.coordenada = coordenada;
		this.masa = masa;
	}

	public CoordenadaMasaWritable(DoubleWritable latitud, DoubleWritable longitud, DoubleWritable masa) {
		this.coordenada = new CoordenadaWritable(latitud, longitud);
		this.masa = masa;
	}

	public CoordenadaMasaWritable() {
		this.masa = new DoubleWritable();
		this.coordenada = new CoordenadaWritable();
	}

	public CoordenadaWritable getCoordenada() {
		return coordenada;
	}

	public DoubleWritable getMasa() {
		return masa;
	}

	public void setCoordenada(CoordenadaWritable coordenada) {
		this.coordenada = coordenada;
	}

	public void setMasa(DoubleWritable masa) {
		this.masa = masa;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		masa.readFields(in);
		coordenada.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		masa.write(out);
		coordenada.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenada == null) ? 0 : coordenada.hashCode());
		result = prime * result + ((masa == null) ? 0 : masa.hashCode());
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
		CoordenadaMasaWritable other = (CoordenadaMasaWritable) obj;
		if (coordenada == null) {
			if (other.coordenada != null)
				return false;
		} else if (!coordenada.equals(other.coordenada))
			return false;
		if (masa == null) {
			if (other.masa != null)
				return false;
		} else if (!masa.equals(other.masa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return coordenada.toString() + ", " + masa.toString();
	}

	//Solo quiero que compare las masas para ordenarlas (no vea producto, campaña)
	@Override
	public int compareTo(CoordenadaMasaWritable cw) {
		return masa.compareTo(cw.masa);
	}

}
