

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

public class dbMenuGUI extends JFrame implements ActionListener, ChangeListener {

	static String defaultD="default";
	static String WoundWaitCommand="wound";
	static String WaitDieCommand="die";
	static String CautiousWaitingCommand="cautious";
	static int ClientsNum;
	private JPanel contentPane;
	static int deadlockFun;
	static clientGUI frame1;
	public static ArrayList<clientGUI> clientsGUI = new ArrayList<clientGUI>();
	//public static String TSdata[][];

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//
	//		TSdata=new String[26][3];
	//		for(int i=0;i<26;i++) {
	//			char temp=(char)(i+65);
	//			TSdata[i][0]=String.valueOf(temp);
	//
	//		}
	//		for(int i=0;i<26;i++) {
	//			for(int j=1;j<3;j++) {
	//				TSdata[i][j]="0";
	//			}
	//		}
	//		String[] columnNames = {"Resource",
	//				"MaxReadTS",
	//				"MaxWriteTS",};
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					dbMenuGUI frame = new dbMenuGUI(TSdata,columnNames);
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//
	//
	//	}

	/**
	 * Create the frame.
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

		//group for deadlocks

		ButtonGroup group1=new ButtonGroup();
		JRadioButton rdbtnDefault = new JRadioButton("Default",true);
		panel_1.add(rdbtnDefault);
		rdbtnDefault.setActionCommand(defaultD);
		group1.add(rdbtnDefault);
		rdbtnDefault.addActionListener(this);

		JRadioButton rdbtnWaitdie = new JRadioButton("Wait/Die");
		panel_1.add(rdbtnWaitdie);
		rdbtnWaitdie.setActionCommand(WaitDieCommand);
		group1.add(rdbtnWaitdie);
		rdbtnWaitdie.addActionListener(this);

		JRadioButton rdbtnWoundwait = new JRadioButton("Wound/Wait");
		panel_1.add(rdbtnWoundwait);
		rdbtnWoundwait.setActionCommand(WoundWaitCommand);
		group1.add(rdbtnWoundwait);
		rdbtnWoundwait.addActionListener(this);


		JRadioButton rdbtnCautiousWaiting = new JRadioButton("Cautious Waiting");
		panel_1.add(rdbtnCautiousWaiting);
		rdbtnCautiousWaiting.setActionCommand(CautiousWaitingCommand);
		group1.add(rdbtnCautiousWaiting);
		rdbtnCautiousWaiting.addActionListener(this);


		//number of clients
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
				String command=	group1.getSelection().getActionCommand();
				System.out.println(command);
				int numofClients=slider.getValue();
				System.out.println(numofClients);
				int temp=650;
				int temp2=0;
				for(int i=0;i<numofClients;i++){

					String name="Client"+(i+1);
					int id=i+1;
					if(i==numofClients/2){
						temp=850;
						temp2=0;
					}
					int x=10+temp2*300;
					int y =temp;
					temp2++;
					int pos=i;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								clientGUI frame = new clientGUI(x,y,TSdata,columnNames,id);
								frame.setVisible(true);
								frame.setTitle(name);
								clientsGUI.add(new clientGUI(x,y,TSdata,columnNames,id));
								//frame1 = new clientGUI(x,y,TSdata,columnNames,id);
								clientsGUI.get(pos).setVisible(true);
								clientsGUI.get(pos).setTitle(name);
								//frame1.setVisible(true);
								//frame1.setTitle(name);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

				}

				Database.startpressed=true;

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






	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(defaultD)){
			//action
			System.out.println("default");
			deadlockFun=0;
		}	

		else if(e.getActionCommand().equals(WaitDieCommand)){
			//action
			System.out.println("die");
			deadlockFun=1;
		}
		else if(e.getActionCommand().equals(WoundWaitCommand)){
			//action
			System.out.println("wound");
			deadlockFun=2;
		}
		else if(e.getActionCommand().equals(CautiousWaitingCommand)){
			//action
			System.out.println("cautious");
			deadlockFun=3;
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source=(JSlider) e.getSource();
		if(!source.getValueIsAdjusting()){
			ClientsNum=(int)source.getValue();
			System.out.println(ClientsNum);
		}
	}
}
