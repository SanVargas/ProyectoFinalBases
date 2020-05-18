package modelo.entidad;

public class Medicamento {
	private String registroSanitario;
	private String nombre;
	private double gramaje;
	private Drogueria drogueria;
	private OrdenMedicamento ordenMedicamento;

	public Medicamento(String registroSanitario, String nombre, double gramaje, Drogueria drogueria,
			OrdenMedicamento ordenMedicamento) {
		super();
		this.registroSanitario = registroSanitario;
		this.nombre = nombre;
		this.gramaje = gramaje;
		this.drogueria = drogueria;
		this.ordenMedicamento = ordenMedicamento;
	}

	public String getRegistroSanitario() {
		return registroSanitario;
	}

	public void setRegistroSanitario(String registroSanitario) {
		this.registroSanitario = registroSanitario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getGramaje() {
		return gramaje;
	}

	public void setGramaje(double gramaje) {
		this.gramaje = gramaje;
	}

	public Drogueria getDrogueria() {
		return drogueria;
	}

	public void setDrogueria(Drogueria drogueria) {
		this.drogueria = drogueria;
	}

	public OrdenMedicamento getOrdenMedicamento() {
		return ordenMedicamento;
	}

	public void setOrdenMedicamento(OrdenMedicamento ordenMedicamento) {
		this.ordenMedicamento = ordenMedicamento;
	}

}
