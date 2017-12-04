
public class algorithms {

	public static int wound_wait(int TSi,int TSj){
		int DESi;
		if(TSi>TSj){
			DESi=1;//wait
		}
		else{
			DESi=0;//kill (j)
		}

		return DESi;
	}

	public static int wait_die(int TSi,int TSj){
		int DESi;
		if(TSi<TSj){
			DESi=1;//wait
		}
		else{
			DESi=0;//(i) dies 
		}
		return DESi;
	}
}
