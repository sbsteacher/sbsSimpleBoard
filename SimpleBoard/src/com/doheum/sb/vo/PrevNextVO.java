package com.doheum.sb.vo;

//이전글, 다음글 i_board값
public class PrevNextVO {
	private final int prev;
	private final int next;
	
	public PrevNextVO(int prev, int next) {
		super();
		this.prev = prev;
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	public int getNext() {
		return next;
	}
}
