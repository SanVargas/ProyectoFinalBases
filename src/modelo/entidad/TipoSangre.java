package modelo.entidad;

import java.util.ArrayList;

public class TipoSangre {
	private String rh;
	private String grupoSanguineo;
	private String id;
	private ArrayList<HistoriaClinica> lstHistoriaClinica;

	public TipoSangre(String rh, String grupoSanguineo, String id) {
		super();
		this.rh = rh;
		this.grupoSanguineo = grupoSanguineo;
		this.id = id;

		lstHistoriaClinica = new ArrayList<HistoriaClinica>();

	}

	public ArrayList<HistoriaClinica> getLstHistoriaClinica() {
		return lstHistoriaClinica;
	}

	public void setLstHistoriaClinica(ArrayList<HistoriaClinica> lstHistoriaClinica) {
		this.lstHistoriaClinica = lstHistoriaClinica;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
