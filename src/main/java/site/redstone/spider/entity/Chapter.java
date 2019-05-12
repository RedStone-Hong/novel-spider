package site.redstone.spider.entity;

public class Chapter {
	private String chapter_name;
	private String chapter_url;
	private String chapter_content;
	private Chapter last_chapter;
	private Chapter next_chapter;
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public String getChapter_url() {
		return chapter_url;
	}
	public void setChapter_url(String chapter_url) {
		this.chapter_url = chapter_url;
	}
	public String getChapter_content() {
		return chapter_content;
	}
	public void setChapter_content(String chapter_content) {
		this.chapter_content = chapter_content;
	}
	public Chapter getLast_chapter() {
		return last_chapter;
	}
	public void setLast_chapter(Chapter last_chapter) {
		this.last_chapter = last_chapter;
	}
	public Chapter getNext_chapter() {
		return next_chapter;
	}
	public void setNext_chapter(Chapter next_chapter) {
		this.next_chapter = next_chapter;
	}
	@Override
	public String toString() {
		return "Chapter [chapter_name=" + chapter_name + ", chapter_url="
				+ chapter_url + ", last_chapter=" + last_chapter
				+ ", next_chapter=" + next_chapter + "]";
	}
	
}
