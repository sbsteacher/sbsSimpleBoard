package com.doheum.sb;

public class TestMain {

	public static void main(String[] args) {
		getIndex(1, 5); //0
		getIndex(2, 5); //5
		getIndex(3, 5); //10		
		
		getIndex(1, 10); //0
		getIndex(2, 10); //10
		getIndex(3, 10); //20
		
		getIndex(1, 15); //0
		getIndex(2, 15); //15
		getIndex(3, 15); //30		
	}
	
	public static void getIndex(int page, int showCnt) {
		int result = (page - 1) * showCnt;
		System.out.println(result);
	}

}
 