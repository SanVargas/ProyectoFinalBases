package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.entidad.Paciente;

public class VentanaCRUD implements Initializable {

	@FXML
	Stage stage;

	Controlador controlador;

	private int posicionPacienteEnTabla;

	ObservableList<Paciente> pacientes;

	@FXML
	private ComboBox<String> cmbGrupoSnaguineo;

	@FXML
	private Button btnBuscarPaciente;

	@FXML
	private Button btnAgregarPaciente;

	@FXML
	private TextField txtNombrePaciente;

	@FXML
	private ComboBox<String> cmbRH;

	@FXML
	private ComboBox<String> cmbSeleccionarEPS;

	@FXML
	private RadioButton radioBtnEliminarPaciente;

	@FXML
	private Button btnEliminarPaciente;

	@FXML
	private TextField txtDNIPaciente;

	@FXML
	private Button btnLimpiarVenPaciente;

	@FXML
	private TextField txtEstaturaPaciente;

	@FXML
	private Button btnModificarPaciente;

	@FXML
	private TextField txtDireccionPaciente;

	@FXML
	private TextField txtBuscarPaciente;

	@FXML
	private ToggleGroup radioGroupOpcionPaciente;

	@FXML
	private RadioButton radioBtnModifcarPaciente;

	@FXML
	private RadioButton radioBtnAgregarPaciente;

	@FXML
	private TextField txtPesoPaciente;

	@FXML
	private TableView<Paciente> tablaPaciente;

	@FXML
	private TableColumn<Paciente, String> columnaNombrePaciente;

	@FXML
	private TableColumn<Paciente, String> columnaDNIPaciente;

	@FXML
	private TableColumn<Paciente, String> columnaDireccionPaciente;

	@FXML
	void actionAgregarPaciente(ActionEvent event) {

		txtDNIPaciente.setEditable(false);
		String nombre = txtNombrePaciente.getText();
		String dni = txtDNIPaciente.getText();
		String direccion = txtDireccionPaciente.getText();
		String grupoS = cmbGrupoSnaguineo.getValue();
		String rh = cmbRH.getValue();
		double estatura = 0;
		double peso = 0;

		try {
			estatura = Double.parseDouble(txtEstaturaPaciente.getText());
			peso = Double.parseDouble(txtPesoPaciente.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Revise los valores ingresados. error: " + e.getMessage());
		}

		Paciente p = controlador.principal.insertarPaciente(nombre, dni, direccion, estatura, peso, grupoS, rh);

		if (p != null) {
			columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
			columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
			columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

			tablaPaciente.getItems().add(p);
		}

	}

	@FXML
	void actionEliminarPaciente(ActionEvent event) {

		controlador.principal.eliminarPaciente(txtDNIPaciente.getText());
		actionLimpiarVenPaciente(event);
		mostrarPaciente();
	}

	@FXML
	void actionModificarPaciente(ActionEvent event) {

		txtDNIPaciente.setEditable(false);
		String nombre = txtNombrePaciente.getText();
		String dni = txtDNIPaciente.getText();
		String direccion = txtDireccionPaciente.getText();
		double peso = Double.parseDouble(txtPesoPaciente.getText());
		double estatura = Double.parseDouble(txtEstaturaPaciente.getText());

		String grupoS = cmbGrupoSnaguineo.getValue();
		String rh = cmbRH.getValue();

		controlador.principal.modificarPaciente(dni, nombre, direccion, peso, estatura, rh, grupoS, null);
		
		actionLimpiarVenPaciente(event);
		mostrarPaciente();

	}

	@FXML
	void actionBuscarPaciente(ActionEvent event) {

		Paciente p = controlador.principal.buscarPaciente(txtBuscarPaciente.getText());
		ObservableList<Paciente> items1 = FXCollections.observableArrayList();
		tablaPaciente.setItems(items1);

		if (p != null) {
			columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
			columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
			columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

			tablaPaciente.getItems().add(p);
			radioBtnEliminarPaciente.setDisable(false);
			radioBtnModifcarPaciente.setDisable(false);
			btnModificarPaciente.setDisable(false);
			btnEliminarPaciente.setDisable(false);
			btnLimpiarVenPaciente.setDisable(false);
			btnAgregarPaciente.setDisable(true);
			radioBtnAgregarPaciente.setDisable(true);
		} else {
			JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
			txtDNIPaciente.setText(txtBuscarPaciente.getText());
			txtBuscarPaciente.setText("");
			txtNombrePaciente.setText("");
			txtDireccionPaciente.setText("");
			txtPesoPaciente.setText("");
			txtEstaturaPaciente.setText("");
			radioBtnAgregarPaciente.setDisable(false);
			btnLimpiarVenPaciente.setDisable(false);
			radioBtnEliminarPaciente.setDisable(true);
			radioBtnModifcarPaciente.setDisable(true);
			btnModificarPaciente.setDisable(true);
			btnEliminarPaciente.setDisable(true);
			mostrarPaciente();

		}

	}

	@FXML
	void actionRadioBtnAgregarPaciente(ActionEvent event) {

		btnAgregarPaciente.setDisable(false);

		txtNombrePaciente.setEditable(true);
		txtDireccionPaciente.setEditable(true);
		txtDNIPaciente.setEditable(true);
		txtPesoPaciente.setEditable(true);
		txtEstaturaPaciente.setEditable(true);
		cmbGrupoSnaguineo.setDisable(false);
		cmbRH.setDisable(false);
		cmbSeleccionarEPS.setDisable(false);

	}

	@FXML
	void actionRadioBtnEliminarPaciente(ActionEvent event) {

		btnEliminarPaciente.setDisable(false);
		btnModificarPaciente.setDisable(true);
		btnAgregarPaciente.setDisable(true);

		txtNombrePaciente.setEditable(false);
		txtDireccionPaciente.setEditable(false);
		txtDNIPaciente.setEditable(false);
		txtPesoPaciente.setEditable(false);
		txtEstaturaPaciente.setEditable(false);
		cmbGrupoSnaguineo.setDisable(true);
		cmbRH.setDisable(true);

	}

	@FXML
	void actionRadioBtnModifcarPaciente(ActionEvent event) {

		btnEliminarPaciente.setDisable(true);
		btnModificarPaciente.setDisable(false);
		btnAgregarPaciente.setDisable(true);

		txtNombrePaciente.setEditable(true);
		txtDireccionPaciente.setEditable(true);
		txtPesoPaciente.setEditable(true);
		txtEstaturaPaciente.setEditable(true);
		cmbGrupoSnaguineo.setDisable(false);
		cmbRH.setDisable(false);
		cmbSeleccionarEPS.setDisable(false);

	}

	void mostrarPaciente() {
		ArrayList<Paciente> lstPaciente = controlador.principal.mostrarDatos();

		for (Paciente paciente : lstPaciente) {

			columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
			columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
			columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

			tablaPaciente.getItems().add(paciente);
		}
	}

	@FXML
	void actionLimpiarVenPaciente(ActionEvent event) {
		txtBuscarPaciente.setText("");
		txtDireccionPaciente.setText("");
		txtDNIPaciente.setText("");
		txtEstaturaPaciente.setText("");
		txtNombrePaciente.setText("");
		txtPesoPaciente.setText("");

		ObservableList<Paciente> items1 = FXCollections.observableArrayList();

		tablaPaciente.setItems(items1);

	}

	private final ListChangeListener<Paciente> selectorTablaPaciente = new ListChangeListener<Paciente>() {

		@Override
		public void onChanged(ListChangeListener.Change<? extends Paciente> c) {
			ponerPacienteSeleccionado();
		}
	};

	public Paciente getTablaPacienteSeleccionada() {

		if (tablaPaciente != null) {
			List<Paciente> tabla = tablaPaciente.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final Paciente competicionSeleccionada = tabla.get(0);
				return competicionSeleccionada;
			}
		}

		return null;
	}

	private void ponerPacienteSeleccionado() {

		final Paciente paciente = getTablaPacienteSeleccionada();

		if (paciente != null) {

			txtNombrePaciente.setText(paciente.getNombre());
			txtDNIPaciente.setText(paciente.getDni());
			txtDireccionPaciente.setText(paciente.getDireccion());
			txtPesoPaciente.setText(paciente.getHistoriaClinica().getPeso() + "");
			txtEstaturaPaciente.setText(paciente.getHistoriaClinica().getEstatura() + "");
			txtPesoPaciente.setText(paciente.getHistoriaClinica().getPeso() + "");
			cmbGrupoSnaguineo.getSelectionModel().select(0);
			cmbRH.getSelectionModel().select(0);

			txtNombrePaciente.setEditable(false);
			txtDNIPaciente.setEditable(false);
			txtDireccionPaciente.setEditable(false);
			txtPesoPaciente.setEditable(false);
			txtEstaturaPaciente.setEditable(false);
			txtPesoPaciente.setEditable(false);
			cmbGrupoSnaguineo.setDisable(true);
			cmbRH.setDisable(true);

			btnLimpiarVenPaciente.setDisable(false);
			radioBtnEliminarPaciente.setDisable(false);
			radioBtnModifcarPaciente.setDisable(false);

		}
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
		txtPesoPaciente.setEditable(false);
		txtEstaturaPaciente.setEditable(false);
		cmbGrupoSnaguineo.setDisable(true);
		cmbRH.setDisable(true);
		btnAgregarPaciente.setDisable(true);
		btnEliminarPaciente.setDisable(true);
		btnModificarPaciente.setDisable(true);
		radioBtnAgregarPaciente.setDisable(true);
		radioBtnEliminarPaciente.setDisable(true);
		radioBtnModifcarPaciente.setDisable(true);
		cmbSeleccionarEPS.setDisable(true);
		btnLimpiarVenPaciente.setDisable(true);

		ObservableList<String> items1 = FXCollections.observableArrayList();

		items1.add("O");
		items1.add("A");
		items1.add("AB");
		items1.add("B");

		cmbGrupoSnaguineo.setItems(items1);

		cmbGrupoSnaguineo.getSelectionModel().select(0);

		ObservableList<String> items2 = FXCollections.observableArrayList();

		items2.add("-");
		items2.add("+");

		cmbRH.setItems(items2);

		cmbRH.getSelectionModel().select(0);

		final ObservableList<Paciente> tablaPersonaSel = tablaPaciente.getSelectionModel().getSelectedItems();
		tablaPersonaSel.addListener(selectorTablaPaciente);

	}

}