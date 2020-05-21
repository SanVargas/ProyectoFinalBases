package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conector.ConectorBD;

public class ControladorCargo {
	ConectorBD cc;
	Connection con;
	Principal principal;
	
	public ControladorCargo(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc=cc;
		this.con=con;
		this.principal = principal;
	}
	
	public void insertarCargo() {

		try {
	
			String SQL1 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "0001");
			pst1.setString(2, "Cajero");
			pst1.setString(3, "Atiende caja");

			String SQL2 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "0002");
			pst2.setString(2, "Farmaceuta");
			pst2.setString(3, "Atiende drogueria");


			String SQL3 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "0003");
			pst3.setString(2, "Enfermero");
			pst3.setString(3, "Atiende pacientes");


			String SQL4 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst4 = con.prepareStatement(SQL4);
			pst4.setString(1, "0004");
			pst4.setString(2, "Oficinista");
			pst4.setString(3, "Trabaja en oficina");


			String SQL5 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst5 = con.prepareStatement(SQL5);
			pst5.setString(1, "0005");
			pst5.setString(2, "Paramedico");
			pst5.setString(3, "Trabaja ambulancia");
			
			String SQL6 = "insert into Cargo (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst6 = con.prepareStatement(SQL6);
			pst6.setString(1, "0006");
			pst6.setString(2, "Otro");
			pst6.setString(3, "No especificado");

			pst1.execute();
			pst2.execute();
			pst3.execute();
			pst4.execute();
			pst5.execute();
			pst6.execute();

		} catch (SQLException e) {
			
		}

	}
	
}
