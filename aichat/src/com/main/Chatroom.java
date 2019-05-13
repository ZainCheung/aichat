package com.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.event.ReplyActionThread;
import com.event.SendActionlisterner;
import com.robot.Robot;

import javax.swing.ScrollPaneConstants;

public class Chatroom extends JFrame {

	public static Robot robot;
	public static int beginY = 50;// 初始消息框纵坐标
	public static int chatPanelEndY = 700;// 聊天面板底部纵坐标
	public static int beginWeight = 830;// 聊天面板初始宽度,不变
	public static int beginHeight = 630;// 聊天面板初始高度
	public static int allHeight = 630;// 聊天面板总高度
	public static int myMessageCount = 0;// 我发送的消息数量
	public static int itMessageCount = 0;// 对方发送的消息数量
	public static int allMessageCount = 0;// 双方一共发送消息数量
	public static int allMessageRow = 0;// 双方一共发送消息行数
	public static JPanel contentPane;
	public static JPanel chatPanel;
	public static JTextArea writeArea;
	public static JLabel stateLabel;
	public static JScrollPane scrollPane;
	public static JButton sendButton;
	public static SendActionlisterner sendMessageAction = new SendActionlisterner();
	public static ReplyActionThread replyMessageAction = new ReplyActionThread();

	public static int getMyMessageCount() {
		return myMessageCount;
	}

	public static void setMyMessageCount() {
		Chatroom.myMessageCount++;
		Chatroom.allMessageCount++;
	}

	public static int getItMessageCount() {
		return itMessageCount;
	}

	public static void setItMessageCount() {
		Chatroom.itMessageCount++;
		Chatroom.allMessageCount++;
	}

	public static int getAllMessageCount() {
		return allMessageCount;
	}

	public static void setAllMessageCount() {
		Chatroom.allMessageCount++;
	}

	public static int getAllMessageRow() {
		return allMessageRow;
	}

	public static void setAllMessageRow(int row) {
		Chatroom.allMessageRow = allMessageRow + row;
	}

	public static int getBeginHeight() {
		return beginHeight;
	}

//	public static void setBeginHeight(int row) {
//		Chatroom.beginHeight = beginHeight + 70 * row;
//	}
	public static void setBeginHeight(int high) {
		Chatroom.beginHeight = beginHeight + high+20;
	}
	public static void reduceBeginHeight(int high) {
		Chatroom.beginHeight = beginHeight -  high;
	}
	
	public static int getAllHeight() {
		return allHeight;
	}

	public static void setAllHeight(int allHeight) {
		Chatroom.allHeight = allHeight;
	}

	public static int getBeginY() {
		return beginY;
	}

	public static void setBeginY(int row) {
		Chatroom.beginY = beginY + 50 * row + 20;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
					Chatroom frame = new Chatroom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chatroom() {
		
		robot =new Robot();//生成机器人
		robot.name="小小苏";
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 30, 900, 1050);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(-5, 54, 856, 646);
		contentPane.add(scrollPane);

		chatPanel = new JPanel();
		chatPanel.setPreferredSize(new Dimension(beginWeight, beginHeight));
		scrollPane.setViewportView(chatPanel);
		chatPanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 694, 847, 215);
		contentPane.add(scrollPane_1);

		sendButton = new JButton("\u53D1\u9001");
		sendButton.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		sendButton.setBounds(690, 909, 121, 43);
		sendButton.setName("发送");
		contentPane.add(sendButton);
		sendButton.addActionListener(sendMessageAction);

		writeArea = new JTextArea();
		writeArea.setLineWrap(true);
		writeArea.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		scrollPane_1.setViewportView(writeArea);
		writeArea.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (writeArea.getText().length() != 0&&!writeArea.getText().equals("\n")) {
						sendButton.doClick();
						e.consume();//销毁调用它的对象,在这里用来删除多出的换行
					}else if (writeArea.getText().equals("\n")) {
						writeArea.setText(null);
						e.consume();
					}
					else {
						e.consume();
					}
				}

			}

		});

		stateLabel = new JLabel(robot.name);
		stateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		stateLabel.setBounds(41, 0, 709, 55);
		contentPane.add(stateLabel);
	}
}
