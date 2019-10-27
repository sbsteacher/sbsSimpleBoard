package com.doheum.sb;

import java.util.*;

public class ListTest {

	public static void main(String[] args) {
		List<BoardVo> list = new ArrayList();
				
		BoardVo vo = new BoardVo("", "");
		list.add(vo);
		
		BoardVo vo2 = list.get(0);
		
		BoardVo obj3 = list.get(0);
		
		list.size();
	
	}

}
