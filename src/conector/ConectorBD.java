package conector;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConectorBD {

	public static final String URL = "jdbc:mysql://localhost:3306/Proyecto";
	public static final String USER = "root";
	public static final String PASSWORD = "1234";
	
	public static void main(String[] args) {
				
			Connection c = null; 
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				c = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("se conecto");
				
			}catch(Exception e){
				
				System.out.println("no se conecto");				
			}
	}
	
	
}
