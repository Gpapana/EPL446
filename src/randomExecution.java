import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class randomExecution {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Random num = new Random();
		int numClient=num.nextInt(8)+3;

		for(int i=0;i<numClient;i++){
			String filename="clients/Client";
			String fnum=new Integer(i+1).toString();
			String fullname=filename+fnum+".txt";
			File f=new File(fullname);
			PrintWriter writer=new PrintWriter(f);
			writer.println("B");
			int Tnum=num.nextInt(18)+3;
			String Ts[][]=new String[Tnum][4];
			for(int z=0;z<Tnum;z++){
				for(int w=0;w<4;w++){
					Ts[z][w]=" ";
				}
			}
			for(int j=0;j<Tnum;j++){
				/*int T=num.nextInt(3);
				switch(T){
				case 0: Ts[j][0]="R";
				break;
				case 1: Ts[j][0]="W";
				break;
				default: Ts[j][0]="D";
				break;
				}*/
				double T=num.nextDouble();

				if(T>=0.9){
					Ts[j][0]="D";
				}
				else if(T>=0.6){
					Ts[j][0]="W";
				}
				else{
					Ts[j][0]="R";
				}
				int data=num.nextInt(26)+65;
				char temp=(char)data;
				Ts[j][1]=Character.toString(temp);
				if(!Ts[j][0].equals("D")){
					int pos=num.nextInt(256);
					Ts[j][2]= Integer.toString(pos);
				}
				if(Ts[j][0].equals("W")){
					int caseupper=num.nextInt(2);
					if(caseupper==1){
						data=num.nextInt(26)+65;
						temp=(char)data;
						Ts[j][3]=Character.toString(temp);
					}
					else{
						data=num.nextInt(26)+97;
						temp=(char)data;
						Ts[j][3]=Character.toString(temp);
					}

				}
				for(int k=0;k<4;k++){
					writer.print(Ts[j][k]+" ");
				}
				writer.println();
			}


			double com=num.nextDouble();
			if(com<0.7){
				writer.print("C");
			}
			else{
				writer.print("A");
			}
			writer.close();
		}



	}

}