package site.redstone.spider.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import site.redstone.spider.download.DownloadPool;
import site.redstone.spider.engine.AnalynizeEngine;
import site.redstone.spider.entity.BookInfo;
import site.redstone.spider.entity.Chapter;
import site.redstone.spider.factory.AnalynizeEngineFactory;


@Controller
public class NovelController {
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView searchHtml(HttpServletRequest request, HttpServletResponse response,String key_word,ModelAndView mv,String source_name) {
		List<BookInfo> bookInfoList = null;
		AnalynizeEngine engine = AnalynizeEngineFactory.getAnalynizeEngine(source_name);
		try {
			bookInfoList = engine.search(key_word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("bookInfoList",bookInfoList);
		mv.setViewName("novels");
		return mv;
	}
	
	
	@RequestMapping(value = "/chapter")
	public ModelAndView chapterHtml(HttpServletRequest request, HttpServletResponse response, BookInfo bookInfo,ModelAndView mv,String source_name) {
		AnalynizeEngine engine = AnalynizeEngineFactory.getAnalynizeEngine(source_name);
		try {
			bookInfo = engine.chapterListAnalynize(bookInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("chapters");
		mv.addObject("bookInfo", bookInfo);
		return mv;
	}
	
	@RequestMapping(value = "/content",method = RequestMethod.GET)
	public ModelAndView contentHtml(HttpServletRequest request, HttpServletResponse response, Chapter chapter,ModelAndView mv ,String source_name,String book_url) {
		AnalynizeEngine engine = AnalynizeEngineFactory.getAnalynizeEngine(source_name);
		try {
			chapter = engine.chapterContentAnalynize(chapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("chapter", chapter);
		mv.addObject("source_name", source_name);
		mv.addObject("book_url", book_url);
		mv.setViewName("content");
		return mv;
	}
	
	@RequestMapping(value = "/downloadRequest",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> downloadTxt(HttpServletRequest request, HttpSession session, String url, BookInfo bookInfo, String source_name) {
		AnalynizeEngine engine = AnalynizeEngineFactory.getAnalynizeEngine(source_name);
		try {
			bookInfo = engine.chapterListAnalynize(bookInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String task_key = DownloadPool.submitTask(null, bookInfo.getChapter_list(), engine);
		session.setAttribute(task_key, bookInfo);
		Map<String,String> result = new HashMap<String, String>();
		result.put("task_key", task_key);
		return result;
	}
	
	@RequestMapping(value = "/getTaskProgress",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Double> getTaskProgress(HttpServletRequest request, HttpSession session, String task_key) {
		Map<String,Double> result = new HashMap<String, Double>();
		result.put("progress", DownloadPool.getPercentage(task_key));
		return result;
	}
	
	@RequestMapping(value = "/download",method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response, HttpSession session, String task_key) {
		BookInfo bookInfo = (BookInfo) session.getAttribute(task_key);
		StringBuilder sb = new StringBuilder();
		for (Chapter chapter : bookInfo.getChapter_list()) {
			System.out.println(chapter);
			sb.append(chapter.getChapter_name() + "\r\n");
			sb.append(chapter.getChapter_content().replaceAll("<br>", "\r\n").replaceAll("&nbsp;", " "));
		}
		session.removeAttribute(task_key);
		response.setContentType("application/x-download");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(bookInfo.getBook_name().getBytes("utf-8"),"ISO8859-1") + ".txt");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	    response.setContentLength(sb.toString().getBytes().length);//设置下载内容大小 
	    BufferedOutputStream output = null;
	    try {
			output = new BufferedOutputStream(response.getOutputStream());
			output.write(sb.toString().getBytes());
			output.flush();
			response.flushBuffer();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } //  用户可能取消了下载
	    finally {
		     if (output != null) {
		    	try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		     }
	    }
	}
	
	
}
