package com.doheum.sb;

public class Test2Main {

	public static void main(String[] args) {
		sum(5, 5);
		sum(10, 5);
		
		int result = sum2(1, 5);
		System.out.println(result);
		
		System.out.println(sum2(10, 20));
	}
	
	public static void sum(int n1, int n2) {
		System.out.println(n1 + n2);
	}
	
	public static int sum2(int n1, int n2) {
		return n1 + n2;
	}

}
