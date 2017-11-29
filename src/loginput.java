

public class loginput {
	int id;
	int transactionNum;
	char command;
	char document;
	int position;
	char value;
	int TS;
	int status; // 0=unlocked 1=readlock 2=writelock
	loginput lockedby;
	
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
	
	public char getCommand() {
		return command;
	}
	public void setCommand(char command) {
		this.command = command;
	}
	public char getDocument() {
		return document;
	}
	public void setDocument(char document) {
		this.document = document;
	}
	public int getDocposition() {
		return position;
	}
	public void setDocposition(int docposition) {
		this.position = docposition;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTS() {
		return TS;
	}
	public void setTS(int tS) {
		TS = tS;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public loginput getLockedby() {
		return lockedby;
	}
	public void setLockedby(loginput lockedby) {
		this.lockedby = lockedby;
	}
	public int getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}
}
