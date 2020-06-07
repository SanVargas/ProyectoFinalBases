package gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.alertas.Alerta;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * Clase encargada de controlar la ventana Reporte.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
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

		try {

			String path = "src/reportes/Cita.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, null, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}
	}

	@FXML
	void actionBtnDiagnosticosPorPaciente(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("dni", txtDniPaciente.getText());
			String path = "src/reportes/DiagnosticosPorPaciente.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnDrogueriasPorMedicamento(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("medicamento", cmbMedicamento.getValue());
			String path = "src/reportes/DrogueriaPorMedicamento.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnEmpleados(ActionEvent event) {

		try {

			String path = "src/reportes/Empleado.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, null, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnEmpleadosPorCargo(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("cargo", cmbCargo.getValue());
			String path = "src/reportes/EmpleadoPorCargo.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnEquiposPorAmbulancia(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("placa", cmbPlaca.getValue());
			String path = "src/reportes/EquiposPorAmbulancia.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnMedicamentosPorDrogueria(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("drogueria", cmbDrogueria.getValue());
			String path = "src/reportes/MedicamentosPorDrogueria.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnMedicosPorEspecialidad(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("especialidad", cmbEspecialidad.getValue());
			String path = "src/reportes/MedicoPorEspecialidad.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnPacientes(ActionEvent event) {

		try {

			String path = "src/reportes/Paciente.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, null, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnPacientesPorAfiliacion(ActionEvent event) {

		try {

			Map<String, Object> parametro = new HashMap<String, Object>();
			parametro.put("ServicioSalud", cmbAfiliacion.getValue());
			String path = "src/reportes/PacientesPorEPS.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnPacientesPorTipoSangre(ActionEvent event) {

		try {

			Map<String, Object> parametro = controlador.principal.getControladorTipoSangre()
					.devolverMap(cmbTipoSangre.getValue());
			String path = "src/reportes/PacientePorTipoSangre.jasper";
			JasperPrint jprint = JasperFillManager.fillReport(path, parametro, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

	}

	@FXML
	void actionBtnPersonalMedico(ActionEvent event) {

		try {

			String path = "src/reportes/Medico.jasper";
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
			JasperPrint jprint = JasperFillManager.fillReport(path, null, controlador.principal.con);
			JasperViewer view = new JasperViewer(jprint, false);

			view.setVisible(true);

		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "No se encontraron datos, verifique.", AlertType.ERROR);
		}

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

	public void llenarComboBox() {

		cmbAfiliacion.setItems(controlador.principal.getControladorPaciente().verEPS());

		cmbPlaca.setItems(controlador.principal.getControladorAmbulancia().verAmbulancia());

		cmbDrogueria.setItems(controlador.principal.getControladorDrogueria().verDrogueria());

		cmbMedicamento.setItems(controlador.principal.getControladorMedicamento().verMedicamento());

		cmbEspecialidad.setItems(controlador.principal.getControladorMedico().verEspecialidades());

		cmbCargo.setItems(controlador.principal.getControladorEmpleado().verCargo());

		ObservableList<String> tSangre = FXCollections.observableArrayList();
		tSangre.add("O+");
		tSangre.add("O-");
		tSangre.add("A+");
		tSangre.add("A-");
		tSangre.add("B+");
		tSangre.add("B-");
		tSangre.add("AB+");
		tSangre.add("AB-");

		cmbTipoSangre.setItems(tSangre);

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

	}

}
