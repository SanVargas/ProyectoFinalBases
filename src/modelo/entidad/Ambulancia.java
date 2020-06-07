package modelo.entidad;

import java.util.ArrayList;
/**
 * Clase encargada de controlar la entidad.
 * 
 * @author Diego riveros - Lissete Quebrada - Santiago Vargas
 *
 */
public class Ambulancia {
	private String placa;
	private String categoria;
	private boolean enServicio;
	ArrayList<RegistroAmbulancia> lstRegistros;
	ArrayList<EquipoMedico> lstEquipos;

	public Ambulancia(String placa, String categoria, boolean enServicio) {
		super();
		this.placa = placa;
		this.categoria = categoria;
		this.enServicio = enServicio;
		lstRegistros = new ArrayList<RegistroAmbulancia>();
		lstEquipos = new ArrayList<EquipoMedico>();
		
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

	public ArrayList<RegistroAmbulancia> getLstRegistros() {
		return lstRegistros;
	}

	public void setLstRegistros(ArrayList<RegistroAmbulancia> lstRegistros) {
		this.lstRegistros = lstRegistros;
	}

	public ArrayList<EquipoMedico> getLstEquipos() {
		return lstEquipos;
	}

	public void setLstEquipos(ArrayList<EquipoMedico> lstEquipos) {
		this.lstEquipos = lstEquipos;
	}
	
	

}
