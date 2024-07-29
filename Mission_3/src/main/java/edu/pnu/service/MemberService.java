package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
		@Autowired
		MemberDAO dao;
		
		public MemberService() {
			System.out.println("MemberService 구동");
		}
		
		
		public List<MemberVO> getAllMembers(){
			return dao.getAllMembers();
		}
		
		public MemberVO getMemberById(Integer id) {	
			return dao.getMemberById(id);
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

