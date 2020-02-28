import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class P2 {
    
    public static void main(String[] args) throws IOException{
        String inputFile = "p2.in";
        String outputFile = "p2.out";


        /* Deschid fisierele */
        MyScanner sc = new MyScanner(new FileInputStream(inputFile));
        PrintWriter pw = new PrintWriter(new FileWriter(outputFile));

        /* Initializez lista care va contine valorile din fisier */
        ArrayList<Integer> list = new ArrayList<>();

        /* Citesc numarul de elemente, respectiv numarul de eliminari 
            de pe prima linie 
        */
        int n = sc.nextInt();
        int k = sc.nextInt() + 1;
        /* Citesc cele n numere de pe a doua linie si le inserez in lista */
        for (int i = 0; i < n; i++) {list.add(sc.nextInt());}

        /* Le sortez descrescator pentru ca indiferent de ce as elimina
            aceasta problema se va rezuma la problema 1
        */
        list.sort((a, b) -> (b - a));

        /* Initializez o matrice in care voi retine rezultate partiale */
        /* Pe linii se afla numerele sortate descrescator (pe indecsi de la 0 -> n),
            iar coloanele vor reprezenta eliminarile (eliminari de la 0 -> k elemente)
        */
        int[][] matrix = new int[n][k];

        /* Initializez coltul din stanga sus cu primul element din lista
            (adica cel mai mare) si aplic algoritmul meu
        */
        matrix[0][0] = list.get(0);

        /* Calculez prima coloana (cazul de baza) corespunzatoare diferentelor
            maxime fara nicio eliminare (k = 0). Pe runda para adun (pentru TUZGU),
            Iar pe runda impara scad (pentru RITZA)
        */
        for(int i = 1; i < n; i++) {
            if((i % 2) == 0) {matrix[i][0] = matrix[i - 1][0] + list.get(i);}
            else {matrix[i][0] = matrix[i - 1][0] - list.get(i);}
        }

        /* Parcurg fiecare linie si pentru fiecare eliminare (de la 0 -> k - 1 ) 
            calculez diferentele maxime pentru fiecare jucator (in functie de paritatea)
            indecsilor
        */
        for(int i = 1; i < n; i++) {
            for (int j = 1; j < i + 1 && j < k; j++) {
                /* Cele doua cazuri sunt momentele in care am facut deja eliminarea
                    unui element sau cazul celalalt in care eliminarea nu s-a facut
                */
                int caz1 = matrix[i - 1][j - 1];
                int caz2 = matrix[i - 1][j];
                
                if((Math.abs(i - j) % 2 == 0)) {caz2 += list.get(i);}
                else                           {caz2 -= list.get(i);}

                /* Completez in matrice diferenta cea mai buna dintre ambele cazuri
                    (daca e mai bine sa elimin pe cel curent sau deja am eliminat ceva
                    in favoarea jucatorului curent)
                */
                matrix[i][j] = Math.max(caz1, caz2);
            }
        }

        /* Rezultatul problemei se gaseste pe ultima linie si ultima coloana */
        pw.write(matrix[n - 1][k - 1] + "");

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
    
    public static void showMatrix(int[][] matrix) {
        System.out.println("***********MATRIX**********");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("***************************");
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