package ex07_jdbc.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ex07_jdbc.DBConn;

public class SalesDAO {
	
	//일일 상품 판매 리스트
	List<Map<String, Object>> dayItemSalesList(){
		List<Map<String, Object>> list= new ArrayList<>();
		//connection 객체 생성
		Connection conn=DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT S.SALEDATE, S.SEQ, S.ITEMCODE, I.ITEMNAME, S.QTY, S.AMOUNT ");
		sql.append("FROM SALES S INNER JOIN ITEM I ON (S.ITEMCODE = I.ITEMCODE) ");
		sql.append("ORDER BY S.SALEDATE,S.SEQ");
		
		//pstmt
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String saledate= rs.getString("saledate");
				int seq= rs.getInt("seq");
				String itemcode=rs.getString("itemcode");
				String itemname=rs.getString("itemname");
				int qty=rs.getInt("qty");
				int amount=rs.getInt("amount");
				//System.out.println(saledate+ itemcode+itemname);
				//매출한건 데이터 저장
				Map<String, Object> map =new HashMap<>();
				map.put("saledate", saledate);
				map.put("seq", seq);
				map.put("itemcode", itemcode);
				map.put("itemname", itemname);
				map.put("qty", qty);
				map.put("amount", amount);
				list.add(map);
			}//while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;	
		
	}//dayItemSalesList-일일 상품 판매 리스트
	
	
	//일일 상품별 집계 판매
	List<Map<String, Object>> dayItemSell(){
		List<Map<String, Object>> list= new ArrayList<>();
		Connection conn=DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT S.SALEDATE, S.ITEMCODE, I.ITEMNAME, SUM(S.QTY) QTY, SUM(S.AMOUNT) AMOUNT ");
		sql.append("FROM SALES S INNER JOIN ITEM I ON(S.ITEMCODE = I.ITEMCODE) ");
		sql.append("GROUP BY S.SALEDATE, S.ITEMCODE, I.ITEMNAME ");
		sql.append("HAVING SUM(S.QTY) > 3 ");
		sql.append("ORDER BY S.SALEDATE, AMOUNT DESC");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String saledate= rs.getString("saledate");
				String itemcode=rs.getString("itemcode");
				String itemname=rs.getString("itemname");
				int qty=rs.getInt("qty");
				int amount=rs.getInt("amount");
				Map<String, Object> map =new HashMap<>();
				map.put("saledate", saledate);
				map.put("itemcode", itemcode);
				map.put("itemname", itemname);
				map.put("qty", qty);
				map.put("amount", amount);
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	//일일 상품별 집계 판매
	List <Map<String, Object>> dayItemSalesTotal(String saledate){
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn=DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer sb=new StringBuffer();
 		sb.append("SELECT I.ITEMCODE, I.ITEMNAME, I.PRICE, NVL(S.AMOUNT,0) AMOUNT, NVL(I.BIGO,' ') BIGO, I.REGDATE\r\n" + 
				"FROM ITEM I LEFT JOIN (SELECT ITEMCODE, SUM(AMOUNT) AMOUNT \r\n" + 
				"    FROM SALES WHERE SALEDATE=? GROUP BY ITEMCODE) S\r\n" + 
				"    ON I.ITEMCODE=S.ITEMCODE\r\n" + 
				"ORDER BY I.ITEMCODE");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1,saledate);
			rs=pstmt.executeQuery();
				while(rs.next()) {
				Map<String, Object> map=new HashMap<>();
				map.put("itemcode", rs.getString("itemcode"));
				map.put("itemname", rs.getString("itemname"));
				map.put("price", rs.getInt("price"));
				map.put("amount", rs.getString("amount"));
				map.put("bigo", rs.getString("bigo"));
				map.put("regdate", rs.getString("regdate"));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	//VIEW를 이용해서 상품별 판매 조회
	List <Map<String,Object>> itemSales(String saledate){
		List<Map<String,Object>> list=new ArrayList<>(); 
		Connection conn=DBConn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT*FROM ITEM_SALES_VIEW\r\n" + 
				"WHERE SALEDATE=?;");
		
		//S.*,I.ITEMNAME,IA.QTY QTYAVG
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1,saledate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map=new HashMap<>();
				map.put("saledate", rs.getString("saledate"));
				map.put("seq", rs.getInt("seq"));
				map.put("itemcode", rs.getString("itemcode"));
				map.put("qty", rs.getInt("qty"));
				map.put("amount", rs.getInt("amount"));
				map.put("regdate", rs.getString("regdate"));
				map.put("itemname", rs.getString("itemname"));
				map.put("qtyavg", rs.getInt("qtyavg"));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//
	
	
	
	
	
}
