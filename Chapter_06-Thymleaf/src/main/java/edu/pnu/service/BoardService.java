package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {

	List<Board> getBoardList(Board board);

	Board getBoard(Board board);

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

}