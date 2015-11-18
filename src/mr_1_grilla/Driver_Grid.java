package mr_1_grilla;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import entities.CeldaWritable;

public class Driver_Grid {

	public static void main(String[] args) throws Exception {
		long init = new Date().getTime();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");

		System.setProperty("anchoCelda", "0.0001");
//		System.setProperty("campaña", "2012-2013");
//		System.setProperty("producto", "soja");
//		System.setProperty("filtrar", "S");//S(SI)/N(NO)

		job.setJarByClass(Driver_Grid.class);
		// TODO: specify a mapper
		job.setMapperClass(Mapper_Grid.class);
		// TODO: specify a reducer
		job.setReducerClass(Reducer_Grid_Rinde.class);

		// TODO: specify output types
		job.setOutputKeyClass(CeldaWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		// TODO: specify input and output DIRECTORIES
		FileInputFormat.setInputPaths(job, new Path(
				"/media/pruebahadoop/06668E5C668E4C7D/Ubuntu_Share/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/poster/PODA_sortSinMR/part-r-00000"));
		FileOutputFormat.setOutputPath(job, new Path("/media/pruebahadoop/06668E5C668E4C7D/Ubuntu_Share/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/poster/Grilla/"));

		if (job.waitForCompletion(true)) {
			long fin = new Date().getTime();
			System.out.println("Tardó estos segundos: " + (fin - init)/1000);
		}
//		if (!job.waitForCompletion(true))
//			return;
	}

}
