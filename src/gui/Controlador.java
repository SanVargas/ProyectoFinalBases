package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Principal;

public class Controlador {

	public Principal principal;

	public Controlador(Principal principal) throws FileNotFoundException, ClassNotFoundException, IOException {
		super();
		this.principal = principal;
	}
	
	
	/**
	 * Controlador De la Ventana Principal en JavaFx
	 * @throws Exception
	 */
	public void ventanaPrueba() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/prueba.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		Prueba vp = fxmlLoader.getController();
		vp.setControlador(this);
		vp.setVentanaPrincipal(primaryStage);
	}

}
