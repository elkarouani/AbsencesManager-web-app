package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;

import dao.AbsencesManagerDAO;
import entities.Absence;
import entities.Etudiant;
import entities.Seance;


@ManagedBean(name="ajoutAbsence")
@SessionScoped

public class AjoutAbsenceBean {
	
	private AbsencesManagerDAO dao; 
	private List<Absence> absences;
	
	private long id;
	private long id_etudiant = 0;
	private long id_seance = 0;
	private Part justification;
	private char remarque;
	
	
	private List<SelectItem> etudiants;
	private List<SelectItem> seances;
	
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		
		//setEtudiant(new ArrayList<Etudiant>());
		//setSeance(new ArrayList<Seance>());
		
//		Etudiant.add(new SelectItem(0, "---------"));
//		Seance.add(new SelectItem(0, "---------"));
//		for(Etudiant etudiant : dao.getEtudiant()) {
//			Etudiant.add(new SelectItem(etudiant.getId(), etudiant.getNom()+" "+etudiant.getPrenom()));
//		}
//		for(Seance seance : dao.getSeance()) {
//			Seance.add(new SelectItem(seance.getId(), seance.getModule().getLibelle()));
//		}
		
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
//		System.out.println("File name : " + getFilename(justification));
		File file = null;
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\\","/");
				filename = filename.substring(1, filename.length()-1);
				file = new File(filename);
			}
		}
		dao.addFile(file);
//		System.out.println("New path : " + moveFileToUploads(justification));
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

	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	public List<SelectItem> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<SelectItem> etudiants) {
		this.etudiants = etudiants;
	}
	public List<SelectItem> getSeances() {
		return seances;
	}
	public void setSeances(List<SelectItem> seances) {
		this.seances = seances;
	}
	public String getFilename(Part justification) {
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("/","");
				return filename.substring(filename.lastIndexOf('\\')+1, filename.length() - 1);
			}
		}
		return null;
	}
	
	public String moveFileToUploads(Part justification){
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\\","/");
				filename = filename.substring(1, filename.length()-1);
				File file = new File(filename);

				String extension = file.getName().substring(file.getName().indexOf(".") + 1);
				String fileName = file.getName().substring(0, file.getName().indexOf("."));
				
				String newPath = "C:\\Users\\KDragon\\git\\AbsencesManager-web-app\\WebContent\\uploads\\" + fileName + "_" + (new Date()).getTime() + "." + extension;
				File newFile = new File(newPath);
				
				try {
					
					FileInputStream inStream = new FileInputStream(file);
					FileOutputStream outStream = new FileOutputStream(newFile);
		        	
		    	    byte[] buffer = new byte[1024];
		    		
		    	    int length;
		    	    //copy the file content in bytes 
		    	    while ((length = inStream.read(buffer)) > 0){
		    	  
		    	    	outStream.write(buffer, 0, length);
		    	 
		    	    }
		    	 
		    	    inStream.close();
		    	    outStream.close();
		    	    
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return newPath;
			}
		}
		return null;
	}
	
}
