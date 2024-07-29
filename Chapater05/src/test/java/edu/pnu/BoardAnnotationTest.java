package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardAnnotationTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@BeforeEach
	public void dataPrepare() {
		for(int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터 ");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(1L);
			
			boardRepo.save(board);
		}
	}
	@Test
	public void testQueryAnnotationTest1() {
		List<Board> list = boardRepo.queryAnnotationTest1("테스트 제목 10");
		System.out.println("검색 결과");
		
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> list = boardRepo.queryAnnotationTest2("테스트 제목 10");
		System.out.println("검색 결과");
		
		for(Object[] row : list) {
			System.out.println("--->" + Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> list = boardRepo.queryAnnotationTest3("테스트 제목 10");
		System.out.println("검색 결과");
		
		for(Object[] row : list) {
			System.out.println("--->" + Arrays.toString(row));
		}
	}
	
} 
