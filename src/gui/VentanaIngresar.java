package gui;

import java.net.URL;
import java.security.Principal;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
//dministrador a = controlador.principal.getControladorAdmin().buscarAdministador(txtCorreo.getText(), txtClave.getText());
		 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
