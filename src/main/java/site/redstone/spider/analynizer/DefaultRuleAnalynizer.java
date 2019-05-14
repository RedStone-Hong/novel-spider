package site.redstone.spider.analynizer;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;

import site.redstone.spider.entity.BookInfo;
import site.redstone.spider.entity.BookSource;
import site.redstone.spider.entity.Chapter;

/**
 * @desc 默认的规则解析类
 * @date 2019-05-10
 * @author 洪高伟
 */
public class DefaultRuleAnalynizer implements RuleAnalynizer{

	@Override
	public Map<String, String> analynizeRule(String rule,BookSource bookSource) throws Exception {
		if(null == rule || "".equals(rule)) {
			return null;
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("index", "0");
		result.put("prefix", "");
		String[] ruleArr = rule.split("@");
		for (int i = 0; i < ruleArr.length; i++) {
			if (i == 0) {
				result.put("selector", ruleArr[0]);
			}
			if (i == 1) {
				result.put("index", ruleArr[1]);
			}
			if (i == 2) {
				if (!ruleArr[2].equals("text") && !ruleArr[2].equals("html")) {
					result.put("attr", ruleArr[2].split(":")[1]);
				} else {
					result.put("method", ruleArr[2]);
				}
			}
			if (i == 3) {
				if(ruleArr[3].equals("context")){
					result.put("prefix", bookSource.getSource_url());
				}else if(ruleArr[3].equals("this")) {
					result.put("prefix", "this");
				}else{
					result.put("prefix", "");
				}
			}
			if (i == 4){
				String[] tempStr = ruleArr[4].split("-:-");
				result.put("split_mark", tempStr[0]);
				result.put("split_index", tempStr[1]);
			}
		}
		return result;
	}

	@Override
	public void bookNameAnalynize(BookInfo bookInfo, Map<String, String> book_name_rule, Element element) {
		bookInfo.setBook_name(commonRuleAnalynize(book_name_rule, element));
	}

	@Override
	public void bookAuthorAnalynize(BookInfo bookInfo, Map<String, String> book_author_rule, Element element) {
		bookInfo.setAuthor(commonRuleAnalynize(book_author_rule, element));
	}

	@Override
	public void searchListBookUrlAnalynize(BookInfo bookInfo, Map<String, String> book_url_rule, Element element) {
		bookInfo.setBook_url(commonRuleAnalynize(book_url_rule, element));
	}

	@Override
	public void lastChapterNameAnalynize(BookInfo bookInfo, Map<String, String> last_chapter_name_rule, Element element) {
		bookInfo.setLast_chapter_name(commonRuleAnalynize(last_chapter_name_rule, element));
	}

	@Override
	public void lastChapterUrlAnalynize(BookInfo bookInfo, Map<String, String> last_chapter_url_rule, Element element) {
		bookInfo.setLast_chapter_url(commonRuleAnalynize(last_chapter_url_rule, element));
	}

	@Override
	public void bookIntroduceAnalynize(BookInfo bookInfo, Map<String, String> introduce_rule, Element element) {
		bookInfo.setIntroduce(commonRuleAnalynize(introduce_rule, element));
	}

	@Override
	public void bookCoverAnalynize(BookInfo bookInfo, Map<String, String> cover_rule, Element element) {
		bookInfo.setCover_url(commonRuleAnalynize(cover_rule, element));	
	}

	@Override
	public void chapterNameAnalynize(Chapter chapter, Map<String, String> chapter_name_rule, Element element) {
		chapter.setChapter_name(commonRuleAnalynize(chapter_name_rule, element));
	}

	@Override
	public void chapterUrlAnalynize(Chapter chapter, Map<String, String> chapter_url_rule, Element element,String prefix) {
		chapter.setChapter_url(commonRuleAnalynize(chapter_url_rule, element,prefix));
	}

	@Override
	public void chapterListUrlAnalynize(BookInfo bookInfo, Map<String, String> chapter_list_url_rule,
			Element element) {
		bookInfo.setChapter_list_url(commonRuleAnalynize(chapter_list_url_rule, element));
	}

	@Override
	public void chapterContentAnalynize(Chapter chapter, Map<String, String> chapter_content_rule, Element element) {
		chapter.setChapter_content(commonRuleAnalynize(chapter_content_rule, element));
	}

	private String commonRuleAnalynize(Map<String, String> rule, Element element,String prefix){
		if(rule == null) {
			return null;
		}
		String result = null;
		if (rule.get("method") != null) {
			if("text".equals(rule.get("method").toString())) {
				result = element.select(rule.get("selector")).get(Integer.valueOf(rule.get("index"))).text();
			}
			if("html".equals(rule.get("method").toString())) {
				result = element.select(rule.get("selector")).get(Integer.valueOf(rule.get("index"))).html();
			}
		} else {
			result = element.select(rule.get("selector")).get(Integer.valueOf(rule.get("index"))).attr((String) rule.get("attr"));
		}
		if(result != null && !"".equals(result)){
			if("this".equals(rule.get("prefix"))) {
				result = prefix + result;
			}else {
				result = rule.get("prefix") + result;
			}
		}else{
			result = null;
		}
		if(rule.get("split_mark") != null && result != null){
			String[] tempStr = result.split(rule.get("split_mark"));
			result = tempStr[Integer.valueOf(rule.get("split_index"))];
		}
		return result;
	}
	
	private String commonRuleAnalynize(Map<String, String> rule, Element element){
		return commonRuleAnalynize(rule,element,null);
	}
}
