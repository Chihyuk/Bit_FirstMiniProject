package LoginMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
	 public static Connection connectConnection(){
		 String url = "jdbc:oracle:thin:@192.168.0.57:1521:xe";
	     Connection con = null;
	     //Statement st = null;
	     //ResultSet rs = null;
	     try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				System.out.println("�����ͺ��̽� ������ ...");
				con = DriverManager.getConnection(url, "mini", "1234");
				System.out.println("�����ͺ��̽� ���� ����");
				System.out.println("-----------------------------------------------------------");
			} catch (ClassNotFoundException e) {
				System.out.println("JDBC ����̹��� ã�� ���߽��ϴ�...");
			} catch (SQLException e) {
				System.out.println("�����ͺ��̽� ���� ����");
			}
			return con;
		}
	 public static void main(String[] args) {
		 connectConnection();
		}
}

	 
