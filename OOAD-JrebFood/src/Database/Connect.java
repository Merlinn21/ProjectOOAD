package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Connect {

	private final String USERNAME = "root"; 
	private final String PASSWORD = ""; 
	private final String DATABASE = "jrebfood"; 
	private final String HOST = "localhost:3306"; 
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	public Connection con;
	public Statement stm;
	
    public void ConnectDatabase() {
    	try {  
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);  
            stm = con.createStatement(); 
        } catch(Exception e) {

        }  
    }
}
