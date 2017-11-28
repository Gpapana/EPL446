package epl446_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
	 * Create the frame.
	 * @param columnNames 
	 */
	public dbGUI(String TSdata[][], String[] columnNames) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLog = new JLabel("LOG");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLog.setBounds(53, 32, 164, 14);
		contentPane.add(lblLog);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 57, 260, 313);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText(Database.log.toString());

		JLabel lblNewTransaction = new JLabel("New Transaction");
		lblNewTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewTransaction.setBounds(696, 32, 164, 14);
		contentPane.add(lblNewTransaction);

		JLabel lblTransaction = new JLabel("transcaction");
		lblTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransaction.setBounds(696, 72, 164, 14);
		contentPane.add(lblTransaction);






		JLabel lblLocks = new JLabel("DB Response");
		lblLocks.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocks.setBounds(424, 24, 135, 31);
		contentPane.add(lblLocks);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(350, 57, 260, 313);
		contentPane.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setEditable(false);

		JLabel lblNewLabel_1 = new JLabel("Timestamps");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(696, 120, 164, 14);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(645, 157, 253, 213);
		contentPane.add(scrollPane_2);

		DefaultTableModel model=new DefaultTableModel(TSdata,columnNames);
		//table_1 = new JTable(TSdata,columnNames);
		table_1=new JTable();
		table_1.setModel(model);
		scrollPane_2.setViewportView(table_1);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				lblTransaction.setText("");
				//
				textArea.setText("");
				for(int i=0; i<TSdata.length;i++){
					for(int j=0;j<TSdata[0].length;j++){
						textArea.append(TSdata[i][j]);
					}
					textArea.append("\n");
				}
				//
				textArea_1.setText("");
				for(int i=0; i<TSdata.length;i++){
					for(int j=0;j<TSdata[0].length;j++){
						textArea_1.append(TSdata[i][j]);
					}
					textArea_1.append("\n");
				}
				//
				TSdata[0][1]="23";
				DefaultTableModel model1=new DefaultTableModel(TSdata,columnNames);
				table_1.setModel(model1);

				////
			}
		});
		btnNext.setBounds(25, 416, 134, 23);
		contentPane.add(btnNext);


		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(461, 416, 134, 23);
		contentPane.add(btnFinish);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
}
