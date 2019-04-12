package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;

import dao.AbsencesManagerDAO;
import entities.Absence;
import entities.Etudiant;
import entities.Seance;


@ManagedBean(value="ajoutAbsence")
@SessionScoped

public class AjoutAbsenceBean {
	
	private AbsencesManagerDAO dao; 
	private List<Absence> absences;
	
	private long id;
	private long id_etudiant = 0;
	private long id_seance = 0;
	private Part justification;
	private char remarque;
	
	
	private List<SelectItem> Etudiant;
	private List<SelectItem> Seance;
	
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		
		//setEtudiant(new ArrayList<Etudiant>());
		//setSeance(new ArrayList<Seance>());
		Etudiant.add(new SelectItem(0, "---------"));
		Seance.add(new SelectItem(0, "---------"));
		for(Etudiant etudiant : dao.getEtudiant()) {
			Etudiant.add(new SelectItem(etudiant.getId(), etudiant.getNom()+" "+etudiant.getPrenom()));
		}
		for(Seance seance : dao.getSeance()) {
			Seance.add(new SelectItem(seance.getId(), seance.getModule().getLibelle()));
		}
		
	}
	public String addAbsence(Absence absence) {
		Absence ab = new Absence();
		ab.setId(id);
		//ab.setEtudiant(id_etudiant);
		//ab.setSeance(id_seance);
		ab.setRemarque(remarque);
//		ab.setJustification(justification);
//		ab.write("D:\\data\\"+getFilename(justification));
		dao.addAbsence(ab);
		//System.out.println("Absence ajouté !");
		return "Absence ajouté!";
	}
	public void tester(ActionEvent event) {
		System.out.println(justification.getName());
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_etudiant() {
		return id_etudiant;
	}
	public void setId_etudiant(long id_etudiant) {
		this.id_etudiant = id_etudiant;
	}
	public long getId_seance() {
		return id_seance;
	}
	public void setId_seance(long id_seance) {
		this.id_seance = id_seance;
	}
	public Part getJustification() {
		return justification;
	}
	public void setJustification(Part justification) {
		this.justification = justification;
	}
	public char getRemarque() {
		return remarque;
	}
	public void setRemarque(char remarque) {
		this.remarque = remarque;
	}
	public List<SelectItem> getEtudiant() {
		return Etudiant;
	}

	public void setEtudiant(List<SelectItem> etudiant) {
		Etudiant = etudiant;
	}


	public List<SelectItem> getSeance() {
		return Seance;
	}


	public void setSeance(List<SelectItem> seance) {
		Seance = seance;
	}
	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	
	private static String getFilename(String string) {
		
//		for(String cd: string.getHeader("content-disposition").spilt(";")) {
//			if(cd.trim().startsWith("filename")) {
//				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("/","");
//				return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
//			}
//			
//		}
		
		return null;
		
	}
	

}
