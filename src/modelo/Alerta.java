package modelo;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Clase para empaquetar metodo de confirmacion o de informacion hacia el 
 * el cliente
 * @author  Ivan Andres Guapacha - Juan Miguel Giraldo
 *
 */
public class Alerta {

	
	/**
	 * Metodo para mostrar una dialogo con informacion ya sea de advertencia o de error al usuario
	 * @param titulo
	 * @param encabezado
	 * @param mensaje
	 * @param tipo
	 */
	public static void mostrarAlerta(String titulo,String encabezado,String mensaje, AlertType tipo) 
	{
		Alert miAlerta = new Alert(tipo);
		miAlerta.setTitle(titulo);
		miAlerta.setHeaderText(encabezado);
		miAlerta.setContentText(mensaje);
		
		miAlerta.show();
	}
	
	/**
	 * Metodo para mostrar el dialogo de confirmacion de una accion al usuario
	 * @param titulo
	 * @param encabezado
	 * @param mensaje
	 * @return
	 */
	public static boolean mostrarAletarConfirmacion(String titulo,String encabezado,String mensaje)
	{
		boolean salida = false;
		Alert miAlerta = new Alert(AlertType.CONFIRMATION);
		miAlerta.setTitle(titulo);
		miAlerta.setHeaderText(encabezado);
		miAlerta.setContentText(mensaje);
		
		Optional<ButtonType> result = miAlerta.showAndWait();
		if (result.get() == ButtonType.OK)
		{
		    salida = true;
		}
		 
		return salida;
	}
}
