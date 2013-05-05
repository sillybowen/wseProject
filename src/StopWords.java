import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StopWords {
	Set<String> set = new HashSet<String>();
	public StopWords () {
		BufferedReader br = null;
		try {			 
			String sCurrentLine;
			br = new BufferedReader(new FileReader("./files/stopwords"));

			while ((sCurrentLine = br.readLine()) != null) {
				set.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public boolean contain (String s) {
		return set.contains(s);
	}
}
