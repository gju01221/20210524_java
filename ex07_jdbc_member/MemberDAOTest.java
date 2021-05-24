package ex07_jdbc_member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class MemberDAOTest {
	MemberDAO mdao = new MemberDAO(); //객체 생성
	@Test
	void testSelectList() {
		List<MemberDTO> mlist=mdao.selectList();
		System.out.println(mlist);
	}

	@Test
	void testSelectOne() {
		String userid = "java";
		MemberDTO mdto =mdao.selectOne(userid);
		System.out.println(mdto);
	}

	@Test
	void testInsert() {
		String userid = "java";
		String passwd = "1111";
		int birthyear= 2006;
		MemberDTO mdto = new MemberDTO(userid, passwd, birthyear);
		int cnt =mdao.insert(mdto);
		
		assertNotEquals(1,cnt );
	}

	@Test
	void testUpdate() {
		String userid = "java";
		String passwd = "1111";
		int birthyear= 2006;
		MemberDTO mdto = new MemberDTO(userid, passwd, birthyear);
		int cnt = mdao.update(mdto);
		assertNotEquals(1,cnt );
	}

	@Test
	void testDelete() {
		String userid = "java";
		int cnt=mdao.delete(userid);
		assertNotEquals(1,cnt );
	}

}
