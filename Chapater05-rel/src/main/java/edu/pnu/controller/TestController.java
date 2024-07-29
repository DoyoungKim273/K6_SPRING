package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/boards")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	@PostMapping("/board")
	public void insertBoard(){
		Board board = new Board();
		board.setTitle("첫번째 게시글");
//		board.setWriter("테스터");
		board.setContent("잘 등록 되나요?"); 
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);	
	}
	
	@PutMapping("/board")
	public void updateBoard(Long seq) {
		Board board = boardRepo.findById(seq).get();
//		board.update(board);
//		board.setTitle("첫번째 게시글 수정");
//		board.setWriter("테스터 수정");
//		board.setContent("잘 수정 되나요?"); 
//		board.setCreateDate(new Date());
//		board.setCnt(0L);
		
		boardRepo.save(board);	
		
//		System.out.println("== 1번 게시글 조회 ==");
//		Board board = boardRepo.findById(1L).get();
//		System.out.println("== 게시글 제목 수정 ==");
//		board.setTitle("제목을 수정했습니다.");
//		boardRepo.save(board);
	}
	
	@DeleteMapping("/board")
//	public void deleteBoard(Long seq){
//		boardRepo.deleteById(seq);
//	}
	public Board deleteBoard(@PathVariable Long seq) {
		Board board = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return board;
	}
}
