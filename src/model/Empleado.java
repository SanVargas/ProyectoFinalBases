package model;

public class Empleado {
	
	private String id;
	private String nombre;
	private String licencia;
	
	private Cargo cargo;
	
	public Empleado(String id, String nombre, String licencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.licencia = licencia;
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
	
	

}
