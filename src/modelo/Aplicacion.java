package modelo;

import gui.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Aplicacion extends Application {
	
	/**
	 * Main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		launch(args);
	}	
	
	
	/**
	 * Metodo ejecutor de Java FX
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Principal principal = new Principal();
		Controlador controlador = new Controlador(principal);
		controlador.ventanaCRUD();
		controlador.principal.insertarGrupoSangre();
	}
	

}
