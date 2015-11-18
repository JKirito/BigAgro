package mr_0_poda_masa;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import entities.MasaProductoCampañaWritable;

/**
 * A partitioner ensures only that one reducer receives all the records for a
 * year; it doesn’t change the fact that the reducer groups by key within the
 * partition.
 *
 * @author pruebahadoop
 *
 */
public class ProductoCampañaPartitioner extends Partitioner<MasaProductoCampañaWritable, NullWritable> {

//	@Override
//	public int getPartition(DoubleWritable key, DoubleWritable value, int numPartitions) {
//		// multiply by 127 to perform some mixing
//		return Math.abs(1 * 127) % numPartitions;
//	}

	//TODO: no se está llamando! Debe ser porque hay un único reducer (o cero)
	@Override
	public int getPartition(MasaProductoCampañaWritable arg0, NullWritable arg1, int numPartitions) {
		System.out.println("PARTITIONER: "+arg0.getProdCamp());
		return arg0.getProdCamp().hashCode() % numPartitions;
	}
}
