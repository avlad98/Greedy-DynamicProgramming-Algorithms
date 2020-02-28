import java.util.*;
import java.io.*;

public class P1 {
    
    public static void main(String[] args) throws IOException{
        String inputFile = "p1.in";
        String outputFile = "p1.out";

        /* Deschid fisierele */
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        PrintWriter pw = new PrintWriter(new FileWriter(outputFile));

        /* Deoarece Scanner / MyScanner sunt lente am folosit parsarea mea */
        /* Citesc prima linie si obtin primul numar -> n */
        Integer n = Integer.parseInt(bf.readLine());
        /* Citesc a doua linie si o despart in token-uri pentru a putea converti
            string-urile in numere
        */
        String[] tokens = (bf.readLine()).split(" ");
        
        /* Initializez vectorul de n elemente cu 0 */
        Integer[] vector = new Integer[n];
        /* Si adaug in el elementele citite (convertite) */
        for (int i = 0; i < tokens.length; i++) {vector[i] = Integer.parseInt(tokens[i]);}

        /* Le sortez descrescator pentru a putea alege eficient numerele care aduc avantaj */
        Arrays.sort(vector, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });

        /* Sum1 = suma pentru TUZGU */
        int sum1 = 0;
        /* Sum2 = suma pentru RITZA */
        int sum2 = 0;
        /* Turn decide al cui e randul sa extraga elementul
            1 -> TUZGU
            2 -> RITZA
        */        
        int turn = 1;


        /* Parcurg vectorul ordonat descrescator si pe rand i se atribuie fiecaruia
            cate un element. Aceasta metoda asigura mereu ca cei doi vor avea suma maxima
        */
        for (int i = 0; i < n; i++) {
            if(turn == 1) sum1 += vector[i];
            else          sum2 += vector[i];
            
            /* Aici schimb tura la fiecare alegere */
            turn *= -1;
        }

        /* Diferenta dintre cele doua sume va fi rezultatul problemei */
        int difference = sum1 - sum2;

        pw.write(difference + "");
        bf.close();
        pw.close();
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