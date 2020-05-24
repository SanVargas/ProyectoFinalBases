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

/**
 * Clase encargada de controlar lo relacionado con medico.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class ControladorMedico {
	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorMedico(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public void insertarOtraEspecialidad(String licencia, String especialidad) {

		try {
			ResultSet rs1 = principal.getControladorEspecialidad().buscarEspecialidad(especialidad);

			PreparedStatement ps1;
			String SQL1 = "INSERT INTO Medico_has_Especialidad (Medico_licencia, Especialidad_id) VALUES (?,?)";
			ps1 = con.prepareStatement(SQL1);
			ps1.setString(1, licencia);

			while (rs1.next()) {
				ps1.setString(2, rs1.getString("id"));
			}

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente la especialidad.",
					AlertType.CONFIRMATION);
			ps1.execute();

		} catch (SQLException e) {

			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar la especialidad.", AlertType.ERROR);

		}

	}

	public Medico insertarMedico(String licencia, String nombre, String especialidad) {
		Medico m = null; 
		PreparedStatement ps;
		ResultSet rs;
		try {
			String SQL = "INSERT INTO Medico (licencia, nombre) VALUES (?,?)";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia); 
			ps.setString(2, nombre);
			ps.execute();

			ResultSet rs1 = principal.getControladorEspecialidad().buscarEspecialidad(especialidad);

			PreparedStatement ps1;
			String SQL1 = "INSERT INTO Medico_has_Especialidad (Medico_licencia, Especialidad_id) VALUES (?,?)";
			ps1 = con.prepareStatement(SQL1);
			ps1.setString(1, licencia);

			while (rs1.next()) {
				ps1.setString(2, rs1.getString("id"));
			}

			ps1.execute();

			m = new Medico(licencia, nombre);

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (SQLException e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return m;
	}

	public void modificarMedico(String licencia, String nombre) {
		try {
			PreparedStatement ps;

			String SQL = "UPDATE Medico SET nombre = ? WHERE licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, licencia);

			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void eliminarMedico(String licencia) {
		PreparedStatement ps;
		PreparedStatement ps1;
		PreparedStatement ps2;

		try {

			String SQL2 = "UPDATE Cita SET Medico_licencia = ? WHERE Medico_licencia = ?";
			ps2 = con.prepareStatement(SQL2);
			ps2.setString(1, null);
			ps2.setString(1, licencia);

			String SQL1 = "DELETE FROM Medico_has_Especialidad WHERE Medico_licencia = ?";
			ps1 = con.prepareStatement(SQL1);
			ps1.setString(1, licencia);

			String SQL = "DELETE FROM Medico WHERE licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia);

			ps2.execute();
			ps1.execute();
			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se elimino existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al eliminar.", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public Medico buscarMedico(String licencia) {
		Medico m = null;
		PreparedStatement ps;
		ResultSet rs;

		try {
			String SQL = "SELECT * FROM Medico WHERE licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia);
			rs = ps.executeQuery();

			if (rs.next()) {
				m = new Medico(rs.getNString("licencia"), rs.getNString("nombre"));
			}

		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
			a.printStackTrace();
		}
		return m;
	}

	public ObservableList<String> verEspecialidades() {

		ObservableList<String> items3 = FXCollections.observableArrayList();

		ArrayList<Especialidad> lstEspecialidades = principal.getControladorEspecialidad().mostrarEspecialidades();

		for (Especialidad eps : lstEspecialidades) {
			items3.add(eps.getNombre());
		}
		return items3;
	}

	public String buscarEspecialidadDelMedico(String licencia) {
		
		ResultSet rs1 = null;
		PreparedStatement ps;
		ResultSet rs2 = null;
		PreparedStatement ps1;
		String numEsp = "";

		try {
			String SQL = "SELECT * FROM Medico_has_Especialidad WHERE Medico_licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia);
			rs1 = ps.executeQuery();

			while (rs1.next()) {

				String SQL1 = "SELECT * FROM Especialidad WHERE id = ?";
				ps1 = con.prepareStatement(SQL1);
				ps1.setString(1, rs1.getString("Especialidad_id"));
				rs2 = ps.executeQuery();

				while (rs2.next()) {

					numEsp = rs2.getString("nombre");
				}

			}

		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
			a.printStackTrace();
		}
		
		return numEsp;
	}

	public ArrayList<Medico> mostrarDatosMedicoPorEspecialidad(String especialidad) {
		ArrayList<Medico> lstMedico = new ArrayList<Medico>();

		try {

			ResultSet rs;
			ResultSet rs1 = principal.getControladorEspecialidad().buscarEspecialidad(especialidad);
			ResultSet rs2;
			PreparedStatement ps;
			PreparedStatement ps1;

			while (rs1.next()) {

				String SQL = "SELECT * FROM Medico_has_Especialidad WHERE Especialidad_id = ?";
				ps = con.prepareStatement(SQL);
				ps.setString(1, rs1.getString("id"));
				rs = ps.executeQuery();

				while (rs.next()) {

					String SQL1 = "SELECT * FROM Medico WHERE licencia = ?";
					ps1 = con.prepareStatement(SQL1);
					ps1.setString(1, rs.getString("Medico_licencia"));
					rs2 = ps1.executeQuery();

					while (rs2.next()) {

						String licencia = rs2.getString("licencia");
						String nombre = rs2.getString("nombre");

						Medico m = new Medico(nombre, licencia);

						lstMedico.add(m);

					}

				}

			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstMedico;

	}

	public ArrayList<Medico> mostrarDatosMedico() {
		ArrayList<Medico> lstMedico = new ArrayList<Medico>();
		String SQL = "SELECT * FROM Medico";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String licencia = rs.getString("licencia");
				String nombre = rs.getString("nombre");

				Medico m = new Medico(nombre, licencia);

				lstMedico.add(m);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstMedico;
	}

}
