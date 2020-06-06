package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		primaryStage.getIcons().add(new Image("ventanas/clinica.png"));
		primaryStage.setTitle("HSJD");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaCRUD vc = fxmlLoader.getController();
		vc.setControlador(this);
		vc.setVentanaPrincipal(primaryStage);
		vc.mostrarPaciente();
		vc.mostrarEps();
		vc.mostrarMedico();
		vc.mostrarEmpleado();
		vc.mostrarCita();
	}
	
	/**
	 * Controlador De la Ventana Loggeo en JavaFx
	 * @throws Exception
	 */
	public void ventanaIngresar() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/ventanaIngreso.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.getIcons().add(new Image("ventanas/clinica.png"));
		primaryStage.setTitle("HSJD");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaIngresar vc = fxmlLoader.getController();
		vc.setControlador(this);
		vc.setStage(primaryStage);
	}
	
	/**
	 * Controlador De la Ventana Administrador en JavaFx
	 * @throws Exception
	 */
	public void ventanaAdministrador()throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/ventanaAdministrador.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.getIcons().add(new Image("ventanas/clinica.png"));
		primaryStage.setTitle("HSJD");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaAdministrador vA = fxmlLoader.getController();
		vA.setControlador(this);
		vA.setStage(primaryStage);
	}
	
	/**
	 * Controlador De la Ventana Reporte en JavaFx
	 * @throws Exception
	 */
	public void ventanaReporte() throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ventanas/ventanaReporte.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.getIcons().add(new Image("ventanas/clinica.png"));
		primaryStage.setTitle("HSJD");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		VentanaReporte vc = fxmlLoader.getController();
		vc.setControlador(this);
		vc.setStage(primaryStage);
		vc.llenarComboBox();
	}

}
