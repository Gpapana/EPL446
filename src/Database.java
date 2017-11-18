import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.Menu;


public class Database/* extends Menu*/ {
/*
	Thread[] clients =null;

	@Override
	public int doButtonAction() {

		String inputfile;
		String name;

		switch (option_update) {
		case 1: {

			System.out.println("The Update is  Immediate");
			break;
		}
		case 2: {

			System.out.println("The Update is  Deferred");
			break;
		}
		default: {
			System.out.println("ERROR.You didnt Choose");
		}
		}

		switch (option_type) {
		case 1: {

			System.out.println("The Type is  Wound and wait");
			break;
		}
		case 2: {

			System.out.println("The Type is  Wait And Die");
			break;
		}
		case 3: {

			System.out.println("The Type is  Cautious Waiting");
			break;
		}
		default: {
			System.out.println("ERROR.You didnt Choose");
		}
		}

		clients = new Thread[num_of_clients];
		int numofCl=0;
		for (int i = 0; i < num_of_clients; i++) {
			inputfile = "inputs/input" + (i + 1) + ".txt";
			name = "Client" + (i + 1);
			try {
				/*
				clients[i] = new Thread(new client(inputfile, name, (i + 1)));
				clients[i].start();
				numofCl++;
				 
			} catch (Exception e) {
				System.out.println("There is no file with name " + inputfile);
			}
		}


		System.out.println("The Number of Clients are " + numofCl);
		/*Thread t1 = new Thread(new TCPServer("Server", option_update, option_type,numofCl));
		t1.setDaemon(true);
		t1.start();
		 
		return 0;
	}

*/

	static private char[] A = new char[256];
	static private char[] B = new char[256];
	static private char[] C = new char[256];
	static private char[] D = new char[256];
	static private char[] E = new char[256];
	static private char[] F = new char[256];
	static private char[] G = new char[256];
	static private char[] H = new char[256];
	static private char[] I = new char[256];
	static private char[] J = new char[256];
	static private char[] K = new char[256];
	static private char[] L = new char[256];
	static private char[] M = new char[256];
	static private char[] N = new char[256];
	static private char[] O = new char[256];
	static private char[] P = new char[256];
	static private char[] Q = new char[256];
	static private char[] R = new char[256];
	static private char[] S = new char[256];
	static private char[] T = new char[256];
	static private char[] U = new char[256];
	static private char[] V = new char[256];
	static private char[] W = new char[256];
	static private char[] X = new char[256];
	static private char[] Y = new char[256];
	static private char[] Z = new char[256];

	static String[][] actions;
	static int threadNum=0;
	static int timestamp=0;

	public static ArrayList<loginput> log = new ArrayList<loginput>();

	static void printLog (){
		for (int i=0; i<log.size(); i++){
			loginput l = log.get(i);

			System.out.print(l.id+" client"+l.transactionNum+" TS="+l.TS+" ");
			if (l.command=='B' || l.command=='C' || l.command=='A'){
				System.out.println(l.command);
			}
			if (l.command=='D'){
				System.out.println(l.command+" "+l.document);
			}
			if (l.command=='W' || l.command=='R'){
				System.out.println(l.command+" "+l.document+" "+l.position+" "+l.value);
			}
		}
	}
	public static void main(String[] args) {

		for (int i=0; i<A.length; i++){
			A[i]=' ';
			B[i]=' ';
			C[i]=' ';
			D[i]=' ';
			E[i]=' ';
			F[i]=' ';
			G[i]=' ';
			H[i]=' ';
			I[i]=' ';
			J[i]=' ';
			K[i]=' ';
			L[i]=' ';
			M[i]=' ';
			N[i]=' ';
			O[i]=' ';
			P[i]=' ';
			Q[i]=' ';
			R[i]=' ';
			S[i]=' ';
			T[i]=' ';
			U[i]=' ';
			V[i]=' ';
			W[i]=' ';
			X[i]=' ';
			Y[i]=' ';
			Z[i]=' ';
		}

		Scanner sc=new Scanner(System.in);
		System.out.println("Number of cliends?");
		int num=sc.nextInt();
		sc.close();

		actions = new String[num][20];

		for (int i=0; i<num; i++){
			File fl = new File ("cliends/client"+(i+1)+".txt");
			try {
				Scanner sc1=new Scanner(fl);
				int j=0;
				while (sc1.hasNextLine()){
					actions[i][j] = sc1.nextLine();
					j++;
				}
				sc1.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		// SOURTES

		for (int i=0; i<num; i++){
			new client().start();
		}
		
		for (int i=0; i<num; i++){
			c[i].join();
		}

		printLog();
		System.out.println("FROM REPOSITORY!!!!!!!!!!!!!!!!!!!");
	}

}
