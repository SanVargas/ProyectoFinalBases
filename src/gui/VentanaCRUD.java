package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.alertas.Alerta;
import modelo.entidad.Empleado;
import modelo.entidad.Eps;
import modelo.entidad.Medico;
import modelo.entidad.Paciente;

public class VentanaCRUD implements Initializable {
	@FXML
	Stage stage;
	Controlador controlador;

// PACIENTE
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
	private ComboBox<String> cmbDescTelefonoPaciente;
	@FXML
	private TextField txtTelefonoPaciente;
	@FXML
	private Button btnAgregarTelefonoPaciente;
	
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
		String numero = txtTelefonoPaciente.getText();
		String descripcion = cmbDescTelefonoPaciente.getValue();

		try {
			estatura = Double.parseDouble(txtPesoPaciente.getText());
			peso = Double.parseDouble(txtEstaturaPaciente.getText());
		} catch (Exception e) {
			Alerta.mostrarAlerta("Error", "Alerta", "Revise la informacion.", AlertType.ERROR);
		}

		String eps = cmbSeleccionarEPS.getValue();

		Paciente p = controlador.principal.getControladorPaciente().insertarPaciente(nombre, dni, direccion, estatura,
				peso, grupoS, rh, eps, numero, descripcion);

		if (p != null) {
			columnaNombrePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nombre"));
			columnaDNIPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("dni"));
			columnaDireccionPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("direccion"));

			tablaPaciente.getItems().add(p);
		}

		txtNombrePaciente.setEditable(false);
		txtDireccionPaciente.setEditable(false);
		txtDNIPaciente.setEditable(false);
		txtPesoPaciente.setEditable(false);
		txtEstaturaPaciente.setEditable(false);
		cmbGrupoSnaguineo.setDisable(true);
		cmbRH.setDisable(true);
		btnAgregarPaciente.setDisable(true);
		cmbSeleccionarEPS.setDisable(true);
		btnAgregarTelefonoPaciente.setDisable(false);
		txtTelefonoPaciente.setText("");

	}

	@FXML
	void actionEliminarPaciente(ActionEvent event) {

		controlador.principal.getControladorPaciente().eliminarPaciente(txtDNIPaciente.getText());
		actionLimpiarVenPaciente(event);
		mostrarPaciente();

		btnEliminarEps.setDisable(true);
	}

	@FXML
	void actionModificarPaciente(ActionEvent event) {

		txtDNIPaciente.setEditable(false);
		String nombre = txtNombrePaciente.getText();
		String dni = txtDNIPaciente.getText();
		String direccion = txtDireccionPaciente.getText();
		double peso = Double.parseDouble(txtPesoPaciente.getText());
		double estatura = Double.parseDouble(txtEstaturaPaciente.getText());
		String eps = cmbSeleccionarEPS.getValue();

		String grupoS = cmbGrupoSnaguineo.getValue();
		String rh = cmbRH.getValue();

		controlador.principal.getControladorPaciente().modificarPaciente(dni, nombre, direccion, peso, estatura, rh,
				grupoS, eps);

		actionLimpiarVenPaciente(event);
		mostrarPaciente();

	}

	@FXML
	void actionBuscarPaciente(ActionEvent event) {

		if (!txtBuscarPaciente.getText().trim().isEmpty()) {
			Paciente p = controlador.principal.getControladorPaciente().buscarPaciente(txtBuscarPaciente.getText());
			ObservableList<Paciente> items1 = FXCollections.observableArrayList();
			tablaPaciente.setItems(items1);

			cmbSeleccionarEPS.setItems(controlador.principal.getControladorPaciente().verEPS());
			cmbSeleccionarEPS.getSelectionModel().select(0);

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
				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Busqueda no encontrada.", AlertType.CONFIRMATION);

				if (radioBtnAgregarPaciente.isSelected()) {
					btnAgregarPaciente.setDisable(false);
					txtNombrePaciente.setEditable(true);
					txtDireccionPaciente.setEditable(true);
					txtDNIPaciente.setEditable(false);
					txtPesoPaciente.setEditable(true);
					txtEstaturaPaciente.setEditable(true);
					cmbGrupoSnaguineo.setDisable(false);
					cmbRH.setDisable(false);
					cmbSeleccionarEPS.setDisable(false);
					txtTelefonoPaciente.setDisable(false);
					btnAgregarTelefonoPaciente.setDisable(true);
					cmbDescTelefonoPaciente.setDisable(false);
				}

				txtDNIPaciente.setText(txtBuscarPaciente.getText());
				txtBuscarPaciente.setText("");
				txtNombrePaciente.setText("");
				txtDireccionPaciente.setText("");
				txtPesoPaciente.setText("");
				txtEstaturaPaciente.setText("");
				radioBtnAgregarPaciente.setDisable(false);

				radioBtnAgregarPaciente.setFocusTraversable(false);
				btnLimpiarVenPaciente.setDisable(false);
				radioBtnEliminarPaciente.setDisable(true);
				radioBtnModifcarPaciente.setDisable(true);
				btnModificarPaciente.setDisable(true);
				btnEliminarPaciente.setDisable(true);
				mostrarPaciente();

			}

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un valor valido.", AlertType.ERROR);
		}

	}

	@FXML
	void actionRadioBtnAgregarPaciente(ActionEvent event) {

		btnAgregarPaciente.setDisable(false);
		txtNombrePaciente.setEditable(true);
		txtDireccionPaciente.setEditable(true);
		txtDNIPaciente.setEditable(false);
		txtPesoPaciente.setEditable(true);
		txtEstaturaPaciente.setEditable(true);
		cmbGrupoSnaguineo.setDisable(false);
		cmbRH.setDisable(false);
		cmbSeleccionarEPS.setDisable(false);
		txtTelefonoPaciente.setDisable(false);
		btnAgregarTelefonoPaciente.setDisable(true);
		cmbDescTelefonoPaciente.setDisable(false);

	}

	@FXML
	void actionRadioBtnEliminarPaciente(ActionEvent event) {

		btnEliminarPaciente.setDisable(false);
		btnModificarPaciente.setDisable(true);
		btnAgregarPaciente.setDisable(true);
		txtTelefonoPaciente.setDisable(true);
		btnAgregarTelefonoPaciente.setDisable(true);
		cmbDescTelefonoPaciente.setDisable(true);

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

		cmbSeleccionarEPS.setItems(controlador.principal.getControladorPaciente().verEPS());
		cmbSeleccionarEPS.getSelectionModel().select(0);

		txtTelefonoPaciente.setDisable(false);
		btnAgregarTelefonoPaciente.setDisable(false);
		cmbDescTelefonoPaciente.setDisable(false);

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

		ObservableList<Paciente> items1 = FXCollections.observableArrayList();
		tablaPaciente.setItems(items1);

		ArrayList<Paciente> lstPaciente = controlador.principal.getControladorPaciente().mostrarDatos();

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
		txtTelefonoPaciente.setText("");

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

			if (radioBtnModifcarPaciente.isSelected()) {
				txtNombrePaciente.setEditable(true);
				txtDireccionPaciente.setEditable(true);
				txtDNIPaciente.setEditable(false);
				txtPesoPaciente.setEditable(true);
				txtEstaturaPaciente.setEditable(true);
				cmbGrupoSnaguineo.setDisable(false);
				cmbRH.setDisable(false);
				cmbSeleccionarEPS.setDisable(false);
				txtTelefonoPaciente.setDisable(false);
				cmbDescTelefonoPaciente.setDisable(false);
			} else {
				txtNombrePaciente.setEditable(false);
				txtDNIPaciente.setEditable(false);
				txtDireccionPaciente.setEditable(false);
				txtPesoPaciente.setEditable(false);
				txtEstaturaPaciente.setEditable(false);
				txtPesoPaciente.setEditable(false);
				cmbGrupoSnaguineo.setDisable(true);
				cmbRH.setDisable(true);
			}

			txtNombrePaciente.setText(paciente.getNombre());
			txtDNIPaciente.setText(paciente.getDni());
			txtDireccionPaciente.setText(paciente.getDireccion());
			txtPesoPaciente.setText(paciente.getHistoriaClinica().getPeso() + "");
			txtEstaturaPaciente.setText(paciente.getHistoriaClinica().getEstatura() + "");
			txtPesoPaciente.setText(paciente.getHistoriaClinica().getPeso() + "");
			cmbGrupoSnaguineo.getSelectionModel().select(0);
			cmbRH.getSelectionModel().select(0);

			btnLimpiarVenPaciente.setDisable(false);
			radioBtnEliminarPaciente.setDisable(false);
			radioBtnModifcarPaciente.setDisable(false);
			btnAgregarPaciente.setDisable(true);
			radioBtnAgregarPaciente.setDisable(true);

			txtTelefonoPaciente.setDisable(false);
			btnAgregarTelefonoPaciente.setDisable(false);
			cmbDescTelefonoPaciente.setDisable(false);

		}
	}

	@FXML
	void actionAgregarTelefonopaciente(ActionEvent event) {
		if (!txtDNIPaciente.getText().trim().isEmpty()) {

			String numero = txtTelefonoPaciente.getText();
			String descripcion = cmbDescTelefonoPaciente.getValue();
			String dni = txtDNIPaciente.getText();

			controlador.principal.getControladorPaciente().agregarTelefono(numero, descripcion, dni);

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un numero de telefono valido.", AlertType.ERROR);
		}

		txtTelefonoPaciente.setText("");

	}

	// FIN PACIENTE
	// ---------------
	// EPS
	@FXML
	private Button btnAgregarEps;
	@FXML
	private Button btnEliminarEps;
	@FXML
	private RadioButton radioBtnAgregarEps;
	@FXML
	private TextField txtBuscarEps;
	@FXML
	private Button btnModificarEps;
	@FXML
	private Button btnBuscarEps;
	@FXML
	private TextField txtNombreEps;
	@FXML
	private TextField txtNitEps;
	@FXML
	private Button btnLimpiarVenEps;
	@FXML
	private TableView<Eps> tablaEps;
	@FXML
	private TableColumn<Eps, String> columnaNombreEps;
	@FXML
	private TableColumn<Eps, String> columnaNitEps;
	@FXML
	private RadioButton radioBtnModifcarEps;
	@FXML
	private RadioButton radioBtnEliminarEps;
	@FXML
	private ToggleGroup radioGroupOpcionEps;
	@FXML
	private ComboBox<String> cmbEspecialidad;

	@FXML
	void actionAgregarEps(ActionEvent event) {
		txtNitEps.setEditable(false);
		String nit = txtNitEps.getText();
		String nombre = txtNombreEps.getText();

		Eps ep = controlador.principal.getControladorEps().insertarEps(nit, nombre);

		if (ep != null) {
			columnaNombreEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nombre"));
			columnaNitEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nit"));

			tablaEps.getItems().add(ep);
		}
		txtNitEps.setText("");
		txtNombreEps.setText("");
		btnAgregarEps.setDisable(true);
	}

	@FXML
	void actionEliminarEps(ActionEvent event) {
		controlador.principal.getControladorEps().eliminarEps(txtNitEps.getText());
		actionLimpiarVenEps(event);
		mostrarEps();
	}

	@FXML
	void actionModificarEps(ActionEvent event) {
		txtNitEps.setEditable(false);
		String nit = txtNitEps.getText();
		String nombre = txtNombreEps.getText();

		controlador.principal.getControladorEps().modificarEps(nit, nombre);

		actionLimpiarVenEps(event);
		mostrarEps();
	}

	@FXML
	void actionBuscarEps(ActionEvent event) {

		if (!txtBuscarEps.getText().trim().isEmpty()) {

			Eps eps = controlador.principal.getControladorEps().buscarEpsNit(txtBuscarEps.getText());
			ObservableList<Eps> items1 = FXCollections.observableArrayList();
			tablaEps.setItems(items1);

			if (eps != null) {
				columnaNombreEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nombre"));
				columnaNitEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nit"));

				tablaEps.getItems().add(eps);
				radioBtnEliminarEps.setDisable(false);
				radioBtnModifcarEps.setDisable(false);
				btnModificarEps.setDisable(false);
				btnEliminarEps.setDisable(false);
				btnLimpiarVenEps.setDisable(false);
				btnAgregarEps.setDisable(true);
				radioBtnAgregarEps.setDisable(true);
			} else {
				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Busqueda no encontrada.", AlertType.CONFIRMATION);
				txtNitEps.setText(txtBuscarEps.getText());
				txtBuscarEps.setText("");

				radioBtnAgregarEps.setDisable(false);
				btnLimpiarVenEps.setDisable(false);
				radioBtnEliminarEps.setDisable(true);
				radioBtnModifcarEps.setDisable(true);
				btnModificarEps.setDisable(true);
				btnEliminarEps.setDisable(true);
				mostrarEps();
			}
		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un valor valido.", AlertType.ERROR);
		}

	}

	@FXML
	void actionRadioBtnAgregarEps(ActionEvent event) {
		btnAgregarEps.setDisable(false);
		txtNitEps.setEditable(false);
		txtNombreEps.setEditable(true);
	}

	@FXML
	void actionRadioBtnEliminarEps(ActionEvent event) {
		btnEliminarEps.setDisable(false);
		btnModificarEps.setDisable(true);
		btnAgregarEps.setDisable(true);

		txtNitEps.setEditable(false);
		txtNombreEps.setEditable(false);
	}

	@FXML
	void actionRadioBtnModifcarEps(ActionEvent event) {
		btnEliminarEps.setDisable(true);
		btnModificarEps.setDisable(false);
		btnAgregarEps.setDisable(true);

		txtNitEps.setEditable(false);
		txtNombreEps.setEditable(true);
	}

	@FXML
	void actionLimpiarVenEps(ActionEvent event) {
		txtBuscarEps.setText("");
		txtNitEps.setText("");
		txtNombreEps.setText("");

		ObservableList<Eps> items1 = FXCollections.observableArrayList();

		tablaEps.setItems(items1);

	}

	void mostrarEps() {
		ArrayList<Eps> lstEps = controlador.principal.getControladorEps().mostrarDatosEps();

		for (Eps eps : lstEps) {

			columnaNombreEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nombre"));
			columnaNitEps.setCellValueFactory(new PropertyValueFactory<Eps, String>("nit"));

			tablaEps.getItems().add(eps);
		}
	}

	private final ListChangeListener<Eps> selectorTablaEps = new ListChangeListener<Eps>() {

		@Override
		public void onChanged(ListChangeListener.Change<? extends Eps> c) {
			ponerEpsSeleccionado();
		}
	};

	public Eps getTablaEpsSeleccionada() {
		if (tablaEps != null) {
			List<Eps> tabla = tablaEps.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final Eps competicionSeleccionada = tabla.get(0);
				return competicionSeleccionada;
			}
		}
		return null;
	}

	private void ponerEpsSeleccionado() {
		final Eps eps = getTablaEpsSeleccionada();

		if (eps != null) {
			txtNitEps.setText(eps.getNit());
			txtNombreEps.setText(eps.getNombre());

			txtNitEps.setEditable(false);
			txtNombreEps.setEditable(false);

			btnLimpiarVenEps.setDisable(false);
			radioBtnEliminarEps.setDisable(false);
			radioBtnModifcarEps.setDisable(false);
		}
	}

	// FIN EPS
	// -------------------------------------------

	// MEDICO
	@FXML
	private TextField txtNombreMedico;
	@FXML
	private RadioButton radioBtnAgregarMedico;
	@FXML
	private TableColumn<Medico, String> columnaLicenciaMedico;
	@FXML
	private TextField txtBuscarMedico;
	@FXML
	private TableColumn<Medico, String> columnaNombreMedico;
	@FXML
	private RadioButton radioBtnModifcarMedico;

	@FXML
	private TextField txtLicenciaMedico;
	@FXML
	private Button btnAgregarMedico;
	@FXML
	private Button btnEliminarMedico;
	@FXML
	private Button btnModificarMedico;
	@FXML
	private Button btnBuscarMedico;
	@FXML
	private TableView<Medico> tablaMedico;
	@FXML
	private Button btnAgregarEspecialidadMedico;
	@FXML
	private Button btnLimpiarVenMedico;
	@FXML
	private RadioButton radioBtnEliminarMedico;
	@FXML
	private ToggleGroup radioGroupOpcionMedico;

	@FXML
	void actionAgregarMedico(ActionEvent event) {
		txtLicenciaMedico.setEditable(false);
		String licencia = txtLicenciaMedico.getText();
		String nombre = txtNombreMedico.getText();
		String especialidad = cmbEspecialidad.getValue();

		Medico m = controlador.principal.getControladorMedico().insertarMedico(licencia, nombre, especialidad);

		if (m != null) {
			columnaLicenciaMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("licencia"));
			columnaNombreMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombre"));

			tablaMedico.getItems().add(m);
		}
		txtLicenciaMedico.setText("");
		txtNombreMedico.setText("");
		btnAgregarMedico.setDisable(true);
		radioBtnAgregarMedico.setDisable(true);

		actionLimpiarVenMedico(event);
		mostrarMedico();

	}

	@FXML
	void actionEliminarMedico(ActionEvent event) {
		controlador.principal.getControladorMedico().eliminarMedico(txtLicenciaMedico.getText());

		actionLimpiarVenMedico(event);
		mostrarMedico();
	}

	@FXML
	void actionModificarMedico(ActionEvent event) {
		txtLicenciaMedico.setEditable(false);
		String licencia = txtLicenciaMedico.getText();
		String nombre = txtNombreMedico.getText();
		btnAgregarEspecialidadMedico.setDisable(false);

		controlador.principal.getControladorMedico().modificarMedico(licencia, nombre);

		actionLimpiarVenMedico(event);
		mostrarMedico();
	}

	@FXML
	void actionBuscarMedico(ActionEvent event) {

		if (!txtBuscarMedico.getText().trim().isEmpty()) {
			Medico m = controlador.principal.getControladorMedico().buscarMedico(txtBuscarMedico.getText());
			ObservableList<Medico> items1 = FXCollections.observableArrayList();
			tablaMedico.setItems(items1);

			cmbEspecialidad.setItems(controlador.principal.getControladorMedico().verEspecialidades());
			cmbEspecialidad.getSelectionModel().select(0);

			if (m != null) {
				columnaNombreMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombre"));
				columnaLicenciaMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("licencia"));

				tablaMedico.getItems().add(m);
				radioBtnEliminarMedico.setDisable(false);
				radioBtnModifcarMedico.setDisable(false);
				btnModificarMedico.setDisable(false);
				btnEliminarMedico.setDisable(false);
				btnLimpiarVenMedico.setDisable(false);
				btnAgregarMedico.setDisable(true);
				radioBtnAgregarMedico.setDisable(true);
			} else {
				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Busqueda no encontrada.", AlertType.CONFIRMATION);
				txtLicenciaMedico.setText(txtBuscarMedico.getText());
				txtBuscarMedico.setText("");

				radioBtnAgregarMedico.setDisable(false);
				btnLimpiarVenMedico.setDisable(false);
				radioBtnEliminarMedico.setDisable(true);
				radioBtnModifcarMedico.setDisable(true);
				btnModificarMedico.setDisable(true);
				btnEliminarMedico.setDisable(true);
				mostrarMedico();

				if (radioBtnAgregarMedico.isSelected()) {
					btnAgregarMedico.setDisable(false);
					txtLicenciaMedico.setEditable(false);
					txtNombreMedico.setEditable(true);
				}

			}

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un valor valido.", AlertType.ERROR);
		}

	}

	@FXML
	void actionRadioBtnAgregarMedico(ActionEvent event) {
		btnAgregarMedico.setDisable(false);
		cmbEspecialidad.setDisable(false);
		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(true);
		btnAgregarEspecialidadMedico.setDisable(true);
	}

	@FXML
	void actionRadioBtnEliminarMedico(ActionEvent event) {

		btnEliminarMedico.setDisable(false);
		btnModificarMedico.setDisable(true);
		btnAgregarMedico.setDisable(true);
		btnAgregarEspecialidadMedico.setDisable(true);
		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(false);

	}

	@FXML
	void actionRadioBtnModifcarMedico(ActionEvent event) {

		btnEliminarMedico.setDisable(true);
		btnModificarMedico.setDisable(false);
		btnAgregarMedico.setDisable(true);
		btnAgregarEspecialidadMedico.setDisable(false);
		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(true);
	}

	@FXML
	void actionLimpiarVenMedico(ActionEvent event) {

		txtBuscarMedico.setText("");
		txtLicenciaMedico.setText("");
		txtNombreMedico.setText("");

		ObservableList<Medico> items1 = FXCollections.observableArrayList();

		tablaMedico.setItems(items1);
	}

	@FXML
	void actionAgregarEspecialidadMedico(ActionEvent event) {

		String especialidad = cmbEspecialidad.getValue();
		String licencia = txtLicenciaMedico.getText();

		controlador.principal.getControladorMedico().insertarOtraEspecialidad(licencia, especialidad);
	}

	void mostrarMedico() {

		ArrayList<Medico> lstMedico = controlador.principal.getControladorMedico().mostrarDatosMedico();

		for (Medico m : lstMedico) {
			columnaLicenciaMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("licencia"));
			columnaNombreMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombre"));

			tablaMedico.getItems().add(m);
		}
	}

	private final ListChangeListener<Medico> selectorTablaMedico = new ListChangeListener<Medico>() {

		@Override
		public void onChanged(ListChangeListener.Change<? extends Medico> c) {
			ponerMedicoSeleccionado();
		}
	};

	public Medico getTablaMedicoSeleccionada() {
		if (tablaMedico != null) {
			List<Medico> tabla = tablaMedico.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final Medico competicionSeleccionada = tabla.get(0);
				return competicionSeleccionada;
			}
		}
		return null;
	}

	private void ponerMedicoSeleccionado() {
		final Medico m = getTablaMedicoSeleccionada();

		if (m != null) {
			txtLicenciaMedico.setText(m.getLicencia());
			txtNombreMedico.setText(m.getNombre());

			if (radioBtnModifcarMedico.isSelected()) {
				txtLicenciaMedico.setEditable(false);
				txtNombreMedico.setEditable(true);
			} else {
				txtLicenciaMedico.setEditable(false);
				txtNombreMedico.setEditable(false);
			}

			btnLimpiarVenMedico.setDisable(false);
			radioBtnEliminarMedico.setDisable(false);
			radioBtnModifcarMedico.setDisable(false);
		}
	}

	// FIN MEDICO
	// -----------------------------------
	// EMPLEADO
	@FXML
	private Button btnBuscarEmpleado;

	@FXML
	private TextField txtNombreEmpleado;

	@FXML
	private Button btnAgregarEmpleado;

	@FXML
	private Button btnLimpiarEmpleado;

	@FXML
	private TableView<Empleado> tablaEmpleado;

	@FXML
	private RadioButton radioBtnModifcarEmpleado;

	@FXML
	private Button btnAgregarTelefonoEmpleado;

	@FXML
	private TextField txtMostrarDescripcion;

	@FXML
	private ComboBox<String> cmbTelefonoEmpleado;

	@FXML
	private TextField txtTelefonoEmpleado;

	@FXML
	private TextField txtMostrarSalario;

	@FXML
	private TableColumn<Empleado, String> columnaCargoEmpleado;

	@FXML
	private ComboBox<String> cmbCargoEmpleado;

	@FXML
	private ToggleGroup radioGroupOpcionEmpleado;

	@FXML
	private Button btnEliminarEmpleado;

	@FXML
	private TextField txtBuscarEmpleado;

	@FXML
	private TableColumn<Empleado, String> columnaIdEmpleado;

	@FXML
	private RadioButton radioBtnAgregarEmpleado;

	@FXML
	private Button btnModificarEmpleado;

	@FXML
	private TextField txtLicenciaEmpleado;

	@FXML
	private TableColumn<Empleado, String> columnaNombreEmpleado;

	@FXML
	private RadioButton radioBtnEliminarEmpleado;

	@FXML
	private TextField txtIdEmpleado;

	@FXML
	void actionAgregarEmpleado(ActionEvent event) {

		txtDNIPaciente.setEditable(false);
		String nombre = txtNombreEmpleado.getText();
		String id = txtIdEmpleado.getText();
		String licencia = txtLicenciaEmpleado.getText();
		String cargo = cmbCargoEmpleado.getValue();
		String telefono = txtTelefonoEmpleado.getText();
		String descripcion = cmbTelefonoEmpleado.getValue();

		Empleado emp = controlador.principal.getControladorEmpleado().insertarEmpleado(nombre, id, licencia, cargo,
				telefono, descripcion);

		if (emp != null) {
			columnaNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
			columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dni"));
			columnaCargoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("licencia"));

			tablaEmpleado.getItems().add(emp);

			txtNombreEmpleado.setText("");
			txtIdEmpleado.setText("");
			txtLicenciaEmpleado.setText("");
			txtTelefonoEmpleado.setText("");

		}

		txtNombreEmpleado.setEditable(false);
		txtIdEmpleado.setEditable(false);
		txtLicenciaEmpleado.setEditable(false);
		txtTelefonoEmpleado.setEditable(false);
		cmbCargoEmpleado.setDisable(true);
		cmbDescTelefonoPaciente.setDisable(true);
		btnAgregarEmpleado.setDisable(true);
		btnAgregarTelefonoEmpleado.setDisable(false);

		mostrarEmpleado();
	}

	@FXML
	void actionEliminarEmpleado(ActionEvent event) {

		controlador.principal.getControladorEmpleado().eliminarPaciente(txtIdEmpleado.getText());

		actionLimpiarVenEmpleado(event);
		mostrarEmpleado();

	}

	@FXML
	void actionModificarEmpleado(ActionEvent event) {

		txtIdEmpleado.setEditable(false);
		txtLicenciaEmpleado.setEditable(false);
		String id = txtIdEmpleado.getText();
		String nombre = txtNombreEmpleado.getText();
		String cargo = cmbCargoEmpleado.getValue();

		controlador.principal.getControladorEmpleado().modificarEmpleado(nombre, id, cargo);

		actionLimpiarVenEmpleado(event);
		mostrarEmpleado();
	}

	@FXML
	void actionBuscarEmpleado(ActionEvent event) {

		if (!txtBuscarEmpleado.getText().trim().isEmpty()) {
			Empleado emp = controlador.principal.getControladorEmpleado().buscarEmpleado(txtBuscarEmpleado.getText());
			ObservableList<Empleado> items1 = FXCollections.observableArrayList();
			tablaEmpleado.setItems(items1);
			btnAgregarTelefonoEmpleado.setDisable(true);

			cmbCargoEmpleado.setItems(controlador.principal.getControladorEmpleado().verCargo());
			cmbCargoEmpleado.getSelectionModel().select(0);

			if (emp != null) {

				columnaNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
				columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dni"));
				columnaCargoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("licencia"));

				tablaEmpleado.getItems().add(emp);

				radioBtnEliminarEmpleado.setDisable(false);
				radioBtnModifcarEmpleado.setDisable(false);
				btnModificarEmpleado.setDisable(false);
				btnEliminarEmpleado.setDisable(false);
				btnLimpiarEmpleado.setDisable(false);
				btnAgregarEmpleado.setDisable(true);
				radioBtnAgregarEmpleado.setDisable(true);
			} else {
				Alerta.mostrarAlerta("Confirmacion", "Alerta", "Busqueda no encontrada.", AlertType.CONFIRMATION);
				txtIdEmpleado.setText(txtBuscarEmpleado.getText());
				txtBuscarEmpleado.setText("");

				radioBtnAgregarEmpleado.setDisable(false);
				btnLimpiarEmpleado.setDisable(false);
				radioBtnEliminarEmpleado.setDisable(true);
				radioBtnModifcarEmpleado.setDisable(true);
				btnModificarEmpleado.setDisable(true);
				btnEliminarEmpleado.setDisable(true);
				mostrarEmpleado();
				btnAgregarEmpleado.setDisable(true);
				txtLicenciaEmpleado.setEditable(false);
				txtNombreEmpleado.setEditable(false);
				txtIdEmpleado.setEditable(false);
				cmbCargoEmpleado.setDisable(true);

				if (radioBtnAgregarEmpleado.isSelected()) {
					btnAgregarEmpleado.setDisable(false);
					txtLicenciaEmpleado.setEditable(true);
					txtNombreEmpleado.setEditable(true);
					txtTelefonoEmpleado.setEditable(true);
					cmbCargoEmpleado.setDisable(false);

				}

			}
			mostrarEmpleado();

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un valor valido.", AlertType.ERROR);
		}

	}

	@FXML
	void actionRadioBtnAgregarEmpleado(ActionEvent event) {
		btnAgregarEmpleado.setDisable(false);
		cmbCargoEmpleado.setDisable(false);
		txtLicenciaEmpleado.setEditable(true);
		txtNombreEmpleado.setEditable(true);
		txtIdEmpleado.setEditable(false);
		txtTelefonoEmpleado.setEditable(true);
		btnAgregarTelefonoEmpleado.setDisable(true);
		cmbTelefonoEmpleado.setDisable(false);
	}

	@FXML
	void actionRadioBtnEliminarEmpleado(ActionEvent event) {
		btnEliminarEmpleado.setDisable(false);
		btnModificarEmpleado.setDisable(true);
		btnAgregarEmpleado.setDisable(true);
		txtLicenciaEmpleado.setEditable(false);
		txtIdEmpleado.setEditable(false);
		txtTelefonoEmpleado.setEditable(false);
		txtNombreEmpleado.setEditable(false);
		btnAgregarTelefonoEmpleado.setDisable(true);

	}

	@FXML
	void actionRadioBtnModifcarEmpleado(ActionEvent event) {

		btnEliminarEmpleado.setDisable(true);
		btnModificarEmpleado.setDisable(false);
		btnAgregarEmpleado.setDisable(true);
		btnAgregarTelefonoEmpleado.setDisable(false);
		txtLicenciaEmpleado.setEditable(false);
		txtNombreEmpleado.setEditable(true);
		txtIdEmpleado.setEditable(false);
		txtTelefonoEmpleado.setEditable(true);
	}

	@FXML
	void actionLimpiarVenEmpleado(ActionEvent event) {

		txtBuscarEmpleado.setText("");
		txtNombreEmpleado.setText("");
		txtIdEmpleado.setText("");
		txtLicenciaEmpleado.setText("");
		txtTelefonoEmpleado.setText("");
		txtMostrarDescripcion.setText("");
		txtMostrarSalario.setText("");

		ObservableList<Empleado> items1 = FXCollections.observableArrayList();

		tablaEmpleado.setItems(items1);
	}

	@FXML
	void actionAgregarTelefonoEmpleado(ActionEvent event) {

		if (!txtTelefonoEmpleado.getText().trim().isEmpty() && !txtIdEmpleado.getText().trim().isEmpty()) {

			String numero = txtTelefonoEmpleado.getText();
			String descripcion = cmbTelefonoEmpleado.getValue();
			String id = txtIdEmpleado.getText();

			controlador.principal.getControladorEmpleado().agregarTelefonoEmpleado(numero, descripcion, id);

		} else {
			Alerta.mostrarAlerta("Error", "Alerta", "Ingrese un numero de telefono valido.", AlertType.ERROR);
		}

		txtTelefonoEmpleado.setText("");

		mostrarEmpleado();

	}

	void mostrarEmpleado() {
		ObservableList<Empleado> items1 = FXCollections.observableArrayList();

		tablaEmpleado.setItems(items1);

		ArrayList<Empleado> lstEmpleado = controlador.principal.getControladorEmpleado().mostrarDatos();

		for (Empleado emp : lstEmpleado) {
			columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("id"));
			columnaNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
			columnaCargoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("licencia"));

			tablaEmpleado.getItems().add(emp);
		}
	}

	private final ListChangeListener<Empleado> selectorTablaEmpleado = new ListChangeListener<Empleado>() {

		@Override
		public void onChanged(ListChangeListener.Change<? extends Empleado> c) {
			ponerEmpleadoSeleccionado();
		}
	};

	public Empleado getTablaEmpleadoSeleccionada() {
		if (tablaEmpleado != null) {
			List<Empleado> tabla = tablaEmpleado.getSelectionModel().getSelectedItems();
			if (tabla.size() == 1) {
				final Empleado competicionSeleccionada = tabla.get(0);
				return competicionSeleccionada;
			}
		}
		return null;
	}

	private void ponerEmpleadoSeleccionado() {
		cmbCargoEmpleado.setItems(controlador.principal.getControladorEmpleado().verCargo());
		cmbCargoEmpleado.getSelectionModel().select(0);

		final Empleado paciente = getTablaEmpleadoSeleccionada();

		if (paciente != null) {

			if (radioBtnModifcarEmpleado.isSelected()) {

				btnEliminarEmpleado.setDisable(true);
				btnModificarEmpleado.setDisable(false);
				btnAgregarEmpleado.setDisable(true);
				txtLicenciaEmpleado.setEditable(false);
				txtNombreEmpleado.setEditable(true);
				txtIdEmpleado.setEditable(false);
				txtTelefonoEmpleado.setEditable(true);
			} else {

				txtNombreEmpleado.setEditable(false);
				cmbCargoEmpleado.setDisable(true);
				cmbDescTelefonoPaciente.setDisable(true);
				txtTelefonoEmpleado.setEditable(false);
				cmbDescTelefonoPaciente.setDisable(true);
			}

			txtNombreEmpleado.setText(paciente.getNombre());
			txtIdEmpleado.setText(paciente.getId());
			txtLicenciaEmpleado.setText(paciente.getLicencia());
			txtMostrarSalario.setText(paciente.getCargo().getSalario() + "");
			txtMostrarDescripcion.setText(paciente.getCargo().getDescripcion());
			cmbCargoEmpleado.setDisable(false);

			if (paciente.getCargo().getNombre().equals("Cajero")) {
				cmbCargoEmpleado.getSelectionModel().select(0);
			} else if (paciente.getCargo().getNombre().equals("Farmaceuta")) {
				cmbCargoEmpleado.getSelectionModel().select(1);
			} else if (paciente.getCargo().getNombre().equals("Enfermero")) {
				cmbCargoEmpleado.getSelectionModel().select(2);
			} else if (paciente.getCargo().getNombre().equals("Oficinista")) {
				cmbCargoEmpleado.getSelectionModel().select(3);
			} else if (paciente.getCargo().getNombre().equals("Paramedico")) {
				cmbCargoEmpleado.getSelectionModel().select(4);
			} else {
				cmbCargoEmpleado.getSelectionModel().select(5);
			}

			btnLimpiarEmpleado.setDisable(false);
			radioBtnEliminarEmpleado.setDisable(false);
			radioBtnModifcarEmpleado.setDisable(false);
			btnAgregarEmpleado.setDisable(true);
			radioBtnAgregarEmpleado.setDisable(true);

			txtTelefonoEmpleado.setDisable(false);
			btnAgregarTelefonoEmpleado.setDisable(false);
			cmbTelefonoEmpleado.setDisable(false);
			cmbCargoEmpleado.setDisable(false);

		}
	}

	// FIN EMPLEADO
//-------------------
	// CITA
	@FXML
	private Button btnAgregarCita;

	@FXML
	private DatePicker calendarCita;

	@FXML
	private TableView<?> tablaCita;

	@FXML
	private RadioButton radioBtnModifcarCita;

	@FXML
	private RadioButton radioBtnAgregarCita;

	@FXML
	private Button btnModificarCita;

	@FXML
	private TableColumn<?, ?> columnaIdCita;

	@FXML
	private TextField txtIdCita;

	@FXML
	private TableColumn<?, ?> columnaMedicoCita;

	@FXML
	private Button btnEliminarCita;

	@FXML
	private TextField txtBuscarCita;

	@FXML
	private RadioButton radioBtnEliminarCita;

	@FXML
	private Button btnLimpiarCita;

	@FXML
	private Button btnFiltrarEspecialidadCita;

	@FXML
	private ComboBox<?> cmbEspecialidadCita;

	@FXML
	private ToggleGroup radioGroupOpcionCita;

	@FXML
	private TableView<?> tablaMedicoCita;

	@FXML
	private TableColumn<?, ?> columnaFechaCita;

	@FXML
	private TableColumn<?, ?> columnaLicenciaCita;

	@FXML
	private Button btnBuscarCita;

	@FXML
	private TextField txtPacienteCita;

	@FXML
	void actionAgregarCita(ActionEvent event) {

	}

	@FXML
	void actionEliminarCita(ActionEvent event) {

	}

	@FXML
	void actionModificarCita(ActionEvent event) {

	}

	@FXML
	void actionBuscarCita(ActionEvent event) {

	}

	@FXML
	void actionRadioBtnAgregarCita(ActionEvent event) {

	}

	@FXML
	void actionRadioBtnEliminarCita(ActionEvent event) {

	}

	@FXML
	void actionRadioBtnModifcarCita(ActionEvent event) {

	}

	@FXML
	void actionLimpiarVenCita(ActionEvent event) {

	}

	@FXML
	void actionFiltrarEspecialidadCita(ActionEvent event) {

	}

	// FIN CITA
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// PACIENTE
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
		btnAgregarPaciente.setDisable(true);
		txtTelefonoPaciente.setDisable(true);
		btnAgregarTelefonoPaciente.setDisable(true);
		cmbDescTelefonoPaciente.setDisable(true);

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

		ObservableList<String> items3 = FXCollections.observableArrayList();
		items3.add("Principal");
		items3.add("Movil");
		items3.add("Casa");
		items3.add("Trabajo");
		cmbDescTelefonoPaciente.setItems(items3);
		cmbDescTelefonoPaciente.getSelectionModel().select(0);
		cmbTelefonoEmpleado.setItems(items3);
		cmbTelefonoEmpleado.getSelectionModel().select(0);

		final ObservableList<Paciente> tablaPersonaSel = tablaPaciente.getSelectionModel().getSelectedItems();
		tablaPersonaSel.addListener(selectorTablaPaciente);

		// EPS
		txtNitEps.setEditable(false);
		txtNombreEps.setEditable(false);

		btnAgregarEps.setDisable(true);
		btnEliminarEps.setDisable(true);
		btnModificarEps.setDisable(true);
		radioBtnAgregarEps.setDisable(true);
		radioBtnEliminarEps.setDisable(true);
		radioBtnModifcarEps.setDisable(true);
		btnLimpiarVenEps.setDisable(true);

		final ObservableList<Eps> tablaEpsSel = tablaEps.getSelectionModel().getSelectedItems();
		tablaEpsSel.addListener(selectorTablaEps);

		// Medico
		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(false);

		btnAgregarMedico.setDisable(true);
		btnEliminarMedico.setDisable(true);
		btnModificarMedico.setDisable(true);
		radioBtnAgregarMedico.setDisable(true);
		radioBtnEliminarMedico.setDisable(true);
		radioBtnModifcarMedico.setDisable(true);
		btnLimpiarVenMedico.setDisable(true);
		btnAgregarEspecialidadMedico.setDisable(true);
		cmbEspecialidad.setDisable(true);

		// EMPLEADO
		txtNombreEmpleado.setEditable(false);
		txtIdEmpleado.setEditable(false);
		txtLicenciaEmpleado.setEditable(false);
		radioBtnAgregarEmpleado.setDisable(true);
		radioBtnEliminarEmpleado.setDisable(true);
		radioBtnModifcarEmpleado.setDisable(true);
		cmbTelefonoEmpleado.setDisable(true);
		txtTelefonoEmpleado.setEditable(false);
		btnAgregarEmpleado.setDisable(true);
		btnEliminarEmpleado.setDisable(true);
		btnModificarEmpleado.setDisable(true);
		btnLimpiarEmpleado.setDisable(true);
		btnAgregarTelefonoEmpleado.setDisable(true);

		final ObservableList<Empleado> tablaEmpleadoSel = tablaEmpleado.getSelectionModel().getSelectedItems();
		tablaEmpleadoSel.addListener(selectorTablaEmpleado);

		final ObservableList<Medico> tablaMedicoSel = tablaMedico.getSelectionModel().getSelectedItems();
		tablaMedicoSel.addListener(selectorTablaMedico);

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

}