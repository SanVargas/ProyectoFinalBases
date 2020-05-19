package conector;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class ConectorBD {

	public static final String URL = "jdbc:mysql://localhost:3306/Proyecto";
	public static final String USER = "root";
	public static final String PASSWORD = "1234";

	public static Connection establecerConexion() {
		Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			JOptionPane.showMessageDialog(null, "Conexion exitosa con la base de datos.");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Conexion NO exitosa con la base de datos.");
		}

		return c;

	}

}
