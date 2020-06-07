package modelo.entidad;
/**
 * Clase encargada de controlar la entidad.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class Telefono_Empleado {
	private String numero;
	private String descripcion;
	private Empleado empleado;

	public Telefono_Empleado(String numero, String descripcion, Empleado empleado) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.empleado = empleado;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
