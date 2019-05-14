package site.redstone.spider.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "/download",method = RequestMethod.GET)
	public void downloadTxt(HttpServletRequest request, HttpServletResponse response, String url,String fileName) {
		String content = "";	
		try {
			OutputStream os = response.getOutputStream();
			response.setContentType("text/plain");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".txt");
				os.write(content.getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
	
}
