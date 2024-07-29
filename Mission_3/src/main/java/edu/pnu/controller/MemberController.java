package edu.pnu.controller;

import java.util.List;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;


@RestController
public class MemberController {
	@Autowired
	MemberService memberService = new MemberService();
	
	public MemberController() throws SQLException {
//		memberService.setMemberDAO(new MemberDAO());	
		System.out.println("MemberController 생성");
	}
	
	@GetMapping("/members")
	public List<MemberVO> getAllMember(){
		return memberService.getAllMembers();
	}
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return memberService.getMemberById(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		return memberService.addMemberJSON(memberVO);
	}
	
	@PutMapping("/member")
	public MemberVO updateMembers(MemberVO memberVO) {
		return memberService.updateMembers(memberVO);
	}

	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		return memberService.removeMember(id);
	}
	
}
