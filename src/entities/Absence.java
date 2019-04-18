package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="absence")
public class Absence implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_seance")
	private int idSeance;
	
	@ManyToOne
	@JoinColumn(name="id_seance", nullable=false,referencedColumnName="id", updatable=false, insertable=false)
	private Seance seance;
	
	
	@ManyToOne
	@JoinColumn(name="id_etudiant", referencedColumnName="id")
	private Etudiant etudiant;
	
	private String justification;
	
	private char remarque;
	
	@ManyToOne
	@JoinColumn(name="id_demande_absence", referencedColumnName="id")
	private DemandeAbsence demandeAbsence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public String getJustification() {
		return justification;
	}
	
	public void setJustification(String justification) {
		this.justification = justification;
	}

	public char getRemarque() {
		return remarque;
	}

	public void setRemarque(char remarque) {
		this.remarque = remarque;
	}

	public DemandeAbsence getDemandeAbsence() {
		return demandeAbsence;
	}

	public void setDemandeAbsence(DemandeAbsence demandeAbsence) {
		this.demandeAbsence = demandeAbsence;
	}

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}
}
