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
import modelo.entidad.Medicamento;

public class ControladorMedicamento {

	ConectorBD cc;
	Connection con;
	Principal principal;

	public ControladorMedicamento(ConectorBD cc, Connection con, Principal principal) {
		super();
		this.cc = cc;
		this.con = con;
		this.principal = principal;
	}

	public ObservableList<String> verMedicamento() {
		ObservableList<String> lstMedicamento = FXCollections.observableArrayList();

		ArrayList<Medicamento> lstMedicamento2 = mostrarDatosMedicamento();

		for (Medicamento m : lstMedicamento2) {
			lstMedicamento.add(m.getNombre());
		}
		return lstMedicamento;
	}

	public ArrayList<Medicamento> mostrarDatosMedicamento() {
		ArrayList<Medicamento> lstMedicamentos = new ArrayList<Medicamento>();
		String SQL = "SELECT * FROM Medicamento";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);

			while (rs.next()) {
				String registroSanitario = rs.getString("registroSanitario");
				String nombre = rs.getString("nombre");
				double gramaje = rs.getDouble("gramaje");

				Medicamento m = new Medicamento(registroSanitario, nombre, gramaje, null, null);

				lstMedicamentos.add(m);
			}

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Error al listar.", AlertType.ERROR);
			e.printStackTrace();
		}

		return lstMedicamentos;
	}
	
}
