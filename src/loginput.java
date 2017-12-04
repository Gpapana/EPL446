

public class loginput {
	int id;
	int transactionNum;
	char command;
	char document;
	int position;
	char value;
	int TS;
	
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
