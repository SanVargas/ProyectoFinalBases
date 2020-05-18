package modelo.entidad;

import java.sql.Date;

public class RegistroAmbulancia {

	private Date fechaHora;
	private String numero;
	private Ambulancia ambulancia;
	private Paciente paciente;
	private Empleado empleado;

	public RegistroAmbulancia(Date fechaHora, String numero, Ambulancia ambulancia, Paciente paciente,
			Empleado empleado) {
		super();
		this.fechaHora = fechaHora;
		this.numero = numero;
		this.ambulancia = ambulancia;
		this.paciente = paciente;
		this.empleado = empleado;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
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
