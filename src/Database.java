import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//https://github.com/Gpapana/EPL446.git
public class Database {

	private static ArrayList<Character> A = new ArrayList<Character>();
	private static ArrayList<Character> B = new ArrayList<Character>();
	private static ArrayList<Character> C = new ArrayList<Character>();
	private static ArrayList<Character> D = new ArrayList<Character>();
	private static ArrayList<Character> E = new ArrayList<Character>();
	private static ArrayList<Character> F = new ArrayList<Character>();
	private static ArrayList<Character> G = new ArrayList<Character>();
	private static ArrayList<Character> H = new ArrayList<Character>();
	private static ArrayList<Character> I = new ArrayList<Character>();
	private static ArrayList<Character> J = new ArrayList<Character>();
	private static ArrayList<Character> K = new ArrayList<Character>();
	private static ArrayList<Character> L = new ArrayList<Character>();
	private static ArrayList<Character> M = new ArrayList<Character>();
	private static ArrayList<Character> N = new ArrayList<Character>();
	private static ArrayList<Character> O = new ArrayList<Character>();
	private static ArrayList<Character> P = new ArrayList<Character>();
	private static ArrayList<Character> Q = new ArrayList<Character>();
	private static ArrayList<Character> R = new ArrayList<Character>();
	private static ArrayList<Character> S = new ArrayList<Character>();
	private static ArrayList<Character> T = new ArrayList<Character>();
	private static ArrayList<Character> U = new ArrayList<Character>();
	private static ArrayList<Character> V = new ArrayList<Character>();
	private static ArrayList<Character> W = new ArrayList<Character>();
	private static ArrayList<Character> X = new ArrayList<Character>();
	private static ArrayList<Character> Y = new ArrayList<Character>();
	private static ArrayList<Character> Z = new ArrayList<Character>();

	static client[] c;
	static int num;
	static String[][] actions;
	public static int waitforgraph[][];
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
			synchronized (c[i]) {c[i].notifyAll();}
		}
	}

	synchronized static void printGraph(){

		for(int i=0;i<waitforgraph.length;i++){
			for(int j=0;j<waitforgraph.length;j++){
				if(waitforgraph[i][j]==1){
					System.out.println("client_"+(i+1)+" waits for client_"+(j+1));
				}
			}
		}
	}

	synchronized static int updategraph (int id, String[] par){
		int disition=1; // 0-> kill // 1-> continue wait // 2-> lock it
		int flag =1;
		char command=par[0].charAt(0);
		if (command=='B' || command=='C' || command=='A'){
			return 2;
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
				if (l.fileName==document && l.position==position){
					flag=0;
					if ( l.state=='S' && command=='W'){
						waitforgraph[id-1][l.client-1]=1;
						for(int j=0; j<waitforgraph.length;j++){
							if(waitforgraph[l.client-1][j]==1){
								disition=0;
								waitforgraph[id-1][l.client-1]=0; 
							}
						}break;
					}
					else if ( l.state=='S' && command=='D'){
						waitforgraph[id-1][l.client-1]=1; 
						for(int j=0; j<waitforgraph.length;j++){
							if(waitforgraph[l.client-1][j]==1){
								disition=0;
								waitforgraph[id-1][l.client-1]=0; 
							}
						}break;
					}else if (l.state=='X'){
						waitforgraph[id-1][l.client-1]=1;
						for(int j=0; j<waitforgraph.length;j++){
							if(waitforgraph[l.client-1][j]==1){
								disition=0;
								waitforgraph[id-1][l.client-1]=0; 
							}
						}break;
					}
				}else if (l.fileName==document && l.state=='X' && command=='D'){
					flag=0;
					waitforgraph[id-1][l.client-1]=1;
					for(int j=0; j<waitforgraph.length;j++){
						if(waitforgraph[l.client-1][j]==1){
							disition=0;
							waitforgraph[id-1][l.client-1]=0; 
						}
					}break;
				}else if (l.fileName==document && l.state=='S' && command=='D'){
					flag=0;
					waitforgraph[id-1][l.client-1]=1;
					for(int j=0; j<waitforgraph.length;j++){
						if(waitforgraph[l.client-1][j]==1){
							disition=0;
							waitforgraph[id-1][l.client-1]=0; 
						}
					}break;
				}else if(l.fileName==document && l.position==-1 && l.state=='X'){
					flag=0;
					waitforgraph[id-1][l.client-1]=1;
					for(int j=0; j<waitforgraph.length;j++){
						if(waitforgraph[l.client-1][j]==1){
							disition=0;
							waitforgraph[id-1][l.client-1]=0; 
						}
					}break;
				}
			}//end of if id!=client
			waitforgraph[id-1][l.client-1]=0;
		}//for ends here
		if (flag==1){
			return 2;
		}else{
			return disition;
		}
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
				else if (l.fileName==document && l.position==position && l.state=='S' && command=='D'){
					enemy=l.ts; break;
				}
				else if (l.fileName==document && l.position==position && l.state=='X'){
					enemy=l.ts; break;
				}
				else if (l.fileName==document && l.state=='S' && command=='D'){
					enemy=l.ts; break;
				}
				else if (l.fileName==document && l.state=='X' && command=='D'){
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

	//checks locks and gives response 
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
				else if (l.fileName==document && l.state=='S' && command=='D'){
					disition=0; break;
				}
				else if (l.fileName==document && l.state=='X' && command=='D'){
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

	//kills the client with a specific timestamp
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
					e.printStackTrace();
				}
			}
		}
		return enemyID;
	}

	//executes the right command as it comes in to log
	synchronized static int execute (int ts, int id, String[] par, int i){
		timestamp++;	
		if (i==0){
			ts=timestamp;
		}

		loginput input = new loginput();
		input.id=timestamp;
		input.transactionNum=id;

		//saves the command in input 
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
		//Requests lock
		getLocks(input, id, ts);
		//execute the command
		switch(input.command){
		case 'R': Database.read(input);break;
		case 'W': Database.write(input);break;
		case 'D': Database.delete(input);break;
		case 'A': Database.abort(input);break;
		}

		input.TS=ts;
		log.add(input);
		return ts;
	}

	//read from file.
	static void read (loginput in){
		//no need to implement at this moment
	}

	//empties the file.
	static void delete (loginput in){
		int tmp=0;
		char ch= in.document;
		//find the specific file 
		switch (ch){
		case 'A':  tmp=A.size();
		A.clear();
		break;
		case 'B':   tmp=B.size();
		B.clear();
		break;
		case 'C':   tmp=C.size();
		C.clear();
		break;
		case 'D':   tmp=D.size();
		D.clear();
		break;
		case 'E':   tmp=E.size();
		E.clear();
		break;
		case 'F':   tmp=F.size();
		F.clear();
		break;
		case 'G':   tmp=G.size();
		G.clear();
		break;
		case 'H':   tmp=H.size();
		H.clear();
		break;
		case 'I':   tmp=I.size();
		I.clear();
		break;
		case 'J':   tmp=J.size();
		J.clear();
		break;
		case 'K':  tmp=K.size();
		K.clear();
		break;
		case 'L':   tmp=L.size();
		L.clear();
		break;
		case 'M':   tmp=M.size();
		M.clear();
		break;
		case 'N':   tmp=N.size();
		N.clear();
		break;
		case 'O':   tmp=O.size();
		O.clear();
		break;
		case 'P':   tmp=P.size();
		P.clear();
		break;
		case 'Q':   tmp=Q.size();
		Q.clear();
		break;
		case 'R':   tmp=R.size();
		R.clear();
		break;
		case 'S':   tmp=S.size();
		S.clear();
		break;
		case 'T':   tmp=T.size();
		T.clear();
		break;
		case 'U':   tmp=U.size();
		U.clear();
		break;
		case 'V':   tmp=V.size();
		V.clear();
		break;
		case 'W':  tmp=W.size();
		W.clear();
		break;
		case 'X':   tmp=X.size();
		X.clear();
		break;
		case 'Y':   tmp=Y.size();
		Y.clear();
		break;
		case 'Z':   tmp=Z.size();
		Z.clear();
		break;
		}		
		//write to file
		try{
			PrintWriter printWriter = new PrintWriter ("Database/"+ch+".txt","UTF-8");
			for(int j=0;j<tmp;j++){
				printWriter.println (" ");
			}
			// close connection
			printWriter.close (); 
		}catch(Exception e){
			System.out.println(e);
		}
	}

	//writes in the Specific database file in the right position
	@SuppressWarnings("unchecked")
	static void write (loginput in){

		char ch= in.document;
		int pos=in.position;
		ArrayList<Character> LOL = new ArrayList<Character>();
		//finds the right file and writes all the arraylist for each file to file
		switch (ch){
		case 'A':  if(pos<=A.size()){
			A.set(pos-1, in.value);
		}else{
			for(int i=A.size();i<pos-1;i++){
				A.add(' ');
			}
			A.add(in.value);
		}
		LOL=(ArrayList<Character>) A.clone();
		break;
		case 'B':   if(pos<=B.size()){
			B.set(pos-1, in.value);
		}else{
			for(int i=B.size();i<pos-1;i++){
				B.add(' ');
			}
			B.add(in.value);
		}
		LOL=(ArrayList<Character>) B.clone();
		break;
		case 'C':   if(pos<=C.size()){
			C.set(pos-1, in.value);
		}else{
			for(int i=C.size();i<pos-1;i++){
				C.add(' ');
			}
			C.add(in.value);
		}
		LOL=(ArrayList<Character>) C.clone();
		break;
		case 'D':  if(pos<=D.size()){
			D.set(pos-1, in.value);
		}else{
			for(int i=D.size();i<pos-1;i++){
				D.add(' ');
			}
			D.add(in.value);
		}
		LOL=(ArrayList<Character>) D.clone();
		break;
		case 'E':   if(pos<=E.size()){
			E.set(pos-1, in.value);
		}else{
			for(int i=E.size();i<pos-1;i++){
				E.add(' ');
			}
			E.add(in.value);
		}
		LOL=(ArrayList<Character>) E.clone();
		break;
		case 'F':   if(pos<=F.size()){
			F.set(pos-1, in.value);
		}else{
			for(int i=F.size();i<pos-1;i++){
				F.add(' ');
			}
			F.add(in.value);	
		}
		LOL=(ArrayList<Character>) F.clone();
		break;
		case 'G':   if(pos<=G.size()){
			G.set(pos-1, in.value);
		}else{
			for(int i=G.size();i<pos-1;i++){
				G.add(' ');
			}
			G.add(in.value);
		}
		LOL=(ArrayList<Character>) G.clone();
		break;
		case 'H':   if(pos<=H.size()){
			H.set(pos-1, in.value);
		}else{
			for(int i=H.size();i<pos-1;i++){
				H.add(' ');
			}
			H.add(in.value);
		}
		LOL=(ArrayList<Character>) H.clone();;
		break;
		case 'I':   if(pos<=I.size()){
			I.set(pos-1, in.value);
		}else{
			for(int i=I.size();i<pos-1;i++){
				I.add(' ');
			}
			I.add(in.value);
		}
		LOL=(ArrayList<Character>) I.clone();
		break;
		case 'J':   if(pos<=J.size()){
			J.set(pos-1, in.value);
		}else{
			for(int i=J.size();i<pos-1;i++){
				J.add(' ');
			}
			J.add(in.value);
		}
		LOL=(ArrayList<Character>) J.clone();
		break;
		case 'K':  if(pos<=K.size()){
			K.set(pos-1, in.value);
		}else{
			for(int i=K.size();i<pos-1;i++){
				K.add(' ');
			}
			K.add(in.value);
		}
		LOL=(ArrayList<Character>) K.clone();
		break;
		case 'L':   if(pos<=L.size()){
			L.set(pos-1, in.value);
		}else{
			for(int i=L.size();i<pos-1;i++){
				L.add(' ');
			}
			L.add(in.value);
		}
		LOL=(ArrayList<Character>) L.clone();
		break;
		case 'M':  if(pos<=M.size()){
			M.set(pos-1, in.value);
		}else{
			for(int i=M.size();i<pos-1;i++){
				M.add(' ');
			}
			M.add(in.value);
		}
		LOL=(ArrayList<Character>) M.clone();
		break;
		case 'N':   if(pos<=N.size()){
			N.set(pos-1, in.value);
		}else{
			for(int i=N.size();i<pos-1;i++){
				N.add(' ');
			}
			N.add(in.value);
		}
		LOL=(ArrayList<Character>) N.clone();
		break;
		case 'O':  if(pos<=O.size()){
			O.set(pos-1, in.value);
		}else{
			for(int i=O.size();i<pos-1;i++){
				O.add(' ');
			}
			O.add(in.value);
		}
		LOL=(ArrayList<Character>) O.clone();
		break;
		case 'P':  if(pos<=P.size()){
			P.set(pos-1, in.value);
		}else{
			for(int i=P.size();i<pos-1;i++){
				P.add(' ');
			}
			P.add(in.value);
		}
		LOL=(ArrayList<Character>) P.clone();
		break;
		case 'Q':  if(pos<=Q.size()){
			Q.set(pos-1, in.value);
		}else{
			for(int i=Q.size();i<pos-1;i++){
				Q.add(' ');
			}
			Q.add(in.value);
		}
		LOL=(ArrayList<Character>) Q.clone();
		break;
		case 'R':  if(pos<=R.size()){
			R.set(pos-1, in.value);
		}else{
			for(int i=R.size();i<pos-1;i++){
				R.add(' ');
			}
			R.add(in.value);
		}
		LOL=(ArrayList<Character>) R.clone();
		break;
		case 'S':  if(pos<=S.size()){
			S.set(pos-1, in.value);
		}else{
			for(int i=S.size();i<pos-1;i++){
				S.add(' ');
			}
			S.add(in.value);
		}
		LOL=(ArrayList<Character>) S.clone();
		break;
		case 'T':  if(pos<=T.size()){
			T.set(pos-1, in.value);
		}else{
			for(int i=T.size();i<pos-1;i++){
				T.add(' ');
			}
			T.add(in.value);
		}
		LOL=(ArrayList<Character>) T.clone();
		break;
		case 'U':  if(pos<=U.size()){
			U.set(pos-1, in.value);
		}else{
			for(int i=U.size();i<pos-1;i++){
				U.add(' ');
			}
			U.add(in.value);
		}
		LOL=(ArrayList<Character>) U.clone();
		break;
		case 'V':  if(pos<=V.size()){
			V.set(pos-1, in.value);
		}else{
			for(int i=V.size();i<pos-1;i++){
				V.add(' ');
			}
			V.add(in.value);
		}
		LOL=(ArrayList<Character>) V.clone();
		break;
		case 'W': if(pos<=W.size()){
			W.set(pos-1, in.value);
		}else{
			for(int i=W.size();i<pos-1;i++){
				W.add(' ');
			}
			W.add(in.value);
		}
		LOL=(ArrayList<Character>) W.clone();
		break;
		case 'X':  if(pos<=X.size()){
			X.set(pos-1, in.value);
		}else{
			for(int i=X.size();i<pos-1;i++){
				X.add(' ');
			}
			X.add(in.value);
		}
		LOL=(ArrayList<Character>) X.clone();
		break;
		case 'Y':  if(pos<=Y.size()){
			Y.set(pos-1, in.value);
		}else{
			for(int i=Y.size();i<pos-1;i++){
				Y.add(' ');
			}
			Y.add(in.value);
		}
		LOL=(ArrayList<Character>) Y.clone();
		break;
		case 'Z':  if(pos<=Z.size()){
			Z.set(pos-1, in.value);
		}else{
			for(int i=Z.size();i<pos-1;i++){
				Z.add(' ');
			}
			Z.add(in.value);
		}
		LOL=(ArrayList<Character>) Z.clone();
		break;
		}
		//write happens here
		try{
			PrintWriter printWriter = new PrintWriter ("Database/"+ch+".txt","UTF-8");
			for(int j=0;j<LOL.size();j++){
				printWriter.println (LOL.get(j));
			}
			// close connection
			printWriter.close (); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//in case of abort rollback happens here
	@SuppressWarnings("unchecked")
	static void abort (loginput in){
		ArrayList<Character> LOL = new ArrayList<Character>();

		//starts from last log input and moves backwards to the until it finds the beginning of the aborted client
		for (int p=log.size(); p>0; p--){	
			loginput l = log.get(p-1);
			if (l.transactionNum==in.transactionNum && l.command=='B'){
				break;
			}//undo the write
			if (l.transactionNum==in.transactionNum && l.command=='W'){
				char tmp=' ';
				char ch=l.document;
				int pos=l.position;
				for (int j=p; j>0; j--){
					loginput k = log.get(j-1);
					if (l.transactionNum!=k.transactionNum && l.document==k.document && l.position==k.position && k.command=='W' && tmp==' '){
						tmp=k.value;
					}
					//find the file and change it
					switch (ch){
					case 'A':  
						A.set(pos-1, tmp);
						LOL=(ArrayList<Character>) A.clone();
						break;
					case 'B':
						B.set(pos-1, tmp);
						LOL=(ArrayList<Character>) B.clone();
						break;
					case 'C': 
						C.set(pos-1, tmp);
						LOL=(ArrayList<Character>) C.clone();
						break;
					case 'D':
						D.set(pos-1, tmp);
						LOL=(ArrayList<Character>) D.clone();
						break;
					case 'E': 
						E.set(pos-1, tmp);
						LOL=(ArrayList<Character>) E.clone();
						break;
					case 'F': 
						F.set(pos-1, tmp);
						LOL=(ArrayList<Character>) F.clone();
						break;
					case 'G': 
						G.set(pos-1, tmp);
						LOL=(ArrayList<Character>) G.clone();
						break;
					case 'H':
						H.set(pos-1, tmp);
						LOL=(ArrayList<Character>) H.clone();
						break;
					case 'I': 
						I.set(pos-1, tmp);
						LOL=(ArrayList<Character>) I.clone();
						break;
					case 'J': 
						J.set(pos-1, tmp);
						LOL=(ArrayList<Character>) J.clone();
						break;
					case 'K':
						K.set(pos-1, tmp);
						LOL=(ArrayList<Character>) K.clone();
						break;
					case 'L': 
						L.set(pos-1, tmp);
						LOL=(ArrayList<Character>) L.clone();
						break;
					case 'M':
						M.set(pos-1, tmp);
						LOL=(ArrayList<Character>) M.clone();
						break;
					case 'N': 
						N.set(pos-1, tmp);
						LOL=(ArrayList<Character>) N.clone();
						break;
					case 'O': 
						O.set(pos-1, tmp);
						LOL=(ArrayList<Character>) O.clone();
						break;
					case 'P': 
						P.set(pos-1, tmp);
						LOL=(ArrayList<Character>) P.clone();
						break;
					case 'Q': 
						Q.set(pos-1, tmp);
						LOL=(ArrayList<Character>) Q.clone();
						break;
					case 'R': 
						R.set(pos-1, tmp);
						LOL=(ArrayList<Character>) R.clone();
						break;
					case 'S': 
						S.set(pos-1, tmp);
						LOL=(ArrayList<Character>) S.clone();
						break;
					case 'T': 
						T.set(pos-1, tmp);
						LOL=(ArrayList<Character>) T.clone();
						break;
					case 'U': 
						U.set(pos-1, tmp);
						LOL=(ArrayList<Character>) U.clone();
						break;
					case 'V': 
						V.set(pos-1, tmp);
						LOL=(ArrayList<Character>) V.clone();
						break;
					case 'W':
						W.set(pos-1, tmp);
						LOL=(ArrayList<Character>) W.clone();
						break;
					case 'X': 
						X.set(pos-1, tmp);
						LOL=(ArrayList<Character>) X.clone();
						break;
					case 'Y': 
						Y.set(pos-1, tmp);
						LOL=(ArrayList<Character>) Y.clone();
						break;
					case 'Z': 
						Z.set(pos-1, tmp);
						LOL=(ArrayList<Character>) Z.clone();
						break;
					}
					//write to file
					try{
						PrintWriter printWriter = new PrintWriter ("Database/"+ch+".txt","UTF-8");
						for(int n=0;n<LOL.size();n++){
							printWriter.println (LOL.get(n));
						}
						// close connection
						printWriter.close (); 
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
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
		//System.out.println("deadlockFunction="+deadlockFunction);
		//sc.close();
		waitforgraph=new int [num][num];
		num=dbMenuGUI.ClientsNum;
		actions = new String[num][22];

		for (int i=0; i<num; i++){
			for(int j=0; j<num; j++){
				waitforgraph[i][j]=0;
			}
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