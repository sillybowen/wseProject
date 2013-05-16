import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class IndexerCombiner
  extends Reducer<Text, Text, Text, Text> {
    public static class PLComparator implements Comparator<PostList> {
        public int compare(PostList doc1, PostList doc2) {
            if (doc1._id<doc2._id) return -1;
            return 1;
        }
    }
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {

    Vector<PostList> pls = new Vector<PostList>();
    
    String count ="";
    for (Text value : values) {
    	if (count.length()!=0)
    		count+="\t";
    	count+=value.toString();
    }
    
    context.write(key, new Text(count));
  }
}
