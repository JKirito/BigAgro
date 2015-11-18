package mr_2_sortAndPoda_UNUSED;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * De esta forma agrupa todos los valores como si fuesen iguales (por eso el "0")
 *
 * Necesito que haga eso para que todas las celdas vayan a parar a la misma lista (k,List(celdas))
 * y despues eliminar outliers de esa lista (se ordena en KeyComparator.java)
 *
 * @author pruebahadoop
 *
 */
public class GroupComparator extends WritableComparator {
	protected GroupComparator() {
		super(DoubleWritable.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		return 0;
	}
}