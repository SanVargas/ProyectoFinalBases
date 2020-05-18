package modelo.entidad;

public class EquipoMedico {
	private String nombre;
	private String id;
	private String descripcion;
	private Ambulancia ambulancia;

	public EquipoMedico(String nombre, String id, String descripcion, Ambulancia ambulancia) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.descripcion = descripcion;
		this.ambulancia = ambulancia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

}
