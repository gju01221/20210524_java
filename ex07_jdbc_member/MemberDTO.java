package ex07_jdbc_member;

import java.sql.Date;

public class MemberDTO {
	private String userid;
	private String passwd;
	private int birthyear;
	private Date regdate;
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String userid, String passwd, int birthyear, Date regdate) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.birthyear = birthyear;
		this.regdate = regdate;
	}
	public MemberDTO(String userid, String passwd, int birthyear) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.birthyear = birthyear;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", birthyear=" + birthyear + ", regdate="
				+ regdate + "]";
	}


	
	
}
