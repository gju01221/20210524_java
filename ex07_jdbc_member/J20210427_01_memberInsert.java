package ex07_jdbc_member;


import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class J20210427_01_memberInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Connection conn = DBConn.getConn();
		Scanner sc= new Scanner(System.in);
		MemberDAO mdao=new MemberDAO();
		int login=0;//로그인 체크
		while(true) {
			System.out.println("=======================");
			if(login==0) {
			System.out.println("|  "+(0-login)+".로그인           |");
			System.out.println("|  "+(1-login)+".회원 가입        |");
			}
			if(login==1) {
			System.out.println("|  "+(2-login)+".회원 정보 수정   |");
			System.out.println("|  "+(3-login)+".회원 정보 삭제   |");
			System.out.println("|  "+(4-login)+".회원 정보 조회   |");
			System.out.println("|  "+(5-login)+".전체 회원 조회   |");
			System.out.println("|  9.로그아웃         |");
			}
			System.out.println("=======================");
			System.out.printf("Ｎ 입력:");
			int no=sc.nextInt();
			if(no==0-login) {//로그인
				//userid, passwd 입력받기
				System.out.printf("아이디 입력:");
				String userid=sc.next();
				//id가 존재하지 않습니다(id체크)
				MemberDTO mdto=mdao.selectOne(userid);
				//if(mdto=null)
				if (!mdto.getUserid().contentEquals(userid)) {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※아이디가 틀립니다.※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}
				System.out.printf("비번 입력:");
				String passwd=sc.next();
				//pw 불일치(pw체크)
				if(!mdto.getPasswd().equals(passwd)){
					System.out.println("※※※※※※※※※※※※※※※※※※※");
					System.out.println("비밀번호가 틀립니다 ");
					System.out.println("※※※※※※※※※※※※※※※※※※※");
				}
				else{
					System.out.println("=======================");
					System.out.println("로그인 성공");
					login=1;
					continue;
					
				}
				
			}
			if(no==5-login) {//전체 회원 조회
				List<MemberDTO> mlist=mdao.selectList();
				System.out.println(mlist);
				if (mlist==null) {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※ 없는 정보 입니다 ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}
												
			}else if (no==4-login){//한건조회
				List<MemberDTO> mlist=mdao.selectList();
				System.out.println("=======================");
				for (MemberDTO m: mlist) {
					System.out.println("등록된 아이디:"+m.getUserid());
				}
				System.out.println("=======================");
				System.out.printf("조회할 아이디 입력:");
				String userid = sc.next();
				MemberDTO mdto =mdao.selectOne(userid);
				System.out.println(mdto);//mdto가 null이 아닐때 성공
				if (mdto==null) {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※ 없는 정보 입니다 ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}
				
			}else if (no==1-login) {//추가, 회원가입
				String userid;
				while(true) {
					System.out.println("=======================");
					System.out.printf("Ｅ 아이디 입력:");
					userid =sc.next();
					MemberDTO mdto=mdao.selectOne(userid);
					if(mdto!=null) {
						System.out.println("※※※※※※※※※※※※※※※※※※※※");
						System.out.println("※  중복된 아이디   ※");
						System.out.println("※※※※※※※※※※※※※※※※※※※※");
					} else break;
				}
				System.out.println("=======================");
				System.out.printf("Ｅ 비번 입력:");
				String passwd =sc.next();
				System.out.println("=======================");
				System.out.printf("Ｎ 태어난 해 입력(4자리):");
				int birthyear= sc.nextInt();
				MemberDTO mdto = new MemberDTO(userid, passwd, birthyear);
				int cnt = mdao.insert(mdto);
				if (cnt>0) {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※  회원 가입 완료  ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}else {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※  회원 가입 실패  ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}
				
			}else if(no==2-login) {//수정
				System.out.println("=======================");
				System.out.printf("Ｅ 변경할 회원의 아이디:");
				String userid= sc.next();
				System.out.println("=======================");
				System.out.printf("Ｅ 변경할 비번:");
				String passwd = sc.next();
				System.out.println("=======================");
				System.out.printf("Ｎ 태어난 해 입력(4자리):");
				int birthyear= sc.nextInt();
				MemberDTO mdto = new MemberDTO(userid, passwd, birthyear);
				int cnt = mdao.update(mdto);
			
			}else if(no==3-login) {//삭제
				List<MemberDTO> mlist=mdao.selectList();
				System.out.println("=======================");
				for (MemberDTO m: mlist) {
					System.out.println("등록된 아이디:"+m.getUserid());
				}
				String userid;
				System.out.println("=======================");
				System.out.printf("Ｅ 삭제할 회원 아이디:");
				userid =sc.next();
				MemberDTO mdt=mdao.selectOne(userid);
				try {
					int cnt= mdao.delete(userid);
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※ 삭제 되었습니다. ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
				}catch(Exception e) {
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					System.out.println("※ 없는 회원 입니다 ※");
					System.out.println("※※※※※※※※※※※※※※※※※※※※");
					
				}
			}else if(no==9) {
				System.out.println("※※※※※※※※※※※※※※※※※※※※");
				System.out.println("※   로 그  아 웃   ※");
				System.out.println("※※※※※※※※※※※※※※※※※※※※");
				login=0;
			}
			else
				System.out.println("잘못된 입력");
		}//while
						
		
		
	}

}
