package site.redstone.spider.factory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import site.redstone.spider.entity.BookSource;

/**
 * 书源工厂类
 * @author 洪高伟
 * @date 2019-05-11
 */
public class BookSourceFactory {
	private static Map<String,BookSource> bookSourceCache = new HashMap<String, BookSource>();
	
	static{
		Resource resource = new ClassPathResource("BookSource.txt");
		File file;
		try {
			file = resource.getFile();
			BookSourceFactory.addBookSourceFromFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BookSource getBookSource(String source_name){
		return bookSourceCache.get(source_name);
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
	
	public static void addBookSourceFromJson(String json){
		List<BookSource> sourceList = new ArrayList<BookSource>();
		JSONArray jsonArr = JSON.parseArray(json);
		for(int i = 0;i<jsonArr.size();i++){
			BookSource source = JSON.toJavaObject(jsonArr.getJSONObject(i), BookSource.class);
			sourceList.add(source);
		}
		for (BookSource source : sourceList) {
			bookSourceCache.put(source.getSource_name(), source);
		}
	}
	
	public static void addBookSourceFromFile(File sourceFile) throws Exception{
		FileInputStream fis = new FileInputStream(sourceFile);
		addBookSourceFromStream(fis);
	}
	
	public static void addBookSourceFromStream(InputStream in) throws Exception{
		addBookSourceFromJson(converStreamToString(in));
	}
	
	public static void main(String[] args) throws Exception {
		addBookSourceFromFile(new File("D:/json.txt"));
	}
	
}
