
public class client extends Thread{

	//////////////////////////////////////////////////////////////////////
	
	public void run(){
		Database.threadNum++;
		int id=Database.threadNum;
		int ts=0;
		String[] par = new String[4];

		for (int i=0; i<2000; i++){ //NA POSKOLIOUNTE LIO TA trasactions PRIN KSEKINISOUN DOULIA
			int p=i;
		}
		
		for (int i=0; i<Database.actions[id-1].length; i++){
			String s=Database.actions[id-1][i];
			if (s!=null){
				par = s.split(" ");
				int disition;
				while((disition=checkLocks(id,par))==0){
					System.out.println("Client "+id+" waits for "+par[1].charAt(0));
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Client "+id+" continues!!!");
				}
				ts=execute(ts, id, par, i);
			}

			
			if (i%10==0){
				yield();
			}
		}
		freeLocks(id);
		synchronized (this) {Database.wake();}
	}
	
	///////////////////////////////////////////////////////////////////////
	
	
	synchronized static int execute (int ts, int id, String[] par, int i){//TODO
		
		Database.timestamp++;	
		if (i==0){
			ts=Database.timestamp;
		}
		
		loginput input = new loginput();
		input.id=Database.timestamp;
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
		
		int ok=1;
		if (input.command=='R' || input.command=='W' || input.command=='D'){
			char document=par[1].charAt(0);
			int position;
			if (input.command=='D'){
				position=-1;
			}
			else{
				position=Integer.parseInt(par[2]);
			}
			for (int j=0; j<Database.locks.size(); j++){
				lock g=Database.locks.get(j);
				if (g.client==id){
					if (g.fileName==par[1].charAt(0) && g.position==-1){
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
					l.fileName=par[1].charAt(0);
					l.position=Integer.parseInt(par[2]);
					l.state='S';
				}
				if (input.command=='W'){
					l.client=id;
					l.ts=ts;
					l.fileName=par[1].charAt(0);
					l.position=Integer.parseInt(par[2]);
					l.state='X';
				}
				if (input.command=='D'){
					l.client=id;
					l.ts=ts;
					l.fileName=par[1].charAt(0);
					l.position=-1;
					l.state='X';
				}
				
				
				Database.locks.add(l);
				System.out.println("Client "+l.client+" "+l.state+"-lock on "+l.fileName+" "+l.position);
			}
			
		}
		
		
		
		switch(input.command){
			case 'R': Database.read(input);break;
			case 'W': Database.write(input);break;
			case 'D': Database.delete(input);break;
		}
		
		input.TS=ts;
		
		Database.log.add(input);
		

		return ts;
	}
	
	synchronized static void freeLocks (int id){
		for (int i=0; i<Database.locks.size(); i++){
			lock l=Database.locks.get(i);
			if (l.client==id){
				Database.locks.remove(i);
				System.out.println("Client "+id+": removed lock on "+l.fileName+" "+l.position);
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
		
		for (int i=0; i<Database.locks.size(); i++){
			lock l=Database.locks.get(i);
			if (l.client!=id){
				if (l.fileName==document && l.position==position && l.state=='S' && command=='R'){
					disition=1;
				}
				else if (l.fileName==document && l.position==position && l.state=='S' && command=='W'){
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
}
