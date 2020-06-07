package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * Clase encargada de controlar la ventana Administrador.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class VentanaAdministrador implements Initializable {
	@FXML
	Stage stage;
	Controlador controlador;
	@FXML
	private Button btnGenerarReportes;
	@FXML
	private Button btnGestionarBD;

	@FXML
	void actionBtnGenerarReportes(ActionEvent event) {
		
		try {
			controlador.ventanaReporte();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	@FXML
	void actionBtnGestionarBD(ActionEvent event) {
		try {
			controlador.ventanaCRUD();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
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
