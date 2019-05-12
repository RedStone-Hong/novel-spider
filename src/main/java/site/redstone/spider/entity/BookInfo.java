package site.redstone.spider.entity;

import java.util.List;

public class BookInfo {
	private String source_name;
	private String book_name;
	private String book_url;
	private String last_chapter_name;
	private String last_chapter_url;
	private String chapter_list_url;
	private String author;
	private String introduce;
	private String cover_url;
	private List<Chapter> chapter_list;
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_url() {
		return book_url;
	}
	public void setBook_url(String book_url) {
		this.book_url = book_url;
	}
	public String getLast_chapter_name() {
		return last_chapter_name;
	}
	public void setLast_chapter_name(String last_chapter_name) {
		this.last_chapter_name = last_chapter_name;
	}
	public String getLast_chapter_url() {
		return last_chapter_url;
	}
	public void setLast_chapter_url(String last_chapter_url) {
		this.last_chapter_url = last_chapter_url;
	}
	public String getChapter_list_url() {
		return chapter_list_url;
	}
	public void setChapter_list_url(String chapter_list_url) {
		this.chapter_list_url = chapter_list_url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public List<Chapter> getChapter_list() {
		return chapter_list;
	}
	public void setChapter_list(List<Chapter> chapter_list) {
		this.chapter_list = chapter_list;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookInfo [book_name=");
		builder.append(book_name);
		builder.append(", source_name=");
		builder.append(source_name);
		builder.append(", book_url=");
		builder.append(book_url);
		builder.append(", last_chapter_name=");
		builder.append(last_chapter_name);
		builder.append(", last_chapter_url=");
		builder.append(last_chapter_url);
		builder.append(", chapter_list_url=");
		builder.append(chapter_list_url);
		builder.append(", author=");
		builder.append(author);
		builder.append(", introduce=");
		builder.append(introduce);
		builder.append(", cover_url=");
		builder.append(cover_url);
		builder.append(", chapter_list=");
		builder.append(chapter_list==null?"null":chapter_list.size());
		builder.append("]");
		return builder.toString();
	}
	
	
}
