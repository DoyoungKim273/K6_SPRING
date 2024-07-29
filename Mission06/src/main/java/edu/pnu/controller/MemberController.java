package edu.pnu.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@RestController
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@GetMapping("/members")
	public List<Member> test(){
		return memberRepo.findAll();
	}

	@GetMapping("/member")
	public Member getMember(String username) {
		return memberRepo.findById(username).get();
	}
	
	private Connection con;
	@PostMapping("/member")
	public Member addMember(Member member) {
		String query = "INSERT INTO MEMBER(username, password, role) VALUES (?, ?, ?)";
		PreparedStatement psmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getUsername());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getRole());
			psmt.executeUpdate();
			psmt.close();
			
			query = "SELECT * FROM MEMBER WHERE USERNAME = ? AND PASSWORD = ? AND ROLE = ?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, member.getUsername());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getRole());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return Member.builder()
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.role(rs.getString("role"))
						.build();
			}
			psmt.close();
			return member;
		} catch (Exception e){
			System.out.println("멤버 추가 중 예외 발생");
		}
		return null;
	}
}
