package modelo.entidad;

import java.sql.Date;
import java.util.ArrayList;

public class Diagnostico {
	private String resultado;
	private String descripcion;
	private Date fecha;
	private HistoriaClinica historiaClinica;
	private Medico medico;
	private ArrayList<OrdenMedicamento> lstOrdenMedicamento;

	public Diagnostico(String resultado, String descripcion, Date fecha, HistoriaClinica historiaClinica,
			Medico medico) {
		super();
		this.resultado = resultado;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.historiaClinica = historiaClinica;
		this.medico = medico;

		lstOrdenMedicamento = new ArrayList<OrdenMedicamento>();

	}

	public ArrayList<OrdenMedicamento> getLstOrdenMedicamento() {
		return lstOrdenMedicamento;
	}

	public void setLstOrdenMedicamento(ArrayList<OrdenMedicamento> lstOrdenMedicamento) {
		this.lstOrdenMedicamento = lstOrdenMedicamento;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
