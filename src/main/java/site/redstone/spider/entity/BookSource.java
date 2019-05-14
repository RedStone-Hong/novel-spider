package site.redstone.spider.entity;

public class BookSource {
	private String source_name;//书源名称							
	private String source_url;//书源地址
	private boolean key_words_encode;//关键字Encoder
	private String search_url_rule;//搜索规则
	private String search_list_rule;//搜索列表规则
	private String search_list_name_rule;//列表书名规则
	private String search_list_author_rule;//列表作者规则
	private String search_list_last_chapter_name_rule;//列表最新章节名称规则
	private String search_list_last_chapter_url_rule;//列表最新章节url规则
	private String search_list_cover_rule;//列表封面规则
	private String search_list_book_rule;//列表书籍详情url规则
	private String search_list_introduce_rule;//列表书籍简介规则
	
	private String book_name_rule;//书籍页书名规则
	private String book_cover_rule;//书籍页封面规则
	private String book_last_chapter_url_rule;//最新章节url规则
	private String book_last_chapter_name_rule;//最新章节名称规则
	private String book_author_rule;//作者规则
	private String book_introduce_rule;//书籍页简介规则
	private String book_chapter_list_url_rule;//书籍页列表url规则 --可与详情同页即为null
	private String book_chapter_list_rule;//章节页章节列表规则
	private String book_chapter_url_rule;//章节列表章节url规则
	private String book_chapter_name_rule;//章节列表章节名称规则
	private String book_content_rule;//章节内容规则
	private String book_next_chapter_list_rule;//章节页下一页规则
	
	private String content_last_chapter_url_rule;
	private String content_next_chapter_url_rule;
	private String content_chapter_name_rule;
	private String request_method;//请求方式get/post
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getSource_url() {
		return source_url;
	}
	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}
	public boolean isKey_words_encode() {
		return key_words_encode;
	}
	public void setKey_words_encode(boolean key_words_encode) {
		this.key_words_encode = key_words_encode;
	}
	public String getSearch_url_rule() {
		return search_url_rule;
	}
	public void setSearch_url_rule(String search_url_rule) {
		this.search_url_rule = search_url_rule;
	}
	public String getSearch_list_rule() {
		return search_list_rule;
	}
	public void setSearch_list_rule(String search_list_rule) {
		this.search_list_rule = search_list_rule;
	}
	public String getSearch_list_name_rule() {
		return search_list_name_rule;
	}
	public void setSearch_list_name_rule(String search_list_name_rule) {
		this.search_list_name_rule = search_list_name_rule;
	}
	public String getSearch_list_author_rule() {
		return search_list_author_rule;
	}
	public void setSearch_list_author_rule(String search_list_author_rule) {
		this.search_list_author_rule = search_list_author_rule;
	}
	public String getSearch_list_last_chapter_name_rule() {
		return search_list_last_chapter_name_rule;
	}
	public void setSearch_list_last_chapter_name_rule(String search_list_last_chapter_name_rule) {
		this.search_list_last_chapter_name_rule = search_list_last_chapter_name_rule;
	}
	public String getSearch_list_last_chapter_url_rule() {
		return search_list_last_chapter_url_rule;
	}
	public void setSearch_list_last_chapter_url_rule(String search_list_last_chapter_url_rule) {
		this.search_list_last_chapter_url_rule = search_list_last_chapter_url_rule;
	}
	public String getSearch_list_cover_rule() {
		return search_list_cover_rule;
	}
	public void setSearch_list_cover_rule(String search_list_cover_rule) {
		this.search_list_cover_rule = search_list_cover_rule;
	}
	public String getSearch_list_book_rule() {
		return search_list_book_rule;
	}
	public void setSearch_list_book_rule(String search_list_book_rule) {
		this.search_list_book_rule = search_list_book_rule;
	}
	public String getSearch_list_introduce_rule() {
		return search_list_introduce_rule;
	}
	public void setSearch_list_introduce_rule(String search_list_introduce_rule) {
		this.search_list_introduce_rule = search_list_introduce_rule;
	}
	public String getBook_name_rule() {
		return book_name_rule;
	}
	public void setBook_name_rule(String book_name_rule) {
		this.book_name_rule = book_name_rule;
	}
	public String getBook_cover_rule() {
		return book_cover_rule;
	}
	public void setBook_cover_rule(String book_cover_rule) {
		this.book_cover_rule = book_cover_rule;
	}
	public String getBook_last_chapter_url_rule() {
		return book_last_chapter_url_rule;
	}
	public void setBook_last_chapter_url_rule(String book_last_chapter_url_rule) {
		this.book_last_chapter_url_rule = book_last_chapter_url_rule;
	}
	public String getBook_last_chapter_name_rule() {
		return book_last_chapter_name_rule;
	}
	public void setBook_last_chapter_name_rule(String book_last_chapter_name_rule) {
		this.book_last_chapter_name_rule = book_last_chapter_name_rule;
	}
	public String getBook_author_rule() {
		return book_author_rule;
	}
	public void setBook_author_rule(String book_author_rule) {
		this.book_author_rule = book_author_rule;
	}
	public String getBook_introduce_rule() {
		return book_introduce_rule;
	}
	public void setBook_introduce_rule(String book_introduce_rule) {
		this.book_introduce_rule = book_introduce_rule;
	}
	public String getBook_chapter_list_url_rule() {
		return book_chapter_list_url_rule;
	}
	public void setBook_chapter_list_url_rule(String book_chapter_list_url_rule) {
		this.book_chapter_list_url_rule = book_chapter_list_url_rule;
	}
	public String getBook_chapter_list_rule() {
		return book_chapter_list_rule;
	}
	public void setBook_chapter_list_rule(String book_chapter_list_rule) {
		this.book_chapter_list_rule = book_chapter_list_rule;
	}
	public String getBook_chapter_url_rule() {
		return book_chapter_url_rule;
	}
	public void setBook_chapter_url_rule(String book_chapter_url_rule) {
		this.book_chapter_url_rule = book_chapter_url_rule;
	}
	public String getBook_chapter_name_rule() {
		return book_chapter_name_rule;
	}
	public void setBook_chapter_name_rule(String book_chapter_name_rule) {
		this.book_chapter_name_rule = book_chapter_name_rule;
	}
	public String getBook_content_rule() {
		return book_content_rule;
	}
	public void setBook_content_rule(String book_content_rule) {
		this.book_content_rule = book_content_rule;
	}
	public String getBook_next_chapter_list_rule() {
		return book_next_chapter_list_rule;
	}
	public void setBook_next_chapter_list_rule(String book_next_chapter_list_rule) {
		this.book_next_chapter_list_rule = book_next_chapter_list_rule;
	}
	public String getContent_last_chapter_url_rule() {
		return content_last_chapter_url_rule;
	}
	public void setContent_last_chapter_url_rule(String content_last_chapter_url_rule) {
		this.content_last_chapter_url_rule = content_last_chapter_url_rule;
	}
	public String getContent_next_chapter_url_rule() {
		return content_next_chapter_url_rule;
	}
	public void setContent_next_chapter_url_rule(String content_next_chapter_url_rule) {
		this.content_next_chapter_url_rule = content_next_chapter_url_rule;
	}
	public String getContent_chapter_name_rule() {
		return content_chapter_name_rule;
	}
	public void setContent_chapter_name_rule(String content_chapter_name_rule) {
		this.content_chapter_name_rule = content_chapter_name_rule;
	}
	public String getRequest_method() {
		return request_method;
	}
	public void setRequest_method(String request_method) {
		this.request_method = request_method;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookSource [source_name=");
		builder.append(source_name);
		builder.append(", source_url=");
		builder.append(source_url);
		builder.append(", key_words_encode=");
		builder.append(key_words_encode);
		builder.append(", search_url_rule=");
		builder.append(search_url_rule);
		builder.append(", search_list_rule=");
		builder.append(search_list_rule);
		builder.append(", search_list_name_rule=");
		builder.append(search_list_name_rule);
		builder.append(", search_list_author_rule=");
		builder.append(search_list_author_rule);
		builder.append(", search_list_last_chapter_name_rule=");
		builder.append(search_list_last_chapter_name_rule);
		builder.append(", search_list_last_chapter_url_rule=");
		builder.append(search_list_last_chapter_url_rule);
		builder.append(", search_list_cover_rule=");
		builder.append(search_list_cover_rule);
		builder.append(", search_list_book_rule=");
		builder.append(search_list_book_rule);
		builder.append(", search_list_introduce_rule=");
		builder.append(search_list_introduce_rule);
		builder.append(", book_name_rule=");
		builder.append(book_name_rule);
		builder.append(", book_cover_rule=");
		builder.append(book_cover_rule);
		builder.append(", book_last_chapter_url_rule=");
		builder.append(book_last_chapter_url_rule);
		builder.append(", book_last_chapter_name_rule=");
		builder.append(book_last_chapter_name_rule);
		builder.append(", book_author_rule=");
		builder.append(book_author_rule);
		builder.append(", book_introduce_rule=");
		builder.append(book_introduce_rule);
		builder.append(", book_chapter_list_url_rule=");
		builder.append(book_chapter_list_url_rule);
		builder.append(", book_chapter_list_rule=");
		builder.append(book_chapter_list_rule);
		builder.append(", book_chapter_url_rule=");
		builder.append(book_chapter_url_rule);
		builder.append(", book_chapter_name_rule=");
		builder.append(book_chapter_name_rule);
		builder.append(", book_content_rule=");
		builder.append(book_content_rule);
		builder.append(", book_next_chapter_list_rule=");
		builder.append(book_next_chapter_list_rule);
		builder.append(", content_last_chapter_url_rule=");
		builder.append(content_last_chapter_url_rule);
		builder.append(", content_next_chapter_url_rule=");
		builder.append(content_next_chapter_url_rule);
		builder.append(", content_chapter_name_rule=");
		builder.append(content_chapter_name_rule);
		builder.append(", request_method=");
		builder.append(request_method);
		builder.append("]");
		return builder.toString();
	}
	
}
