package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import conector.ConectorBD;
import modelo.entidad.HistoriaClinica;
import modelo.entidad.Paciente;

public class Principal {

	ConectorBD cc = new ConectorBD();
	Connection con = cc.establecerConexion();
	private PreparedStatement ps;
	private ResultSet rs;

	public Paciente insertarPaciente(String nombre, String dni, String direccion, double altura, double peso,
			String grupoS, String rh) {

		Paciente p = null;
		HistoriaClinica hc = null;
		String numero = generarAleatorio() + "";
		String idGrupoS = determinarIdSanguineo(rh, grupoS);

		try {

			String SQL1 = "insert into HistoriaClinica (numero, informacionMedica, estatura, peso, Paciente_dni, TipoSangre_id) values (?,?,?, ?, ?, ?)";

			PreparedStatement pst1 = con.prepareStatement(SQL1);

			pst1.setString(1, numero);
			pst1.setString(2, "");
			pst1.setDouble(3, altura);
			pst1.setDouble(4, peso);
			pst1.setString(5, dni);
			pst1.setString(6, idGrupoS);

			String SQL2 = "insert into Paciente (nombre, dni, direccion, EPS_nit, HistoriaClinica_numero) values (?,?, ?, ?, ?)";

			PreparedStatement pst2 = con.prepareStatement(SQL2);

			pst2.setString(1, nombre);
			pst2.setString(2, dni);
			pst2.setString(3, direccion);
			pst2.setString(4, null);
			pst2.setString(5, numero);

			hc = new HistoriaClinica(numero, "", 70.2, 180.1, null, p);

			p = new Paciente(nombre, dni, direccion, hc, null);

			hc.setPaciente(p);

			pst2.execute();
			pst1.execute();

			JOptionPane.showMessageDialog(null, "Se agrego correctamente");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"No se agrego correctamente, verifique la información. error: " + e.getMessage());
			p = null;
		}

		return p;

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
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo consulta" + e.getMessage());
			e.printStackTrace();
		}

		return p;

	}

	public void modificar() {

		try {

			String SQL = "UPDATE EPS SET nit = ? , nombre = ? WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, "1234");
			ps.setString(2, "Cafesalud");
			ps.setString(3, "1232392");

			ps.execute();

			JOptionPane.showMessageDialog(null, "Se realizo la modificacion existosamente!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo modificacion" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void eliminar() {

		try {

			String SQL = "DELETE FROM EPS WHERE nit = ?";
			ps = con.prepareStatement(SQL);
			ps.setString(1, "1234");

			ps.execute();

			JOptionPane.showMessageDialog(null, "Se realizo la eliminacion existosamente!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo eliminacion" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
	}

	public int generarAleatorio() {
		return (int) Math.floor(Math.random() * (10000 - 0 + 1) + 0);
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
