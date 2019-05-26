package site.redstone.spider.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import site.redstone.spider.util.UrlUtil;

public class PPT_spider {
	public static void main(String[] args) throws Exception {
		//Document doc =  UrlUtil.gatherDocument("http://sc.chinaz.com/ppt/free_2.html", "get");
		//System.out.println(doc.select("#container div"));
		//ppt();
		//pptAnalynize();
		//System.out.println(pptAnalynize().size());
		//download("http://pic1.sc.chinaz.com/Files/pic/moban/201904/zppt4769_s.jpg", "D:/test.jpg");
		download_ppt();
	}
	
	public static void ppt() throws Exception {
		List<String> pptList = new ArrayList<String>();
		int ec = 0;
		for(int i=2;i<=507;i++) {
			try {
				System.out.println(i);
				Document doc =  UrlUtil.gatherDocument("http://sc.chinaz.com/ppt/free_"+ i +".html", "get");
				Elements ppts = doc.select("#container div");
				for (Element e : ppts) {
					String download_page = e.select("a").get(0).attr("href");
					Document download_doc = UrlUtil.gatherDocument(download_page, "get");
					String download_url = download_doc.select(".downlist a").get(5).attr("href");
					String s = e.select("a img").get(0).attr("src") + "-" + download_url + "-" + e.select("a").get(1).text();
					pptList.add(s);
				}
			}catch(Exception e) {
				System.out.println(i + "出现异常!跳过");
				ec ++;
			}
		}
		System.out.println("异常页面"+ec);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("D:/ppt.txt")));
		for (String s : pptList) {
			bw.write(s + "\n");
		}
		bw.close();
	}
	
	public static List<Map<String,String>> pptAnalynize() throws Exception {
		List<Map<String,String>> pptList = new ArrayList<Map<String,String>>();
		BufferedReader br = new BufferedReader(new FileReader(new File("D:/ppt.txt")));
		String s = "";
		while((s = br.readLine()) != null) {
			Map<String,String> map = new HashMap<String, String>();
			String[] pptInfo = s.split("-");
			map.put("img_url", pptInfo[0]);
			map.put("download_url", pptInfo[1]);
			map.put("ppt_name", pptInfo[2]);
			pptList.add(map);
		}
		br.close();
		return pptList;
	}
	
	 public static void download(String urlString, String filename) throws Exception  
	    {
	        // 构造URL
	        URL url;
	        File file = new File(filename);
	        if(!file.getParentFile().exists()) {
	        	file.getParentFile().mkdirs();
	        }
	            url = new URL(urlString);
	             // 打开连接
	            URLConnection con = url.openConnection();
	            // 输入流
	            InputStream is = con.getInputStream();
	            // 1K的数据缓冲
	            byte[] bs = new byte[1024];
	            // 读取到的数据长度
	            int len;
	            // 输出的文件流s
	            OutputStream os = new FileOutputStream(filename);
	            // 开始读取
	            while ((len = is.read(bs)) != -1) {
	              os.write(bs, 0, len);
	            }
	            // 完毕，关闭所有链接
	            os.close();
	            is.close();
	    }   
	
	 
	 public static void download_ppt() throws Exception {
		List<Map<String,String>> download_list =  pptAnalynize();
		int count = 0;
		for (Map<String, String> map : download_list) {
			String name = map.get("ppt_name");
			download(map.get("img_url"), "E:/ppt/"+name+"/"+name+".jpg");
			download(map.get("download_url"), "E:/ppt/"+name+"/"+name+".rar");
			System.out.println("当前下载第" + (++count) +"个ppt!");
		}
	 }
}
