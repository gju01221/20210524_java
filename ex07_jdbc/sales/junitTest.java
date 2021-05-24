package ex07_jdbc.sales;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class junitTest {
	SalesDAO sdao=new SalesDAO();
	@Test
	void testdayItemSalesList() {
		List<Map<String, Object>> list= sdao.dayItemSalesList();
		for(Map<String, Object>map:list) {
		System.out.println(map.get("saledate"));
		System.out.println(map.get("seq"));
		System.out.println(map.get("itemcode"));
		System.out.println(map.get("itemname"));
		System.out.println(map.get("qty"));
		System.out.println(map.get("amount"));
		}
		//sdao.dayItemSalesList();

	}
	@Test
	void testdayItemSell() {
		List<Map<String, Object>> list= sdao.dayItemSell();
		for(Map<String, Object>map:list) {
		System.out.println(map.get("saledate"));
		System.out.println(map.get("itemcode"));
		System.out.println(map.get("itemname"));
		System.out.println(map.get("qty"));
		System.out.println(map.get("amount"));
		}
		//sdao.dayItemSalesList();
	}
	
	@Test
	void testdayItemSalesTotal() {
		SalesDAO sdao =new SalesDAO();
		List<Map<String, Object>> list=sdao.dayItemSalesTotal("2021-04-29");
		//System.out.println(list);
		for(Map<String, Object>map:list) {
			System.out.println(map.get("itemcode")+" "+map.get("itemname")
			+" "+map.get("price")+" "+map.get("amount")+" "+map.get("bigo")
			+" "+map.get("regdate"));
		}
		
	}
	
	
	
	
	
	
}
