package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conector.ConectorBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import modelo.Principal;
import modelo.alertas.Alerta;
import modelo.entidad.Especialidad;
import modelo.entidad.Medico;

public class ControladorEspecialidad {
	ConectorBD cc;
	Connection con;
	Principal principal;
	 

	public ControladorEspecialidad(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}
	
	public ResultSet buscarEspecialidad(String nombre)
	{
		ResultSet rs1 = null;
		PreparedStatement ps;
		
		try {
			String SQL = "SELECT * FROM ESPECIALIDAD WHERE nombre = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			rs1 = ps.executeQuery();

		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
			a.printStackTrace();
		}
		
		
		return rs1;
		
	}
	

	public void insertarEspecialidad() {

		try {

			String SQL1 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "00001");
			pst1.setString(2, "Pediatra");
			pst1.setString(3, "Estudia al niño y sus enfermedades.");

			String SQL2 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "00002");
			pst2.setString(2, "Neurologo");
			pst2.setString(3, "Estudia al sistema nervioso y sus enfermedades.");

			String SQL3 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "00003");
			pst3.setString(2, "Cardiologo");
			pst3.setString(3, "Estudia al sistema cardiaco  y sus enfermedades.");
			
			String SQL5 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst5 = con.prepareStatement(SQL5);
			pst5.setString(1, "00005");
			pst5.setString(2, "Endocrinologo");
			pst5.setString(3, "Estudia el sistema endocrino y sus enfermedades.");

			String SQL6 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst6 = con.prepareStatement(SQL6);
			pst6.setString(1, "00006");
			pst6.setString(2, "Dermatologo");
			pst6.setString(3, "Estudia la epidermis y sus enfermedades.");
			
			String SQL7 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst7 = con.prepareStatement(SQL7);
			pst7.setString(1, "00007");
			pst7.setString(2, "Reumatologo");
			pst7.setString(3, "Estudia traumas y sus enfermedades relacionadas.");
			
			String SQL8 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst8 = con.prepareStatement(SQL8);
			pst8.setString(1, "00008");
			pst8.setString(2, "otorrinolaringologo");
			pst8.setString(3, "Estudia las estructuras relacionadas con cuello y cabeza y sus respectivas enfermedades.");
			
			String SQL9 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst9 = con.prepareStatement(SQL9);
			pst9.setString(1, "00009");
			pst9.setString(2, "Cirugia Plastica");
			pst9.setString(3, "Realiza procedimientos quirurgicos de caracter esteticos.");
			
			String SQL10 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst10 = con.prepareStatement(SQL10);
			pst10.setString(1, "000010");
			pst10.setString(2, "Cirujano General");
			pst10.setString(3, "Realiza procedimientos quirurgicos.");
			
			String SQL11 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst11 = con.prepareStatement(SQL11);
			pst11.setString(1, "000011");
			pst11.setString(2, "Cirujano Pediatra");
			pst11.setString(3, "Realiza procedimientos quirurgicos a personas menores de 16 anios.");
			
			String SQL13 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst13 = con.prepareStatement(SQL13);
			pst13.setString(1, "000013");
			pst13.setString(2, "Oncologo");
			pst13.setString(3, "Estudia y trata los diferentes tipos de cancer existentes.");
			
			String SQL15 = "INSERT INTO Especialidad (id, nombre, descripcion) values (?,?,?)";
			PreparedStatement pst15 = con.prepareStatement(SQL15);
			pst15.setString(1, "000014");
			pst15.setString(2, "Gastroenterologo");
			pst15.setString(3, "Estudia el sistema digestivo y sus enfermedades");
			
			pst1.execute();
			pst2.execute();
			pst3.execute();
			pst5.execute();
			pst6.execute();
			pst7.execute();
			pst8.execute();
			pst9.execute();
			pst10.execute();
			pst11.execute();
			pst13.execute();
			pst15.execute();

		} catch (SQLException e) {

		}

	}
	
	public ArrayList<Especialidad> mostrarEspecialidades() {
		String SQL = "SELECT * FROM ESPECIALIDAD";
		ArrayList<Especialidad> lstEspecialidad = new ArrayList<Especialidad>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Especialidad e = new Especialidad(id, nombre, descripcion);

				lstEspecialidad.add(e);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		
		return lstEspecialidad;
	}
	
}
