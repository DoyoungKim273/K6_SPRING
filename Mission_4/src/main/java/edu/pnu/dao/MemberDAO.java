package edu.pnu.dao;

import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.LogVO;
import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

public class MemberDAO {
		
	private Connection con;
//	public Statement stmt;
//	public PreparedStatement psmt;
//	public ResultSet rs;
	
	public MemberDAO() {
		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:tcp://localhost/~/mydatabase";
			String id = "sa";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");

		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public List<MemberVO> getAllMembers(){
		List<MemberVO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MemberVO vo = MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate"))
						.build();

				list.add(vo);
			}
			System.out.println("list 받기 성공");
		} catch (SQLException e){
			System.out.println("Member 호출 중 예외 발생");
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e ){
				e.printStackTrace();
			}
		}
		return list;
	}


	public Map< String, Object > getMemberById(Integer id) {
		Map<String, Object> result = new HashMap<>();
		MemberVO memberVO = new MemberVO();
		LogVO logVO = new LogVO();
		String query = "SELECT * FROM MEMBER WHERE ID = ? ";
		logVO.setMethod("GET");
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			logVO.setSQLString(psmt.toString());
			
			if(rs.next()) {
				memberVO.setId(rs.getInt("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPass(rs.getString("pass"));
				memberVO.setRegidate(rs.getDate("regidate"));
				
				logVO.setSuccess(true);
			}
		} catch (Exception e){
			logVO.setSuccess(false);
			System.out.println("아이디 조회 중 예외 발생");
		}
		result.put("logVO", logVO);
		result.put("memberVO", memberVO);		
		return result;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		String query = "INSERT INTO MEMBER(pass, name) VALUES (?, ?)";
		PreparedStatement psmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
			psmt.close();
			
			query = "SELECT * FROM MEMBER WHERE PASS = ? AND NAME = ?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate"))
						.build();
			}
			psmt.close();
			return memberVO;
		} catch (Exception e){
			System.out.println("멤버 추가 중 예외 발생");
		}
		return null;
	}
	
	public MemberVO addMemberJSON(MemberVO memberVO) {
		String query = "INSERT INTO MEMBER(pass, name) VALUE (?, ?)";
		PreparedStatement psmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
			psmt.close();
			
			query = "SELECT * FROM MEMBER WHERE PASS = ? AND NAME = ?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return MemberVO.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.pass(rs.getString("pass"))
						.regidate(rs.getDate("regidate"))
						.build();
			}
			psmt.close();
		
			return memberVO;
		} catch (Exception e){
			System.out.println("멤버 추가 중 예외 발생2");
		}
		return null;
	}

	public MemberVO updateMembers(MemberVO memberVO) {	
		String query = "UPDATE MEMBER SET NAME=?, PASS=? WHERE ID=?";
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getPass());
			psmt.setInt(3, memberVO.getId());
//			psmt.setDate(4, memberVO.getRegidate());
			psmt.executeUpdate();
		} catch (Exception e){
			System.out.println("업데이트 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}
	
	public int removeMember(Integer id) {
		String query = "DELETE FROM MEMBER WHERE ID = ?";
		PreparedStatement psmt = null;      
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (Exception e){
			System.out.println("삭제 중 예외 발생");
			e.printStackTrace();
		}
		return 1;
	}
}
