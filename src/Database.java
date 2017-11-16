import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
		
	public static void main(String[] args) {

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
		
		for (int i=0; i<num; i++){
			new client().start();
		}
		
	}

}
