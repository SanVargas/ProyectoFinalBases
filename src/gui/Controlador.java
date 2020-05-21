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
	public void ventanaCRUD() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/ventanaCRUD.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaCRUD vc = fxmlLoader.getController();
		vc.setControlador(this);
		vc.setVentanaPrincipal(primaryStage);
		vc.mostrarPaciente();
		vc.mostrarEps();
	}

}
