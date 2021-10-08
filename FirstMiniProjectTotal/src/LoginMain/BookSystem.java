package LoginMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Admin.Admin;
import Client.Client;
import LoginMain.Member;
import LoginMain.MemberDAO;

public class BookSystem {
	static Scanner s = new Scanner(System.in);
	public static void totalService() throws SQLException {
		//SignUp sign = new SignUp();
		int menu = 0;
		do{
			System.out.println("============================메인============================");
			System.out.println("사용하실 메뉴의 번호를 입력해주세요");
			System.out.println("1.회원가입 | 2.회원 로그인 | 3.관리자 로그인 | 0. 종료");
			menu = s.nextInt();

			switch(menu){
			case 1:
				signup();
				break;
			case 2:
				int gl = guestlogin();
				if(gl == 1) {
					Client.clientMain();
				}
				break;
			case 3:
				int ml = managerlogin();
				if(ml == 1) {
					Admin.adminMain();
				}
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}while(menu != 0);
	}


	public static void signup() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userid;
		String passwd;
		String username;
		String birth;
		String phone;

		Scanner s = new Scanner(System.in);

		System.out.println("===========================회원가입===========================");
		System.out.printf("ID: ");
		userid = s.next();
		System.out.printf("PassWord: ");
		passwd = s.next();
		System.out.printf("이름: ");
		username = s.next();
		System.out.printf("생일: ");
		birth = s.next();
		System.out.printf("전화번호: ");
		phone = s.next();

		try {
			String sql = "insert into signup values"
					+ "('" +userid+"', '" +passwd+"', '" +username+"', '" +birth+"', '" +phone+ "')";

			con = DB.connectConnection();
			ps = con.prepareStatement(sql);
			int r = ps.executeUpdate();

			System.out.println("추가되었습니다.('" +userid+"', '" +passwd+"', '" +username+"', '" +birth+"', '" +phone+ "')");

		}catch (SQLException sql) {
			System.out.println("실패!");
			sql.printStackTrace();
		}finally {
			try{
				if ( rs != null ){rs.close();}   
				if ( ps != null ){ps.close();}   
				if ( con != null ){con.close(); }
			}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static String userid = null;

	private static int guestlogin() {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>(); 
		MemberDAO dao = new MemberDAO(); 
		ArrayList<Member> members = new ArrayList<Member>();
		try { 
			members = dao.getMembers(); 
			for(int i=0; i<members.size();i++) 
				map.put(members.get(i).getId(),members.get(i).getPw());
			while(true) { 
				System.out.println("id와 password를 입력해주세요."); 
				System.out.print("id : "); 
				userid = s.next(); 
				System.out.print("passwd : "); 
				String passwd = s.next(); 
				if(!map.containsKey(userid)) {
					System.out.println("ID가 존재하지 않음");
					continue;
				}
				else if(!(map.get(userid)).equals(passwd)) { 
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
				else {
					System.out.println("============================사용자===========================");
					System.out.println("안녕하세요 "+ userid +"님");
					return 1;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	public static int managerlogin() {
		// TODO Auto-generated method stub
		do {
			System.out.print("ID: ");
			String id = s.next();
			if(id.equals(Manager.ID));{
				System.out.print("password: ");
				String pw = s.next();

				if(pw.equals(Manager.PASSWORD)) {
					System.out.println("로그인 성공");
					System.out.println("============================관리자===========================");
					return 1;
				} else {
					System.out.println("비밀번호가 틀렷습니다");
					System.out.println("입력한 아이디가 존재하지 않음");
					return 0;
				} 
			}
		} while(true);
	}
}

