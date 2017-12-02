public class client extends Thread{

	int id=0;
	int ts=0;
	int i=0;
	int restart=0;
	//////////////////////////////////////////////////////////////////////
	static boolean pressed=false;
	public void run(){
		Database.threadNum++;
		id=Database.threadNum;
		int function=dbMenuGUI.deadlockFun;
		System.out.println("Function = "+function);
		String[] par = new String[4];
		int enemyTS=0;


		for (int i=0; i<Database.actions[id-1].length; i++){
			//////////CONNECTION WITH NEXT BUTTON////////////// 
			if(!dbMenuGUI.auto){
				while(true){
					if (pressed==true && dbMenuGUI.clientsGUI.get(id-1).cid==id){
						break;
					}
					else{
						try {
							Thread.sleep(200);
						} 
						catch(InterruptedException e) {
						}
					}   
				}
			}
			///////////////////////
			String s=Database.actions[id-1][i];
			if (s!=null){
				par = s.split(" ");
				switch (function){
				case 0: //default
					while(Database.checkLocks(id,par)==0){
						dbGUI.textArea_1.append("Client "+id+" waits for "+par[1].charAt(0)+"\n");
						System.out.println("Client "+id+" waits for "+par[1].charAt(0));
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(false);
						}
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						if(!dbMenuGUI.auto){

							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(true);
						}
						dbGUI.textArea_1.append("Client "+id+" continues!!!"+"\n");
						System.out.println("Client "+id+" continues!!!");
					}

					break;
				case 1: //wound_wait //TODO
					enemyTS=Database.findEnemyTS(id, par, function);
					while (algorithms.wound_wait(ts, enemyTS)==1){
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(false);
						}
						dbGUI.textArea_1.append("Client "+id+" wound_wait for "+par[1].charAt(0)+"\n");
						System.out.println("Client "+id+" wound_wait for "+par[1].charAt(0));
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(true);
						}
						dbGUI.textArea_1.append("Client "+id+" continues!!!"+"\n");
						System.out.println("Client "+id+" continues!!!");
						enemyTS=Database.findEnemyTS(id, par, function);
					}

					if (enemyTS!=500){
						//kill enemy
						int deadID=Database.killHim(enemyTS);
						dbGUI.textArea_1.append("Client "+id+" died \n");
						i=-1;
						if(!dbMenuGUI.auto){
<<<<<<< HEAD
							dbMenuGUI.clientsGUI.get(deadID-1).counter=1;
							dbGUI.lblTransaction.setText("Client "+deadID+" "+Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter-1]);
							dbMenuGUI.clientsGUI.get(deadID-1).lblTransaction.setText(Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter]);
=======
						dbMenuGUI.clientsGUI.get(deadID-1).counter=1;
						dbGUI.lblTransaction.setText("Client "+deadID+" "+Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter-1]);
						dbMenuGUI.clientsGUI.get(deadID-1).lblTransaction.setText(Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter]);
>>>>>>> branch 'master' of https://github.com/Gpapana/EPL446.git
						}
						continue;
					}
					break;
				case 2: //wait_die
					enemyTS=Database.findEnemyTS(id, par, function);
					int temp=algorithms.wait_die(ts, enemyTS);
					while (temp==1){
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(false);
						}
						dbGUI.textArea_1.append("Client "+id+" wait_die for "+par[1].charAt(0)+"\n");
						System.out.println("Client "+id+" wait_die for "+par[1].charAt(0));
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(id-1).btnNext.setEnabled(true);
						}
						dbGUI.textArea_1.append("Client "+id+" continues!!!");
						System.out.println("Client "+id+" continues!!!");
						enemyTS=Database.findEnemyTS(id, par, function);
						temp=algorithms.wait_die(ts, enemyTS);
					}

					if (enemyTS!=0){
						//suicide
						int deadID=Database.killHim(ts);
						i=-1;
						dbGUI.textArea_1.append("Client "+id+" died \n");
						if(!dbMenuGUI.auto){
<<<<<<< HEAD
							dbMenuGUI.clientsGUI.get(deadID-1).counter=1;
							dbGUI.lblTransaction.setText("Client "+deadID+" "+Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter-1]);
							dbMenuGUI.clientsGUI.get(deadID-1).lblTransaction.setText(Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter]);
=======
						dbMenuGUI.clientsGUI.get(deadID-1).counter=1;
						dbGUI.lblTransaction.setText("Client "+deadID+" "+Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter-1]);
						dbMenuGUI.clientsGUI.get(deadID-1).lblTransaction.setText(Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter]);
>>>>>>> branch 'master' of https://github.com/Gpapana/EPL446.git
						}
						continue;
					}
					break;
				case 3: //cautious_waitning
<<<<<<< HEAD
					int des=Database.updategraph (id,par);
					while(des==1){//TODO
						System.out.println("Client "+id+" waits for "+par[1].charAt(0));
						synchronized (this) {
							try {
								wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.println("Client "+id+" continues!!!");
						des=Database.updategraph (id,par);
					}
					if(des==0){
						//suicide
						int deadID=Database.killHim(ts);
						i=-1;
						dbGUI.textArea_1.append("Client "+id+" died \n");
						if(!dbMenuGUI.auto){
							dbMenuGUI.clientsGUI.get(deadID-1).counter=1;
							dbGUI.lblTransaction.setText("Client "+deadID+" "+Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter-1]);
							dbMenuGUI.clientsGUI.get(deadID-1).lblTransaction.setText(Database.actions[deadID-1][dbMenuGUI.clientsGUI.get(deadID-1).counter]);
						}
						continue;
					}
					Database.printGraph();
=======

>>>>>>> branch 'master' of https://github.com/Gpapana/EPL446.git
					break;
				}
				ts=Database.execute(ts, id, par, i+restart);
				//////////////////////////////////////////////////

				System.out.println("ts="+ts);
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
		//Database.freeLocks(id);
		synchronized (this) {Database.wake();}
	}

	///////////////////////////////////////////////////////////////////////

}