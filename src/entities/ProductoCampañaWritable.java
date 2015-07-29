package entities;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class ProductoCampañaWritable implements WritableComparable<ProductoCampañaWritable> {
	private Text producto;
	private Text campaña;

	public ProductoCampañaWritable() {
		this.producto = new Text();
		this.campaña = new Text();
	}


	public ProductoCampañaWritable(String producto, String campaña) {
		this.producto = new Text(producto);
		this.campaña = new Text(campaña);
	}

	public Text getProducto() {
		return producto;
	}

	public Text getCampaña() {
		return campaña;
	}

	public void setProducto(Text producto) {
		this.producto = producto;
	}

	public void setCampaña(Text campaña) {
		this.campaña = campaña;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		producto.readFields(in);
		campaña.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		producto.write(out);
		campaña.write(out);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaña == null) ? 0 : campaña.hashCode());
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
		ProductoCampañaWritable other = (ProductoCampañaWritable) obj;
		if (campaña == null) {
			if (other.campaña != null)
				return false;
		} else if (!campaña.equals(other.campaña))
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
		return campaña + "," + producto;
	}

	@Override
	public int compareTo(ProductoCampañaWritable cw) {
		int cmp = campaña.compareTo(cw.campaña);
		if (cmp != 0) {
			return cmp;
		}
		return producto.compareTo(cw.producto);
	}

}
