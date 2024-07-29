package edu.pnu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.LogVO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
		@Autowired
		private MemberDAO dao = new MemberDAO();
		private LogDAO logDAO = new LogDAO();
		
		public List<MemberVO> getAllMembers(){
			return dao.getAllMembers();
		}
		
		public MemberVO getMemberById(Integer id) {	
			Map<String, Object> result = dao.getMemberById(id);
			logDAO.insertLog((LogVO)result.get("logVO"));
			return (MemberVO)result.get("memberVO");
//			return dao.getMemberById(id);
		
		}


		public MemberVO addMember(MemberVO memberVO) {
			// TODO Auto-generated method stub
			return dao.addMember(memberVO);
		}


		public int removeMember(Integer id) {
			// TODO Auto-generated method stub
			return dao.removeMember(id);
		}


		public MemberVO updateMembers(MemberVO memberVO) {
			// TODO Auto-generated method stub
			return dao.updateMembers(memberVO);
		}


		public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
			// TODO Auto-generated method stub
			return dao.addMemberJSON(memberVO);
		}
		
	
	}

