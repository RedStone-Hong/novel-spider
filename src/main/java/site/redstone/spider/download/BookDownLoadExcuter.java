package site.redstone.spider.download;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import site.redstone.spider.engine.AnalynizeEngine;
import site.redstone.spider.entity.Chapter;

public class BookDownLoadExcuter implements DownLoadExcuter<Chapter>{
	private final static Logger logger = LoggerFactory.getLogger(BookDownLoadExcuter.class); 
	/**
	 * 用于解析章节的引擎
	 */
	private AnalynizeEngine analynizeEngine;

	public void setAnalynizeEngine(AnalynizeEngine analynizeEngine) {
		this.analynizeEngine = analynizeEngine;
	}

	/**
	 * 解析传入的章节集合并下载章节内容
	 * @throws Exception 
	 */
	@Override
	public void excute(String task_key,List<Chapter> tasks) throws Exception {
		Assert.notNull(analynizeEngine, "书籍解析引擎不能为空!(Book AnalynizeEngine must not be null!)");
		for (Chapter chapter : tasks) {
			analynizeEngine.chapterContentAnalynize(chapter);
			DownloadPool.updateProgress(task_key,1);
		}
		logger.info(Thread.currentThread().getName() + "已完成" + task_key + "的任务!");
	}
}
