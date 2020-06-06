package modelo.controladoresEntidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import conector.ConectorBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import modelo.Principal;
import modelo.alertas.Alerta;
import modelo.entidad.Ambulancia;
import modelo.entidad.Drogueria;

public class ControladorDrogueria {

	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorDrogueria(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}
	
	public ObservableList<String> verDrogueria() {
		ObservableList<String> lstDrogueria = FXCollections.observableArrayList();

		ArrayList<Drogueria> lstDrogueria2 = mostrarDatosDrogueria();

		for (Drogueria d : lstDrogueria2) {
			lstDrogueria.add(d.getNombre());
		}
		return lstDrogueria;
	}

	public ArrayList<Drogueria> mostrarDatosDrogueria() {
		ArrayList<Drogueria> lstDroguerias = new ArrayList<Drogueria>();
		String SQL = "SELECT * FROM Drogueria";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String nit = rs.getString("nit");

				Drogueria d = new Drogueria(nombre, nit);

				lstDroguerias.add(d);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}

		return lstDroguerias;
	}

}
