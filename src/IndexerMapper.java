import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Vector;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;


public class IndexerMapper
  extends Mapper<LongWritable, Text, Text, Text> {


  private static final int MISSING = 9999;

  public void output(Vector<String> tokens,String name) throws IOException {
	  File f = new File(name);
	  FileWriter fstream = new FileWriter("./tmp/"+f.getName());
	  BufferedWriter out = new BufferedWriter(fstream);
	  for (int i = 0; i<tokens.size();i++)
		  out.write(tokens.get(i)+"\n");
	  out.close();
  }
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString();
    File f = new File(line);
    String fileName = f.getName();
    Vector<String> tokens = HTMLParser.parse(line);
    Map<String, PostList> wordcount =  count(tokens,fileName);
    //output(tokens,FileName);
    Iterator it = wordcount.entrySet().iterator(); 
    String word;
    PostList count;
    while (it.hasNext()) {
    	Map.Entry pairs = (Map.Entry)it.next();      
    	word = (String) pairs.getKey();
    	count = (PostList) pairs.getValue();
    	context.write( new Text(word), new Text(count.toString()));
    }
  }
  public Map<String,Integer> loadFileToID() {
		Map<String,Integer> ret = new HashMap<String,Integer>();
		try {
			BufferedReader br = null;
			String sCurrentLine;
			br = new BufferedReader(new FileReader("./tmp/tmp"));
			int count = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				File f = new File(sCurrentLine);
				ret.put(f.getName(), count);
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	  return ret;
  }
  public Map<String,PostList> count (Vector<String> tokens,String fileName) {
	Map<String,Integer> fileToid = loadFileToID();
	Map<String,PostList> ret = new HashMap<String,PostList>();
	for (int i = 0; i<tokens.size();i++) {
		PostList pl;
		if (!ret.containsKey(tokens.get(i))) {
			pl = new PostList(fileName,fileToid.get(fileName));
			ret.put(tokens.get(i), pl);
		} else {
			pl = ret.get(tokens.get(i));
		}
		pl.addPost(i);
	}
	return ret;
  }
}


