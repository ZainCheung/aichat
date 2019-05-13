package com.event;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;

import com.main.Chatroom;

public class ReplyActionThread implements Runnable {

	final static int limitNum = 11; // 一行多少字
	int x = 0, y = 50, weight = 50, height = 50;
	public static int row;
	public static SendRunnable sendRunnable = new SendRunnable();
	int chineseNum = 0;
	int signNum = 0;
	int spaceNum = 0;
	int lowCase = 0;
	int upCase = 0;
	int numberNum = 0;

	/**
	 * 检查字符串格式
	 * 
	 * @param str
	 */
	public void checkNum(String str) {
		String[] checkStrs = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			checkStrs[i] = str.substring(1 * i, 1 * (i + 1));
			if (checkStrs[i].equals("@") || checkStrs[i].equals("%") || checkStrs[i].equals("&")
					|| checkStrs[i].equals("。")) {
				chineseNum++;
			} else if (checkStrs[i].equals("\n")) {
				checkStrs[i] = "";
			} else if (checkStrs[i].equals("+") || checkStrs[i].equals("=") || checkStrs[i].equals("$")
					|| checkStrs[i].equals("#") || checkStrs[i].equals("^")) {
				upCase++;
			} else if (checkStrs[i].equals("(") || checkStrs[i].equals(")") || checkStrs[i].equals("[")
					|| checkStrs[i].equals("]") || checkStrs[i].equals("{") || checkStrs[i].equals("}")) {
				spaceNum++;
			} else if (checkStrs[i].matches("\\p{Punct}")) {
				signNum++;
			} else if (checkStrs[i].matches("\\d")) {
				numberNum++;
			} else if (checkStrs[i].matches("\\p{Lower}")) {
				lowCase++;
			} else if (checkStrs[i].matches("\\p{Upper}")) {
				upCase++;
			} else if (checkStrs[i].matches("\\p{Blank}")) {
				spaceNum++;
			} else {
				chineseNum++;
			}
		}
	}

	/**
	 * 格式字符计数器清零
	 */
	public void zeroNum() {
		chineseNum = 0;
		signNum = 0;
		spaceNum = 0;
		lowCase = 0;
		upCase = 0;
		numberNum = 0;
	}
	@Override
	public void run() {
		Timer timer = new Timer();

		String messageText = Chatroom.writeArea.getText();
		//String messageText = sendRunnable.translatetext;
		messageText = messageText.replace("\n", "");// 删掉换行符
		Chatroom.robot.setReciveMessage(messageText);
		Chatroom.robot.setReplyMessage(Chatroom.robot.getReciveMessage());

		String replyMessageText = Chatroom.robot.getReplyMessage();
		//String replyMessageText = Chatroom.robot.getTranslateText(messageText);
		JTextArea myTextArea;
//		int replytime = (messageText.length() <= 11) ? (messageText.length() / 11 + 1) * 1000
//				: messageText.length() / 11 * 1000;// 回复时间
		int num = replyMessageText.length() % limitNum;// 余数
		row = (num == 0) ? (replyMessageText.length() / limitNum) : ((replyMessageText.length() / limitNum) + 1);
		String[] messages = new String[row];

		if (num == 0) {
			for (int i = 0; i < row; i++) {
				messages[i] = replyMessageText.substring(limitNum * i, limitNum * (i + 1));
			}
		} else {
			for (int i = 0; i < row - 1; i++) {
				messages[i] = replyMessageText.substring(limitNum * i, limitNum * (i + 1));
			}
			messages[row - 1] = replyMessageText.substring(limitNum * (row - 1));
		}

		if (messages.length != 0) { // 如果输入内容不为空
			if (replyMessageText.length() <= 11) { // 内容不超过一行
				checkNum(replyMessageText);
				weight = 20 + 30 * chineseNum + 15 * signNum + 10 * spaceNum + 18 * lowCase + 20 * upCase
						+ 18 * numberNum;
			} else {
				weight = 350;
			}
			zeroNum();// 清零格式计数器

			height = 50 * (row);
			x = 50;
			y = Chatroom.getBeginY();

			myTextArea = new JTextArea();
			for (int i = 0; i < messages.length; i++) {
				myTextArea.append(" " + messages[i] + "\n");
			}

			myTextArea.setEditable(false);
			myTextArea.setBackground(Color.WHITE);
			myTextArea.setForeground(Color.BLACK);
			myTextArea.setBounds(x, y, weight, height);
			myTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 30));

			Chatroom.chatPanel.add(myTextArea);
			myTextArea.setVisible(true);
			Chatroom.setAllMessageRow(row);
			Chatroom.setItMessageCount();

			Chatroom.setBeginY(row);

		}
	}

	
	public void run1(String string) {

		Timer timer = new Timer();

//		String messageText = Chatroom.writeArea.getText();
//		String messageText = sendRunnable.translatetext;
		String messageText = string;
		messageText = messageText.replace("\n", "");// 删掉换行符
//		Chatroom.robot.setReciveMessage(messageText);
//		Chatroom.robot.setReplyMessage(Chatroom.robot.getReciveMessage());

//		String replyMessageText = Chatroom.robot.getReplyMessage();
		String replyMessageText = Chatroom.robot.getTranslateText();
		System.out.println(messageText);
		System.out.println(replyMessageText);
		JTextArea myTextArea;
//		int replytime = (messageText.length() <= 11) ? (messageText.length() / 11 + 1) * 1000
//				: messageText.length() / 11 * 1000;// 回复时间
		int num = replyMessageText.length() % limitNum;// 余数
		row = (num == 0) ? (replyMessageText.length() / limitNum) : ((replyMessageText.length() / limitNum) + 1);
		String[] messages = new String[row];

		if (num == 0) {
			for (int i = 0; i < row; i++) {
				messages[i] = replyMessageText.substring(limitNum * i, limitNum * (i + 1));
			}
		} else {
			for (int i = 0; i < row - 1; i++) {
				messages[i] = replyMessageText.substring(limitNum * i, limitNum * (i + 1));
			}
			messages[row - 1] = replyMessageText.substring(limitNum * (row - 1));
		}

		if (messages.length != 0) { // 如果输入内容不为空
			if (replyMessageText.length() <= 11) { // 内容不超过一行
				checkNum(replyMessageText);
				weight = 20 + 30 * chineseNum + 15 * signNum + 10 * spaceNum + 18 * lowCase + 20 * upCase
						+ 18 * numberNum;
			} else {
				weight = 350;
			}
			zeroNum();// 清零格式计数器

			height = 50 * (row);
			x = 50;
			y = Chatroom.getBeginY();

			myTextArea = new JTextArea();
			for (int i = 0; i < messages.length; i++) {
				myTextArea.append(" " + messages[i] + "\n");
			}

			myTextArea.setEditable(false);
			myTextArea.setBackground(Color.WHITE);
			myTextArea.setForeground(Color.BLACK);
			myTextArea.setBounds(x, y, weight, height);
			myTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 30));

			Chatroom.chatPanel.add(myTextArea);
			myTextArea.setVisible(true);
			Chatroom.setAllMessageRow(row);
			Chatroom.setItMessageCount();

			Chatroom.setBeginY(row);

		}

	}
}
