package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;

public class MasaProductoCampañaWritable implements WritableComparable<MasaProductoCampañaWritable> {
	//valor extra, lo uso para ordenar por masa
	private DoubleWritable masa;
	//natural key
	private ProductoCampañaWritable prodCamp;

	public MasaProductoCampañaWritable(double masa, ProductoCampañaWritable prodCamp) {
		this.masa = new DoubleWritable(masa);
		this.prodCamp = prodCamp;
	}

	public MasaProductoCampañaWritable(DoubleWritable masa, ProductoCampañaWritable prodCamp) {
		this.masa = masa;
		this.prodCamp = prodCamp;
	}

	public MasaProductoCampañaWritable(DoubleWritable masa, String producto, String campaña) {
		this.masa = masa;
		this.prodCamp = new ProductoCampañaWritable(producto, campaña);
	}

	public MasaProductoCampañaWritable() {
		this.masa = new DoubleWritable();
		this.prodCamp = new ProductoCampañaWritable();
	}

	public DoubleWritable getMasa() {
		return masa;
	}

	public ProductoCampañaWritable getProdCamp() {
		return prodCamp;
	}

	public void setMasa(DoubleWritable masa) {
		this.masa = masa;
	}

	public void setProdCamp(ProductoCampañaWritable prodCamp) {
		this.prodCamp = prodCamp;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		masa.readFields(in);
		prodCamp.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		masa.write(out);
		prodCamp.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodCamp == null) ? 0 : prodCamp.hashCode());
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
		MasaProductoCampañaWritable other = (MasaProductoCampañaWritable) obj;
		if (prodCamp == null) {
			if (other.prodCamp != null)
				return false;
		} else if (!prodCamp.equals(other.prodCamp))
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
		return prodCamp.toString() + ", " + masa.toString();
	}

	@Override
	public int compareTo(MasaProductoCampañaWritable cw) {
		int compareValue = this.prodCamp.compareTo(cw.prodCamp);
		if (compareValue == 0) {
			compareValue = this.masa.compareTo(cw.masa);
		}
		return compareValue;
	}

}
