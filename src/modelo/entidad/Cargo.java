package modelo.entidad;

import java.util.ArrayList;
/**
 * Clase encargada de controlar la entidad.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class Cargo {

	private String id;
	private String nombre;
	private String descripcion;
	private Double salario;
	ArrayList<Empleado> lstEmpleados;

	public Cargo(String id, String nombre, String descripcion, Double salario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.salario = salario;
		lstEmpleados = new ArrayList<Empleado>();
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Empleado> getLstEmpleados() {
		return lstEmpleados;
	}

	public void setLstEmpleados(ArrayList<Empleado> lstEmpleados) {
		this.lstEmpleados = lstEmpleados;
	}

}
