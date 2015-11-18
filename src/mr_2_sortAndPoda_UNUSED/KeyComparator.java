package mr_2_sortAndPoda_UNUSED;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Al ser las claves los valores del rinde, ordeno estos para que los valores de las lista esten ordenadas según el valor del rinde.
 *
 *
 * @author pruebahadoop
 *
 */
public class KeyComparator extends WritableComparator {
	protected KeyComparator() {
		super(DoubleWritable.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		DoubleWritable rinde1 = (DoubleWritable) w1;
		DoubleWritable rinde2 = (DoubleWritable) w2;
		//Si quisiera que ordene el "NO natural" key en forma descendiente, tengo que mulp *-1
		return rinde1.compareTo(rinde2);
	}
}
