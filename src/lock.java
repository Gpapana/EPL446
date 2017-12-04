
public class lock {
	int client;		//id
	int ts;			//timestamp
	char fileName;	//file effected	
	int position;	//position of the file effected
	char state; 	// S-Share, X-Exclusive
}
