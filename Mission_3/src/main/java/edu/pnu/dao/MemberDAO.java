package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDAO {

	@Autowired
	private final DataSource dataSource;
	
//	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
//	public MemberDAO() {
//		try {
//			Class.forName("org.h2.Driver");
//			String url = "jdbc:h2:tcp://localhost/~/mydatabase";
//			String id = "sa";
//			String pwd = "1234";
//			con = DriverManager.getConnection(url, id, pwd);
//			
//			System.out.println("DB 연결 성공(기본 생성자)");
//
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		
//	}
//	
	
	public List<MemberVO> getAllMembers(){
		List<MemberVO> list = new ArrayList<>();
		String query = "SELECT * FROM MEMBER";
		try {
			stmt = dataSource.getConnection().createStatement();
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


	public MemberVO getMemberById(Integer id) {
		MemberVO memberVO = new MemberVO();
		String query = "SELECT * FROM MEMBER WHERE ID = ? ";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				memberVO.setId(rs.getInt(id));
				memberVO.setName(rs.getString("name"));
				memberVO.setPass(rs.getString("pass"));
				memberVO.setRegidate(rs.getDate("regidate"));
			}
		} catch (Exception e){
			System.out.println("아이디 조회 중 예외 발생");
		}
		return memberVO;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		String query = "INSERT INTO MEMBER(pass, name) VALUES (?, ?)";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
			psmt.close();
			
			query = "SELECT * FROM MEMBER WHERE PASS = ? AND NAME = ?";
			
			psmt = dataSource.getConnection().prepareStatement(query);
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
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.executeUpdate();
			psmt.close();
			
			query = "SELECT * FROM MEMBER WHERE PASS = ? AND NAME = ?";
			
			psmt = dataSource.getConnection().prepareStatement(query);
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
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
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
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (Exception e){
			System.out.println("삭제 중 예외 발생");
			e.printStackTrace();
		}
		return 1;
	}
}
