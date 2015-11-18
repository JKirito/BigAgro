package mr_2_sortAndPoda_UNUSED;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import entities.CeldaWritable;
import entities.CeldaWritableConRindeAndCantPuntosxCelda;

public class Driver_SortRendAndPoda extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new Driver_SortRendAndPoda(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		long init = new Date().getTime();
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "sort&Poda");

		System.setProperty("campaña", "2012-2013");
		System.setProperty("producto", "soja");
		System.setProperty("filtrar", "S");//S(SI)/N(NO)

		job.setJarByClass(Driver_SortRendAndPoda.class);
		job.setPartitionerClass(CeldaPartitioner.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		job.setSortComparatorClass(KeyComparator.class);

		job.setMapOutputKeyClass(DoubleWritable.class);
		job.setMapOutputValueClass(CeldaWritableConRindeAndCantPuntosxCelda.class);

		job.setOutputKeyClass(CeldaWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		// job.setInputFormatClass(TextInputFormat.class);
		// job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapperClass(Mapper_GroupCeldaRend.class);
		job.setReducerClass(Reducer_Poda.class);

		// TODO: specify input and output DIRECTORIES
		FileInputFormat.setInputPaths(job, new Path(
				"/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/podaPorRindeCelda/"+System.getProperty("producto")+"/"+System.getProperty("campaña")+"/1-grilla/part-r-00000"));
		FileOutputFormat.setOutputPath(job, new Path("/media/pruebahadoop/a0eb64d8-ab5e-41a9-8f15-1a9079769f72/javi/Documentos/Monitores rendimiento hackatón agro-datos 2014/CSV/outputs/podaPorRindeCelda/"+System.getProperty("producto")+"/"+System.getProperty("campaña")+"/2-sort&Poda"));

		if (job.waitForCompletion(true)) {
			long fin = new Date().getTime();
			System.out.println("Tardó estos segundos: " + (fin - init)/1000);
		}

		return 0;
	}
}