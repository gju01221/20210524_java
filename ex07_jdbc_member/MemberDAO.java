package ex07_jdbc_member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ex07_jdbc.DBConn;

public class MemberDAO {
	
	//전체조회
	List<MemberDTO> selectList(){
		List<MemberDTO> mlist = new ArrayList<>();
		Connection conn =DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
//		StringBuffer sql = new StringBuffer();
		String sql ="SELECT *FROM MEMBER\r\n" + 
				"ORDER BY REGDATE DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String userid = rs.getString("userid");
				String passwd = rs.getString("passwd");
				int birthyear = rs.getInt("birthyear");
				Date regdate = rs.getDate("regdate");
				MemberDTO mdto = new MemberDTO(userid, passwd, birthyear, regdate);
				mlist.add(mdto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist;
	}//전체조회
	
	//한건조회
	MemberDTO selectOne (String userid) {
		MemberDTO mdto = null;
		Connection conn =DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT *FROM MEMBER\r\n" + 
				"WHERE USERID=?";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String passwd = rs.getString("passwd");
				int birthyear = rs.getInt("birthyear");
				Date regdate = rs.getDate("regdate");
				mdto=new MemberDTO(userid,passwd,birthyear,regdate);
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn !=null) conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return mdto;
	}//한건조회
	
	//추가
	public int insert(MemberDTO mdto){
		int cnt=0;
		//DB접속
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt=null;
		String sql = "INSERT INTO MEMBER(USERID, PASSWD, BIRTHYEAR) VALUES (?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,mdto.getUserid());
			pstmt.setString(2,mdto.getPasswd());
			pstmt.setInt(3,mdto.getBirthyear());
			cnt= pstmt.executeUpdate();
			System.out.println(cnt+"건 추가");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return cnt;
	}//추가
	
	//수정
	int update(MemberDTO mdto) {
		int cnt = 0;
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MEMBER ");
		sql.append("SET PASSWD = ?, ");
		sql.append("BIRTHYEAR = ? ");
		sql.append("WHERE USERID = ?");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, mdto.getPasswd());
			pstmt.setInt(2, mdto.getBirthyear());
			pstmt.setString(3, mdto.getUserid());
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}				
		return cnt;
	}
	
	//삭제
	int delete(String userid) {
		int cnt=0;
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt=null;
		
		String sql="DELETE FROM MEMBER\r\n" + 
				"WHERE USERID=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			cnt=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}//삭제
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
