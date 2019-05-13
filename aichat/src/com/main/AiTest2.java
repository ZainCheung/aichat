package com.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.event.SendActionlisterner;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class AiTest2 extends JFrame {

	public static JPanel contentPane;
	SendActionlisterner sendAction =new SendActionlisterner();
	public static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AiTest2 frame = new AiTest2();
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
	public AiTest2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 791);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton sendButton = new JButton("\u53D1\u9001");
		sendButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		sendButton.setBounds(378, 518, 255, 105);
		contentPane.add(sendButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		textField.setBounds(286, 435, 403, 65);
		contentPane.add(textField);
		textField.setColumns(10);
		sendButton.addActionListener(sendAction);
	}
}
