package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;

public class CeldaWritableConRinde implements WritableComparable<CeldaWritableConRinde> {
	private CeldaWritable celda;
	private DoubleWritable rinde;

	public CeldaWritableConRinde(CeldaWritable celda, double rinde) {
		this.celda = celda;
		this.rinde = new DoubleWritable(rinde);
	}

	public CeldaWritableConRinde() {
		celda = new CeldaWritable();
		rinde = new DoubleWritable();
	}

	public CeldaWritable getCelda() {
		return celda;
	}

	public DoubleWritable getRinde() {
		return rinde;
	}

	public void setCelda(CeldaWritable celda) {
		this.celda = celda;
	}

	public void setRinde(DoubleWritable rinde) {
		this.rinde = rinde;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		celda.readFields(in);
		rinde.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		celda.write(out);
		rinde.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celda == null) ? 0 : celda.hashCode());
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
		CeldaWritableConRinde other = (CeldaWritableConRinde) obj;
		if (celda == null) {
			if (other.celda != null)
				return false;
		} else if (!celda.equals(other.celda))
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
		return celda.toString() + "\t" + rinde.toString();
	}

	@Override
	public int compareTo(CeldaWritableConRinde cw) {
		int cmp = celda.compareTo(cw.celda);
		if (cmp != 0) {
			return cmp;
		}
		return rinde.compareTo(cw.rinde);
	}

}
