import java.io.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
public class HTMLParser {
	
    public static String stemm(String b) {
        String a=b;
        Stemmer s = new Stemmer();
        for (int i = 0;i<b.length();i++)
            if (b.charAt(i)<'0'||b.charAt(i)>'9') {
                s.add(b.charAt(i));
            }
            else return b;
        s.stem();
        a = s.toString();
        return a;
    }

    public static Vector<String> parse(String fileContent) {
        String current = "";
        int bracketCount = 0;
        char c;
        int script = 0;
        Vector<String> ret = new Vector<String>();
        int r;
        for (int i = 0; i<fileContent.length();i++)  {
            c = fileContent.charAt(i);
            if (bracketCount>0) {
                if (current.length()<=10)
                    current+=c;
                if (c=='>')
                    bracketCount --;
                if (c=='<')
                    bracketCount ++;
                if (bracketCount ==0&&current.length()>0) {
                    if (current.charAt(0)=='<') {
                        if (current.length()>=7 && current.substring(0,7).equals("<script")){
                            script ++;
                        }
                        else if (current.equals("</script>")) {
                            script --;
                        }
                    current = "";
                    }
                }
            }
            else if ((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0')) {
                if (script==0) {
                    current = current+c;
                }
            }
            else {
                if (script==0 && current.length()>0 && bracketCount == 0) {
                	String tmp = stemm(current);
                	ret.add(tmp);
                }
                current = "";
                if (c=='<') {
                    bracketCount++;
                    current="<";
                }
            }
        }
        return ret;
    }
}
