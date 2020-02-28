import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class P3 {
    
    public static void main(String[] args) throws IOException {
        String inputFile = "p3.in";
        String outputFile = "p3.out";

        /* Deschid fisierele */
        Scanner sc = new Scanner(new File(inputFile));
        PrintWriter pw = new PrintWriter(new FileWriter(outputFile));

        /* Initializez lista care va contine valorile din fisier */
        ArrayList<Long> list = new ArrayList<>();

        /* Citesc numarul de elemente de pe prima linie */
        int n = sc.nextInt();

        /* Apoi introduc cele n elemente de pe a doua linie in lista */
        for (int i = 0; i < n; i++) {list.add(sc.nextLong());}

        /* Initializez vectorul in care voi retine rezultatele partiale */
        long[] partiale = new long[n];


        long caz1;
        long caz2;

        /* Calculez diferentele maxime pentru distante diferite intre capatul
            din stanga si dreapta
        */
        for (int stanga = (n - 1); stanga >= 0; stanga--) {
            /* Copiez prima data in vector elementul de pe pozitia stanga pentru a avea 
                o valoare din care sa incep
            */
            partiale[stanga] = list.get(stanga);

            /* Calculez cele doua cazuri cand aleg stanga sau dreapta*/
            for (int dreapta = (stanga + 1); dreapta < n; dreapta++) {
                caz1 = list.get(stanga) - partiale[dreapta];
                caz2 = list.get(dreapta) - partiale[dreapta - 1];

                /* Diferenta maxima va fi stocata in vector */
                partiale[dreapta] = Math.max(caz1, caz2);
            }
        }

        /* Rezultatul se afla pe ultima pozitie din vector deoarece acolo se va
            "strange" rezultatul corect calculat pe baza rezultatelor partiale
            anterioare
        */
        pw.write(partiale[n - 1] + "");

        sc.close();
        pw.close();

    }
    
    public static void showList(ArrayList<Integer> list) {
        System.out.println("=============ARRAY=============");
        for (int element : list)
            System.out.print(element + "\t");
        System.out.println();
        System.out.println("==============================");

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
    
    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

	public void close() throws IOException {
		br.close();
	}
}