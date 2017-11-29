
public class client extends Thread{

	//////////////////////////////////////////////////////////////////////
	
	public void run(){
		Database.threadNum++;
		int id=Database.threadNum;
		int ts=0;
		int function=Database.deadlockFunction;
		String[] par = new String[4];
		int enemyTS=0;
		
		for (int i=0; i<Database.actions[id-1].length; i++){
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
					while (algorithms.wound_wait(ts, enemyTS, 0)==0){
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
					while (algorithms.wound_wait(ts, enemyTS, 1)==1){
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
				
				ts=Database.execute(ts, id, par, i);
			}

			if (i%10==0){
				yield();
			}
		}
		Database.freeLocks(id);
		synchronized (this) {Database.wake();}
	}
	
	///////////////////////////////////////////////////////////////////////
	
}
