package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conector.ConectorBD;
import javafx.scene.control.Alert.AlertType;
import modelo.Principal;
import modelo.alertas.Alerta;
import modelo.entidad.Cita;
import modelo.entidad.Medico;
import modelo.entidad.Paciente;

/**
 * Clase encargada de controlar lo relacionado con Cita.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class ControladorCita {
	private Object object;
	private Object object2;
	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorCita(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public Cita insertarCita(Date fechaHora, String id, String Medico_licencia, String Paciente_dni) {
		Cita c = null;
		PreparedStatement ps;
		Medico m = null;
		Paciente p = null;

		try {
			String SQL = "INSERT INTO Cita (fechaHora, id, Medico_licencia, Paciente_dni) VALUES (?,?,?,?,?)";
			ps = con.prepareStatement(SQL);
			ps.setDate(1, fechaHora);
			ps.setString(2, id);
			ps.setString(3, Medico_licencia);
			ps.setString(4, Paciente_dni);
			ps.execute();

			m = principal.getControladorMedico().buscarMedico(Medico_licencia);
			p = principal.getControladorPaciente().buscarPaciente(Paciente_dni);
			c = new Cita(fechaHora, id, m, p);

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (SQLException e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
		}
		return c;
	}

	public void modificarCita(Date fechaHora, String id, String Medico_licencia) {
		try {
			PreparedStatement ps;
			String SQL = "UPDATE Cita SET fechaHora = ?, Medico_licencia = ? WHERE id = ?";
			ps = con.prepareStatement(SQL);
			ps.setDate(1, fechaHora);
			ps.setString(2, Medico_licencia);
			ps.setString(3, id);
			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
		}
	}

	public void eliminarCita(String id) {
		PreparedStatement ps;

		try {
			String SQL = "DELETE FROM Cita WHERE id = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, id);
			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se elimino existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al eliminar.", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public Cita buscarCita(String id) {
		Cita c = null;
		Medico m = null;
		Paciente p = null;
		PreparedStatement ps;
		ResultSet rs;

		try {
			String SQL = "SELECT * FROM Cita WHERE id = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				m = principal.getControladorMedico().buscarMedico(rs.getString("Medico_licencia"));
				p = principal.getControladorPaciente().buscarPaciente(rs.getString("Paciente_dni"));
				c = new Cita(rs.getDate("fechaHora"), rs.getNString("id"), m, p);

			}
		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
			a.printStackTrace();
		}
		return c;
	}

	public ArrayList<Cita> mostrarDatosCita() {
		ArrayList<Cita> lstCita = new ArrayList<Cita>();
		String SQL = "SELECT * FROM Cita";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				Date fechaHora = rs.getDate("fechaHora");
				String id = rs.getString("id");

				Cita c = new Cita(fechaHora, id, null, null);
				lstCita.add(c);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstCita;
	}

}
