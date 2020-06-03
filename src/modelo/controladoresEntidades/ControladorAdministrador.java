package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conector.ConectorBD;
import javafx.scene.control.Alert.AlertType;
import modelo.Principal;
import modelo.alertas.Alerta;
import modelo.entidad.Administrador;

public class ControladorAdministrador {

	ConectorBD cc;
	Connection con;
	Principal principal;
	
	public ControladorAdministrador(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public void insertarAdministradores() {

		try {

			String SQL1 = "INSERT INTO Administrador (usuario, clave) values (?,?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "lissette");
			pst1.setString(2, "123456");

			String SQL2 = "INSERT INTO Administrador (usuario, clave) values (?,?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "santiago");
			pst2.setString(2, "123456");

			String SQL3 = "INSERT INTO Administrador (usuario, clave) values (?,?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "diego");
			pst3.setString(2, "123456");

			

			pst1.execute();
			pst2.execute();
			pst3.execute();

		} catch (SQLException e) {

		}
	}
	
	public Administrador buscarAdministrador(String correo, String clave) {
		ResultSet rs;
		String SQL="";
		PreparedStatement ps;
		Administrador a = null;
		
		try {
			SQL = "SELECT * FROM Administrador WHERE usuario = ? AND clave = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, correo);
			ps.setString(2, clave);
			
			rs = ps.executeQuery();

			if (rs.next()) {
				a = new Administrador(rs.getString("usuario"), rs.getString("clave"));
			}
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Administrador no encontrado.", AlertType.ERROR);
			e.printStackTrace();
		}
		return a;
	}
	
}
