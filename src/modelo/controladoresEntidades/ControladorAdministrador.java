package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conector.ConectorBD;
import modelo.Principal;

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

			String SQL1 = "INSERT INTO Administrador (correo, clave) values (?,?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "LissetteQ@gmail.com");
			pst1.setString(2, "123456");

			String SQL2 = "INSERT INTO Administrador (correo, clave) values (?,?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "SantiagoV@gmail.com");
			pst2.setString(2, "123456");

			String SQL3 = "INSERT INTO Administrador (correo, clave) values (?,?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "DiegoR@gmail.com");
			pst3.setString(2, "123456");

			

			pst1.execute();
			pst2.execute();
			pst3.execute();


		} catch (SQLException e) {

		}

	}
	
}
