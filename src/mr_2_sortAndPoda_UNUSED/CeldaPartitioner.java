package mr_2_sortAndPoda_UNUSED;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * A partitioner ensures only that one reducer receives all the records for a
 * year; it doesn’t change the fact that the reducer groups by key within the
 * partition.
 *
 * @author pruebahadoop
 *
 */
public class CeldaPartitioner extends Partitioner<DoubleWritable, DoubleWritable> {

	@Override
	public int getPartition(DoubleWritable key, DoubleWritable value, int numPartitions) {
		// multiply by 127 to perform some mixing
		return Math.abs(1 * 127) % numPartitions;
	}
}
