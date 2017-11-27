
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	static String ImmediateCommand="immediate";
	static String DefferedCommand="deffered";
	static String WoundWaitCommand="wound";
	static String WaitDieCommand="die";
	static String CautiousWaitingCommand="cautious";
	static int ClientsNum;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		String TSdata[][]=new String[26][3];
		for(int i=0;i<26;i++) {
			char temp=(char)(i+65);
			TSdata[i][0]=String.valueOf(temp);

		}
		for(int i=0;i<26;i++) {
			for(int j=1;j<3;j++) {
				TSdata[i][j]="sdaf";
			}
		}
		String[] columnNames = {"Resource",
				"MaxReadTS",
				"MaxWriteTS",};
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dbMenuGUI frame = new dbMenuGUI(TSdata,columnNames);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param columnNames 
	 * @param tSdata 
	 */
	public dbMenuGUI(String[][] tSdata, String[] columnNames) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUpdateType = new JLabel("Update Type");
		lblUpdateType.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateType.setBounds(25, 35, 157, 14);
		contentPane.add(lblUpdateType);

		JLabel lblDeadlockType = new JLabel("Deadlock Type");
		lblDeadlockType.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeadlockType.setBounds(443, 35, 139, 14);
		contentPane.add(lblDeadlockType);

		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBounds(45, 76, 137, 71);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel(new GridLayout(0, 1));
		panel_1.setBounds(433, 76, 149, 89);
		contentPane.add(panel_1);


		//group for updates
		JRadioButton rdbtnImmediateUpdate = new JRadioButton("Immediate Update");
		rdbtnImmediateUpdate.setBounds(45, 190, 113, 23);
		panel.add(rdbtnImmediateUpdate);
		rdbtnImmediateUpdate.setActionCommand(ImmediateCommand);


		JRadioButton rdbtnDeferredUpdate = new JRadioButton("Deferred Update");
		rdbtnDeferredUpdate.setBounds(57, 245, 107, 23);
		panel.add(rdbtnDeferredUpdate);
		rdbtnDeferredUpdate.setActionCommand(DefferedCommand);


		ButtonGroup group1=new ButtonGroup();
		group1.add(rdbtnImmediateUpdate);
		group1.add(rdbtnDeferredUpdate);


		//group for deadlocks
		JRadioButton rdbtnWoundwait = new JRadioButton("Wound/Wait");
		rdbtnWoundwait.setBounds(443, 184, 85, 23);
		panel_1.add(rdbtnWoundwait);
		rdbtnWoundwait.setActionCommand(WoundWaitCommand);


		JRadioButton rdbtnWaitdie = new JRadioButton("Wait/Die");
		rdbtnWaitdie.setBounds(466, 210, 67, 23);
		panel_1.add(rdbtnWaitdie);
		rdbtnWaitdie.setActionCommand(WaitDieCommand);


		JRadioButton rdbtnCautiousWaiting = new JRadioButton("Cautious Waiting");
		rdbtnCautiousWaiting.setBounds(448, 262, 107, 23);
		panel_1.add(rdbtnCautiousWaiting);
		rdbtnCautiousWaiting.setActionCommand(CautiousWaitingCommand);


		ButtonGroup group2=new ButtonGroup();
		group2.add(rdbtnWoundwait);
		group2.add(rdbtnWaitdie);
		group2.add(rdbtnCautiousWaiting);





		//		JPanel panel1=new JPanel(new GridLayout(5,1));
		//		panel1.add(rdbtnImmediateUpdate);
		//		panel1.add(rdbtnDeferredUpdate);


		//actions listeners
		rdbtnImmediateUpdate.addActionListener(this);
		rdbtnDeferredUpdate.addActionListener(this);
		rdbtnWoundwait.addActionListener(this);
		rdbtnWaitdie.addActionListener(this);
		rdbtnCautiousWaiting.addActionListener(this);



		//number of clients
		JLabel lblNumberOfCliends = new JLabel("Number of Clients");
		lblNumberOfCliends.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfCliends.setBounds(25, 230, 257, 23);
		contentPane.add(lblNumberOfCliends);


		JSlider slider = new JSlider();
		slider.setBounds(25, 277, 359, 57);
		contentPane.add(slider);
		slider.setMinimum(3);
		slider.setMaximum(10);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.addChangeListener(this);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(3);

		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbMenuGUI.this.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							dbGUI frame = new dbGUI(tSdata, columnNames);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
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
		if(e.getActionCommand().equals(ImmediateCommand)){
			//action
			System.out.println("immediate");
		}
		else if(e.getActionCommand().equals(DefferedCommand)){
			//action
			System.out.println("deffered");
		}
		else if(e.getActionCommand().equals(WoundWaitCommand)){
			//action
			System.out.println("wound");
		}
		else if(e.getActionCommand().equals(WaitDieCommand)){
			//action
			System.out.println("die");
		}
		else if(e.getActionCommand().equals(CautiousWaitingCommand)){
			//action
			System.out.println("cautious");
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
