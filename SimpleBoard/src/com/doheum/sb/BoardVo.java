package com.doheum.sb;

public class BoardVo {
	private String title;
	private String content;
	
	public BoardVo(String a, String b) {
		title = a;
		content = b;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//getter, setter 메소드를 통해 (넣고, 뺀다)
	
	//생성자를 통해 (넣는다)
	
	
	
}
