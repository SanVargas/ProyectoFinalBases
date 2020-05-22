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
import modelo.entidad.Cargo;
import modelo.entidad.Empleado;
import modelo.entidad.Eps;
import modelo.entidad.HistoriaClinica;
import modelo.entidad.Medico;
import modelo.entidad.Paciente;
import modelo.entidad.TipoSangre;

public class ControladorEmpleado {
	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorEmpleado(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public Empleado insertarEmpleado(String nombre, String id, String licencia, String cargo, String telefono,
			String descripcion) {

		Empleado emp = null;

		try {

			String SQL1 = "INSERT INTO Empleado (id, nombre, licencia, Cargo_id, Caja_nit, Drogueria_nit) values (?,?,?, ?, ?, ?)";

			PreparedStatement pst1 = con.prepareStatement(SQL1);

			String cargo_id = determinarCodigoCargo(cargo);

			pst1.setString(1, id);
			pst1.setString(2, nombre);
			pst1.setString(3, licencia);
			pst1.setString(4, cargo_id);
			pst1.setString(5, null);
			pst1.setString(6, null);

			String cargoId = determinarCodigoCargo(cargo);
			
			Cargo c = buscarCargo(cargoId);

			emp = new Empleado(id, nombre, licencia, c, null, null);
			
			pst1.execute();
			
			agregarTelefonoEmpleado(telefono, descripcion, id);
			

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (SQLException e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.", AlertType.ERROR);
			emp = null;
		}
		return emp;

	}

	public void modificarEmpleado(String nombre, String id, String cargo) {

		PreparedStatement ps1;
		
		if(cargo != null)
		{
			String cargoID = determinarCodigoCargo(cargo);
			

			try {

				String SQL1 = "UPDATE Empleado SET nombre = ?, Cargo_id = ? WHERE id = ?";
				ps1 = con.prepareStatement(SQL1);
				ps1.setString(1, nombre);
				ps1.setString(2, cargoID);
				ps1.setString(3, id);
				ps1.execute();

				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
			} catch (Exception e) {
				Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
			}
		}else
		{
			try {

				String SQL1 = "UPDATE Empleado SET nombre = ? WHERE id = ?";
				ps1 = con.prepareStatement(SQL1);
				ps1.setString(1, nombre);
				ps1.setString(2, id);
				ps1.execute();

				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se modifico existosamente.", AlertType.CONFIRMATION);
			} catch (Exception e) {
				Alerta.mostrarAlerta("Error", "Alerta", "Error al modificar.", AlertType.ERROR);
			}
		}
		
		
		
	}

	public void eliminarPaciente(String id) {

		PreparedStatement ps1;
		PreparedStatement ps2;

		try {

			String SQL1 = "DELETE FROM Telefono_Empleado WHERE Empleado_id = ?";
			ps1 = con.prepareStatement(SQL1);
			ps1.setString(1, id);

			String SQL = "DELETE FROM Empleado WHERE id = ?";
			ps2 = con.prepareStatement(SQL);
			ps2.setString(1, id);

			ps1.execute();
			ps2.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se elimino existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al eliminar.", AlertType.ERROR);
		}
	}

	public Empleado buscarEmpleado(String id) {
		Empleado emp = null;
		Cargo c = null;
		PreparedStatement ps;
		ResultSet rs;

		try {
			String SQL = "SELECT * FROM Empleado WHERE id = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				c = buscarCargo(rs.getString("Cargo_id"));

				emp = new Empleado(rs.getString("id"), rs.getString("nombre"), rs.getString("licencia"), c, null, null);
			}

		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al buscar.", AlertType.ERROR);
			a.printStackTrace();
		}
		return emp;
	}
	
	public ArrayList<Empleado> mostrarDatos() {

		ArrayList<Empleado> lstEmpleado = new ArrayList<Empleado>();

		String SQL = "SELECT * FROM Empleado";

		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {

				String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				String licencia = rs.getString("licencia");
				Cargo c = buscarCargo(rs.getString("Cargo_id"));
				

				Empleado emp = new Empleado(id, nombre, licencia, c, null, null);

				lstEmpleado.add(emp);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstEmpleado;
	}
	
	public ObservableList<String> verCargo() {
		ObservableList<String> item = FXCollections.observableArrayList();

		ArrayList<Cargo> lstCargo = principal.getControladorCargo().mostrarDatosCargo();

		for (Cargo ca : lstCargo) {
			item.add(ca.getNombre());
		}
		return item;
	}

	public void agregarTelefonoEmpleado(String numero, String descripcion, String dni) {

		String SQL1 = "INSERT INTO Telefono_Empleado (numero, categoria, Empleado_id) values (?,?,?)";

		try {
			PreparedStatement pst1 = con.prepareStatement(SQL1);

			
			pst1.setString(1, numero);
			pst1.setString(2, descripcion);
			pst1.setString(3, dni);
			pst1.execute();

			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se agrego existosamente.", AlertType.CONFIRMATION);
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al agregar.   " + e.getMessage(), AlertType.ERROR);
		}

	}

	public String determinarCodigoCargo(String nombre) {

		String SQL11 = "SELECT * FROM Cargo WHERE nombre = ?";
		String id = "";
		ResultSet rs11;

		try {

			PreparedStatement ps11 = con.prepareStatement(SQL11);
			ps11.setString(1, nombre);
			rs11 = ps11.executeQuery();

			while (rs11.next()) {
				id = rs11.getString("id");
			}
		} catch (Exception e) {

			Alerta.mostrarAlerta("Error", "Alerta", "No se encontro el cargo.", AlertType.ERROR);

		}

		return id;

	}

	public Cargo buscarCargo(String id) {

		PreparedStatement ps;
		ResultSet rs;
		Cargo c = null;

		try {

			String SQL = "SELECT * FROM Cargo WHERE id = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				c = new Cargo(id, rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("salario"));
			}
		} catch (Exception e) {

			Alerta.mostrarAlerta("Error", "Alerta", "No se encontro el cargo.", AlertType.ERROR);

		}

		return c;

	}

}
