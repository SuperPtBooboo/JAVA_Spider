package com.jluzh.jw;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * 
 * @ClassName: DownLoad.java
 * @Description: 智联数据下载
 * @author fenghhhbb
 * @version 1.0
 * @date 2018-3-17 下午4:22:18
 */
public class DownLoad {
	
	public static void main(String[] args){
		
//		List<HashMap<String, String>> maps = getJobInfo("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E7%8F%A0%E6%B5%B7&kw=java&sm=0&p=5", "utf-8");
//		for(HashMap<String, String>map:maps){
//			System.out.println(map.get("jobName"));
//		}
	}	
	/**
	 * 获取网页源代码
	 * @param url 网址
	 * @param encoding 字符编码
	 * @return
	 */
	public static String getByUrlHTML(String urlObj,String encoding){
		InputStreamReader isr = null;
		BufferedReader bf = null;
		StringBuffer sb = new StringBuffer();
		try {
			
			//java net 建立网络连接   异常机制
			URL url = new URL(urlObj);
			//打开网络连接
			URLConnection uc = url.openConnection();
			//建立文件输入流  装饰者模式
			isr = new InputStreamReader(uc.getInputStream(),encoding);
			//高效率读取
			bf = new BufferedReader(isr);
			//网页源代码全部读取完
			//临时文件
			String temp = null ;
			//一边读一边   一边保存在临时文件
			while((temp=bf.readLine())!=null){
				sb.append(temp+"\n");
			}			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("世界上最遥远的距离就是没有网,请检查网络设置!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("对不起,网页丢失,请稍后重试!");
			e.printStackTrace();
		} finally { 
			try {
				if(bf!=null){bf.close();}
				if(isr!=null){isr.close();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	
	/**
	 * 获取数据
	 * @param url 网址
	 * @param encoding 字符编码
	 * @return
	 */
	public static List<HashMap<String, String>> getJobInfo(String url , String encoding){
		//建立网络连接
		String html = getByUrlHTML(url,encoding);
//		System.out.println(html);
		//从网页的源代码去解析数据  解析String
		Document document = Jsoup.parse(html);
		//newlist_list_content_table
		Element element = document.getElementById("newlist_list_content_table");
		//拿到盒子下面的每个table newlist
		Elements elements = document.getElementsByClass("newlist");
		List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
		//拿到table里面的  职业名称  薪水  地点  日期
		for(Element el : elements){
			HashMap<String, String> map = new HashMap<String, String>();
			//职位名称
			String jobName = el.getElementsByClass("zwmc").text();
			//公司名称
			String comName = el.getElementsByClass("gsmc").text();
			//职位月薪
			String money = el.getElementsByClass("zwyx").text();
			//更新时间
			String date = el.getElementsByClass("gxsj").text();
//					System.out.println("职位名称:"+jobName+"\t"+"公司名称:"+comName+"\t"+"职位月薪:"+money+"\t"+"更新时间:"+date+"\t");
//					System.out.println("职位名称:"+jobName);
//					System.out.println("公司名称:"+comName);
//					System.out.println("职位月薪:"+money);
//					System.out.println("更新时间:"+date);
//					System.out.println("-----------------------------");
			map.put("jobName",jobName );
			map.put("comName",comName );
			map.put("money",money );
			map.put("date",date );
			maps.add(map);
		}
		return maps;
	}

}
