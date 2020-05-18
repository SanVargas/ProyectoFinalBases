package modelo.entidad;

import java.util.ArrayList;

public class Caja {
	private int capacidad;
	private String nit;
	private ArrayList<Empleado> lstEmpleado;

	public Caja(int capacidad, String nit) {
		super();
		this.capacidad = capacidad;
		this.nit = nit;

		lstEmpleado = new ArrayList<Empleado>();

	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public ArrayList<Empleado> getLstEmpleado() {
		return lstEmpleado;
	}

	public void setLstEmpleado(ArrayList<Empleado> lstEmpleado) {
		this.lstEmpleado = lstEmpleado;
	}

}
