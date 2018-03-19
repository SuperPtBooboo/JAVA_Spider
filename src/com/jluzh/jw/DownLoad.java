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
 * @Description: ������������
 * @author fenghhhbb
 * @version 1.0
 * @date 2018-3-17 ����4:22:18
 */
public class DownLoad {
	
	public static void main(String[] args){
		
//		List<HashMap<String, String>> maps = getJobInfo("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E7%8F%A0%E6%B5%B7&kw=java&sm=0&p=5", "utf-8");
//		for(HashMap<String, String>map:maps){
//			System.out.println(map.get("jobName"));
//		}
	}	
	/**
	 * ��ȡ��ҳԴ����
	 * @param url ��ַ
	 * @param encoding �ַ�����
	 * @return
	 */
	public static String getByUrlHTML(String urlObj,String encoding){
		InputStreamReader isr = null;
		BufferedReader bf = null;
		StringBuffer sb = new StringBuffer();
		try {
			
			//java net ������������   �쳣����
			URL url = new URL(urlObj);
			//����������
			URLConnection uc = url.openConnection();
			//�����ļ�������  װ����ģʽ
			isr = new InputStreamReader(uc.getInputStream(),encoding);
			//��Ч�ʶ�ȡ
			bf = new BufferedReader(isr);
			//��ҳԴ����ȫ����ȡ��
			//��ʱ�ļ�
			String temp = null ;
			//һ�߶�һ��   һ�߱�������ʱ�ļ�
			while((temp=bf.readLine())!=null){
				sb.append(temp+"\n");
			}			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("��������ңԶ�ľ������û����,������������!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�Բ���,��ҳ��ʧ,���Ժ�����!");
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
	 * ��ȡ����
	 * @param url ��ַ
	 * @param encoding �ַ�����
	 * @return
	 */
	public static List<HashMap<String, String>> getJobInfo(String url , String encoding){
		//������������
		String html = getByUrlHTML(url,encoding);
//		System.out.println(html);
		//����ҳ��Դ����ȥ��������  ����String
		Document document = Jsoup.parse(html);
		//newlist_list_content_table
		Element element = document.getElementById("newlist_list_content_table");
		//�õ����������ÿ��table newlist
		Elements elements = document.getElementsByClass("newlist");
		List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();
		//�õ�table�����  ְҵ����  нˮ  �ص�  ����
		for(Element el : elements){
			HashMap<String, String> map = new HashMap<String, String>();
			//ְλ����
			String jobName = el.getElementsByClass("zwmc").text();
			//��˾����
			String comName = el.getElementsByClass("gsmc").text();
			//ְλ��н
			String money = el.getElementsByClass("zwyx").text();
			//����ʱ��
			String date = el.getElementsByClass("gxsj").text();
//					System.out.println("ְλ����:"+jobName+"\t"+"��˾����:"+comName+"\t"+"ְλ��н:"+money+"\t"+"����ʱ��:"+date+"\t");
//					System.out.println("ְλ����:"+jobName);
//					System.out.println("��˾����:"+comName);
//					System.out.println("ְλ��н:"+money);
//					System.out.println("����ʱ��:"+date);
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
