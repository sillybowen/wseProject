import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IndexerReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {

    int count = 0;
    for (IntWritable value : values) {
        count += value.get();
    }
    context.write(key, new IntWritable(count));

    FileWriter fstream = new FileWriter("./tmp/"+key.toString());
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(Integer.toString(count)+"\n");
    out.close();
  }
}
