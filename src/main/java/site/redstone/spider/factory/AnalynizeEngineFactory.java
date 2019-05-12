package site.redstone.spider.factory;

import java.util.HashMap;
import java.util.Map;

import site.redstone.spider.engine.AnalynizeEngine;

public class AnalynizeEngineFactory {
	
	private static Map<String,AnalynizeEngine> analynizeEngineCache = new HashMap<String, AnalynizeEngine>();
	
	public static AnalynizeEngine getAnalynizeEngine(String source_name){
		AnalynizeEngine engine = analynizeEngineCache.get(source_name);
		
		if(engine == null){
			engine = new AnalynizeEngine().setBookSource(BookSourceFactory.getBookSource(source_name));
			analynizeEngineCache.put(source_name, engine);
		}
		return analynizeEngineCache.get(source_name);
	}
	
	public static int size(){
		return analynizeEngineCache.size();
	}
	
	

}
