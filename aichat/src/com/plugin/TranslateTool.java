package com.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TranslateTool {
	
	private String result = null;//结果
	private String source = null;//源爬虫页面JSON
	private boolean error = false;//错误状态

	public String getResult() {
		return result;
	}
	public String getSource() {
		return source;
	}
	public boolean isError() {
		return error;
	}
	public TranslateTool(String string) {
		try {

            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");
            //版本：1.1，请求方式：get，编码方式：utf-8
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
           
           
            String text = "keyfrom=fadabvaa&key=522071532&type=data&doctype=json&version=1.1&q=" + string;
//            参数说明：
//
//            keyfrom=密匙ID   key=密码
//            
//           　type - 返回结果的类型，固定为data
//
//           　doctype - 返回结果的数据格式，xml或json或jsonp
//
//           　version - 版本，当前最新版本为1.1
//
//           　q - 要翻译的文本，必须是UTF-8编码，字符长度不能超过200个字符，需要进行urlencode编码
//
//           　only - 可选参数，dict表示只获取词典数据，translate表示只获取翻译数据，默认为都获取
//            errorCode：
//
//           　0 - 正常
//
//           　20 - 要翻译的文本过长
//
//           　30 - 无法进行有效的翻译
//
//           　40 - 不支持的语言类型
//
//           　50 - 无效的key
//
//           　60 - 无词典结果，仅在获取词典结果生效
            
            bw.write(text);
            bw.flush();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            StringBuilder builder = new StringBuilder();
            
            while ((line = br.readLine()) != null) {
            	source=line;
                String[] arr = line.split("]");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].contains("translation")) {
                    	//获取查询结果
                    	result=arr[i].substring(17, arr[i].length()-1);
                        //System.out.println(result);
                    }
                    
                    
                }
                if (result==null) {
					//System.out.println("查询无果!");
					error=true;
				}
                if (string==null) {
					error=true;
				}
                
            }
            

            bw.close();
            osw.close();
            os.close();

            br.close();
            isr.close();
            is.close();

        } 
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        	System.err.println("网络已经断开");
//        }
        catch (IOException e) {
        	//System.err.println("网络断开");
        	error=true;
        }
	}
	public static void main(String[] args) {
		TranslateTool tool =new TranslateTool("null");
		System.out.println(tool.result);
		System.out.println(tool.source);
		System.out.println(tool.error);
	}
}
