package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conector.ConectorBD;
import modelo.entidad.Eps;

public class ControladorEps {
	ConectorBD cc;
	Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ControladorEps(ConectorBD cc, Connection con) {
		super();
		this.cc = cc;
		this.con = con;
	}

	public Eps insertarEps(String nit, String nombre) {
		Eps e = null;
		try {
			String SQL = "insert into Eps (nit, nombre) values (?,?)";
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
			String SQL = "UPDATE Eps SET nit = ? , nombre = ? WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);
			ps.setString(2, nombre);

			ps.execute();

			JOptionPane.showMessageDialog(null, "Se realizo la modificacion existosamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo modificacion" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void eliminarEps(String nit) {
		try {
			String SQL = "DELETE FROM Eps WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);

			ps.execute();

			JOptionPane.showMessageDialog(null, "Se realizo la eliminacion existosamente.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo eliminacion. error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Eps buscarEps(String nit) {
		Eps e = null;

		try {
			String SQL = "SELECT * FROM Eps WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, nit);
			rs = ps.executeQuery();

			if (rs.next()) {
				e = new Eps(rs.getNString("nit"), rs.getNString("nombre"));

				/**
				 * String SQL1 = "SELECT * FROM HistoriaClinica"; Statement st1 =
				 * con.createStatement(); ResultSet rs1 = st1.executeQuery(SQL1);
				 * 
				 *while (rs1.next()) {
				 * 
				 * if (rs1.getString("numero").equals(rs.getString("HistoriaClinica_numero"))) {
				 * HistoriaClinica hc = new HistoriaClinica(rs1.getString("numero"),
				 * rs1.getString("InformacionMedica"), rs1.getDouble("estatura"),
				 * rs1.getDouble("peso"), null, p); p.setHistoriaClinica(hc);
				 * 
				 * }
				 * 
				 * }
				 */
			}
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, "No se realizo consulta." + a.getMessage());
			a.printStackTrace();
		}
		return e;
	}
	
	public ArrayList<Eps> mostrarDatosEps() {
		ArrayList<Eps> lstEps = new ArrayList<Eps>();
		String SQL = "Select * from Eps";

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
			JOptionPane.showMessageDialog(null, "Error al listar las Eps's. error: " + e.getMessage());
		}
		return lstEps;
	}

}
