
public class client extends Thread{

	//////////////////////////////////////////////////////////////////////
	
	public void run(){
		Database.threadNum++;
		int id=Database.threadNum;
		int ts=0;
				
		for (int i=0; i<2000; i++){ //NA POSKOLIOUNTE LIO TA trasactions PRIN KSEKINISOUN DOULIA
			int p=i;
		}
		
		for (int i=0; i<Database.actions[id-1].length; i++){
			ts=print(ts, id, Database.actions[id-1][i], i);
			if (i%10==0){
				yield();
			}
		}
		
	}
	
	///////////////////////////////////////////////////////////////////////
	
	synchronized static int print (int ts, int id, String s, int i){
		String[] par = new String[4];
		if (s==null){
			return ts;
		}
		par = s.split(" ");
		
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
		
		input.TS=ts;
		
		Database.log.add(input);
		
//		System.out.print(Database.timestamp+" cliend"+id+" T"+id+"="+ts+" ");
//		for (int j=0; j<par.length; j++){
//			System.out.print(par[j]+" ");
//		}
//		System.out.println();

		return ts;
	}
}
