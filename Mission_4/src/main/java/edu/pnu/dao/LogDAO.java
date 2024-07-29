package edu.pnu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import edu.pnu.domain.LogVO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class LogDAO {

	private Connection con;
//	public Statement stmt;
//	public PreparedStatement psmt;
//	public ResultSet rs;
	
	public LogDAO() {
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
	
	public LogVO insertLog(LogVO logVO) {
		String query = " INSERT INTO DBLOG(METHOD, SQLSTRING, SUCCESS)" + " VALUES (?, ?, ?)";
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, logVO.getMethod());
			psmt.setString(2, logVO.getSQLString());
			psmt.setBoolean(3, logVO.isSuccess());
			psmt.executeUpdate();
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log 추가 중 예외 발생2");
		}
		return null;
	}
}
