package LoginMain;

public class Member {
	private String userid;
	private String passwd;
	//public static int num;
	
	public String getId() {
		return userid;
	}
	
	public void SetId(String userid) {
		this.userid = userid;
	}
	
	public String getPw() {
		return passwd;
	}
	
	public void SetPw(String passwd) {
		this.passwd = passwd;
	}
}
