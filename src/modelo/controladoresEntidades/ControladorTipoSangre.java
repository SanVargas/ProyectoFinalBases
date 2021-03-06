package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import conector.ConectorBD;
import modelo.Principal;
/**
 * Clase encargada de controlar lo relacionado con TipoSangre.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class ControladorTipoSangre {
	ConectorBD cc;
	Connection con;
	Principal principal;
	private PreparedStatement ps;
	private ResultSet rs;

	public ControladorTipoSangre(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public void insertarGrupoSangre() {

		try {
			String SQL = "SELECT * FROM TipoSangre";
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(SQL);

			// if (rs1 == null) {
			String SQL1 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(SQL1);
			pst1.setString(1, "+");
			pst1.setString(2, "O");
			pst1.setString(3, "00001");

			String SQL2 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst2 = con.prepareStatement(SQL2);
			pst2.setString(1, "-");
			pst2.setString(2, "O");
			pst2.setString(3, "00002");

			String SQL3 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst3 = con.prepareStatement(SQL3);
			pst3.setString(1, "+");
			pst3.setString(2, "A");
			pst3.setString(3, "00003");

			String SQL4 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst4 = con.prepareStatement(SQL4);
			pst4.setString(1, "-");
			pst4.setString(2, "A");
			pst4.setString(3, "00004");

			String SQL5 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst5 = con.prepareStatement(SQL5);
			pst5.setString(1, "+");
			pst5.setString(2, "B");
			pst5.setString(3, "00005");

			String SQL6 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst6 = con.prepareStatement(SQL6);
			pst6.setString(1, "-");
			pst6.setString(2, "B");
			pst6.setString(3, "00006");

			String SQL7 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst7 = con.prepareStatement(SQL7);
			pst7.setString(1, "+");
			pst7.setString(2, "AB");
			pst7.setString(3, "00007");

			String SQL8 = "INSERT INTO TipoSangre (rh, grupoSanguineo, id) values (?,?,?)";
			PreparedStatement pst8 = con.prepareStatement(SQL8);
			pst8.setString(1, "-");
			pst8.setString(2, "AB");
			pst8.setString(3, "00008");

			pst1.execute();
			pst2.execute();
			pst3.execute();
			pst4.execute();
			pst5.execute();
			pst6.execute();
			pst7.execute();
			pst8.execute();
			// }

		} catch (SQLException e) {

		}

	}

	public Map<String, Object> devolverMap(String tipoSangre) {

		Map<String, Object> parametro = new HashMap<String, Object>();

		if (tipoSangre.equals("O+")) {
			parametro.put("grupoS", "O");
			parametro.put("rh", "+");
		} else if (tipoSangre.equals("O-")) {
			parametro.put("grupoS", "O");
			parametro.put("rh", "-");
		} else if (tipoSangre.equals("A+")) {
			parametro.put("grupoS", "A");
			parametro.put("rh", "+");
		} else if (tipoSangre.equals("A-")) {
			parametro.put("grupoS", "A");
			parametro.put("rh", "-");
		} else if (tipoSangre.equals("B+")) {
			parametro.put("grupoS", "B");
			parametro.put("rh", "+");
		} else if (tipoSangre.equals("B-")) {
			parametro.put("grupoS", "B");
			parametro.put("rh", "-");
		} else if (tipoSangre.equals("AB+")) {
			parametro.put("grupoS", "AB");
			parametro.put("rh", "+");
		} else if (tipoSangre.equals("AB-")) {
			parametro.put("grupoS", "AB");
			parametro.put("rh", "-");

		}

		return parametro;

	}

}
