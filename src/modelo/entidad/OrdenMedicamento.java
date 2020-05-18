package modelo.entidad;

import java.sql.Date;
import java.util.ArrayList;

public class OrdenMedicamento {

	private String id;
	private Date fechaHora;
	private String descripcion;
	private Diagnostico diagnostico;
	private ArrayList<Medicamento> lstMedicamento;

	public OrdenMedicamento(String id, Date fechaHora, String descripcion, Diagnostico diagnostico) {
		super();
		this.id = id;
		this.fechaHora = fechaHora;
		this.descripcion = descripcion;
		this.diagnostico = diagnostico;

		lstMedicamento = new ArrayList<Medicamento>();

	}

	public ArrayList<Medicamento> getLstMedicamento() {
		return lstMedicamento;
	}

	public void setLstMedicamento(ArrayList<Medicamento> lstMedicamento) {
		this.lstMedicamento = lstMedicamento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

}
