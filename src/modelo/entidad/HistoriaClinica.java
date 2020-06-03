package modelo.entidad;

import java.util.ArrayList;

public class HistoriaClinica {
	private String numero;
	private double peso;
	private double estatura;
	private TipoSangre tipoSangre;
	private Paciente paciente;
	private ArrayList<Examen> lstExamen;
	private ArrayList<Diagnostico> lstDiagnostico;

	public HistoriaClinica(String numero, double peso, double estatura, TipoSangre tipoSangre,
			Paciente paciente) {
		super();
		this.numero = numero;
		this.peso = peso;
		this.estatura = estatura;
		this.tipoSangre = tipoSangre;
		this.paciente = paciente;

		lstDiagnostico = new ArrayList<Diagnostico>();
		lstExamen = new ArrayList<Examen>();

	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getEstatura() {
		return estatura;
	}

	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}

	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ArrayList<Examen> getLstExamen() {
		return lstExamen;
	}

	public void setLstExamen(ArrayList<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}

	public ArrayList<Diagnostico> getLstDiagnostico() {
		return lstDiagnostico;
	}

	public void setLstDiagnostico(ArrayList<Diagnostico> lstDiagnostico) {
		this.lstDiagnostico = lstDiagnostico;
	}

}
