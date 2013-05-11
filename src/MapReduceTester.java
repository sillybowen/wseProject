import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;
//import edu.nyu.cs.cs2580.SearchEngine.Options;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class MapReduceTester {
  public static void loadFilePath() throws IOException{
      final File folder = new File("./data/wiki");
      //final File folder = new File("./data");

      FileWriter fstream = new FileWriter("./tmp/tmp");
      BufferedWriter out = new BufferedWriter(fstream);

      for (final File fileEntry : folder.listFiles()) {
          if (!fileEntry.isDirectory()) {
              out.write(fileEntry.getPath()+"\n");
          }
      }
      out.close();
  }
  public static void main(String[] args) throws Exception {
	  loadFilePath();
     Job job = new Job();
     job.setJarByClass(MapReduceTester.class);
     job.setJobName("Indexer");


     FileInputFormat.addInputPath(job, new Path("./tmp/tmp"));
     FileOutputFormat.setOutputPath(job, new Path("./output"));

     job.setMapperClass(IndexerMapper.class);
     job.setReducerClass(IndexerReducer.class);

     job.setOutputKeyClass(Text.class);
     job.setOutputValueClass(Text.class);

     System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
