package com.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.event.SendActionlisterner;

import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.JLabel;

public class AiTest extends JFrame {

	public static JPanel contentPane;
	public static JTextField chatField;//ÐÅÏ¢ÊäÈë¿ò
	public static JButton sendButton;//·¢ËÍÐÅÏ¢°´Å¥
	public static JPanel chatPanel;//ÁÄÌìÃæ°å
	public static int beginY=50;//³õÊ¼ÏûÏ¢¿ò×Ý×ø±ê
	SendActionlisterner sendAction =new SendActionlisterner();
	

	public static int getBeginY() {
		return beginY;
	}

	public static void setBeginY(int row) {
		AiTest.beginY = beginY+50*row+20;
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
					AiTest frame = new AiTest();
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
	public AiTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 80, 800, 550);
		contentPane.add(scrollPane);
		
		chatPanel = new JPanel();
		chatPanel.setBackground(SystemColor.control);
		scrollPane.setViewportView(chatPanel);
		chatPanel.setLayout(null);
		
		JTextArea myTextArea = new JTextArea();
		myTextArea.setEditable(false);
		myTextArea.setText(" \u4F60\u597D\u5440\u725B\u725B\u725B\u725B\u725B\u725B\u725B\u725B");
		myTextArea.setBackground(new Color(50, 205, 50));
		myTextArea.setForeground(Color.BLACK);
		myTextArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		myTextArea.setBounds(433, 364, 350, 50);
		chatPanel.add(myTextArea);
		
		JTextArea itTextArea = new JTextArea();
		itTextArea.setText(" \u4F60\u597D\u5440\u725B\u725B\u725B\u725B\u725B\u725B\u725B\u725B");
		itTextArea.setForeground(Color.BLACK);
		itTextArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		itTextArea.setEditable(false);
		itTextArea.setBackground(Color.WHITE);
		itTextArea.setBounds(15, 211, 350, 50);
		chatPanel.add(itTextArea);
		
		chatField = new JTextField();
		//chatField.setText();
		chatField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		chatField.setBounds(100, 636, 548, 56);
		contentPane.add(chatField);
		chatField.setColumns(10);
		
		sendButton = new JButton("\u53D1\u9001");
		sendButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		sendButton.setBounds(649, 636, 251, 56);
		contentPane.add(sendButton);
		sendButton.addActionListener(sendAction);
		
		JLabel stateLabel = new JLabel(" \u673A\u5668\u4EBA");
		stateLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		stateLabel.setBounds(100, 26, 800, 47);
		contentPane.add(stateLabel);
	}
}
