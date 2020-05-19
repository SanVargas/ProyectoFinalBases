package gui;

import java.net.URL;
import java.util.ResourceBundle;

import conector.ConectorBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Prueba implements Initializable {

	@FXML
	private Button btnPrueba;

	@FXML
	Stage stage;

	Controlador controlador;

	@FXML
	void actionPrueba(ActionEvent event) {
		controlador.principal.insertar();

	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setVentanaPrincipal(Stage venP) {
		this.stage = venP;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ConectorBD.establecerConexion();

	}

}
