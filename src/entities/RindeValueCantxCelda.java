package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class RindeValueCantxCelda implements WritableComparable<RindeValueCantxCelda> {
	private IntWritable puntosxCelda;
	private DoubleWritable rinde;

	public RindeValueCantxCelda(Double rinde, Integer puntosxCelda) {
		this.puntosxCelda = new IntWritable(puntosxCelda);
		this.rinde = new DoubleWritable(rinde);
	}

	public RindeValueCantxCelda() {
		puntosxCelda = new IntWritable();
		rinde = new DoubleWritable();
	}

	public IntWritable getPuntosxCelda() {
		return puntosxCelda;
	}

	public DoubleWritable getRinde() {
		return rinde;
	}

	public void setPuntosxCelda(IntWritable puntosxCelda) {
		this.puntosxCelda = puntosxCelda;
	}

	public void setRinde(DoubleWritable rinde) {
		this.rinde = rinde;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		puntosxCelda.readFields(in);
		rinde.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		puntosxCelda.write(out);
		rinde.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((puntosxCelda == null) ? 0 : puntosxCelda.hashCode());
		result = prime * result + ((rinde == null) ? 0 : rinde.hashCode());
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
		RindeValueCantxCelda other = (RindeValueCantxCelda) obj;
		if (puntosxCelda == null) {
			if (other.puntosxCelda != null)
				return false;
		} else if (!puntosxCelda.equals(other.puntosxCelda))
			return false;
		if (rinde == null) {
			if (other.rinde != null)
				return false;
		} else if (!rinde.equals(other.rinde))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return rinde + "," + puntosxCelda;
	}

	@Override
	public int compareTo(RindeValueCantxCelda cw) {
		int cmp = puntosxCelda.compareTo(cw.puntosxCelda);
		if (cmp != 0) {
			return cmp;
		}
		return rinde.compareTo(cw.rinde);
	}

}
