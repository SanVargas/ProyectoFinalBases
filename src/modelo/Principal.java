package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public Paciente insertarPaciente(String nombre, String dni, String direccion) {

		Paciente p = null;
		HistoriaClinica hc = null;
		String numero = generarAleatorio() + "";

		try {

			String SQL2 = "insert into HistoriaClinica (numero, informacionMedica, estatura, peso, Paciente_dni, TipoSangre_id) values (?,?,?, ?, ?, ?)";

			PreparedStatement pst1 = con.prepareStatement(SQL2);

			pst1.setString(1, numero);
			pst1.setString(2, "Vemos");
			pst1.setDouble(3, 1.80);
			pst1.setDouble(4, 77.9);
			pst1.setString(5, dni);
			pst1.setString(6, null);

			String SQL1 = "insert into Paciente (nombre, dni, direccion, EPS_nit, HistoriaClinica_numero) values (?,?, ?, ?, ?)";

			PreparedStatement pst = con.prepareStatement(SQL1);

			pst.setString(1, nombre);
			pst.setString(2, dni);
			pst.setString(3, direccion);
			pst.setString(4, null);
			pst.setString(5, numero);

			hc = new HistoriaClinica(numero, "", 70.2, 180.1, null, p);

			p = new Paciente(nombre, dni, direccion, hc, null);

			hc.setPaciente(p);

			pst.execute();
			pst1.execute();

			JOptionPane.showMessageDialog(null, "Se agrego correctamente");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se  ingreso" + e.getMessage());
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
				JOptionPane.showMessageDialog(null, rs.getString("nombre"));
			}
			else {
				JOptionPane.showMessageDialog(null, "no deberia pasar esto");
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

}
