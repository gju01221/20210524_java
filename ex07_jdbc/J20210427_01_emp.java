package ex07_jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class J20210427_01_emp {

	public static void main(String[] args) {
		//사원(employees) 테이블에서 연봉이 1만 이상인
		//사원의 employee_id, first_name, salary 조회
		//연봉의 내림차순으로 정렬
		//
		Connection con= DBConn_emp.getConn();
		List<EmployeesDTO> list= new ArrayList<>(); 
		try {
			String sql="SELECT * FROM EMPLOYEES\r\n" + 
					"WHERE SALARY>=10000\r\n" + 
					"ORDER BY SALARY DESC";
			//인터페이스를 구현한 객체의 메소드를 호출
			Statement stmt=con.createStatement();
			//행과 열로된 데이터를 받아올수 있음
			//sql문을 담은 string객체
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {//오라클의 정보를 가져와 EmployeesDTO넣어줌
				EmployeesDTO edto = new EmployeesDTO();//RegionsDTO에 넣어줌
				String employee_id=rs.getString("employee_id");
				String first_name=rs.getString("first_name");
				int salary=rs.getInt("salary");
				edto.setEmployee_id(employee_id);
				edto.setFirst_name(first_name);
				edto.setSalary(salary);
				list.add(edto);
			}//while
		} catch (SQLException e) {
			System.out.println("sql에러");
			e.printStackTrace();
		}
		//System.out.println(list);
		for(EmployeesDTO edto:list) {
			//System.out.println(edto);
			System.out.println(edto.getEmployee_id()+" "+edto.getFirst_name()+" "+edto.getSalary());
		}
		
		
	}

}
