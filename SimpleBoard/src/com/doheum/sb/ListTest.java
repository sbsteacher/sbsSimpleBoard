package com.doheum.sb;

import java.util.*;

import com.doheum.sb.vo.BoardVO;

public class ListTest {

	public static void main(String[] args) {
		List<BoardVO> list = new ArrayList();
				
		BoardVO vo = new BoardVO("", "");
		list.add(vo);
		
		BoardVO vo2 = list.get(0);
		
		BoardVO obj3 = list.get(0);
		
		list.size();
	
	}

}
