package co.mydiary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiaryOracleDAO implements DAO { // 테이블과 연동하여 다이어리 구현
	
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public int insert(DiaryVO vo) {
		int r = 0;
		try {
			conn = JdbcUtil.connect();
			String sql = "INSERT INTO DIARY VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWdate());
			pstmt.setString(2, vo.getContents());
			r = pstmt.executeUpdate();
			System.out.println(r + "건이 등록됨");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return r;
	}

	@Override
	public void update(DiaryVO vo) {
		try {
			conn = JdbcUtil.connect();
			String sql = "update diary set contents = ? where wdate = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContents());
			pstmt.setString(2, vo.getWdate());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
	}

	@Override
	public int delete(String date) {
		
		try {
			conn = JdbcUtil.connect();
			String sql = "delete from diary where wdate = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return 0;
	}

	@Override
	public DiaryVO selectDate(String date) {
		DiaryVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "select wdate, contents from diary where wdate = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new DiaryVO();
				vo.setWdate(rs.getString("wdate"));
				vo.setContents(rs.getString("contents"));
				System.out.println("날짜검색 Success");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return vo;
	}

	@Override
	public List<DiaryVO> selectContent(String content) {
		ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();
		DiaryVO vo;
		try {
			conn = JdbcUtil.connect();
			String sql = "select * from diary where contents like '%'|| ? ||'%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new DiaryVO();
				vo.setWdate(rs.getString("wdate"));
				vo.setContents(rs.getString("contents"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("검색 실패");
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return list;
	}

	@Override
	public List<DiaryVO> selectAll() {
		List<DiaryVO> list = new ArrayList<DiaryVO>();
		DiaryVO vo;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT WDATE, CONTENTS FROM DIARY";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new DiaryVO();
				vo.setWdate(rs.getString("wdate"));
				vo.setContents(rs.getString("contents"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return list;
	}

}
