package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etudiant")
public class Etudiant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	private String nom;
	
	private String prenom;
	
	private String telephone;
	
	private Date date_naissance;
	
	private String email;
	
	private String numero_inscription;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero_inscription() {
		return numero_inscription;
	}

	public void setNumero_inscription(String numero_inscription) {
		this.numero_inscription = numero_inscription;
	}
}
