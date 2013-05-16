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


public class IndexerReducer
  extends Reducer<Text, Text, Text, Text> {
    public static class PLComparator implements Comparator<String> {
        public int compare(String doc1, String doc2) {
        	int id1 = 0;
        	int id2 = 0;
        	int idx = 0;
        	while (doc1.charAt(idx)<='9' && doc1.charAt(idx)>='0') {
        		id1 = id1*10 + doc1.charAt(idx) -'0';
        		idx ++;
        	}
        	idx = 0;
        	while (doc2.charAt(idx)<='9' && doc2.charAt(idx)>='0') {
        		id2 = id2*10 + doc2.charAt(idx) -'0';
        		idx ++;
        	}
            if (id1<id2) return -1;
            return 1;
        }
    }
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {

    Vector<String> pls = new Vector<String>();
    
    for (Text value : values) {
    	String[] vs = value.toString().split("[\t]");
    	for (String s: vs) {
    		if (s.length()!=0)
    		pls.add(s);
    	}
    }
    
    Collections.sort(pls, new PLComparator());
    
    StringBuffer sb = new StringBuffer();
    sb.append(pls.size());
    for (String pl : pls) {
    	sb.append("\t");
    	sb.append(pl);
    }
    
    context.write(key, new Text(sb.toString()));
  }
}
