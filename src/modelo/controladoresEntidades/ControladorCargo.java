package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conector.ConectorBD;
import javafx.scene.control.Alert.AlertType;
import modelo.Principal;
import modelo.alertas.Alerta;
import modelo.entidad.Cargo;
import modelo.entidad.Eps;

public class ControladorCargo {
	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorCargo(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public void insertarCargo() {

		try {

			String SQL1 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "0001");
			pst1.setString(2, "Cajero");
			pst1.setString(3, "Atiende caja");
			pst1.setDouble(4, 1000000);

			String SQL2 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "0002");
			pst2.setString(2, "Farmaceuta");
			pst2.setString(3, "Atiende drogueria");
			pst2.setDouble(4, 1230000);

			String SQL3 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "0003");
			pst3.setString(2, "Enfermero");
			pst3.setString(3, "Atiende pacientes");
			pst3.setDouble(4, 2300000);

			String SQL4 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst4 = con.prepareStatement(SQL4);
			pst4.setString(1, "0004");
			pst4.setString(2, "Oficinista");
			pst4.setString(3, "Trabaja en oficina");
			pst4.setDouble(4, 1500000);

			String SQL5 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst5 = con.prepareStatement(SQL5);
			pst5.setString(1, "0005");
			pst5.setString(2, "Paramedico");
			pst5.setString(3, "Trabaja ambulancia");
			pst5.setDouble(4, 2700000);

			String SQL6 = "insert into Cargo (id, nombre, descripcion, salario) values (?,?,?, ?)";
			PreparedStatement pst6 = con.prepareStatement(SQL6);
			pst6.setString(1, "0006");
			pst6.setString(2, "Otro");
			pst6.setString(3, "No especificado");
			pst6.setDouble(4, 100000);

			pst1.execute();
			pst2.execute();
			pst3.execute();
			pst4.execute();
			pst5.execute();
			pst6.execute();

		} catch (SQLException e) {

		}

	}

	public ArrayList<Cargo> mostrarDatosCargo() {
		ArrayList<Cargo> lstCargo = new ArrayList<Cargo>();
		String SQL = "SELECT * FROM Cargo";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double salario = rs.getDouble("salario");

				Cargo c = new Cargo(id, nombre, descripcion,salario);

				lstCargo.add(c);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar Cargos.", AlertType.ERROR);
			e.printStackTrace();
		}

		return lstCargo;
	}

}
