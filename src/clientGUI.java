

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class clientGUI extends JFrame {

	private JPanel contentPane;
	public static JButton btnNext;
	public int clientID;
	public static int cid;
	//public static  int TSc;
	public int TimeStamp;
	int counter=0;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					clientGUI frame = new clientGUI();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 * @param pressed 
	 */
	public clientGUI(int x,int y,String[][]TSdata,String columnNames[],int id) {

		clientID=id;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 229, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNext = new JButton("NEXT");
		btnNext.setBounds(23, 83, 130, 25);
		contentPane.add(btnNext);

		JLabel lblCurrentTransaction = new JLabel("current transaction");
		lblCurrentTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTransaction.setBounds(10, 11, 119, 14);
		contentPane.add(lblCurrentTransaction);

		JLabel lblTransaction = new JLabel("");
		lblTransaction.setBounds(20, 36, 103, 14);
		contentPane.add(lblTransaction);

		JLabel lblTs = new JLabel("");
		lblTs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTs.setBounds(142, 36, 46, 14);
		contentPane.add(lblTs);

		JLabel lblTimestamp = new JLabel("TS");
		lblTimestamp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimestamp.setBounds(132, 11, 46, 14);
		contentPane.add(lblTimestamp);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				client.pressed=true;



				TSdata[0][1]="23";
				DefaultTableModel model1=new DefaultTableModel(TSdata,columnNames);
				dbGUI.table_1.setModel(model1);

				cid=clientID;
				//System.out.println("client GUI id="+clientID);

				lblTransaction.setText(Database.actions[clientID-1][counter]);
				dbGUI.lblTransaction.setText("Client "+clientID+" "+Database.actions[clientID-1][counter]);
				if(Database.actions[clientID-1][counter].equals("C")||Database.actions[clientID-1][counter].equals("A")){
					dbMenuGUI.clientsGUI.get(clientID-1).setVisible(false);
				}

				counter++;

				TimeStamp=Database.c[cid-1].ts;
				lblTs.setText(String.valueOf(TimeStamp));
				////

			}
		});
	}
}
