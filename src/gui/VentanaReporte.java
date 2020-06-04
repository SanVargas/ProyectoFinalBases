package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaReporte implements Initializable {

	@FXML
	Stage stage;

	Controlador controlador;

	@FXML
	private Button btnPersonalMedico;

	@FXML
	private Button btnCitas;

	@FXML
	private Button btnPacientes;

	@FXML
	private Button btnEmpleados;

	@FXML
	private TextField txtDniPaciente;

	@FXML
	private Button btnPacientesPorAfiliacion;

	@FXML
	private ComboBox<String> cmbAfiliacion;

	@FXML
	private Button btnDiagnosticosPorPaciente;

	@FXML
	private Button btnMedicosPorEspecialidad;

	@FXML
	private ComboBox<String> cmbEspecialidad;

	@FXML
	private Button btnPacientesPorTipoSangre;

	@FXML
	private ComboBox<String> cmbTipoSangre;

	@FXML
	private Button btnEmpleadosPorCargo;

	@FXML
	private ComboBox<String> cmbCargo;

	@FXML
	private Button btnDrogueriasPorMedicamento;

	@FXML
	private ComboBox<String> cmbMedicamento;

	@FXML
	private Button btnEquiposPorAmbulancia;

	@FXML
	private ComboBox<String> cmbPlaca;

	@FXML
	private Button btnMedicamentosPorDrogueria;

	@FXML
	private ComboBox<String> cmbDrogueria;

	@FXML
	private Button btnVolver;

	@FXML
	void actionBtnCitas(ActionEvent event) {

	}

	@FXML
	void actionBtnDiagnosticosPorPaciente(ActionEvent event) {

	}

	@FXML
	void actionBtnDrogueriasPorMedicamento(ActionEvent event) {

	}

	@FXML
	void actionBtnEmpleados(ActionEvent event) {

	}

	@FXML
	void actionBtnEmpleadosPorCargo(ActionEvent event) {

	}

	@FXML
	void actionBtnEquiposPorAmbulancia(ActionEvent event) {

	}

	@FXML
	void actionBtnMedicamentosPorDrogueria(ActionEvent event) {

	}

	@FXML
	void actionBtnMedicosPorEspecialidad(ActionEvent event) {

	}

	@FXML
	void actionBtnPacientes(ActionEvent event) {

	}

	@FXML
	void actionBtnPacientesPorAfiliacion(ActionEvent event) {

	}

	@FXML
	void actionBtnPacientesPorTipoSangre(ActionEvent event) {

	}

	@FXML
	void actionBtnPersonalMedico(ActionEvent event) {

	}

	@FXML
	void actionBtnVolver(ActionEvent event) {
		
		try {
			controlador.ventanaAdministrador();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
