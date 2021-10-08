package LoginMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO implements DAO{ 
	public Connection getConnection() throws ClassNotFoundException, Exception { 
		String url = "jdbc:oracle:thin:@192.168.0.57:1521:xe";
		Connection con = null; // JDBC와의 연결 객체 초기화 
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.err.println("데이터베이스 연결중 ...");
			con = DriverManager.getConnection(url, "mini", "1234"); // JDBC URL -> DB 연결 
		} catch(ClassNotFoundException e){ // JDBC driver 부재시 예외처리 
			System.out.println("예외처리 1:"+e.getMessage()); // 예외 메시지 (console) 인쇄 
			e.printStackTrace(); 
			} catch(Exception e){ // 모든 예외상황 처리 
				System.out.println("예외처리 2:"+e.getMessage()); 
				e.printStackTrace(); 
				} finally { 
					// System.out.println("JDBC 드라이버와 연결되었습니다"); 
				} 
					return con; 
			}

	
	
	@Override
	public ArrayList<Member> getMembers() throws SQLException, Exception { 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> members = new ArrayList<Member>();
		Member member;
		
		con = DB.connectConnection();
		String sql = "SELECT * FROM signup";

		try {
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			
			while(rs.next()){ 
			member = new Member(); // temp 
			member.SetId(rs.getString("userid")); // id 필드값을 가져옴 
			member.SetPw(rs.getString("passwd")); // pw 필드값을 가져옴 
			members.add(member); // 한명의 정보 -> 전체 인원 현황 객체 
			}
			rs.close(); 
			pstmt.close(); 
			con.close();
		  }catch(SQLException e) { 
			  System.out.println("예외처리 3:"+e.getMessage());
		  }
		return members;
		}
	}	

  

