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
		Connection con = null; // JDBC���� ���� ��ü �ʱ�ȭ 
		try{ 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.err.println("�����ͺ��̽� ������ ...");
			con = DriverManager.getConnection(url, "mini", "1234"); // JDBC URL -> DB ���� 
		} catch(ClassNotFoundException e){ // JDBC driver ����� ����ó�� 
			System.out.println("����ó�� 1:"+e.getMessage()); // ���� �޽��� (console) �μ� 
			e.printStackTrace(); 
			} catch(Exception e){ // ��� ���ܻ�Ȳ ó�� 
				System.out.println("����ó�� 2:"+e.getMessage()); 
				e.printStackTrace(); 
				} finally { 
					// System.out.println("JDBC ����̹��� ����Ǿ����ϴ�"); 
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
			member.SetId(rs.getString("userid")); // id �ʵ尪�� ������ 
			member.SetPw(rs.getString("passwd")); // pw �ʵ尪�� ������ 
			members.add(member); // �Ѹ��� ���� -> ��ü �ο� ��Ȳ ��ü 
			}
			rs.close(); 
			pstmt.close(); 
			con.close();
		  }catch(SQLException e) { 
			  System.out.println("����ó�� 3:"+e.getMessage());
		  }
		return members;
		}
	}	

  

