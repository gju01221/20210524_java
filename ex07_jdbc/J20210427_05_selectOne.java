package ex07_jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class J20210427_05_selectOne {

	public static void main(String[] args) {
		// 한건만 조회
		Connection conn = DBConn.getConn();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		itemDTO idto=null;
		
		String itemcode="8801";
		String sql="SELECT*FROM ITEM\r\n" + 
				"WHERE ITEMCODE=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemcode);
			rs = pstmt.executeQuery();
			if(rs.next()) {//데이터가 있다면
				String itemname=rs.getString("itemname");
				int price = rs.getInt("price");
				String bigo = rs.getNString("bigo");
				Date regdate = rs.getDate("regdate");
				idto = new itemDTO(itemcode,itemname,price,bigo,regdate);
				
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(idto.getItemcode());
		System.out.println(idto.getItemname());
		System.out.println(idto.getPrice());
		System.out.println(idto.getBigo());
		System.out.println(idto.getRegdate());

		
		

	}

}
