package mr_superficiexproductoYcampaña;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import entities.ProductoCampañaWritable;

public class Driver_SuperficieXProductoYCampaña {

	public static void main(String[] args) throws Exception {
		long init = new Date().getTime();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");

//		System.setProperty("anchoCelda", "0.0001");
//		System.setProperty("campaña", "2012-2013");
//		System.setProperty("producto", "soja");
//		System.setProperty("filtrar", "N");//S(SI)/N(NO)

		job.setJarByClass(Driver_SuperficieXProductoYCampaña.class);
		// TODO: specify a mapper
		job.setMapperClass(Mapper_superficiexProductoYCampaña.class);
		// TODO: specify a reducer
		job.setReducerClass(Reducer_superficiexProductoYCampaña.class);

		// TODO: specify output types
		job.setOutputKeyClass(ProductoCampañaWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		// TODO: specify input and output DIRECTORIES
		FileInputFormat.setInputPaths(job, new Path(
				"/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/podaPorRindeCelda/1-grillaTODOS/part-r-00000"));
		FileOutputFormat.setOutputPath(job, new Path("/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/superficieXCampañaYProducto"));

		if (job.waitForCompletion(true)) {
			long fin = new Date().getTime();
			System.out.println("Tardó estos segundos: " + (fin - init)/1000);
		}
//		if (!job.waitForCompletion(true))
//			return;
	}

}
