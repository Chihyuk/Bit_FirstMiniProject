package LoginMain;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {
	String url = "jdbc:oracle:thin:@192.168.0.57:1521:xe";
    Connection con = null;
    static final String oracleDriver="oracle.jdbc.OracleDriver";
    public Connection getConnection() throws ClassNotFoundException, Exception;
    public ArrayList<Member> getMembers() throws SQLException, Exception;
}
