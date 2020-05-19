package model;

public class Ambulancia {
	private String placa;
	private String categoria;
	private boolean enServicio;
	
	public Ambulancia(String placa, String categoria) {
		super();
		this.placa = placa;
		this.categoria = categoria;
		this.enServicio =false;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isEnServicio() {
		return enServicio;
	}

	public void setEnServicio(boolean enServicio) {
		this.enServicio = enServicio;
	}
}
