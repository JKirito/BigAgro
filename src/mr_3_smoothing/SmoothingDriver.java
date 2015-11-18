package mr_3_smoothing;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import entities.CeldaWritable;

public class SmoothingDriver {

	public static void main(String[] args) throws Exception {
		long init = new Date().getTime();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");

		job.setJarByClass(SmoothingDriver.class);
		// TODO: specify a mapper
		job.setMapperClass(SmoothingMapper.class);
		// TODO: specify a reducer
		job.setReducerClass(SmoothingReducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(CeldaWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		// TODO: specify input and output DIRECTORIES
		FileInputFormat.setInputPaths(job, new Path(
				"/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/serie1/Grilla/part-r-00000"));
		FileOutputFormat.setOutputPath(job, new Path("/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/serie1/SmoothingGaussian"));

		if (job.waitForCompletion(true)) {
			long fin = new Date().getTime();
			System.out.println("Tardó estos segundos: " + (fin - init)/1000);
		}
	}

}
