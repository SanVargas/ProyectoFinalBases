package modelo.entidad;

import java.sql.Date;

public class Cita {
	private Date fechaHora;
	private String id;
	private Medico medico;
	private Paciente paciente;
	private Empleado empleado;

	public Cita(Date fechaHora, String id, Medico medico, Paciente paciente, Empleado empleado) {
		super();
		this.fechaHora = fechaHora;
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.empleado = empleado;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
