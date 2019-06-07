package site.redstone.spider.download;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import site.redstone.spider.engine.AnalynizeEngine;
import site.redstone.spider.entity.Chapter;

public class DownloadPool {
	private final static Logger logger = LoggerFactory.getLogger(DownloadPool.class); 
	/**
	 * 用于执行任务的线程池
	 */
	private static ExecutorService pool;
	
	/**
	 * 线程池初始化大小
	 */
	private static int pool_size = 30;
	
	/**
	 * 线程池待执行的任务集合
	 */
	private static Map<String, List<Chapter>> task_collection = new LinkedHashMap<String, List<Chapter>>();
	
	/**
	 * 已完成任务计数器集合
	 */
	private static Map<String, Integer> finishedTask = new HashMap<String, Integer>();
	
	/**
	 * 获取下载任务完成度
	 * @return
	 */
	public static double getPercentage(String task_key) {
		Integer finished_count = finishedTask.get(task_key) == null?0 : finishedTask.get(task_key);
		List<Chapter> taskList = task_collection.get(task_key);
		if(taskList == null || taskList.size() == 0) {
			return 0.0d;
		}
		return (finished_count * 1.0) / (taskList.size() * 1.0);
	}
	
	/**
	 * 更新下载进度
	 * @param task_key
	 */
	public synchronized static void updateProgress(String task_key, int count) {
		Integer finished_count = finishedTask.get(task_key) == null?0 : finishedTask.get(task_key);
		finishedTask.put(task_key, finished_count + count);
		logger.info("task_key:" + task_key + "的任务完成百分比" + String.format("%.2f", getPercentage(task_key) * 100) + "%" );
	};
	
	/**
	 * 提交一个下载任务
	 * @param task_key	识别任务的唯一标识,若为空则自动生成一个
	 * @param taskList	提交的待下载的任务列表
	 */
	public static String submitTask(String task_key, List<Chapter> taskList, AnalynizeEngine analynizeEngine) {
		if(task_key == null) {
			task_key = UUID.randomUUID().toString();
		}
		task_collection.put(task_key, taskList);
		excuteTask(task_key, taskList, analynizeEngine);
		return task_key;
	}
	
	/**
	 * 将任务交给线程池进行下载
	 * @param taskList
	 */
	private static void excuteTask(String task_key, List<Chapter> taskList, AnalynizeEngine analynizeEngine) {
		if(pool == null) {
			pool = Executors.newFixedThreadPool(pool_size);
		}
		int task_num_per_tread = taskList.size() / pool_size;
		for (int i = 0;i < pool_size;i++) {
			List<Chapter> tasks_per_thread = null;
			if(i != pool_size - 1) {
				tasks_per_thread = taskList.subList(task_num_per_tread * i, task_num_per_tread * (1 + i));
			}else {
				tasks_per_thread = taskList.subList(task_num_per_tread * i, taskList.size());
			}
			DownLoadThread<Chapter> r = new DownLoadThread<Chapter>();
			BookDownLoadExcuter excuter = new BookDownLoadExcuter();
			excuter.setAnalynizeEngine(analynizeEngine);
			r.setDownLoadExcuter(excuter);
			r.setDownLoadTasks(tasks_per_thread);
			r.setTask_key(task_key);
			pool.submit(r);
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(400 % 30);
	}
}
