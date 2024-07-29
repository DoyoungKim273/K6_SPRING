package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest2 {
	@Autowired private BoardRepository boardRepo;
	
	@BeforeEach
	public void dataPrepare() {
		for(int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목" + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt((long)(Math.random() * 100));
			
			boardRepo.save(board);
		}
	}

	//title에 1이 포함되는 데이터 출력
	//SELECT * FROM BOARD WHERE TITLE like %1%;
//	@Test // Test에 있는 BoardRepository의 메서드를 이용하므로 어노테이션 붙여줘야함
	public void test2() {
		List<Board> list = boardRepo.findByTitleContaining("1");
		// 데이터 타입이 list 이므로 앞에 데이터 타입 명시
		// List<Board> 까지가 데이터 타입
		// boardRepo 안에서 1 을 포함한 데이터를 find~ 메소드를 이용해 불러온다
		// Board 데이터 타입의 임의의 변수를 기준으로 for 문을 이용해 list 순회하여 출력
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test // test 주석 잊지 말기
	public void test3() {
		List<Board> list = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);	
		//쿼리메소드에서 숫자의 대소까지 표현할 수 있음
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testFindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);
		
		System.out.println("PAGE SIZE : " + pageInfo.getSize());
		System.out.println("TOTAL PAGE : " + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT  : " + pageInfo.getTotalElements());
		System.out.println("NEXT : " + pageInfo.nextPageable());
		
		List<Board> list = pageInfo.getContent();
		System.out.println("검색 결과");
		
		for(Board b : list) {
			System.out.println("---> " + boardRepo.toString());
		}
	}
	
}
