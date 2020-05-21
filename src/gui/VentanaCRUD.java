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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Alerta;
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

		if (!txtDNIPaciente.getText().isEmpty()) {

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
	private ComboBox<?> cmbEspecialidadMedico;
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

		Medico m = controlador.principal.getControladorMedico().insertarMedico(licencia, nombre);

		if (m != null) {
			columnaLicenciaMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("licencia"));
			columnaNombreMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombre"));

			tablaMedico.getItems().add(m);
		}
		txtLicenciaMedico.setText("");
		txtNombreMedico.setText("");
		btnAgregarMedico.setDisable(true);
		radioBtnAgregarMedico.setDisable(false);// ojo
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

		controlador.principal.getControladorMedico().modificarMedico(licencia, nombre);

		actionLimpiarVenMedico(event);
		mostrarMedico();
	}

	@FXML
	void actionBuscarMedico(ActionEvent event) {
		Medico m = controlador.principal.getControladorMedico().buscarMedico(txtBuscarMedico.getText());
		ObservableList<Medico> items1 = FXCollections.observableArrayList();
		tablaMedico.setItems(items1);

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
		}
	}

	@FXML
	void actionRadioBtnAgregarMedico(ActionEvent event) {
		btnAgregarMedico.setDisable(false);
		btnAgregarEspecialidadMedico.setDisable(false);
		
		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(true);
	}

	@FXML
	void actionRadioBtnEliminarMedico(ActionEvent event) {
		btnEliminarMedico.setDisable(false);
		btnModificarMedico.setDisable(true);
		btnAgregarMedico.setDisable(true);

		txtLicenciaMedico.setEditable(false);
		txtNombreMedico.setEditable(false);
	}

	@FXML
	void actionRadioBtnModifcarMedico(ActionEvent event) {
		btnEliminarMedico.setDisable(true);
		btnModificarMedico.setDisable(false);
		btnAgregarMedico.setDisable(true);

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
		Alerta.mostrarAlerta("Confirmacion", "Alerta", "Funcion en mantenimiento.", AlertType.CONFIRMATION);
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

			txtLicenciaMedico.setEditable(false);
			txtNombreMedico.setEditable(false);

			btnLimpiarVenMedico.setDisable(false);
			radioBtnEliminarMedico.setDisable(false);
			radioBtnModifcarMedico.setDisable(false);
		}
	}
	// FIN MEDICO

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
		//cmbEspecialidadMedico.setDisable(true);

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