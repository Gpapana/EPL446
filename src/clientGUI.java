

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
		setBounds(x, y, 218, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNext = new JButton("NEXT");
		btnNext.setBounds(23, 83, 130, 25);
		contentPane.add(btnNext);

		JLabel lblCurrentTransaction = new JLabel("current transaction");
		lblCurrentTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTransaction.setBounds(24, 11, 135, 14);
		contentPane.add(lblCurrentTransaction);

		JLabel lblTransaction = new JLabel("");
		lblTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransaction.setBounds(23, 36, 140, 25);
		contentPane.add(lblTransaction);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				client.pressed=true;
//				String str = "";
//				for(int i=0;i<Database.log.size();i++){
//					str=str+Database.log.get(i).toString();
//				}
//				
//				dbGUI.textArea.setText(str);
				dbGUI.lblTransaction.setText("");

				TSdata[0][1]="23";
				DefaultTableModel model1=new DefaultTableModel(TSdata,columnNames);
				dbGUI.table_1.setModel(model1);
				
				cid=clientID;
				System.out.println("client GUI id="+clientID);
				//testing1.pressed=true;
				
				////
			
			}
		});
	}
}
