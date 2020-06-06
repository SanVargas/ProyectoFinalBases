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

public class ControladorAmbulancia {
	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorAmbulancia(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public ObservableList<String> verAmbulancia() {
		ObservableList<String> lstAmbulancias = FXCollections.observableArrayList();

		ArrayList<Ambulancia> lstAmbulancias2 = mostrarDatosAmbulancia();

		for (Ambulancia a : lstAmbulancias2) {
			lstAmbulancias.add(a.getPlaca());
		}
		return lstAmbulancias;
	}

	public ArrayList<Ambulancia> mostrarDatosAmbulancia() {
		ArrayList<Ambulancia> lstAmbulancias = new ArrayList<Ambulancia>();
		String SQL = "SELECT * FROM Ambulancia";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String placa = rs.getString("placa");
				String categoria = rs.getString("categoria");
				boolean enServicio = rs.getBoolean("enServicio");

				Ambulancia a = new Ambulancia(placa, categoria, enServicio);

				lstAmbulancias.add(a);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}

		return lstAmbulancias;
	}

}
