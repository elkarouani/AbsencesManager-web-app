package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;

import dao.AbsencesManagerDAO;
import entities.Absence;
import entities.DemandeAbsence;
import entities.Etudiant;
import entities.Seance;

@ManagedBean(name="ajoutPreAbsence")
@SessionScoped
public class AjoutPreAbsenceBean {

	private AbsencesManagerDAO dao; 
	private List<SelectItem> etudiants;
	private int id_etudiant = 0;
	private String startDate = "01-01-2000";
	private String startTime = "00:00";
	private String endDate = "01-01-2000";
	private String endTime = "00:00";
	private Part justification;
	private String acceptation;
	
	@PostConstruct	
	public void init(){
		dao = new AbsencesManagerDAO();
		etudiants = new ArrayList<SelectItem>();
		etudiants.add(new SelectItem(0, "---------"));
		for(Etudiant etudiant : dao.getAllEtudiants()) {
			etudiants.add(new SelectItem(etudiant.getId(), etudiant.getNom()+" "+etudiant.getPrenom()));
		}
	}

	public void AddAbsence(ActionEvent event) throws ParseException {
		for(Seance seance : dao.getAllSeances()) {
			
			Date start = new Date();
			start.setDate(Integer.parseInt(startDate.substring(9, 10)));
			start.setMonth(getMonthByString(startDate.substring(4, 7)));
			start.setYear(Integer.parseInt(startDate.substring(24, 28))-1900);
			start.setHours(Integer.parseInt(startTime.substring(11, 13)));
			start.setMinutes(Integer.parseInt(startTime.substring(14, 16)));
			
			Date end = new Date();
			end.setDate(Integer.parseInt(endDate.substring(9, 10)));
			end.setMonth(getMonthByString(endDate.substring(4, 7)));
			end.setYear(Integer.parseInt(endDate.substring(24, 28))-1900);
			end.setHours(Integer.parseInt(endTime.substring(11, 13)));
			end.setMinutes(Integer.parseInt(endTime.substring(14, 16)));
			
			if(seance.getDate_horaire().after(start) && seance.getDate_horaire().before(end)) {
				boolean absence_exist = false;
				for(Absence a : dao.getAllAbsences()){
					if(a.getSeance().equals(seance) && a.getEtudiant().equals(dao.findEtudiant(id_etudiant))){
						absence_exist = true;
					}
				}
				if(!absence_exist){
					Absence absence = new Absence();
					absence.setEtudiant(dao.findEtudiant(id_etudiant));
					absence.setSeance(seance);
					absence.setJustification((acceptation.equals("oui") ? "oui" : "non"));
					absence.setRemarque((acceptation.equals("oui") ? "E".charAt(0) : "A".charAt(0)));
					DemandeAbsence demandeAbsence = new DemandeAbsence();
					demandeAbsence.setJustification_respo(acceptation);
					demandeAbsence.setPiece_joint(moveFileToUploads(justification));
					System.out.println(dao.addDemandeAbsence(demandeAbsence));
					absence.setDemandeAbsence(demandeAbsence);
					System.out.println(dao.addAbsence(absence));
				}
			}
		}
		
		startDate = "01-01-2000";
		startTime = "00:00";
		endDate = "01-01-2000";
		endTime = "00:00";
	}

	public int getMonthByString(String string){
		switch (string) {
			case "Jan": return 0;
			case "Feb": return 1;
			case "Mar": return 2;
			case "Apr": return 3;
			case "May": return 4;
			case "Jun": return 5;
			case "Jul": return 6;
			case "Aug": return 7;
			case "Sep": return 8;
			case "Oct": return 9;
			case "Nov": return 10;
			case "Dec": return 11;
			default: return -1;
		}
	}
	
	public void clear(ActionEvent event) {
		id_etudiant = 0;
	}
	
	public List<SelectItem> getEtudiants() {
		return etudiants;
	}
	
	public void setEtudiants(List<SelectItem> etudiants) {
		this.etudiants = etudiants;
	}
	
	public int getId_etudiant() {
		return id_etudiant;
	}
	
	public void setId_etudiant(int id_etudiant) {
		this.id_etudiant = id_etudiant;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Part getJustification() {
		return justification;
	}
	
	public void setJustification(Part justification) {
		this.justification = justification;
	}
	
	public String getAcceptation() {
		return acceptation;
	}
	
	public void setAcceptation(String acceptation) {
		this.acceptation = acceptation;
	}

	public String moveFileToUploads(Part justification){
		for(String cd : justification.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				// Getting imported file
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\\","/");
				filename = filename.substring(1, filename.length()-1);
				File file = new File(filename);
				
				// Getting imported file specifications
				String extension = file.getName().substring(file.getName().indexOf(".") + 1);
				String fileName = file.getName().substring(0, file.getName().indexOf("."));
				
				// Getting the new file
				URL fileUrl = getClass().getResource("/");
				String newPath = fileUrl.getPath().substring(1) + fileName + "_" + (new Date()).getTime() + "." + extension;
				File newFile = new File(newPath);
				
				// Read from the imported file and write it in the new files
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
				
				// Return the new file path
				return newPath;
			}
		}
		return null;
	}

	
}
