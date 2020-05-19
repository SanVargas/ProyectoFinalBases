package modelo.entidad;

import java.util.ArrayList;

public class Paciente {
	private String nombre;
	private String dni;
	private String direccion;
	private HistoriaClinica historiaClinica;
	private Eps esp;
	ArrayList<RegistroAmbulancia> lstRegistro;
	ArrayList<Cita> lstCita;
	ArrayList<Telefono_Paciente> lstTelefono;

	public Paciente(String nombre, String dni, String direccion, HistoriaClinica historiaClinica, Eps esp) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.historiaClinica = historiaClinica;
		this.esp = esp;

		lstRegistro = new ArrayList<RegistroAmbulancia>();
		lstCita = new ArrayList<Cita>();
		lstTelefono = new ArrayList<Telefono_Paciente>();

	}
	
	public Paciente(String nombre, String dni, String direccion) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;

		lstRegistro = new ArrayList<RegistroAmbulancia>();
		lstCita = new ArrayList<Cita>();
		lstTelefono = new ArrayList<Telefono_Paciente>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public Eps getEsp() {
		return esp;
	}

	public void setEsp(Eps esp) {
		this.esp = esp;
	}

	public ArrayList<RegistroAmbulancia> getLstRegistro() {
		return lstRegistro;
	}

	public void setLstRegistro(ArrayList<RegistroAmbulancia> lstRegistro) {
		this.lstRegistro = lstRegistro;
	}

	public ArrayList<Cita> getLstCita() {
		return lstCita;
	}

	public void setLstCita(ArrayList<Cita> lstCita) {
		this.lstCita = lstCita;
	}

	public ArrayList<Telefono_Paciente> getLstTelefono() {
		return lstTelefono;
	}

	public void setLstTelefono(ArrayList<Telefono_Paciente> lstTelefono) {
		this.lstTelefono = lstTelefono;
	}

}
