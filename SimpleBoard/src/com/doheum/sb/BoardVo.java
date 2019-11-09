package com.doheum.sb;

public class BoardVo {
	private int i_board;
	private String title;
	private String content;
	private String regDateTime;
	private int cnt;
	
	public BoardVo() {}
	
	public BoardVo(String title, String content) {
		this(0, title, content, null);
	}
	
	public BoardVo(int i_board, String title, String content) {
		this(i_board, title, content, null);
	}
	
	public BoardVo(int i_board, String title, String content, String regDateTime) {	
		this.i_board = i_board;
		this.title = title;
		this.content = content;
		this.regDateTime = regDateTime;
	}
	
	public BoardVo(int i_board, String title, String content, String regDateTime, int cnt) {
		super();
		this.i_board = i_board;
		this.title = title;
		this.content = content;
		this.regDateTime = regDateTime;
		this.cnt = cnt;
	}

	public int getI_board() {
		return i_board;
	}

	public void setI_board(int i_board) {
		this.i_board = i_board;
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

	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}






