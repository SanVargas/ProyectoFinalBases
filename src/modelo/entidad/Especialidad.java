package modelo.entidad;

import java.util.ArrayList;
/**
 * Clase encargada de controlar la entidad.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class Especialidad {
	private String id;
	private String nombre;
	private String descripcion;
	private ArrayList<Medico> lstMedico;

	public Especialidad(String id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;

		lstMedico = new ArrayList<Medico>();

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

	public ArrayList<Medico> getLstMedico() {
		return lstMedico;
	}

	public void setLstMedico(ArrayList<Medico> lstMedico) {
		this.lstMedico = lstMedico;
	}

}
