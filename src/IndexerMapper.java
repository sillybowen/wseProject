import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;


public class IndexerMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {


  private static final int MISSING = 9999;


  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString();
    	

    Map<String, Integer> wordcount =  readFile(line);
    
    Iterator it = wordcount.entrySet().iterator(); 
    String word;
    Integer count;
    while (it.hasNext()) {
    	Map.Entry pairs = (Map.Entry)it.next();      
    	word = (String) pairs.getKey();
    	count = (Integer) pairs.getValue();
    	context.write( new Text(word), new IntWritable(count));
    }
  }
  public Map<String,Integer> readFile(String path) throws IOException {
	  Map<String,Integer> ret = new HashMap<String,Integer>();
	  File file = new File(path);
      BufferedReader reader = new BufferedReader( new FileReader (file));
      String         line = null;
      while( ( line = reader.readLine() ) != null ) {
    	  if (ret.containsKey(line)) {
    		  ret.put(line, ret.get(line)+1);
    	  } else {
    		  ret.put(line, 1);
    	  }
      }
      return ret;
  }
}


