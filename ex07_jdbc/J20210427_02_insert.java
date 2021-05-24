package ex07_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class J20210427_02_insert {
	//오라클에 데이터추가:insert
	public static void main(String[] args) {
	Connection conn=DBConn.getConn();
	//Statement stmt=null;
	PreparedStatement pstmt = null;
	
	Scanner sc=new Scanner(System.in);
	System.out.print("상품 코드");
	String itemcode=sc.next();
	sc.nextLine();//엔터값 처리
	System.out.print("상품 명");
	String itemname=sc.nextLine();
	System.out.print("상품 가격");
	int price=sc.nextInt();
	
//	String sql="INSERT INTO ITEM (ITEMCODE, ITEMNAME, PRICE) VALUES("+"'"+itemcode+"',"+"'"+itemname+"',"+price+")";
	String sql="INSERT INTO ITEM (ITEMCODE, ITEMNAME, PRICE) VALUES(?,?,?)";
	System.out.println(sql);
	try {
		//conn.setAutoConmmit(false);
		 pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,itemcode);
		pstmt.setString(2,itemname);
		pstmt.setInt(3,price);
		//stmt=conn.createStatement();
		//ResultSet cnt=stmt.executeQuery(sql);
		int cnt=pstmt.executeUpdate();//적용된 건수를 반환
		System.out.println(cnt+"건 추가");
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("에러");
		e.printStackTrace();
	}
	System.out.println("프로그램 종료");
	}

}
