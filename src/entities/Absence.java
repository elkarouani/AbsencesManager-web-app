package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="absences")
public class Absence implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="id_etudiant", referencedColumnName="id")
	private Etudiant etudiant;
	
	@ManyToOne
	@JoinColumn(name="id_seance", referencedColumnName="id")
	private Seance seance;
	
	private String justification;
	
	private char remarque;
	
	@ManyToOne
	@JoinColumn(name="id_demande_absence", referencedColumnName="id")
	private DemandeAbsence demandeAbsence;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String isJustification() {
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
}
