package modelo.entidad;

import java.util.ArrayList;

public class Eps {
	private String nit;
	private String nombre;
	private ArrayList<Paciente> lstPaciente;

	public Eps(String nit, String nombre) {
		super();
		this.nit = nit;
		this.nombre = nombre;

		lstPaciente = new ArrayList<Paciente>();
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Paciente> getLstPaciente() {
		return lstPaciente;
	}

	public void setLstPaciente(ArrayList<Paciente> lstPaciente) {
		this.lstPaciente = lstPaciente;
	}

}
