package site.redstone.spider.download;

import java.util.List;

public interface DownLoadExcuter<T> {
	void excute(String taks_key, List<T> tasks) throws Exception;
}
