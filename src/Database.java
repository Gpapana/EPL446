import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Database {
	
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
			loginput l = log.get[i];
			
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
		
		printLog();
		System.out.println("FROM REPOSITORY!!!!!!!!!!!!!!!!!!!");
	}

}
