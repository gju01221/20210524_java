package ex07_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class j20210427_04_delete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt=null;
		
		String itemcode = "8802";
		String sql = " DELETE FROM ITEM\r\n" + 
				"WHERE ITEMCODE=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, itemcode);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt+"건 삭제");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
