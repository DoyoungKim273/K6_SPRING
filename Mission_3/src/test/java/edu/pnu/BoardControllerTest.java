package edu.pnu;

import java.util.List;
//import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@SpringBootTest
public class BoardControllerTest {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void test() {
		List<MemberVO> list = memberDAO.getAllMembers();
		
		for(MemberVO m : list)
			System.out.println(m);
	}
}
