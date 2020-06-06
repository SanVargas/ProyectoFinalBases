package gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.alertas.Alerta;
import modelo.entidad.Administrador;

public class VentanaIngresar implements Initializable {
	@FXML
	Stage stage;

	Controlador controlador;

	@FXML
	private Button btnSalir;

	@FXML
	private Button btnIngresar;

	@FXML
	private TextField txtCorreo;

	@FXML
	private PasswordField txtClave;

	@FXML
	void actionBtnSalir(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void actionBtnIngresar(ActionEvent event) {
		String correo = txtCorreo.getText().toLowerCase();
		String clave = txtClave.getText().toLowerCase();
		Administrador a = controlador.principal.getControladorAdmin().buscarAdministrador(correo, clave);

		if (a != null) {
			try {
				controlador.ventanaAdministrador();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Administrador no encontrado.", AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
