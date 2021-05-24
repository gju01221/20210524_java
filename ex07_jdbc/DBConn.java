package ex07_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	

	//컨넥션을 리턴하는 메소드
	public static Connection getConn() {
		Connection conn=null; //필드:자동 초기화
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {//성공하면 실행
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//컨넥션객체 생성
			conn=DriverManager.getConnection(url, user, password);
			
		}catch (ClassNotFoundException e) {//실패 시 실행
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("컨넥션객체 생성 실패");
			e.printStackTrace();
		}
			
		return conn;
		}//getConn
	}
	
	
	
	
	
	

