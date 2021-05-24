package ex07_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn_emp {
	
	static Connection getConn(){
		Connection conn=null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//컨넥션객체 생성
			conn=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				System.out.println("컨넥션 객체 생성 실패");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
				e.printStackTrace();
			}
		
		return conn;
	}

}
