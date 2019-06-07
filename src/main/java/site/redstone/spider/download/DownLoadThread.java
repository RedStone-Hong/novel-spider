package site.redstone.spider.download;

import java.util.List;

import org.springframework.util.Assert;

public class DownLoadThread<T> implements Runnable{
	
	/**
	 * 下载任务所属的书记key
	 */
	private String task_key;
	
	/**
	 * 需要完成的下载任务
	 */
	private List<T> tasks;

	/**
	 * 真正用于执行下载操作的对象
	 */
	private DownLoadExcuter<T> downLoadExcuter;
	
	/**
	 * 设置下载操作执行类
	 * @param downLoadExcuter
	 */
	public void setDownLoadExcuter(DownLoadExcuter<T> downLoadExcuter) {
		this.downLoadExcuter = downLoadExcuter;
	}
	
	/**
	 * 设置下载任务
	 */
	public void setDownLoadTasks(List<T> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * 执行分配的下载任务
	 */
	@Override
	public void run() {
		Assert.notNull(tasks , "待执行任务不可为空! (Task must be not null!)");;
		try {
			downLoadExcuter.excute(task_key, tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTask_key() {
		return task_key;
	}

	public void setTask_key(String task_key) {
		this.task_key = task_key;
	}

}
