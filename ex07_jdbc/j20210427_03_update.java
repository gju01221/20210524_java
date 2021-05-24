package ex07_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class j20210427_03_update {

	public static void main(String[] args) {
		// 수정 : update
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt =null;
		
		String itemcode = "8801";
		String itemname = "칠성사이다";

		
		String sql = "UPDATE ITEM\r\n" + 
				"SET ITEMNAME=?\r\n" + 
				"WHERE ITEMCODE=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,itemname);
			pstmt.setString(2,itemcode);
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt +"건 수정");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
