package LoginMain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class Connect {
	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@192.168.0.57:1521:xe";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("프로그램 연결중 ...");
			con = DriverManager.getConnection(url, "mini", "1234");
			System.out.println("프로그램 연결 성공");
			System.out.println("-----------------------------------------------------------");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다...");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
		}
		return con;
	}
}
