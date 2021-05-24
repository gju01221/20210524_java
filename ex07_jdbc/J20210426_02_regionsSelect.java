package ex07_jdbc;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class J20210426_02_regionsSelect {

	public static void main(String[] args) {
		//컨넥션 객체 얻기
		Connection con = DBConn.getConn();//DBConn을 통해 오라클과 연결
		List<RegionsDTO> list = new ArrayList<>();//RegionsDTO을 통해 id, name 저장
		try {
			String sql="SELECT * FROM REGIONS ORDER BY region_name";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);//행과 열로된 데이터를 받아올수 있음
			while(rs.next()) {//오라클의 id, name 정보 가져옴
				RegionsDTO rdto = new RegionsDTO();//RegionsDTO에 넣어줌
				int region_id = rs.getInt("region_id");
				String region_name= rs.getString("region_name");
				rdto.setRegion_id(region_id);
				rdto.setRegion_name(region_name);
				list.add(rdto);
			}//while
			
		} catch (SQLException e) {
			System.out.println("sql에러");
			e.printStackTrace();
		}
		
		//System.out.println(list);//출력
		for(RegionsDTO dto:list) {
			//System.out.println(dto);
			System.out.println(dto.getRegion_id()+" "+dto.getRegion_name());
		}

	}

}
