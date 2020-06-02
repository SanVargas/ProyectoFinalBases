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
import modelo.entidad.Eps;
import modelo.entidad.HistoriaClinica;
import modelo.entidad.Paciente;
import modelo.entidad.TipoSangre;
/**
 * Clase encargada de controlar lo relacionado con paciente.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class ControladorPaciente {
	ConectorBD cc;
	Connection con;
	Principal principal;
	private PreparedStatement ps;
	private ResultSet rs;

	public ControladorPaciente(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public Paciente insertarPaciente(String nombre, String dni, String direccion, double altura, double peso,
			String grupoS, String rh, String eps, String numeroT, String descripcionT) {

		Paciente p = null;
		HistoriaClinica hc = null;
		String numero = generarAleatorio() + "";
		String idGrupoS = determinarIdSanguineo(rh, grupoS);

		try {

			String SQL1 = "INSERT INTO HistoriaClinica (numero, informacionMedica, estatura, peso, Paciente_dni, TipoSangre_id) values (?,?,?, ?, ?, ?)";

			PreparedStatement pst1 = con.prepareStatement(SQL1);

			pst1.setString(1, numero);
			pst1.setString(2, "");
			pst1.setDouble(3, altura);
			pst1.setDouble(4, peso);
			pst1.setString(5, dni);
			pst1.setString(6, idGrupoS);

			TipoSangre ts = new TipoSangre(rh, grupoS, idGrupoS);
			hc = new HistoriaClinica(numero, "", peso, altura, ts, p);

			String SQL2 = "INSERT INTO Paciente (nombre, dni, direccion, EPS_nit, HistoriaClinica_numero) values (?,?, ?, ?, ?)";

			PreparedStatement pst2 = con.prepareStatement(SQL2);

			pst2.setString(1, nombre);
			pst2.setString(2, dni);
			pst2.setString(3, direccion);

			pst2.setString(5, numero);

			Eps eps1 = principal.getControladorEps().buscarEpsNombre(eps);

			if (eps1 != null) {

				pst2.setString(4, eps1.getNit());
				p = new Paciente(nombre, dni, direccion, hc, eps1);
			}

			if (eps1 == null) {
				pst2.setString(4, null);
				p = new Paciente(nombre, dni, direccion, hc, null);
			}

			pst2.execute();
			pst1.execute();
			agregarTelefono(numeroT, descripcionT, dni);

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (SQLException e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
			p = null;
		}
		return p;

	}

	public void agregarTelefono(String numero, String descripcion, String dni) {

		String SQL1 = "INSERT INTO Telefono_Paciente (numero, descripcion, Paciente_dni) values (?,?,?)";

		try {
			PreparedStatement pst1 = con.prepareStatement(SQL1);

			pst1.setString(1, numero);
			pst1.setString(2, descripcion);
			pst1.setString(3, dni);
			pst1.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
		}

	} 

	public void modificarPaciente(String dni, String nombre, String direccion, double peso, double estatura, String rh,
			String grupoS, String eps) {

		Eps ep = principal.getControladorEps().buscarEpsNombre(eps);
		PreparedStatement ps1;

		try {

			String SQL1 = "UPDATE HistoriaClinica SET estatura = ?, peso = ?, TipoSangre_id = ? WHERE Paciente_dni = ?";
			ps1 = con.prepareStatement(SQL1);
			ps1.setDouble(1, estatura);
			ps1.setDouble(2, peso);
			ps1.setString(3, determinarIdSanguineo(rh, grupoS));
			ps1.setString(4, dni);

			ps1.execute();

			String SQL = "UPDATE Paciente SET nombre = ?, direccion = ?, EPS_nit = ? WHERE dni = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, direccion);
			ps.setString(3, ep.getNit());
			ps.setString(4, dni);

			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
		}
	}

	public void eliminarPaciente(String dni) {

		PreparedStatement ps1;
		PreparedStatement ps3;
		PreparedStatement ps2;

		try {

			String SQL3 = "UPDATE Cita SET Paciente_dni = ? WHERE Paciente_dni = ?";
			ps2 = con.prepareStatement(SQL3);
			ps2.setString(1, null);
			ps2.setString(1, dni);
			

			String SQL1 = "DELETE FROM HistoriaClinica WHERE Paciente_dni = ?";
			ps1 = con.prepareStatement(SQL1);
			ps1.setString(1, dni);

			String SQL2 = "DELETE FROM Telefono_Paciente WHERE Paciente_dni = ?";
			ps3 = con.prepareStatement(SQL2);
			ps3.setString(1, dni);

			String SQL = "DELETE FROM Paciente WHERE dni = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, dni);

			ps2.execute();
			ps3.execute();
			ps1.execute();
			ps.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se elimino existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al eliminar.", AlertType.ERROR);
		}
	}

	public void limpiar() {
	}

	public int generarAleatorio() {
		return (int) Math.floor(Math.random() * (10000 - 0 + 1) + 0);
	}

	public Paciente buscarPaciente(String dniPaciente) {

		Paciente p = null;

		try {

			String SQL = "SELECT * FROM Paciente WHERE dni = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, dniPaciente);
			rs = ps.executeQuery();

			if (rs.next()) {
				p = new Paciente(rs.getNString("nombre"), rs.getNString("dni"), rs.getNString("direccion"));

				String SQL1 = "SELECT * FROM HistoriaClinica";
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery(SQL1);

				while (rs1.next()) {

					if (rs1.getString("numero").equals(rs.getString("HistoriaClinica_numero"))) {
						HistoriaClinica hc = new HistoriaClinica(rs1.getString("numero"),
								rs1.getString("InformacionMedica"), rs1.getDouble("estatura"), rs1.getDouble("peso"),
								null, p);
						p.setHistoriaClinica(hc);

					}

				}
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
		}

		return p;

	}

	public ArrayList<Paciente> mostrarDatos() {

		ArrayList<Paciente> lstPaciente = new ArrayList<Paciente>();

		String SQL = "SELECT * FROM Paciente";

		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {

				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");

				Paciente p = new Paciente(nombre, dni, direccion, null, null);

				String SQL1 = "SELECT * FROM HistoriaClinica";
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery(SQL1);

				while (rs1.next()) {

					if (rs1.getString("numero").equals(rs.getString("HistoriaClinica_numero"))) {
						HistoriaClinica hc = new HistoriaClinica(rs1.getString("numero"),
								rs1.getString("InformacionMedica"), rs1.getDouble("estatura"), rs1.getDouble("peso"),
								null, p);
						p.setHistoriaClinica(hc);
						lstPaciente.add(p);
					}
				}
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstPaciente;
	}

	public ObservableList<String> verEPS() {
		ObservableList<String> items3 = FXCollections.observableArrayList();

		ArrayList<Eps> lstEps = principal.getControladorEps().mostrarDatosEps();

		for (Eps eps : lstEps) {
			items3.add(eps.getNombre());
		}
		return items3;
	}

	public String determinarIdSanguineo(String rh, String grupoS) {
		String id = "";

		if (rh.equals("+") && grupoS.equals("O")) {
			id = "00001";
		}

		if (rh.equals("-") && grupoS.equals("O")) {
			id = "00002";
		}

		if (rh.equals("+") && grupoS.equals("A")) {
			id = "00003";
		}

		if (rh.equals("-") && grupoS.equals("A")) {
			id = "00004";
		}

		if (rh.equals("+") && grupoS.equals("B")) {
			id = "00005";
		}

		if (rh.equals("-") && grupoS.equals("B")) {
			id = "00006";
		}

		if (rh.equals("+") && grupoS.equals("AB")) {
			id = "00007";
		}

		if (rh.equals("-") && grupoS.equals("AB")) {
			id = "00008";
		}

		return id;

	}
}