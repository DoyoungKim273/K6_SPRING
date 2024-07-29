package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController

public class MemberService {
	private List<MemberVO> list = new ArrayList<>();
	
	public MemberService() {
		System.out.println("===> MemberService 구동");
		for(int i = 1; i <= 10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build());
		}
	}
	
	public List<MemberVO> getAllMembers(){
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	public MemberVO getMemberByPass(String pass) {
		for(MemberVO m : list) {
			if(m.getPass() == pass) {
				return m;
			}
		}
		return null;
	}
	
	public MemberVO getMemberByName(String name) {
		for(MemberVO m : list) {
			if(m.getName() == name) {
				return m;
			}
		}
		return null;
	}
	
	
	public MemberVO addMember(MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null) {
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null) {
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	public int updateMembers(MemberVO memberVO) {
		for(MemberVO mm : list) {
			if(mm.getId() == memberVO.getId()) {
				if(memberVO.getName() != null) {
					mm.setName(memberVO.getName());
				}
				if(memberVO.getPass() != null) {
					mm.setPass(memberVO.getPass());
				}
				return 1;
			}
		}
		return 0;
	}
//		MemberVO m = getMemberById(memberVO.getId());
//		if(m == null) {
//			return 0;
//			
//		} else {
//			if(m.getPass() == null && m.getName() == null) {
//				return 0;
//				
//			} else if(m.getPass() == null && m.getName() != null) {
//					m.setName(memberVO.getName());
//					return 2;
//				
//			} else if(m.getPass() != null && m.getName() == null) {
//				m.setPass(memberVO.getPass());
//				return 3;
//			} 
//			else {
//				m.setName(memberVO.getName());
//				m.setPass(memberVO.getPass());
//				return 4;
//			} 
//		}
		
		
//		m = getMemberByName(memberVO.getName());
//		m = getMemberByPass(memberVO.getPass());
//		if(m == null) {
//			return 0;
//		}
		
		
		
		
		
		
		
//		m.getName();
//		m.getPass();
//		m = getMemberByPass(memberVO.getPass());
		
//		if(m == null) {
//			return 0;
//		}
//		if(m.getName() == null) {
//			return 0;
//		}
//		m.setPass(memberVO.getPass());
//		m.setName(memberVO.getName());
//		m.getPass();
//		m.getName();
//		m = getMemberByName(memberVO.getName());
		
//		return 1;
		
	
	
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e){
			return 0;
		}
		return 1;
	}
}
