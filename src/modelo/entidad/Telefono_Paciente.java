package modelo.entidad;

public class Telefono_Paciente {
	private String numero;
	private String descripcion;
	private Paciente paciente;

	public Telefono_Paciente(String numero, String descripcion, Paciente paciente) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.paciente = paciente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
