package epl446_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dbGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
////		String TSdata[][]=new String[26][3];
////		for(int i=0;i<26;i++) {
////			char temp=(char)(i+65);
////				TSdata[i][0]=String.valueOf(temp);
////			
////		}
////		for(int i=0;i<26;i++) {
////			for(int j=1;j<3;j++) {
////				TSdata[i][j]="sdaf";
////			}
////		}
////		String[] columnNames = {"Resource",
////                "MaxReadTS",
////                "MaxWriteTS",};
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					dbGUI frame = new dbGUI(TSdata,columnNames);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param columnNames 
	 */
	public dbGUI(String TSdata[][], String[] columnNames) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLog = new JLabel("LOG");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLog.setBounds(56, 56, 42, 14);
		contentPane.add(lblLog);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 92, 134, 126);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText("adfsgasrfsa\nasdfgadsgard\nasdfgadrgaerg\naedrgsthgeartgsartgarweg\nadfsgasrfsa\nasdfgadsgard\nasdfgadrgaerg\naedrgsthgeartgsartgarweg\n");
		
		JLabel lblNewTransaction = new JLabel("New Transaction");
		lblNewTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewTransaction.setBounds(243, 56, 141, 14);
		contentPane.add(lblNewTransaction);
		
		JLabel lblNewLabel = new JLabel("sfdth");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(243, 94, 154, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(9, 351, 134, 23);
		contentPane.add(btnNext);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(9, 421, 134, 23);
		contentPane.add(btnFinish);
		

		contentPane.add(btnFinish);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JLabel lblLocks = new JLabel("Locks");
		lblLocks.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocks.setBounds(196, 187, 135, 31);
		contentPane.add(lblLocks);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(196, 245, 154, 111);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblNewLabel_1 = new JLabel("Timestamps");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(458, 195, 146, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(387, 234, 230, 157);
		contentPane.add(scrollPane_2);
		
	
		table_1 = new JTable(TSdata,columnNames);
		scrollPane_2.setViewportView(table_1);
	
		
	}
}
