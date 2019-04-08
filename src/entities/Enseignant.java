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
@Table(name="enseignant")
public class Enseignant implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String prenom;
	
	private boolean is_chef;
	
	private String telephone;
	
	private String email;

	@ManyToOne
	@JoinColumn(name="id_module", referencedColumnName="id")
	private Module module;

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

	public boolean isIs_chef() {
		return is_chef;
	}

	public void setIs_chef(boolean is_chef) {
		this.is_chef = is_chef;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}
