public class client extends Thread{

	int id=0;
	int ts=0;
	//////////////////////////////////////////////////////////////////////
	static boolean pressed=false;
	public void run(){
		Database.threadNum++;
		id=Database.threadNum;
		int function=Database.deadlockFunction;
		String[] par = new String[4];
		int enemyTS=0;
		
		
		for (int i=0; i<Database.actions[id-1].length; i++){
	//////////CONNECTION WITH NEXT BUTTON////////////// 
				while(true){
					if (pressed==true && clientGUI.cid==id){
						break;
					}
					else{
						try {
						       Thread.sleep(200);
						    } catch(InterruptedException e) {
						    }
					}
				    
				}
				
				///////////////////////
				//System.out.println("cid="+clientGUI.cid);
			
				//id=clientGUI.cid;
			String s=Database.actions[id-1][i];
			if (s!=null){
				par = s.split(" ");
				switch (function){
				case 0: //default
					while(Database.checkLocks(id,par)==0){
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
					break;
				case 1: //wound_wait
					enemyTS=Database.findEnemyTS(id, par);
					while (algorithms.wound_wait(ts, enemyTS)==0){
						System.out.println("Client "+id+" waits for "+par[1].charAt(0));
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Client "+id+" continues!!!");
						enemyTS=Database.findEnemyTS(id, par);
					}
					if (enemyTS!=0){
						//kill enemy
					}
					break;
				case 2: //wait_die
					enemyTS=Database.findEnemyTS(id, par);
					while (algorithms.wound_wait(ts, enemyTS)==1){
						System.out.println("Client "+id+" waits for "+par[1].charAt(0));
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Client "+id+" continues!!!");
						enemyTS=Database.findEnemyTS(id, par);
					}
					if (enemyTS!=0){
						//suicide
					}
					break;
				case 3: //cautious_waitning
					
					break;
				}
				//id=clientGUI.cid;
				ts=Database.execute(ts, id, par, i);
				//////////////////////////////////////////////////
				
				System.out.println("ts="+ts);
				//clientGUI.TSc=ts;
				String str = "";
				for(int i1=0;i1<Database.log.size();i1++){
					str=str+Database.log.get(i1).toString();
				}
				
				dbGUI.textArea.setText(str);
				///////////////////////////////////////
			}

			if (i%10==0){
				yield();
			}
			
			pressed=false;
		}
		Database.freeLocks(id);
		synchronized (this) {Database.wake();}
	}
	
	///////////////////////////////////////////////////////////////////////
	
}