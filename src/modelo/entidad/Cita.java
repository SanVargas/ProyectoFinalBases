package modelo.entidad;

import java.sql.Date;
/**
 * Clase encargada de controlar la entidad.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class Cita {
	private Date fechaHora;
	private String id;
	private Medico medico;
	private Paciente paciente;

	public Cita(Date fechaHora, String id, Medico medico, Paciente paciente) {
		super();
		this.fechaHora = fechaHora;
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
