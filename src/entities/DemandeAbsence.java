package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="demande_absence")
public class DemandeAbsence implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String justification_respo;
	
	private String piece_joint;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJustification_respo() {
		return justification_respo;
	}

	public void setJustification_respo(String justification_respo) {
		this.justification_respo = justification_respo;
	}

	public String getPiece_joint() {
		return piece_joint;
	}

	public void setPiece_joint(String piece_joint) {
		this.piece_joint = piece_joint;
	}
}
