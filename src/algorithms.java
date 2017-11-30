public class algorithms {

	public static int wound_wait(int TSi,int TSj){
		int DESi;
		if(TSi>TSj){
			DESi=1;//wait
		}
		else{
			//DESj=1;//kill (j)
			DESi=0;
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

	public static int readOP(int pos,int []maxRead,int []maxWrite,int des,int TS){
		if(TS<maxWrite[pos]){
			des=0;
		}
		else{
			des=1;
			maxRead[pos]=TS;
		}
		return des;
	}

	public static int writeOP(int pos,int []maxRead,int []maxWrite,int des,int TS){
		if(TS<maxRead[pos]||TS<maxWrite[pos]){
			des=0;
		}
		else{
			des=1;
			maxWrite[pos]=TS;
		}
		return des;
	}
}