package modelo;

import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import conector.ConectorBD;

public class Principal {
	
	ConectorBD cc = new ConectorBD();
	Connection con = cc.establecerConexion();
	
	
	public void insertar()
	{
		
		try {
			
			String SQL = "insert into EPS (nit, nombre) values (?,?)";
			
			PreparedStatement pst = con.prepareStatement(SQL);
			
			pst.setString(1, "1232392");
			pst.setString(2, "prueba");
			
			
			
			pst.execute();
			JOptionPane.showMessageDialog(null, "Se agrego correctamente");
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se  ingreso" + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
