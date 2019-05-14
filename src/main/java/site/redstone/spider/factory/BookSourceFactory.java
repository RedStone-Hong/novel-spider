package site.redstone.spider.factory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import site.redstone.spider.entity.BookSource;

/**
 * 书源工厂类
 * @author 洪高伟
 * @date 2019-05-11
 */
public class BookSourceFactory {
    private final static Logger logger = LoggerFactory.getLogger(BookSourceFactory.class); 
	private static Map<String,BookSource> bookSourceCache = new HashMap<String, BookSource>();
	static{
		try {
			addBookSourceFromUrl("https://raw.githubusercontent.com/RedStone-Hong/novel-spider/master/src/main/resources/BookSource.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BookSource getBookSource(String source_name){
		return bookSourceCache.get(source_name);
	}
	
	public static List<BookSource> getAllBookSource(){
		List<BookSource> list = new ArrayList<BookSource>();
		list.addAll(bookSourceCache.values());
		return list;
	}
	
	public static void addBookSource(BookSource bookSource){
		bookSourceCache.put(bookSource.getSource_name(), bookSource);
	}
	
	public static int size(){
		return bookSourceCache.size();
	}
	
	public static String converStreamToString(InputStream in) throws Exception{
		BufferedInputStream bis = new BufferedInputStream(in);
		StringBuilder sb = new StringBuilder();
		int len = 0;
		byte[] readArr = new byte[1024 * 1024];
		while ((len = bis.read(readArr, 0, readArr.length))!=-1){
			sb.append(new String(readArr, 0, len));
		}
		return sb.toString();
	}
	
	public static int addBookSourceFromJson(String json){
		List<BookSource> sourceList = new ArrayList<BookSource>();
		JSONArray jsonArr = JSON.parseArray(json);
		for(int i = 0;i<jsonArr.size();i++){
			BookSource source = JSON.toJavaObject(jsonArr.getJSONObject(i), BookSource.class);
			sourceList.add(source);
		}
		for (BookSource source : sourceList) {
			bookSourceCache.put(source.getSource_name(), source);
		}
		return sourceList == null? 0 : sourceList.size();
	}
	
	public static int addBookSourceFromFile(File sourceFile) throws Exception{
		FileInputStream fis = new FileInputStream(sourceFile);
		return addBookSourceFromStream(fis);
	}
	
	public static int addBookSourceFromUrl(String url_string) throws Exception{
		logger.info("开始从指定的url加载书源配置--指定url为" + url_string);
        // 构造URL
        URL url = new URL(url_string);
         // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 调用从流加载书源的方法
        int size = addBookSourceFromStream(is);
        // 完毕，关闭所有链接
        is.close();
        logger.info("加载完毕--成功加载" + size + "个书源配置.");
        return size;
	}
	
	public static int addBookSourceFromStream(InputStream in) throws Exception{
		return addBookSourceFromJson(converStreamToString(in));
	}
	
	public static void main(String[] args) {
		System.out.println(getAllBookSource());
	}
}
