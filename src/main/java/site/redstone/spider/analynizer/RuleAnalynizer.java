package site.redstone.spider.analynizer;

import java.util.Map;

import org.jsoup.nodes.Element;

import site.redstone.spider.entity.BookInfo;
import site.redstone.spider.entity.BookSource;
import site.redstone.spider.entity.Chapter;

/**
 * 规则解析接口
 * @date 2019-05-10
 * @author 洪高伟
 */
public interface RuleAnalynizer {
	/**
	 * 基本规则解析
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> analynizeRule(String rule,BookSource bookSource)throws Exception;
	
	/**
	 * 搜索列表书名规则解析
	 * @param bookInfo
	 * @param book_name_rule
	 * @param element
	 */
	public void bookNameAnalynize(BookInfo bookInfo,Map<String, String> book_name_rule,Element element);
	
	/**
	 * 作者规则解析
	 * @param bookInfo
	 * @param book_author_rule
	 * @param element
	 */
	public void bookAuthorAnalynize(BookInfo bookInfo,Map<String, String> book_author_rule,Element element);
	
	/**
	 * 搜索列表书籍URL规则解析
	 * @param bookInfo
	 * @param book_url_rule
	 * @param element
	 */
	public void searchListBookUrlAnalynize(BookInfo bookInfo,Map<String, String> book_url_rule,Element element);
	
	/**
	 * 最新章节名称规则解析
	 * @param bookInfo
	 * @param last_chapter_name_rule
	 * @param element
	 */
	public void lastChapterNameAnalynize(BookInfo bookInfo,Map<String, String> last_chapter_name_rule,Element element);
	
	/**
	 * 最新章节URL规则解析
	 * @param bookInfo
	 * @param last_chapter_url_rule
	 * @param element
	 */
	public void lastChapterUrlAnalynize(BookInfo bookInfo,Map<String, String> last_chapter_url_rule,Element element);
	
	/**
	 * 简介规则解析
	 * @param bookInfo
	 * @param introduce_rule
	 * @param element
	 */
	public void bookIntroduceAnalynize(BookInfo bookInfo,Map<String, String> introduce_rule,Element element);
	
	/**
	 * 封面规则解析
	 * @param bookInfo
	 * @param cover_rule
	 * @param element
	 */
	public void bookCoverAnalynize(BookInfo bookInfo,Map<String, String> cover_rule,Element element);
	
	/**
	 * 章节名称规则解析
	 * @param bookInfo
	 * @param chapter_list_rule
	 * @param elements
	 */
	public void chapterNameAnalynize(Chapter chapter,Map<String, String> chapter_name_rule,Element element);
	
	/**
	 * 章节URL规则解析
	 * @param bookInfo
	 * @param chapter_list_rule
	 * @param elements
	 */
	public void chapterUrlAnalynize(Chapter chapter,Map<String, String> chapter_url_rule,Element element);
	
	/**
	 * 章节列表页面url规则解析
	 * @param bookInfo
	 * @param chapter_list_url_rule
	 * @param document
	 */
	public void chapterListUrlAnalynize(BookInfo bookInfo,Map<String, String> chapter_list_url_rule,Element element);
	
	/**
	 * 章节内功规则解析
	 * @param chapter
	 * @param chapter_list_url_rule
	 * @param document
	 */
	public void chapterContentAnalynize(Chapter chapter,Map<String, String> chapter_list_url_rule,Element element);
	
}
