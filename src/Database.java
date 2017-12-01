import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//https://github.com/Gpapana/EPL446.git
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
	static client[] c;
	static int num;
	static String[][] actions;
	static int threadNum=0;
	static int timestamp=0;
	static int deadlockFunction;
	static boolean startpressed=false;
	static String TSdata[][];

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
	
	static int graph (int id, String[] par){//TODO
		int des=0;
		for (int i=0; i<locks.size(); i++){
			lock l=locks.get(i);
			if (l.client==id){
			}
		}
		return des;
	}
	
	synchronized static int findEnemyTS (int id, String[] par, int fun) {
		int enemy;
		if (fun==1){
			enemy=500;
		}
		else{
			enemy=0;
		}
		char command=par[0].charAt(0);
		if (command=='B' || command=='C' || command=='A'){
			if (fun==1){
				return 500;
			}
			else{
				return 0;
			}
		}
		char document=par[1].charAt(0);
		int position;
		if (command=='D'){
			position=-1;
		}
		else{
			position=Integer.parseInt(par[2]);
		}
		
		for (int i=0; i<locks.size(); i++){
			lock l=locks.get(i);
			if (l.client!=id){
				if (l.fileName==document && l.position==position && l.state=='S' && command=='W'){
					enemy=l.ts; break;
				}
				else if (l.fileName==document && l.position==position && l.state=='X'){
					enemy=l.ts; break;
				}
				else if (l.fileName==document && l.position==-1 && l.state=='X'){
					enemy=l.ts; break;
				}
			}
			else{
				if (fun==1){
					enemy=500;
				}
				else{
					enemy=0;
				}
			}
		}
		
		return enemy;
	}
	
	synchronized static void getLocks (loginput input, int id, int ts){
		int ok=1;
		if (input.command=='R' || input.command=='W' || input.command=='D'){
			char document=input.document;
			int position;
			if (input.command=='D'){
				position=-1;
			}
			else{
				position=input.position;
			}
			for (int j=0; j<locks.size(); j++){
				lock g=locks.get(j);
				if (g.client==id){
					if (g.fileName==document && g.position==-1){
						ok=0;
						break;
					}
					if (g.fileName==document && g.position==position && g.state=='S' && input.command=='W'){
						g.state='X';
						ok=0;
						break;
					}
					if (g.fileName==document && g.position==position && g.state=='S' && input.command=='R'){
						ok=0;
						break;
					}
					if (g.fileName==document && g.position==position && g.state=='X'){
						ok=0;
						break;
					}
				}
			}
			if (ok==1){
				lock l=new lock();
				if (input.command=='R'){
					l.client=id;
					l.ts=ts;
					l.fileName=input.document;
					l.position=input.position;
					l.state='S';
				}
				if (input.command=='W'){
					l.client=id;
					l.ts=ts;
					l.fileName=input.document;
					l.position=input.position;
					l.state='X';
				}
				if (input.command=='D'){
					l.client=id;
					l.ts=ts;
					l.fileName=input.document;
					l.position=-1;
					l.state='X';
				}
				
				
				locks.add(l);
				System.out.println("Client "+l.client+" "+l.state+"-lock on "+l.fileName+" "+l.position);
			}
			
		}
	}
	
	synchronized static void freeLocks (int id){
		int counter=0;
		int continueLoop=1;
		
		while (continueLoop==1){
			counter=0;
			for (int i=0; i<locks.size(); i++){
				lock l=locks.get(i);
				if (l.client==id){
					System.out.println("Client "+id+": removed lock on "+l.fileName+" "+l.position);
					locks.remove(i);
					counter++;
				}
			}
			if (counter==0){
				continueLoop=0;
			}
		}
		
	}

	synchronized static int checkLocks (int id, String[] par){
		int disition=1;
		char command=par[0].charAt(0);
		if (command=='B' || command=='C' || command=='A'){
			return 1;
		}
		char document=par[1].charAt(0);
		int position;
		if (command=='D'){
			position=-1;
		}
		else{
			position=Integer.parseInt(par[2]);
		}
		
		for (int i=0; i<locks.size(); i++){
			lock l=locks.get(i);
			if (l.client!=id){
				if (l.fileName==document && l.position==position && l.state=='S' && command=='R'){
					disition=1;
				}
				else if (l.fileName==document && l.position==position && l.state=='S' && command=='W'){
					disition=0; break;
				}
				else if (l.fileName==document && l.position==position && l.state=='S' && command=='D'){
					disition=0; break;
				}
				else if (l.fileName==document && l.position==position && l.state=='X'){
					disition=0; break;
				}
				else if (l.fileName==document && l.position==-1 && l.state=='X'){
					disition=0; break;
				}
				else {disition=1;}
			}
			else{
				if (l.fileName==document && l.position==position && l.state=='S' && command=='W'){
					return 1;
				}
				else if (l.fileName==document && l.position==position && l.state=='S' && command=='R'){
					return 1;
				}
				else if (l.fileName==document && l.position==position && l.state=='X'){
					return 1;
				}
				else {disition=1;}
			}
		}
		
		return disition;
	}
	
	synchronized static int killHim (int ts){
		int enemyID=0;
		for (int i=0; i<log.size(); i++){
			loginput b = log.get(i);
			if (b.command=='B' && b.TS==ts){
				enemyID=b.transactionNum;
				break;
			}
		}
		
		for (int i=0; i<num; i++){
			if (c[i].id==enemyID){
				System.out.println("Client "+enemyID+" killed. A moment of silence pleace!!!");
				freeLocks (enemyID);
				//freeLocks(enemyID);
				c[i].i=0;
				c[i].restart=1;
				try {
					wake();
					c[i].yield();
					c[i].sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return enemyID;
	}
	
	synchronized static int execute (int ts, int id, String[] par, int i){//TODO
		timestamp++;	
		if (i==0){
			ts=timestamp;
		}
		
		loginput input = new loginput();
		input.id=timestamp;
		input.transactionNum=id;
		
		
		for (int j=0; j<par.length; j++){
			if (j==0){
				input.command=par[0].charAt(0);
			}
			if (j==1){
				input.document=par[1].charAt(0);
			}
			if (j==2){
				input.position=Integer.parseInt(par[2]);
			}
			if (j==3){
				input.value=par[3].charAt(0);
			}
		}
		
		getLocks(input, id, ts);
		
		
		switch(input.command){
			case 'R': Database.read(input);break;
			case 'W': Database.write(input);break;
			case 'D': Database.delete(input);break;
		}
		
		input.TS=ts;
		
		log.add(input);
		

		return ts;
	}
	
	static void read (loginput in){

	}

	static void delete (loginput in){

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

///////////////GUI CONNECTION///////////////////////
	TSdata=new String[26][3];
	for(int i=0;i<26;i++) {
		char temp=(char)(i+65);
		TSdata[i][0]=String.valueOf(temp);

	}
	for(int i=0;i<26;i++) {
		for(int j=1;j<3;j++) {
			TSdata[i][j]="0";
		}
	}
	String[] columnNames = {"Resource",
			"MaxReadTS",
			"MaxWriteTS",};
	
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				dbMenuGUI frame = new dbMenuGUI(TSdata,columnNames);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	
	
	while(startpressed==false){
	    try {
	       Thread.sleep(200);
	    } catch(InterruptedException e) {
	    }
	}
	
	/////////////////////////////////////////////

	num=dbMenuGUI.ClientsNum;
	int deadlockFunction=dbMenuGUI.deadlockFun;

		actions = new String[num][22];

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