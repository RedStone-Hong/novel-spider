package site.redstone.spider.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.redstone.spider.engine.AnalynizeEngine;

public class AnalynizeEngineFactory {
	private final static Logger logger = LoggerFactory.getLogger(AnalynizeEngineFactory.class); 
	private static Map<String,AnalynizeEngine> analynizeEngineCache = new HashMap<String, AnalynizeEngine>();
	
	public static AnalynizeEngine getAnalynizeEngine(String source_name){
		logger.info("处理书源解析引擎获取请求--请求书源名称为:" + source_name);
		AnalynizeEngine engine = analynizeEngineCache.get(source_name);
		if(engine == null){
			logger.info("待获取书源解析引擎未实例化,进行实例化.");
			engine = new AnalynizeEngine().setBookSource(BookSourceFactory.getBookSource(source_name));
			analynizeEngineCache.put(source_name, engine);
			logger.info("实例化完成,并已添加到引擎缓存中.当前引擎缓存池大小:" + size());
		}else {
			logger.info("未获取到相应书源.");
			return null;
		}
		return analynizeEngineCache.get(source_name);
	}
	
	public static int size(){
		return analynizeEngineCache.size();
	}
	
	

}
