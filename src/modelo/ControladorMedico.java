package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conector.ConectorBD;
import javafx.scene.control.Alert.AlertType;
import modelo.entidad.Medico;
/**
 * Clase encargada de controlar lo relacionado con medico.
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class ControladorMedico {
	ConectorBD cc;
	Connection con;
	Principal principal;
	private PreparedStatement ps;
	private ResultSet rs;

	public ControladorMedico(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public Medico insertarMedico(String licencia, String nombre) {
		Medico m = null;
		try {
			String SQL = "INSERT INTO Medico (licencia, nombre) VALUES (?,?)";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia);
			ps.setString(2, nombre);

			m = new Medico(licencia, nombre);

			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (SQLException e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
			m = null;
		}
		return m;
	}

	public void modificarMedico(String licencia, String nombre) {
		try {
			String SQL = "UPDATE Medico SET nombre = ? WHERE licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, licencia);

			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
		}
	}

	public void eliminarMedico(String licencia) {
		try {
			String SQL = "DELETE FROM Medico WHERE licencia = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, licencia);

			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se elimino existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al eliminar.", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public Medico buscarMedico(String licencia) {
		Medico m = null;

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
