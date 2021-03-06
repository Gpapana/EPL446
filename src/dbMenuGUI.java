import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class dbMenuGUI extends JFrame implements ActionListener, ChangeListener {

	static String defaultD = "default";
	static String WoundWaitCommand = "wound";
	static String WaitDieCommand = "die";
	static String CautiousWaitingCommand = "cautious";
	static int ClientsNum;
	private JPanel contentPane;
	static int deadlockFun;
	public static boolean auto = false;
	public static ArrayList<clientGUI> clientsGUI = new ArrayList<clientGUI>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param columnNames
	 * @param pressed
	 * @param tSdata
	 */
	public dbMenuGUI(String[][] TSdata, String[] columnNames) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeadlockType = new JLabel("Deadlock Type");
		lblDeadlockType.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeadlockType.setBounds(25, 35, 139, 14);
		contentPane.add(lblDeadlockType);

		JPanel panel_1 = new JPanel(new GridLayout(0, 1));
		panel_1.setBounds(33, 72, 149, 89);
		contentPane.add(panel_1);

		// group for deadlocks

		ButtonGroup group1 = new ButtonGroup();
		JRadioButton rdbtnDefault = new JRadioButton("Default", true);
		panel_1.add(rdbtnDefault);
		rdbtnDefault.setActionCommand(defaultD);
		group1.add(rdbtnDefault);
		rdbtnDefault.addActionListener(this);

		JRadioButton rdbtnWoundwait = new JRadioButton("Wound/Wait");
		panel_1.add(rdbtnWoundwait);
		rdbtnWoundwait.setActionCommand(WoundWaitCommand);
		group1.add(rdbtnWoundwait);
		rdbtnWoundwait.addActionListener(this);

		JRadioButton rdbtnWaitdie = new JRadioButton("Wait/Die");
		panel_1.add(rdbtnWaitdie);
		rdbtnWaitdie.setActionCommand(WaitDieCommand);
		group1.add(rdbtnWaitdie);
		rdbtnWaitdie.addActionListener(this);

		JRadioButton rdbtnCautiousWaiting = new JRadioButton("Cautious Waiting");
		panel_1.add(rdbtnCautiousWaiting);
		rdbtnCautiousWaiting.setActionCommand(CautiousWaitingCommand);
		group1.add(rdbtnCautiousWaiting);
		rdbtnCautiousWaiting.addActionListener(this);

		// number of clients
		JLabel lblNumberOfCliends = new JLabel("Number of Clients");
		lblNumberOfCliends.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfCliends.setBounds(25, 230, 257, 23);
		contentPane.add(lblNumberOfCliends);

		JSlider slider = new JSlider();
		slider.setBounds(25, 277, 359, 57);
		contentPane.add(slider);
		slider.setMinimum(2);
		slider.setMaximum(10);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.addChangeListener(this);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(2);

		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dbMenuGUI.this.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							dbGUI frame = new dbGUI(TSdata, columnNames);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				String command = group1.getSelection().getActionCommand();
				System.out.println(command);
				int numofClients = slider.getValue();
				System.out.println(numofClients);
				//int temp = 410;
				int temp = 100;
				int temp2 = 0;
				int temp3=600;
				for (int i = 0; i < numofClients; i++) {

					String name = "Client" + (i + 1);
					int id = i + 1;
				
					if (i == numofClients / 4) {
//						temp = 580;
						temp = 270;
						temp2 = 0;
					}
					if (i == (numofClients / 4) * 2) {
						//temp = 750;
						temp = 420;
						temp2 = 0;
						temp3=0;
					}
					
					if (i == (numofClients / 3) * 2) {
						//temp = 750;
						temp = 600;
						temp2 = 0;
						temp3=0;
					}
					//int x = 10 + temp2 * 250;
					//int y = temp;
					int x = temp3 + temp2 * 250;
					int y = temp;
					temp2++;
					int pos = i;
					if (!auto) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									clientsGUI.add(new clientGUI(x, y, TSdata, columnNames, id));
									clientsGUI.get(pos).setVisible(true);
									clientsGUI.get(pos).setTitle(name);

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					}
				}
				Database.startpressed = true;

			}

		});
		btnStart.setBounds(26, 389, 89, 23);
		contentPane.add(btnStart);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(164, 389, 89, 23);
		contentPane.add(btnExit);

		JCheckBox chckbxAuto = new JCheckBox("Auto");
		chckbxAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auto = chckbxAuto.isSelected();
			}
		});
		chckbxAuto.setBounds(259, 72, 119, 23);
		contentPane.add(chckbxAuto);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(defaultD)) {
			// action
			System.out.println("default");
			deadlockFun = 0;
		} else if (e.getActionCommand().equals(WoundWaitCommand)) {
			// action
			System.out.println("wound");
			deadlockFun = 1;
		} else if (e.getActionCommand().equals(WaitDieCommand)) {
			// action
			System.out.println("die");
			deadlockFun = 2;
		}

		else if (e.getActionCommand().equals(CautiousWaitingCommand)) {
			// action
			System.out.println("cautious");
			deadlockFun = 3;
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			ClientsNum = (int) source.getValue();
			System.out.println(ClientsNum);
		}
	}
}