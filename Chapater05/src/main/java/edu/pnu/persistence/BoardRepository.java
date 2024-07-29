package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	// SELECT * FROM BOARD WHERE TITLE = SEARCHKEYWORD;
	List<Board> findByTitle(String searchKeyword);
	
	//
	List<Board> findByContentContaining(String searchKeyword);
	
//	List<Board> findByTit  leContainingOrContentContaining(String title, String content);
	
	// SELECT * FROM BOARD WHERE TITLE LIKE %SEARCHKEYWORD%;
	List<Board> findByTitleContaining(String searchKeyword);
	
	// SELECT * FROM BOARD WHERE TITLE LIKE %SEARCHKEYWORD% AND CNT > 50;
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);
	
	
	//SELECT * FROM BOARD WHERE CNT BETWEEN 10 AND 50 ORDER BY ASC
	List<Board> findByCntBetween(Long cnt1, Long cnt2);
	
	//SELECT * FROM BOARD WHERE TITLE LIKE %SEARCHKEYWORD% 
	// OR CONTENT LIKE %2% ORDER BY DSC;
	List<Board> findByTitleContainingOrContentContaining(String title,String content);

	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);

//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
//	List<Board> queryAnnotationTest1(String searchKeyword);

//	@Query("SELECT b FROM Board b WHERE b.title LIKE %searchKeyword% ORDER BY b.seq DESC")
//	List<Board> queryAnnotationTest2(@Param("searchKeyword")String searchKeyword);
	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate "
//			+ "FROM Board b"
//			+ "WHERE b.title like %?1% "
//			+ "ORDER BY b.seq DESC")
//	List<Object[]>queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
//	
//	@Query(value="select seq, title, writer, createDate "
//			+ "from board where title like '%'||?1||'%' "
//			+ "order by seq desc", nativeQuery=true)
//	List<Object[]> queryAnnotationTest3(String searchKeyword);
}
