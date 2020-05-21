package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conector.ConectorBD;

public class Principal {

	ConectorBD cc = new ConectorBD();
	Connection con = cc.establecerConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	private ControladorPaciente controladorPaciente;
	private ControladorEps controladorEps;
	private ControladorCargo controladorCargo;
	private ControladorTipoSangre controladorTipoSangre;
	private ControladorMedico controladorMedico;

	public Principal() {
		controladorTipoSangre = new ControladorTipoSangre(cc, con, this);
		controladorTipoSangre.insertarGrupoSangre();
		controladorPaciente = new ControladorPaciente(cc, con, this);
		controladorEps = new ControladorEps(cc, con, this);
		controladorCargo = new ControladorCargo(cc, con, this);
		controladorMedico = new ControladorMedico(cc, con, this);
		controladorCargo.insertarCargo();
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
	
	

}
