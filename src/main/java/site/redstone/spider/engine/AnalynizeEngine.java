package site.redstone.spider.engine;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import site.redstone.spider.analynizer.DefaultRuleAnalynizer;
import site.redstone.spider.analynizer.RuleAnalynizer;
import site.redstone.spider.entity.BookInfo;
import site.redstone.spider.entity.BookSource;
import site.redstone.spider.entity.Chapter;
import site.redstone.spider.factory.AnalynizeEngineFactory;
import site.redstone.spider.util.UrlUtil;

public class AnalynizeEngine {
	
	private RuleAnalynizer ruleAnalynizer;
	private BookSource bookSource;
	private Map<String, Map<String,String>> rules = new HashMap<String, Map<String,String>>();
	
	public AnalynizeEngine setRuleAnalynizer(RuleAnalynizer ruleAnalynizer) {
		this.ruleAnalynizer = ruleAnalynizer;
		return this;
	}
	
	public AnalynizeEngine setBookSource(BookSource bookSource) {
		this.bookSource = bookSource;
		return this;
	}
	private void initRules() throws Exception {
		//使用默认解析器
		if(ruleAnalynizer == null){
			ruleAnalynizer = new DefaultRuleAnalynizer();
		}
		if(rules.size() > 0 ) {
			return;
		}
		// 书名规则解析
		Map<String, String> list_book_name_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_name_rule(),bookSource);
		rules.put("list_book_name_rule", list_book_name_rule);
		// 作者规则解析
		Map<String, String> list_book_author_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_author_rule(),bookSource);
		rules.put("list_book_author_rule", list_book_author_rule);
		// 书籍URL规则解析
		Map<String, String> book_url_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_book_rule(),bookSource);
		rules.put("book_url_rule", book_url_rule);
		// 最新章节地址规则解析
		Map<String, String> list_last_chapter_url_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_last_chapter_url_rule(),bookSource);
		rules.put("list_last_chapter_url_rule", list_last_chapter_url_rule);
		// 最新章节名称规则解析
		Map<String, String> list_last_chapter_name_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_last_chapter_name_rule(),bookSource);
		rules.put("list_last_chapter_name_rule", list_last_chapter_name_rule);
		// 简介规则解析
		Map<String, String> list_introduce_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_introduce_rule(),bookSource);
		rules.put("list_introduce_rule", list_introduce_rule);
		// 封面URL规则解析
		Map<String, String> list_cover_rule = ruleAnalynizer.analynizeRule(bookSource.getSearch_list_cover_rule(),bookSource);
		rules.put("list_cover_rule", list_cover_rule);
		
		// 书籍详情页书名规则解析
		Map<String, String> book_name_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_name_rule(), bookSource);
		rules.put("book_name_rule", book_name_rule);
		// 书籍详情页作者规则解析
		Map<String, String> book_author_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_author_rule(), bookSource);
		rules.put("book_author_rule", book_author_rule);
		// 书籍详情页封面规则解析
		Map<String, String> book_cover_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_cover_rule(), bookSource);
		rules.put("book_cover_rule", book_cover_rule);
		// 书籍详情页简介规则解析
		Map<String, String> book_introduce_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_introduce_rule(), bookSource);
		rules.put("book_introduce_rule", book_introduce_rule);
		// 书籍详情页最新章节名称规则解析
		Map<String, String> book_last_chapter_name_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_last_chapter_name_rule(), bookSource);
		rules.put("book_last_chapter_name_rule", book_last_chapter_name_rule);
		// 书籍详情页最新章节url规则解析
		Map<String, String> book_last_chapter_url_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_last_chapter_url_rule(), bookSource);
		rules.put("book_last_chapter_url_rule", book_last_chapter_url_rule);
		
		// 书籍详情页章节名称规则解析
		Map<String, String> chapter_name_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_chapter_name_rule(),bookSource);
		rules.put("chapter_name_rule", chapter_name_rule);
		// 书籍详情页章节URL规则解析
		Map<String, String> chapter_url_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_chapter_url_rule(),bookSource);
		rules.put("chapter_url_rule", chapter_url_rule);
		// 书籍详情页章节列表所在页面URL规则解析
		Map<String, String> chapter_list_url_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_chapter_list_url_rule(),bookSource);
		rules.put("chapter_list_url_rule", chapter_list_url_rule);
		
		// 章节内容规则解析
		Map<String, String> chapter_content_rule = ruleAnalynizer.analynizeRule(bookSource.getBook_content_rule(),bookSource);
		rules.put("chapter_content_rule", chapter_content_rule);
		
		// 内容页上一章节规则解析
		Map<String, String> content_last_chapter_rule = ruleAnalynizer.analynizeRule(bookSource.getContent_last_chapter_url_rule(),bookSource);
		rules.put("content_last_chapter_url_rule", content_last_chapter_rule);
		// 内容页下一章节规则解析
		Map<String, String> content_next_chapter_rule = ruleAnalynizer.analynizeRule(bookSource.getContent_next_chapter_url_rule(),bookSource);
		rules.put("content_next_chapter_url_rule", content_next_chapter_rule);
		// 内容页章节名称规则解析
		Map<String, String> content_chapter_name_rule = ruleAnalynizer.analynizeRule(bookSource.getContent_chapter_name_rule(),bookSource);
		rules.put("content_chapter_name_rule", content_chapter_name_rule);
	}

	/**
	 * 搜索书籍
	 * @param searchKey
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> search(String searchKey) throws Exception {
		//规则初始化
		initRules();
		// 获取搜索URL
		String search_url = bookSource.getSearch_url_rule();
		// 判断是否搜索结果分页
		boolean page_flag = false;
		// 封装用于返回的书籍信息
		List<BookInfo> bookInfoList = new ArrayList<BookInfo>();
		// 替换搜索关键词
		if(bookSource.isKey_words_encode()) {
			search_url = search_url.replace("searchKey", URLEncoder.encode(searchKey, "gbk"));
		}else {
			search_url = search_url.replace("searchKey", searchKey);
		}
		// 分页页码
		int pageNumber = 1;
		// 判断结果是否需要分页
		if (search_url.contains("searchPage")) {
			page_flag = true;
			search_url = search_url.replace("searchPage", String.valueOf(pageNumber));
		}
		do {
			Document document = UrlUtil.gatherDocument(search_url,bookSource.getRequest_method());
			if (page_flag) {
				pageNumber++;
				search_url = search_url.replace("searchPage", String.valueOf(pageNumber));
			}
			Elements listElements = document.select(bookSource.getSearch_list_rule());
			if(listElements == null || listElements.size() == 0) {
				break;
			}
			if (listElements.size() == 0) {
				break;
			}
			for (Element element : listElements) {
				BookInfo bookInfo = new BookInfo();
				ruleAnalynizer.bookNameAnalynize(bookInfo, rules.get("list_book_name_rule"), element);
				ruleAnalynizer.bookAuthorAnalynize(bookInfo, rules.get("list_book_author_rule"), element);
				ruleAnalynizer.searchListBookUrlAnalynize(bookInfo, rules.get("book_url_rule"), element);
				ruleAnalynizer.lastChapterNameAnalynize(bookInfo, rules.get("list_last_chapter_name_rule"), element);
				ruleAnalynizer.lastChapterUrlAnalynize(bookInfo, rules.get("list_last_chapter_url_rule"), element);
				ruleAnalynizer.bookIntroduceAnalynize(bookInfo, rules.get("list_introduce_rule"), element);
				ruleAnalynizer.bookCoverAnalynize(bookInfo, rules.get("list_cover_rule"), element);
				bookInfo.setSource_name(bookSource.getSource_name());
				bookInfoList.add(bookInfo);
			}
		} while (page_flag);
		return bookInfoList;
	}
	
	
	/**
	 * 解析书籍详情
	 * @param bookInfo
	 * @return
	 * @throws Exception
	 */
	public BookInfo chapterListAnalynize(BookInfo bookInfo) throws Exception {
		//规则初始化
		initRules();
		List<Chapter> chapterList = new ArrayList<Chapter>();
		String book_url = bookInfo.getBook_url();
		Document document = UrlUtil.gatherDocument(book_url,bookSource.getRequest_method());
		/**  封装书籍详情 */
		ruleAnalynizer.bookNameAnalynize(bookInfo, rules.get("book_name_rule"), document);
		ruleAnalynizer.bookAuthorAnalynize(bookInfo, rules.get("book_author_rule"), document);
		ruleAnalynizer.bookIntroduceAnalynize(bookInfo, rules.get("book_introduce_rule"), document);
		ruleAnalynizer.bookCoverAnalynize(bookInfo, rules.get("book_cover_rule"), document);
		ruleAnalynizer.lastChapterNameAnalynize(bookInfo, rules.get("book_last_chapter_name_rule"), document);
		ruleAnalynizer.lastChapterUrlAnalynize(bookInfo, rules.get("book_last_chapter_url_rule"), document);
		
		Document listDocument = null;
		if(bookSource.getBook_chapter_list_url_rule() == null) {
			bookInfo.setChapter_list_url(book_url);
			listDocument = document;
		}else {
			ruleAnalynizer.chapterListUrlAnalynize(bookInfo, rules.get("chapter_list_url_rule"), document);
			listDocument = UrlUtil.gatherDocument(bookInfo.getChapter_list_url(),bookSource.getRequest_method());
		}
		
		Elements listElements = listDocument.select(bookSource.getBook_chapter_list_rule());
		for (Element element : listElements) {
			Chapter chapter = new Chapter();
			ruleAnalynizer.chapterNameAnalynize(chapter, rules.get("chapter_name_rule"), element);
			ruleAnalynizer.chapterUrlAnalynize(chapter, rules.get("chapter_url_rule"), element,bookInfo.getChapter_list_url());
			chapterList.add(chapter);
		}
		bookInfo.setChapter_list(chapterList);
		return bookInfo;
	}
	
	public Chapter chapterContentAnalynize(Chapter chapter) throws Exception {
		//规则初始化
		initRules();
		Document document = UrlUtil.gatherDocument(chapter.getChapter_url(),bookSource.getRequest_method());
		ruleAnalynizer.chapterContentAnalynize(chapter, rules.get("chapter_content_rule"), document);
		ruleAnalynizer.chapterNameAnalynize(chapter, rules.get("content_chapter_name_rule"), document);
		Chapter last_chapter = new Chapter();
		ruleAnalynizer.chapterUrlAnalynize(last_chapter, rules.get("content_last_chapter_url_rule"), document, null);
		Chapter next_chapter = new Chapter();
		ruleAnalynizer.chapterUrlAnalynize(next_chapter, rules.get("content_next_chapter_url_rule"), document, null);
		chapter.setLast_chapter(last_chapter);
		chapter.setNext_chapter(next_chapter);
		return chapter;
	}
	
	
	public static void main(String[] args) throws Exception {
		AnalynizeEngine engine = AnalynizeEngineFactory.getAnalynizeEngine("顶点小说");
		List<BookInfo> bookInfoList = engine.search("天蚕");
		BookInfo bookInfo = bookInfoList.get(0);
		//BookInfo bookInfo = new BookInfo();
		//bookInfo.setBook_url("https://www.xbiquge6.com/78_78513/");
		bookInfo = engine.chapterListAnalynize(bookInfo);
		//Chapter chapter = bookInfo.getChapter_list().get(10);
		//chapter = engine.chapterContentAnalynize(chapter);
		System.out.println(bookInfo);
		//Document doc = UrlUtil.gatherDocument("https://www.x23us.com/modules/article/search.php?searchtype=keywords&searchkey=%CC%EC%B2%CF", "get");
		//Elements eles = doc.select(".grid tbody tr:not([align=center])");
		//System.out.println(eles);
		
		
		
	}


}
