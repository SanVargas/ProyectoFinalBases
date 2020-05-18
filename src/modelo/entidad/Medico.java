package modelo.entidad;

import java.util.ArrayList;

public class Medico {
	private String nombre;
	private String licencia;
	private ArrayList<Especialidad> lstEspecialidad;

	public Medico(String nombre, String licencia) {
		super();
		this.nombre = nombre;
		this.licencia = licencia;

		lstEspecialidad = new ArrayList<Especialidad>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public ArrayList<Especialidad> getLstEspecialidad() {
		return lstEspecialidad;
	}

	public void setLstEspecialidad(ArrayList<Especialidad> lstEspecialidad) {
		this.lstEspecialidad = lstEspecialidad;
	}

}
