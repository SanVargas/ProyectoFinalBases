package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conector.ConectorBD;
import modelo.controladoresEntidades.ControladorAdministrador;
import modelo.controladoresEntidades.ControladorAmbulancia;
import modelo.controladoresEntidades.ControladorCargo;
import modelo.controladoresEntidades.ControladorCita;
import modelo.controladoresEntidades.ControladorDrogueria;
import modelo.controladoresEntidades.ControladorEmpleado;
import modelo.controladoresEntidades.ControladorEps;
import modelo.controladoresEntidades.ControladorEspecialidad;
import modelo.controladoresEntidades.ControladorMedicamento;
import modelo.controladoresEntidades.ControladorMedico;
import modelo.controladoresEntidades.ControladorPaciente;
import modelo.controladoresEntidades.ControladorTipoSangre;

public class Principal {

	public ConectorBD cc = new ConectorBD();
	public Connection con = cc.establecerConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	private ControladorPaciente controladorPaciente;
	private ControladorEps controladorEps;
	private ControladorCargo controladorCargo;
	private ControladorTipoSangre controladorTipoSangre;
	private ControladorMedico controladorMedico;
	private ControladorEspecialidad controladorEspecialidad;
	private ControladorEmpleado controladorEmpleado;
	private ControladorCita controladorCita;
	private ControladorAdministrador controladorAdmin;
	private ControladorAmbulancia controladorAmbulancia;
	private ControladorDrogueria controladorDrogueria;
	private ControladorMedicamento controladorMedicamento;

	public Principal() {
		controladorTipoSangre = new ControladorTipoSangre(cc, con, this);
		controladorTipoSangre.insertarGrupoSangre();
		controladorPaciente = new ControladorPaciente(cc, con, this);
		controladorEps = new ControladorEps(cc, con, this);
		controladorCargo = new ControladorCargo(cc, con, this);
		controladorMedico = new ControladorMedico(cc, con, this);
		controladorCargo.insertarCargo();
		controladorEspecialidad = new ControladorEspecialidad(cc, con, this);
		controladorEspecialidad.insertarEspecialidad();
		controladorEmpleado = new ControladorEmpleado(cc, con, this);
		controladorCita = new ControladorCita(cc, con, this);
		controladorAdmin = new ControladorAdministrador(cc, con, this);
		controladorAdmin.insertarAdministradores();
		controladorAmbulancia = new ControladorAmbulancia(cc, con, this);
		controladorDrogueria = new ControladorDrogueria(cc, con, this);
		controladorMedicamento = new ControladorMedicamento(cc, con, this);
	}

	public ControladorDrogueria getControladorDrogueria() {
		return controladorDrogueria;
	}

	public void setControladorDrogueria(ControladorDrogueria controladorDrogueria) {
		this.controladorDrogueria = controladorDrogueria;
	}

	public ControladorMedicamento getControladorMedicamento() {
		return controladorMedicamento;
	}

	public void setControladorMedicamento(ControladorMedicamento controladorMedicamento) {
		this.controladorMedicamento = controladorMedicamento;
	}

	public ControladorAmbulancia getControladorAmbulancia() {
		return controladorAmbulancia;
	}

	public void setControladorAmbulancia(ControladorAmbulancia controladorAmbulancia) {
		this.controladorAmbulancia = controladorAmbulancia;
	}

	public ControladorEmpleado getControladorEmpleado() {
		return controladorEmpleado;
	}

	public void setControladorEmpleado(ControladorEmpleado controladorEmpleado) {
		this.controladorEmpleado = controladorEmpleado;
	}

	public ControladorPaciente getControladorPaciente() {
		return controladorPaciente;
	}

	public void setControladorPaciente(ControladorPaciente controladorPaciente) {
		this.controladorPaciente = controladorPaciente;
	}

	public ControladorEps getControladorEps() {
		return controladorEps;
	}

	public void setControladorEps(ControladorEps controladorEps) {
		this.controladorEps = controladorEps;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ControladorCargo getControladorCargo() {
		return controladorCargo;
	}

	public void setControladorCargo(ControladorCargo controladorCargo) {
		this.controladorCargo = controladorCargo;
	}

	public ControladorTipoSangre getControladorTipoSangre() {
		return controladorTipoSangre;
	}

	public void setControladorTipoSangre(ControladorTipoSangre controladorTipoSangre) {
		this.controladorTipoSangre = controladorTipoSangre;
	}

	public ControladorMedico getControladorMedico() {
		return controladorMedico;
	}

	public void setControladorMedico(ControladorMedico controladorMedico) {
		this.controladorMedico = controladorMedico;
	}

	public ControladorEspecialidad getControladorEspecialidad() {
		return controladorEspecialidad;
	}

	public void setControladorEspecialidad(ControladorEspecialidad controladorEspecialidad) {
		this.controladorEspecialidad = controladorEspecialidad;
	}

	public ControladorCita getControladorCita() {
		return controladorCita;
	}

	public void setControladorCita(ControladorCita controladorCita) {
		this.controladorCita = controladorCita;
	}

	public ControladorAdministrador getControladorAdmin() {
		return controladorAdmin;
	}

	public void setControladorAdmin(ControladorAdministrador contraladorAdmin) {
		this.controladorAdmin = contraladorAdmin;
	}

}
