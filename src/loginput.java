

public class loginput {
	int id;					//current timestamp
	int transactionNum;		//client number
	char command;			//type of command B,R,W,D,C,A
	char document;			//Document effected
	int position;			//position in document effected
	char value;				//value for write/read
	int TS;					//clients timestamp
	
	public String toString(){
		if (command=='B' || command=='C' || command=='A'){
			return "TS= "+id+" "+"client "+String.valueOf(transactionNum)+" "+String.valueOf(command)+"\n";
		}
		else if (command=='D'){
			return "TS= "+id+" "+"client "+String.valueOf(transactionNum)+" "+String.valueOf(command)+" "+String.valueOf(document)+"\n";
		}
		else if (command=='W' || command=='R'){
			return "TS= "+id+" "+"client "+String.valueOf(transactionNum)+" "+String.valueOf(command)
			+" "+String.valueOf(document)+" "+String.valueOf(position)+" "+String.valueOf(value)+"\n";
		}
		return "";
		
	}
	
}
