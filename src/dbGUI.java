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
	public static JTextArea textArea;
	public static JLabel lblTransaction;
	public static JTextArea textArea_1;
	

	/**
	 * Create the frame.
	 * @param columnNames 
	 */
	public dbGUI(String TSdata[][], String[] columnNames) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 25, 551, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLog = new JLabel("LOG");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLog.setBounds(53, 32, 64, 14);
		contentPane.add(lblLog);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 200, 200);
		contentPane.add(scrollPane);

		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
//		String str = "";
//		for(int i=0;i<Database.log.size();i++){
//			str=str+Database.log.get(i).toString();
//		}
//		dbGUI.textArea.setText(str);

		JLabel lblNewTransaction = new JLabel("New Transaction");
		lblNewTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewTransaction.setBounds(178, 303, 135, 14);
		contentPane.add(lblNewTransaction);

		lblTransaction = new JLabel("transcaction");
		lblTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransaction.setBounds(300, 303, 164, 14);
		contentPane.add(lblTransaction);

		JLabel lblLocks = new JLabel("DB Response");
		lblLocks.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocks.setBounds(280, 24, 135, 31);
		contentPane.add(lblLocks);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(250, 50, 200, 200);
		contentPane.add(scrollPane_1);

		 textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setText("");

		DefaultTableModel model=new DefaultTableModel(TSdata,columnNames);

		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(20, 299, 134, 23);
		contentPane.add(btnFinish);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
}