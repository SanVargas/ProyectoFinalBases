
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.entidad.Paciente;

public class Prueba implements Initializable {

	@FXML
	Stage stage;

	Controlador controlador;

	@FXML
	private Tab pes2;

	@FXML
	private Button btnModificarPaciente;

	@FXML
	private TextField txtDireccionPaciente;

	@FXML
	private TextField txtBuscarPaciente;

	@FXML
	private ToggleGroup radioGroupOpcionPaciente;

	@FXML
	private Button btnBuscarPaciente;

	@FXML
	private Button btnAgregarPaciente;

	@FXML
	private Tab pes1;

	@FXML
	private TextField txtNombrePaciente;

	@FXML
	private Button btnEliminarPaciente;

	@FXML
	private Button btnEPS;

	@FXML
	private TextField txtDNIPaciente;

	@FXML
	private TableView<Paciente> tablaPaciente;

	@FXML
	private TableColumn<Paciente, String> columnaDireccionPaciente;

	@FXML
	private TableColumn<Paciente, String> columnaDNIPaciente;

	@FXML
	private TableColumn<Paciente, String> columnaNombrePaciente;

	@FXML
	void actionAgregarPaciente(ActionEvent event) {

		String nombre = txtNombrePaciente.getText();
		String dni = txtDNIPaciente.getText();
		String direccion = txtDireccionPaciente.getText();

		Paciente p = controlador.principal.insertarPaciente(nombre, dni, direccion);

		columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
		columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
		columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

		tablaPaciente.getItems().add(p);

	}

	@FXML
	void actionEliminarPaciente(ActionEvent event) {

	}

	@FXML
	void actionModificarPaciente(ActionEvent event) {

	}

	@FXML
	void actionBuscarPaciente(ActionEvent event) {

		Paciente p = controlador.principal.buscarPaciente(txtBuscarPaciente.getText());
		
		columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
		columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
		columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

		tablaPaciente.getItems().add(p);
		
		
		
		
	}

	@FXML
	void actionRadioBtnAgregarPaciente(ActionEvent event) {
		txtNombrePaciente.setEditable(true);
		txtDireccionPaciente.setEditable(true);
		txtDNIPaciente.setEditable(true);
		btnAgregarPaciente.setDisable(false);
	}

	@FXML
	void actionRadioBtnEliminarPaciente(ActionEvent event) {

	}

	@FXML
	void actionRadioBtnModifcarPaciente(ActionEvent event) {

	}

	@FXML
	void actionEPS(ActionEvent event) {
		
		

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
		txtNombrePaciente.setEditable(false);
		txtDireccionPaciente.setEditable(false);
		txtDNIPaciente.setEditable(false);
		btnAgregarPaciente.setDisable(true);
		btnEliminarPaciente.setDisable(true);
		btnModificarPaciente.setDisable(true);

	}

}
