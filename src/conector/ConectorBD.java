package conector;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class ConectorBD {

	public static final String URL = "jdbc:mysql://localhost:3306/Proyecto";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	public static Connection establecerConexion() {
		Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexion exitosa con la base de datos.");
		} catch (Exception e) {
			System.out.println("Conexion no exitosa con la base de datos, verifique. error: " + e.getMessage());
		}

		return c;

	}

}
