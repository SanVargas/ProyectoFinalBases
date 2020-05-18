package modelo.entidad;

import java.sql.Date;

public class Examen {
	private String resultado;
	private Date fecha;
	private HistoriaClinica historiaClinica;

	public Examen(String resultado, Date fecha, HistoriaClinica historiaClinica) {
		super();
		this.resultado = resultado;
		this.fecha = fecha;
		this.historiaClinica = historiaClinica;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

}
