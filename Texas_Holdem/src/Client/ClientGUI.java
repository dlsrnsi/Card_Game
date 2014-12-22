package Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ClientGUI {

	final JPanel panel_Textplay = new JPanel();
	JMenuItem mntmStart;
	JMenuItem mntmOption;
	JMenuItem mntmExit;
	JMenuItem mntmInformation;
	List<JTextArea> card1List;
	JTextArea Player1_Card1;
	JTextArea Player2_Card1;
	JTextArea Player3_Card1;
	JTextArea Player4_Card1;
	JTextArea Player5_Card1;
	JTextArea Player6_Card1;
	List<JTextArea> card2List;
	JTextArea Player1_Card2;
	JTextArea Player2_Card2;
	JTextArea Player3_Card2;
	JTextArea Player4_Card2;
	JTextArea Player5_Card2;
	JTextArea Player6_Card2;
	List<JTextArea> tableCardList;
	JTextArea TableCard1;
	JTextArea TableCard2;
	JTextArea TableCard3;
	JTextArea TableCard4;
	JTextArea TableCard5;
	JTextArea StateText;
	List<JLabel> playerStatusList;
	JLabel PlayerStatus1;
	JLabel PlayerStatus2;
	JLabel PlayerStatus3;
	JLabel PlayerStatus4;
	JLabel PlayerStatus5;
	JLabel PlayerStatus6;
	List<JLabel> playerNameList;
	JLabel PlayerName1;
	JLabel PlayerName2;
	JLabel PlayerName3;
	JLabel PlayerName4;
	JLabel PlayerName5;
	JLabel PlayerName6;
	JLabel TableStatus;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void setframe(String str) {
		StateText.setText(StateText.getText() + str + "\n");
	}

	public String getShape(int card) {
		switch (card % 4) {
		case 0:
			return "Clover";
		case 1:
			return "Diamond";
		case 2:
			return "Spade";
		case 3:
			return "Heart";
		default:
			return "";
		}
	}

	public String getNumber(int card) {
		switch (card / 4) {
		case 0:
			return "2";
		case 1:
			return "3";
		case 2:
			return "4";
		case 3:
			return "5";
		case 4:
			return "6";
		case 5:
			return "7";
		case 6:
			return "8";
		case 7:
			return "9";
		case 8:
			return "10";
		case 9:
			return "J";
		case 10:
			return "Q";
		case 11:
			return "K";
		case 12:
			return "A";
		default:
			return "";

		}
	}

	// userCard/userNum/card
	public void setframeUserCard(String str) {
		int userNum = Integer.parseInt(str.split("/")[1]);
		int card = Integer.parseInt(str.split("/")[2]);
		if (card1List.get(userNum).getText().equals(""))
			card1List.get(userNum).setText(
					getShape(card) + "_" + getNumber(card));
		else
			card2List.get(userNum).setText(
					getShape(card) + "_" + getNumber(card));
	}

	public void setframeUserState(String str) {
		String userNum = str.split("/")[1];
		String state = str.split("/")[2];
		playerStatusList.get(Integer.parseInt(userNum)).setText(
				"State : " + state);
	}

	public void setframeUserMoney(String str) {
		String userNum = str.split("/")[1];
		String money = str.split("/")[2];
		playerStatusList.get(Integer.parseInt(userNum)).setText(
				"Money : " + money);
	}

	public void setframetableCard(String str) {
		int i = Integer.parseInt(str.split("/")[1]);
		String card = getShape(i)+"_"+getNumber(i);
		if(tableCardList.get(0).getText().equals("")){
			tableCardList.get(0).setText(card);
		}
		else if(tableCardList.get(0).getText().equals(card)){	}
		else if(tableCardList.get(1).getText().equals("")){
			tableCardList.get(1).setText(card);
		}
		else if(tableCardList.get(1).getText().equals(card)){	}
		else if(tableCardList.get(2).getText().equals("")){
			tableCardList.get(2).setText(card);
		}
		else if(tableCardList.get(2).getText().equals(card)){	}
		else if(tableCardList.get(3).getText().equals("")){
			tableCardList.get(3).setText(card);
		}
		else if(tableCardList.get(3).getText().equals(card)){	}
		else if(tableCardList.get(4).getText().equals("")){
			tableCardList.get(4).setText(card);
		}
		else if(tableCardList.get(4).getText().equals(card)){	}
	}

	public void setframetableMoney(String str) {
		String money = str.split("/")[1];
		TableStatus.setText("Money : " + money);
	}

	public void setframeuserName(String str) {
		String userNum = str.split("/")[1];
		String name = str.split("/")[2];
		playerNameList.get(Integer.parseInt(userNum)).setText(name);
	}

	public void Myframe(Thread thread) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 933, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		mntmStart = new JMenuItem("Start");
		mntmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((SenderThread) thread).setstr("StartGame/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnGame.add(mntmStart);

		mntmOption = new JMenuItem("Option");
		mnGame.add(mntmOption);
		mntmOption.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame frame1 = new JFrame();
				JPanel contentPane;
				JTextField textField;
				frame1.setTitle("Change Minimal Bet");
				frame1.setBounds(100, 100, 300, 89);
				frame1.setVisible(true);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame1.setContentPane(contentPane);
				contentPane.setLayout(new BorderLayout(0, 0));
				textField = new JTextField();
				contentPane.add(textField, BorderLayout.CENTER);
				textField.setColumns(10);
				JButton button = new JButton("\uD655\uC778");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int minimalBet = Integer.parseInt(textField.getText());
						try {
							((SenderThread) thread).setstr("ChangeOption/"
									+ minimalBet);
							frame1.dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				contentPane.add(button, BorderLayout.EAST);

			}
		});
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnGame.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmInformation = new JMenuItem("Information");
		mnHelp.add(mntmInformation);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.add(panel_33);
		panel_33.setLayout(new BorderLayout(0, 0));

		JPanel panel_36 = new JPanel();
		panel_33.add(panel_36, BorderLayout.NORTH);

		PlayerName4 = new JLabel("");
		panel_36.add(PlayerName4);

		JPanel panel_37 = new JPanel();
		panel_33.add(panel_37, BorderLayout.SOUTH);
		panel_37.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_39 = new JPanel();
		panel_37.add(panel_39);

		Player4_Card1 = new JTextArea();
		Player4_Card1.setRows(5);
		Player4_Card1.setColumns(6);
		panel_39.add(Player4_Card1);

		JPanel panel_40 = new JPanel();
		panel_37.add(panel_40);

		Player4_Card2 = new JTextArea();
		Player4_Card2.setRows(5);
		Player4_Card2.setColumns(6);
		panel_40.add(Player4_Card2);

		JPanel panel_38 = new JPanel();
		panel_33.add(panel_38, BorderLayout.CENTER);

		PlayerStatus4 = new JLabel("");
		panel_38.add(PlayerStatus4);

		JPanel panel_34 = new JPanel();
		panel_34.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.add(panel_34);
		panel_34.setLayout(new BorderLayout(0, 0));

		JPanel panel_41 = new JPanel();
		panel_34.add(panel_41, BorderLayout.NORTH);

		PlayerName5 = new JLabel("");
		panel_41.add(PlayerName5);

		JPanel panel_42 = new JPanel();
		panel_34.add(panel_42, BorderLayout.SOUTH);
		panel_42.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_44 = new JPanel();
		panel_42.add(panel_44);

		Player5_Card1 = new JTextArea();
		Player5_Card1.setRows(5);
		Player5_Card1.setColumns(6);
		panel_44.add(Player5_Card1);

		JPanel panel_45 = new JPanel();
		panel_42.add(panel_45);

		Player5_Card2 = new JTextArea();
		Player5_Card2.setRows(5);
		Player5_Card2.setColumns(6);
		panel_45.add(Player5_Card2);

		JPanel panel_43 = new JPanel();
		panel_34.add(panel_43, BorderLayout.CENTER);

		PlayerStatus5 = new JLabel("");
		panel_43.add(PlayerStatus5);

		JPanel panel_35 = new JPanel();
		panel_35.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.add(panel_35);
		panel_35.setLayout(new BorderLayout(0, 0));

		JPanel panel_46 = new JPanel();
		panel_35.add(panel_46, BorderLayout.NORTH);

		PlayerName6 = new JLabel("");
		panel_46.add(PlayerName6);

		JPanel panel_47 = new JPanel();
		panel_35.add(panel_47, BorderLayout.SOUTH);
		panel_47.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_49 = new JPanel();
		panel_47.add(panel_49);

		Player6_Card1 = new JTextArea();
		Player6_Card1.setRows(5);
		Player6_Card1.setColumns(6);
		panel_49.add(Player6_Card1);

		JPanel panel_50 = new JPanel();
		panel_47.add(panel_50);

		Player6_Card2 = new JTextArea();
		Player6_Card2.setRows(5);
		Player6_Card2.setColumns(6);
		panel_50.add(Player6_Card2);

		JPanel panel_48 = new JPanel();
		panel_35.add(panel_48, BorderLayout.CENTER);

		PlayerStatus6 = new JLabel("");
		panel_48.add(PlayerStatus6);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(panel_7, BorderLayout.CENTER);

		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);

		TableCard1 = new JTextArea();
		TableCard1.setRows(5);
		TableCard1.setColumns(6);
		panel_8.add(TableCard1);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);

		TableCard2 = new JTextArea();
		TableCard2.setRows(5);
		TableCard2.setColumns(6);
		panel_9.add(TableCard2);

		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10);

		TableCard3 = new JTextArea();
		TableCard3.setRows(5);
		TableCard3.setColumns(6);
		panel_10.add(TableCard3);

		JPanel panel_11 = new JPanel();
		panel_7.add(panel_11);

		TableCard4 = new JTextArea();
		TableCard4.setRows(5);
		TableCard4.setColumns(6);
		panel_11.add(TableCard4);

		JPanel panel_12 = new JPanel();
		panel_7.add(panel_12);

		TableCard5 = new JTextArea();
		TableCard5.setRows(5);
		TableCard5.setColumns(6);
		panel_12.add(TableCard5);

		TableStatus = new JLabel("");
		panel_7.add(TableStatus);

		JPanel panel_13 = new JPanel();
		panel.add(panel_13, BorderLayout.NORTH);
		panel_13.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_13.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_19 = new JPanel();
		panel_15.add(panel_19, BorderLayout.SOUTH);
		panel_19.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_20 = new JPanel();
		panel_19.add(panel_20);

		Player1_Card1 = new JTextArea();
		Player1_Card1.setRows(5);
		Player1_Card1.setColumns(6);
		panel_20.add(Player1_Card1);

		JPanel panel_21 = new JPanel();
		panel_19.add(panel_21);

		Player1_Card2 = new JTextArea();
		Player1_Card2.setRows(5);
		Player1_Card2.setColumns(6);
		panel_21.add(Player1_Card2);

		JPanel PlayerName = new JPanel();
		panel_15.add(PlayerName, BorderLayout.NORTH);

		PlayerName1 = new JLabel("");
		PlayerName.add(PlayerName1);

		JPanel panel_18 = new JPanel();
		panel_15.add(panel_18, BorderLayout.CENTER);

		PlayerStatus1 = new JLabel("");
		panel_18.add(PlayerStatus1);

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_13.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_22 = new JPanel();
		panel_16.add(panel_22, BorderLayout.NORTH);

		PlayerName2 = new JLabel("");
		panel_22.add(PlayerName2);

		JPanel panel_23 = new JPanel();
		panel_16.add(panel_23, BorderLayout.SOUTH);
		panel_23.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_26 = new JPanel();
		panel_23.add(panel_26);

		Player2_Card1 = new JTextArea();
		Player2_Card1.setRows(5);
		Player2_Card1.setColumns(6);
		panel_26.add(Player2_Card1);

		JPanel panel_27 = new JPanel();
		panel_23.add(panel_27);

		Player2_Card2 = new JTextArea();
		Player2_Card2.setRows(5);
		Player2_Card2.setColumns(6);
		panel_27.add(Player2_Card2);

		JPanel panel_25 = new JPanel();
		panel_16.add(panel_25, BorderLayout.CENTER);

		PlayerStatus2 = new JLabel("");
		panel_25.add(PlayerStatus2);

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_13.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));

		JPanel panel_28 = new JPanel();
		panel_17.add(panel_28, BorderLayout.NORTH);

		PlayerName3 = new JLabel("");
		panel_28.add(PlayerName3);

		JPanel panel_29 = new JPanel();
		panel_17.add(panel_29, BorderLayout.SOUTH);
		panel_29.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_31 = new JPanel();
		panel_29.add(panel_31);

		Player3_Card1 = new JTextArea();
		Player3_Card1.setRows(5);
		Player3_Card1.setColumns(6);
		panel_31.add(Player3_Card1);

		JPanel panel_32 = new JPanel();
		panel_29.add(panel_32);

		Player3_Card2 = new JTextArea();
		Player3_Card2.setRows(5);
		Player3_Card2.setColumns(6);
		panel_32.add(Player3_Card2);

		JPanel panel_30 = new JPanel();
		panel_17.add(panel_30, BorderLayout.CENTER);

		PlayerStatus3 = new JLabel("");
		panel_30.add(PlayerStatus3);

		JPanel panel_Select = new JPanel();
		frame.getContentPane().add(panel_Select, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Call");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((SenderThread) thread).setstr("Call/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Select.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Raise");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame1 = new JFrame();
				JPanel contentPane;
				JTextField textField;
				frame1.setTitle("Write increasing money");
				frame1.setBounds(100, 100, 300, 89);
				frame1.setVisible(true);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame1.setContentPane(contentPane);
				contentPane.setLayout(new BorderLayout(0, 0));
				textField = new JTextField();
				contentPane.add(textField, BorderLayout.CENTER);
				textField.setColumns(10);
				JButton button = new JButton("\uD655\uC778");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int money = Integer.parseInt(textField.getText());
						try {
							((SenderThread) thread).setstr("Raise/" + money);
							frame1.dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				contentPane.add(button, BorderLayout.EAST);
			}
		});
		panel_Select.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Check");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((SenderThread) thread).setstr("Check/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Select.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Die");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((SenderThread) thread).setstr("Die/");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Select.add(btnNewButton_3);
		panel_Textplay.setLocation(new Point(20, 20));
		frame.getContentPane().add(panel_Textplay, BorderLayout.WEST);
		panel_Textplay.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_Textplay.add(panel_1, BorderLayout.NORTH);

		StateText = new JTextArea();
		StateText.setRows(12);
		StateText.setColumns(20);
		panel_1.add(StateText);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_Textplay.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

//		PlayerStatus = new JLabel("1111111111111111111111111111");
//		panel_3.add(PlayerStatus);

		card1List = new ArrayList();
		card1List.add(Player1_Card1);
		card1List.add(Player2_Card1);
		card1List.add(Player3_Card1);
		card1List.add(Player4_Card1);
		card1List.add(Player5_Card1);
		card1List.add(Player6_Card1);
		card2List = new ArrayList();
		card2List.add(Player1_Card2);
		card2List.add(Player2_Card2);
		card2List.add(Player3_Card2);
		card2List.add(Player4_Card2);
		card2List.add(Player5_Card2);
		card2List.add(Player6_Card2);
		playerStatusList = new ArrayList();
		playerStatusList.add(PlayerStatus1);
		playerStatusList.add(PlayerStatus2);
		playerStatusList.add(PlayerStatus3);
		playerStatusList.add(PlayerStatus4);
		playerStatusList.add(PlayerStatus5);
		playerStatusList.add(PlayerStatus6);
		playerNameList = new ArrayList();
		playerNameList.add(PlayerName1);
		playerNameList.add(PlayerName2);
		playerNameList.add(PlayerName3);
		playerNameList.add(PlayerName4);
		playerNameList.add(PlayerName5);
		playerNameList.add(PlayerName6);
		tableCardList = new ArrayList();
		tableCardList.add(TableCard1);
		tableCardList.add(TableCard2);
		tableCardList.add(TableCard3);
		tableCardList.add(TableCard4);
		tableCardList.add(TableCard5);
	}
}