package modelo.entidad;

import java.util.ArrayList;

public class Empleado {

	private String id;
	private String nombre;
	private String licencia;
	private Cargo cargo;
	private ArrayList<Telefono_Empleado> lstTelefono;
	private ArrayList<Cita> lstCita;
	private ArrayList<RegistroAmbulancia> lstRegistroAmbulancia;
	private Drogueria drogueria;
	private Caja caja;

	public Empleado(String id, String nombre, String licencia, Cargo cargo, Drogueria drogueria, Caja caja) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.licencia = licencia;
		this.cargo = cargo;
		this.drogueria = drogueria;
		this.caja = caja;

		lstTelefono = new ArrayList<Telefono_Empleado>();
		lstCita = new ArrayList<Cita>();
		lstRegistroAmbulancia = new ArrayList<RegistroAmbulancia>();

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

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ArrayList<Telefono_Empleado> getLstTelefono() {
		return lstTelefono;
	}

	public void setLstTelefono(ArrayList<Telefono_Empleado> lstTelefono) {
		this.lstTelefono = lstTelefono;
	}

	public ArrayList<Cita> getLstCita() {
		return lstCita;
	}

	public void setLstCita(ArrayList<Cita> lstCita) {
		this.lstCita = lstCita;
	}

	public ArrayList<RegistroAmbulancia> getLstRegistroAmbulancia() {
		return lstRegistroAmbulancia;
	}

	public void setLstRegistroAmbulancia(ArrayList<RegistroAmbulancia> lstRegistroAmbulancia) {
		this.lstRegistroAmbulancia = lstRegistroAmbulancia;
	}

	public Drogueria getDrogueria() {
		return drogueria;
	}

	public void setDrogueria(Drogueria drogueria) {
		this.drogueria = drogueria;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

}
