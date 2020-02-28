import java.util.*;
import java.io.*;

public class P4 {
    
    public static void main(String[] args) {
        
    }

}

class MyScanner {
	BufferedReader br;
	StringTokenizer st;
		 
	public MyScanner(FileInputStream f) {
		br = new BufferedReader(new InputStreamReader(f));
	}

	public String next() throws IOException {
		while (st == null || !st.hasMoreElements())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}
		 
	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	public void close() throws IOException {
		br.close();
	}
}