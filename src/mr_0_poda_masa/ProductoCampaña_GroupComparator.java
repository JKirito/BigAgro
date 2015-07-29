package mr_0_poda_masa;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import entities.MasaProductoCampañaWritable;

/**
 * Once the data reaches a reducer, all data is grouped by key. Since we have a
 * composite key, we need to make sure records are grouped solely by the natural
 * key. This is accomplished by writing a custom GroupPartitioner.
 *
 * @author pruebahadoop
 *
 */
public class ProductoCampaña_GroupComparator extends WritableComparator {
	protected ProductoCampaña_GroupComparator() {
		super(MasaProductoCampañaWritable.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		MasaProductoCampañaWritable prodCamp1 = (MasaProductoCampañaWritable) w1;
		MasaProductoCampañaWritable prodCamp2 = (MasaProductoCampañaWritable) w2;
		return prodCamp1.getProdCamp().compareTo(prodCamp2.getProdCamp());
	}
}