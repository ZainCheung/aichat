package com.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.plugin.TranslateTool;

public class Robot {

	public String name;
	public String sex;
	public int age;
	int index_max = 0;
	Database base = new Database();
	private String reciveMessage = null;
	private String replyMessage = null;
	private boolean requestion=false;//检测是否开启追问,默认不追问
	Random random = new Random();
	public TranslateTool translate;
	// int[] relevance = new int[base.getAnswers().size()];// 相关度数组,长度与数据库长度相同

	public boolean isRequestion() {
		return requestion;
	}

	public void setRequestion(String string) {
		if (queryBase(string, base.givenTranslate)) {
			
			this.requestion =true;
		}else {
			this.requestion = requestion;
		}
	}
	
	public void setRequestion(boolean bool) {
			this.requestion = bool;
	}

	public String getReciveMessage() {
		return reciveMessage;
	}

	public void setReciveMessage(String reciveMessage) {
		this.reciveMessage = reciveMessage;
		// getRelevance();
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String reciveMessage) {
		this.replyMessage = oneCharReplyMessage(reciveMessage);
		if (this.replyMessage == null) {
			this.replyMessage = givenReplyMessage(reciveMessage);
			if (this.replyMessage == null) {
				this.replyMessage = labelReplyMessage(reciveMessage);
				if (this.replyMessage == null) {
					this.replyMessage = getRelevance(reciveMessage);
					if (this.replyMessage == null) {
						this.replyMessage = randomReplyMessage(base.getUnknownAnswers());
					}
				}
			}
		}
	}

	/**
	 * 根据收到信息的字符相关度抽取回复信息
	 * 
	 * @param reciveMessage
	 * @return
	 */
	public String getRelevance(String reciveMessage) {

		int length = reciveMessage.length();
		int[] relevance = new int[base.getAnswers().size()];// 相关度数组,长度与数据库长度相同
		List<String> answers = base.getAnswers();// 获取数据库信息
		String[] strs = new String[reciveMessage.length()];// 收到的信息转化为单字符数组
		String[][] twoStrs = new String[length][length];// 收到的信息切碎成双字符并储存到双字符数组
		List<String> twoStrsList = new ArrayList<String>();// 从双字符数组中筛选掉null并储存到双字符集合中
		String[][][] twoBases = new String[relevance.length][100][100];// 数据库内的信息切碎成双字符
		List<String> twoBasesList = new ArrayList<String>();

		/**
		 * 单个字符的匹配
		 */
		for (int i = 0; i < reciveMessage.length(); i++) {
			strs[i] = reciveMessage.substring(i, (i + 1));
			for (int j = 0; j < relevance.length; j++) {
				for (int k = 0; k < base.getAnswers().get(j).length(); k++) {
					if (strs[i].equals(base.getAnswers().get(j).substring(k, k + 1))) {
						relevance[j]++;// 找到相符字,增加相关度
					}
				}
			}
		}

		/**
		 * 双字符的匹配
		 */
		for (int d = 0; d < answers.size(); d++) {// 循环记录条数
			for (int b = 0; b < answers.get(d).length() - 1; b++) {// 行
				for (int c = 0; c < answers.get(d).length() - b - 1; c++) {// 列
					twoBases[d][b][c] = answers.get(d).substring(b, b + 1)
							+ answers.get(d).substring(b + c + 1, b + c + 2);

				}
			}
		}
		for (int i = 0; i < twoBases.length; i++) {// 循环记录条数
			for (int j = 0; j < twoBases[i].length; j++) {// 行
				for (int k = 0; k < twoBases[i][j].length; k++) {// 列
					if (twoBases[i][j][k] != null) {
						twoBasesList.add(twoBases[i][j][k]);
					}
				}
			}
		}
		// 以下处理收到的信息
		for (int i = 0; i < reciveMessage.length() - 1; i++) {
			for (int j = 0; j < reciveMessage.length() - 1 - i; j++) {
				twoStrs[i][j] = reciveMessage.substring(i, i + 1) + reciveMessage.substring(i + j + 1, i + j + 2);
			}
		}
		for (int i = 0; i < twoStrs.length; i++) {
			for (int j = 0; j < twoStrs[i].length; j++) {
				if (twoStrs[i][j] != null) {
					twoStrsList.add(twoStrs[i][j]);
				}

			}
		}
		// 下面进行双字符加权
		for (int i = 0; i < twoStrsList.size(); i++) {
			// 循环每条记录
			for (int j = 0; j < twoBases.length; j++) {
				for (int k = 0; k < twoBases[j].length; k++) {
					for (int m = 0; m < twoBases[j][k].length; m++) {
						if (twoStrsList.get(i).equals(twoBases[j][k][m])) {
							relevance[j]++;
						}
					}

				}
			}
		}
		// 下面推选出权位最高的一项,记录他在数据库里的下标

		index_max = 0;
		for (int i = 0; i < relevance.length; i++) {
			if (relevance[i] > relevance[index_max]) {
				index_max = i;
			}
		}
//		System.out.println(index_max);
//		System.out.println(relevance[index_max]);
		if (relevance[index_max] >= 1) {// 相关度阈值控制
			return answers.get(index_max);
		} else {
			return null;
		}

		// setReplyMessage(answers.get(index_max));// 完成回复信息的提取

	}

	/**
	 * 随机抽取回答信息方法
	 * 
	 * @param lists
	 * @return
	 */
	public String randomReplyMessage(List<String> lists) {
		int num = random.nextInt(lists.size());
		return lists.get(num);
	}

	/**
	 * 记录一条字符串出现多少次指定字符串片段
	 * 
	 * @param text 母字符串
	 * @param sub  片段
	 * @return 出现的次数
	 */
	public int getCount(String text, String sub) {
		int count = 0, start = 0;
		while ((start = text.indexOf(sub, start)) >= 0) {
			start += sub.length();
			count++;
		}
		return count;
	}

	/**
	 * 分析信息里的标签,这里是两个字标签
	 * 
	 * @param string
	 * @return
	 */
	public String[] getLabel_two(String string) {
		String[] labels = new String[string.length() - 1];
		for (int i = 0; i < string.length() - 1; i++) {
			labels[i] = string.substring(i, i + 2);
		}
		return labels;
	}

	/**
	 * 分析信息里的标签,这里是三个字标签
	 * 
	 * @param string
	 * @return
	 */
	public String[] getLabel_three(String string) {
		if (string.length() >= 3) {
			String[] labels = new String[string.length() - 2];
			for (int i = 0; i < string.length() - 2; i++) {
				labels[i] = string.substring(i, i + 3);
			}
			return labels;
		} else {
			return null;
		}
	}

	/**
	 * 按标签抽取回答信息
	 * 
	 * @param string
	 * @return
	 */
	public String labelReplyMessage(String string) {
		int[] counts = new int[base.getAnswers().size()];
		String[] twoLabels = getLabel_two(string);
		String[] threeLabels = getLabel_three(string);
		String returnTranslateString="";
		//下面进行翻译模块
		for (int j = 0; j < twoLabels.length; j++) {
			if (twoLabels[j].equals("翻译")) {
					returnTranslateString+=string.substring(j+2, string.length()) ;
				System.out.println(returnTranslateString);
				translate=new TranslateTool(returnTranslateString);
				System.out.println(getTranslateText());
				return getTranslateText();
			}
		}
		for (int i = 0; i < base.getAnswers().size(); i++) {
			for (int j = 0; j < twoLabels.length; j++) {
				counts[i] += getCount(base.getAnswers().get(i), twoLabels[j]);
			}
			if (string.length() >= 3) {
				for (int k = 0; k < twoLabels.length; k++) {
					try {
						counts[i] += getCount(base.getAnswers().get(i), threeLabels[k]);
					} catch (Exception e) {
					}
				}
			}
		}

		index_max = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > counts[index_max]) {
				index_max = i;
			}
		}
//		System.out.println(index_max);
//		System.out.println("最大相关度为" + counts[index_max]);
		if (counts[index_max] >= 1) {// 相关度阈值控制
			return base.getAnswers().get(index_max);
		} else {
			return null;
		}
	}

	/**
	 * 判断字符串是否存在特定库内
	 * 
	 * @param string
	 * @param givenList
	 * @return
	 */
	public boolean queryBase(String string, List<String> givenList) {
		for (int i = 0; i < givenList.size(); i++) {
			if (string.equals(givenList.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 特定问题就特定回答
	 * 
	 * @param string
	 * @return
	 */
	public String givenReplyMessage(String string) {

		if (queryBase(string, base.givenList_Hi)) {
			return randomReplyMessage(base.answersList_Hi);
		} else if (queryBase(string, base.givenList_KuaKua)) {
			return randomReplyMessage(base.answersList_KuaKua);
//		} else if (queryBase(string, base.givenTranslate)) {
//			return randomReplyMessage(base.answersTranslate);
		} else {
			return null;
		}

	}

	/**
	 * 一个字符回复方法
	 * 
	 * @param string
	 * @return
	 */
	public String oneCharReplyMessage(String string) {
		// 如果接受到的信息长度为一且不为数字与字母
		if (string.length() == 1 && !string.matches("\\p{Alnum}")) {
			return string + randomReplyMessage(base.oneCharAnswers);
		} else {
			return null;
		}

	}
	public String getTranslateText() {
		if (translate.isError()==true) {
			return randomReplyMessage(base.errorTranslate);
		}
		
		return translate.getResult();
	}
}
