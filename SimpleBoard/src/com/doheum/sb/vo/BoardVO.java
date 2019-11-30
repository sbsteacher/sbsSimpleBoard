package com.doheum.sb.vo;

public class BoardVO {
	private int i_board;
	private int grp;
	private int seq;
	private int floor;
	private String title;
	private String content;
	private String regDateTime;
	private int cnt;
	private String uid;
	private String nm;
	private int favorite;
	
	public BoardVO() {}
	
	public BoardVO(String title, String content) {
		this(0, title, content, null);
	}
	
	public BoardVO(int i_board, String title, String content) {
		this(i_board, title, content, null);
	}
	
	public BoardVO(int i_board, String title, String content, String regDateTime) {	
		this.i_board = i_board;
		this.title = title;
		this.content = content;
		this.regDateTime = regDateTime;
	}
	
	public BoardVO(int i_board, String title, String content, String regDateTime, int cnt) {
		super();
		this.i_board = i_board;
		this.title = title;
		this.content = content;
		this.regDateTime = regDateTime;
		this.cnt = cnt;
	}
	
	public int getGrp() {
		return grp;
	}

	public void setGrp(int grp) {
		this.grp = grp;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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






