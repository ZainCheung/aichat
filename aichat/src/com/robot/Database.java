package com.robot;

import java.util.ArrayList;
import java.util.List;

public class Database {

	List<String> answers = new ArrayList<String>();//相关度抽取库
	List<String> unknownAnswers = new ArrayList<String>();//无法理解库
	List<String> oneCharAnswers = new ArrayList<String>();//一个字符返回库
	List<String> questionAnswers = new ArrayList<String>();
	
	List<String> givenList_Hi = new ArrayList<String>();//打招呼接收库
	List<String> answersList_Hi = new ArrayList<String>();//打招呼返回库
	
	List<String> givenList_KuaKua = new ArrayList<String>();//夸夸接收库
	List<String> answersList_KuaKua = new ArrayList<String>();//夸夸返回库
	
	List<String> givenTranslate = new ArrayList<String>();//翻译内容接收库
	List<String> answersTranslate = new ArrayList<String>();//翻译反馈提示库
	List<String> errorTranslate = new ArrayList<String>();//翻译内容接收库


	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(String string) {
		this.answers.add(string);
	}

	public List<String> getQuestionAnswers() {
		return unknownAnswers;
	}

	public void setQuestionAnswers(String string) {
		this.unknownAnswers.add(string);
	}
	
	public List<String> getUnknownAnswers() {
		return unknownAnswers;
	}

	public void setUnknownAnswers(String string) {
		this.unknownAnswers.add(string);
	}

	public Database() {
		setAnswers("嗯嗯");
		setAnswers("哦哦");
		setAnswers("今天天气不错,应该是个晴天!");
		setAnswers("好呀好呀！找我干嘛嘞？");
		setAnswers("现在几点了我哪知道!");
		setAnswers("小胖在宿舍!");
		setAnswers("嘻嘻");
		setAnswers("妹妹找媳妇去了呗!");
		setAnswers("老苏当然,还在睡觉!");
		setAnswers("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈啊哈哈哈哈");
		setAnswers("傻人有傻福嘛");
		setAnswers("小冯意小冯意,嘿嘿");
		setAnswers("羁绊是什么");
		setAnswers("快来和小小苏玩耍吧");
		setAnswers("超子最帅!");
		setAnswers("今年都9102年了!");
		setAnswers("我的名字叫小小苏哇,我可是你的小可爱呀,略略略");

		setUnknownAnswers("我听不懂你说什么");
		setUnknownAnswers("还能不能好好聊天了");
		setUnknownAnswers("你这样让我很惆怅呐");
		setUnknownAnswers("我晕，你不会是要考我吧？");
		setUnknownAnswers("用户体验不对啊亲");
		setUnknownAnswers("说，继续说，我还没听过呢");
		setUnknownAnswers("什么意思？你帮我翻译下呗");
		setUnknownAnswers("不是电脑卡了，就是脑袋卡了。。");
		setUnknownAnswers("本人不在，有事发红包");
		setUnknownAnswers("这位人类，你自己来告诉我答案吧，我洗耳恭听");
		setUnknownAnswers("你说的我都记在小本本里了");
		setUnknownAnswers("甜蜜蜜，你笑得甜蜜蜜，好像花儿插在牛粪里，永远在一气");
		setUnknownAnswers("摇头晃脑，一问三不知");
		setUnknownAnswers("你说的我都记在小本本里了");
		setUnknownAnswers("我读书少，不知道你在说什么。");
		
		oneCharAnswers.add("什么,我没听懂,再说一次好不好");
		oneCharAnswers.add("什么,再具体点");
		oneCharAnswers.add("?你想说什么，慢慢说");
		oneCharAnswers.add("?我猜不到你想表达的意思。");
		oneCharAnswers.add("。。。这可让我怎么接");
		oneCharAnswers.add("?这可让我说什么才好。");
		oneCharAnswers.add("?一个字搞毛线啊");
		oneCharAnswers.add("?一个字让我怎么猜");
		
		givenList_Hi.add("你好");
		givenList_Hi.add("你好啊");
		givenList_Hi.add("你好呀");
		givenList_Hi.add("在吗");
		givenList_Hi.add("在吗?");
		givenList_Hi.add("在?");
		givenList_Hi.add("嗨");
		givenList_Hi.add("哈喽");
		givenList_Hi.add("hi");
		givenList_Hi.add("hello");
		
		answersList_Hi.add("在的呢");
		answersList_Hi.add("你的小可爱已上线");
		answersList_Hi.add("您的小可爱到了,请签收一下");
		answersList_Hi.add("嗨");
		answersList_Hi.add("哈喽");
		answersList_Hi.add("hi");
		answersList_Hi.add("hello");
		answersList_Hi.add("想我啦？哈哈");
		
		givenList_KuaKua.add("夸夸我");
		givenList_KuaKua.add("快夸夸我");
		givenList_KuaKua.add("快夸我");
		givenList_KuaKua.add("夸夸我嘛");
		givenList_KuaKua.add("求夸");
		givenList_KuaKua.add("求夸我");
		givenList_KuaKua.add("你夸我");
		givenList_KuaKua.add("你夸我帅");
		givenList_KuaKua.add("夸我帅");
		
		answersList_KuaKua.add("你好能干哦！");
		answersList_KuaKua.add("我夸你，谁夸我啊？");
		answersList_KuaKua.add("你真帅,,,个屁！");
		answersList_KuaKua.add("人有两种，一种好看的，一种难看的，你夹在中间，属于好难看的。");
		answersList_KuaKua.add("你好能干哦！");
		
		givenTranslate.add("帮我翻译一下");
		givenTranslate.add("帮我翻译一下啊");
		givenTranslate.add("帮我翻译一下吧");
		givenTranslate.add("帮我翻译一下呗");
		givenTranslate.add("翻译");
		givenTranslate.add("帮我翻译");
		givenTranslate.add("在线翻译");
		errorTranslate.add("翻译一下");
		
		errorTranslate.add("呃,小小苏没听懂");
		errorTranslate.add("翻译很有意思 哈哈");
		errorTranslate.add("小小苏能力有限,翻译不出来");
		errorTranslate.add("脑袋宕机了,没有翻译呦");
		errorTranslate.add("检查一下单词有没有拼写错误吧");
		errorTranslate.add("小提示:小小苏目前只会翻译英文哦");
		
		answersTranslate.add("好的,请说");
		answersTranslate.add("你说。。。");

	}

}
