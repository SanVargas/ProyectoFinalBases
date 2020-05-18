package modelo.entidad;

import java.util.ArrayList;

public class Drogueria {
	private String nombre;
	private String nit;
	private ArrayList<Medicamento> lstMedicamento;
	private ArrayList<Empleado> lstEmpleado;

	public Drogueria(String nombre, String nit) {
		super();
		this.nombre = nombre;
		this.nit = nit;

		lstEmpleado = new ArrayList<Empleado>();
		lstMedicamento = new ArrayList<Medicamento>();
	}

	public ArrayList<Medicamento> getLstMedicamento() {
		return lstMedicamento;
	}

	public void setLstMedicamento(ArrayList<Medicamento> lstMedicamento) {
		this.lstMedicamento = lstMedicamento;
	}

	public ArrayList<Empleado> getLstEmpleado() {
		return lstEmpleado;
	}

	public void setLstEmpleado(ArrayList<Empleado> lstEmpleado) {
		this.lstEmpleado = lstEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

}
