package site.redstone.spider.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UrlUtil {
	/**
	 * 获取指定url的页面源代码
	 * */
	public static Document gatherDocument(String url,String method) throws Exception {
		Document document = null;
		Connection connect = Jsoup.connect(url);
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", url);
		header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
		header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		header.put("Accept-Language", "zh-cn,zh;q=0.5");
		header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
		header.put("Connection", "keep-alive");
		Connection data = connect.headers(header);
		if ("get".equals(method)) {
			document = data.get();
		} else {
			document = data.post();
		}
		return document;
	}
}
