
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import conector.ConectorBD;
import javafx.scene.control.Alert.AlertType;
import modelo.entidad.Eps;

public class ControladorEps {
	ConectorBD cc;
	Connection con;
	Principal principal;
	private PreparedStatement ps;
	private ResultSet rs;

	public ControladorEps(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public Eps insertarEps(String nit, String nombre) {
		Eps e = null;
		try {
			String SQL = "INSERT INTO EPS (nit, nombre) VALUES (?,?)";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);
			ps.setString(2, nombre);

			e = new Eps(nit, nombre);

			ps.execute();

			JOptionPane.showMessageDialog(null, "Se agrego correctamente la EPS");
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null,
					"No se agrego correctamente, verifique la informaci�n. error: " + a.getMessage());
			e = null;
		}
		return e;
	}

	public void modificarEps(String nit, String nombre) {
		try {
			String SQL = "UPDATE EPS SET nit = ? , nombre = ? WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);
			ps.setString(2, nombre);
			ps.setString(3,nit);

			ps.execute();
			
			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se realizo la modificacion existosamente", AlertType.CONFIRMATION);
			
		} catch (Exception e) {
			Alerta.mostrarAlerta("Mensaje", "Error", "No se realizo modificacion", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public void eliminarEps(String nit) {
		try {
			String SQL1 = "SELECT * FROM Paciente WHERE EPS_nit = ?";
			PreparedStatement ps1 = con.prepareStatement(SQL1);
			ResultSet rs1;
			ps1.setString(1, nit);
			rs1 = ps1.executeQuery();

			while (rs1.next()) {
				String SQL2 = "UPDATE Paciente SET EPS_nit = ? WHERE dni = ?";
				PreparedStatement ps2 = con.prepareStatement(SQL2);
				ps2.setString(1, null);
				ps2.setString(2, rs1.getString("dni"));
				ps2.execute();
			}

			String SQL = "DELETE FROM EPS WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);

			ps.execute();
			
			Alerta.mostrarAlerta("Confirmacion", "Alerta", "Se realizo la eliminacion existosamente.", AlertType.CONFIRMATION);
			
		} catch (Exception e) {
			
			Alerta.mostrarAlerta("Error", "Alerta", "No se realizo la eliminacion existosamente.", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public Eps buscarEpsNit(String nit) {
		Eps e = null;

		try {
			String SQL = "SELECT * FROM EPS WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);
			rs = ps.executeQuery();

			if (rs.next()) {
				e = new Eps(rs.getNString("nit"), rs.getNString("nombre"));

			}
		} catch (Exception a) {
			
			Alerta.mostrarAlerta("Error", "Alerta", "No se realizo consulta.", AlertType.ERROR);
			a.printStackTrace();
		}
		return e;
	}

	public Eps buscarEpsNombre(String nombre) {
		Eps e = null;

		try {
			String SQL = "SELECT * FROM EPS WHERE nombre = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nombre);
			rs = ps.executeQuery();

			if (rs.next()) {
				e = new Eps(rs.getNString("nit"), rs.getNString("nombre"));

			}
		} catch (Exception a) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se realizo consulta.", AlertType.ERROR);
			a.printStackTrace();
		}
		return e;
	}

	public ArrayList<Eps> mostrarDatosEps() {
		ArrayList<Eps> lstEps = new ArrayList<Eps>();
		String SQL = "SELECT * FROM EPS";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String nit = rs.getString("nit");
				String nombre = rs.getString("nombre");

				Eps e = new Eps(nit, nombre);

				lstEps.add(e);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar las Eps's. error: ", AlertType.ERROR);
			e.printStackTrace();
		}
		return lstEps;
	}

}
