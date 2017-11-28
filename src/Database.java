import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Interface.Menu;

public class Database{
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
	static client[] c;
	static int num;
	static String[][] actions;
	static int threadNum=0;
	static int timestamp=0;
	static int deadlockFunction=0;// default =0 wait/die=1 wound/wait=2 cautious=3

	public static ArrayList<loginput> log = new ArrayList<loginput>();
	public static ArrayList<lock> locks = new ArrayList<lock>();

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

	static void wake (){
		for (int i=0; i<num; i++){
			synchronized (c[i]) {c[i].notify();}
		}
	}

	static void read (loginput in){

		//System.out.println("read is here");
	}

	static void delete (loginput in){

		//System.out.println("delete is here");

		char ch= in.document;
		char[] tmp = new char[256];
		for(int i=0;i<tmp.length;i++){
			tmp[i]=' ';
		}
		switch (ch){
		case 'A':  A[in.position]=in.value;
		A=tmp;
		break;
		case 'B':  B[in.position]=in.value;
		B=tmp;
		break;
		case 'C':  C[in.position]=in.value;
		C=tmp;break;
		case 'D':  D[in.position]=in.value;
		D=tmp;break;
		case 'E':  E[in.position]=in.value;
		E=tmp;break;
		case 'F':  F[in.position]=in.value;
		F=tmp;
		break;
		case 'G':  G[in.position]=in.value;
		G=tmp;
		break;
		case 'H':  H[in.position]=in.value;
		H=tmp;break;
		case 'I':  I[in.position]=in.value;
		I=tmp;break;
		case 'J':  J[in.position]=in.value;
		J=tmp;break;
		case 'K':  K[in.position]=in.value;
		K=tmp;break;
		case 'L':  L[in.position]=in.value;
		L=tmp;break;
		case 'M':  M[in.position]=in.value;
		M=tmp;break;
		case 'N':  N[in.position]=in.value;
		N=tmp;break;
		case 'O':  O[in.position]=in.value;
		O=tmp;break;
		case 'P':  P[in.position]=in.value;
		P=tmp;break;
		case 'Q':  Q[in.position]=in.value;
		Q=tmp;break;
		case 'R':  R[in.position]=in.value;
		R=tmp;break;
		case 'S':  S[in.position]=in.value;
		S=tmp;break;
		case 'T':  T[in.position]=in.value;
		T=tmp;break;
		case 'U':  U[in.position]=in.value;
		U=tmp;break;
		case 'V':  V[in.position]=in.value;
		V=tmp;break;
		case 'W':  W[in.position]=in.value;
		W=tmp;break;
		case 'X':  X[in.position]=in.value;
		X=tmp;break;
		case 'Y':  Y[in.position]=in.value;
		Y=tmp;break;
		case 'Z':  Z[in.position]=in.value;
		Z=tmp;break;
		}		
		try{
			PrintWriter printWriter = new PrintWriter ("Database/"+ch+".txt","UTF-8");
			for(int j=0;j<tmp.length;j++){
				//System.out.println("i= "+i+" j= "+j);
				printWriter.print (tmp[j]+" ");
			}

			// close connection
			printWriter.close (); 

		}catch(Exception e){
			System.out.println(e);
		}
	}

	static void write (loginput in){
		//System.out.println("write is here");

		char ch= in.document;
		char[] tmp = new char[256];

		switch (ch){
		case 'A':  A[in.position]=in.value;
		tmp=A;break;
		case 'B':  B[in.position]=in.value;
		tmp=B;break;
		case 'C':  C[in.position]=in.value;
		tmp=C;break;
		case 'D':  D[in.position]=in.value;
		tmp=D;break;
		case 'E':  E[in.position]=in.value;
		tmp=E;break;
		case 'F':  F[in.position]=in.value;
		tmp=F;break;
		case 'G':  G[in.position]=in.value;
		tmp=G;break;
		case 'H':  H[in.position]=in.value;
		tmp=H;break;
		case 'I':  I[in.position]=in.value;
		tmp=I;break;
		case 'J':  J[in.position]=in.value;
		tmp=J;break;
		case 'K':  K[in.position]=in.value;
		tmp=K;break;
		case 'L':  L[in.position]=in.value;
		tmp=L;break;
		case 'M':  M[in.position]=in.value;
		tmp=M;break;
		case 'N':  N[in.position]=in.value;
		tmp=N;break;
		case 'O':  O[in.position]=in.value;
		tmp=O;break;
		case 'P':  P[in.position]=in.value;
		tmp=P;break;
		case 'Q':  Q[in.position]=in.value;
		tmp=Q;break;
		case 'R':  R[in.position]=in.value;
		tmp=R;break;
		case 'S':  S[in.position]=in.value;
		tmp=S;break;
		case 'T':  T[in.position]=in.value;
		tmp=T;break;
		case 'U':  U[in.position]=in.value;
		tmp=U;break;
		case 'V':  V[in.position]=in.value;
		tmp=V;break;
		case 'W':  W[in.position]=in.value;
		tmp=W;break;
		case 'X':  X[in.position]=in.value;
		tmp=X;break;
		case 'Y':  Y[in.position]=in.value;
		tmp=Y;break;
		case 'Z':  Z[in.position]=in.value;
		tmp=Z;break;
		}		
		try{
			PrintWriter printWriter = new PrintWriter ("Database/"+ch+".txt","UTF-8");
			for(int j=0;j<tmp.length;j++){
				//System.out.println("i= "+i+" j= "+j);
				printWriter.print (tmp[j]+" ");
			}

			// close connection
			printWriter.close (); 

		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws InterruptedException {
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
		num=sc.nextInt();
		System.out.println("Deadlock prevention Function?");
		int deadlockFunction=sc.nextInt();
		sc.close();

		actions = new String[num][22];//22 was 20

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

		c = new client[num]; 

		for (int i=0; i<num; i++){
			c[i] = new client();
			c[i].start();
		}

		for (int i=0; i<num; i++){
			c[i].join();
		}


		printLog();
		System.out.println("--END--");
	}
}
