package mr_0_podasinMR_masa;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import entities.CamposCSVWritable;
import entities.CoordenadaMasaWritable;
import entities.ProductoCampañaWritable;

public class Driver_PodaMasa extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new Driver_PodaMasa(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		long init = new Date().getTime();
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "poda masa");

//		System.setProperty("campaña", "2009-2010");
//		System.setProperty("producto", "soja");

		job.setJarByClass(Driver_PodaMasa.class);

		job.setMapOutputKeyClass(ProductoCampañaWritable.class);
		job.setMapOutputValueClass(CoordenadaMasaWritable.class);

		job.setOutputKeyClass(CamposCSVWritable.class);
		job.setOutputValueClass(NullWritable.class);

		// job.setInputFormatClass(TextInputFormat.class);
		// job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapperClass(Mapper_Campos.class);
		job.setReducerClass(Reducer_PodaMasa.class);

		FileInputFormat.setInputPaths(job, new Path(
				"/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/input/serie-unica.csv"));
		FileOutputFormat.setOutputPath(job, new Path("/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/PODA_sortSinMR/"));

		if (job.waitForCompletion(true)) {
			long fin = new Date().getTime();
			System.out.println("Tardó estos segundos: " + (fin - init)/1000);
		}
		return 0;
	}
}