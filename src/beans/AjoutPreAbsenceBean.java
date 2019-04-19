package beans;

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

@ManagedBean(name="ajoutPreAbsence")
@SessionScoped
public class AjoutPreAbsenceBean {

	private AbsencesManagerDAO dao; 
	private List<SelectItem> etudiants;
	private int id_etudiant = 0;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private Part justification;
	private String acceptation;

	@PostConstruct	
	public void init(){
		dao = new AbsencesManagerDAO();
		etudiants = new ArrayList<SelectItem>();
		etudiants.add(new SelectItem(0, "---------"));
		for(Etudiant etudiant : dao.getEtudiant()) {
			etudiants.add(new SelectItem(etudiant.getId(), etudiant.getNom()+" "+etudiant.getPrenom()));
		}
	}

	public void AddAbsence(ActionEvent event) {
		Absence ab = new Absence();
		for(Seance seance : dao.getAllSeances()) {
			ab.setEtudiant(dao.findEtudiant(id_etudiant));
			Date startDateTime = new Date(startDate + startTime);
			Date endDateTime = new Date(endDate + endTime);
			if(startDateTime.after(startDateTime) && startDateTime.before(endDateTime)) {
				ab.setSeance(seance);
			}
		}
		System.out.println(dao.addAbsence(ab));
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

//		public void tester(ActionEvent event) {
//			System.out.println("File name : " + getFilename(justification));
//			String newPath = moveFileToUploads(justification);
//			System.out.println(new File(newPath).exists());
//			System.out.println("New path : " + newPath);
//		}

//		public String getFilename(Part justification) {
//			for(String cd : justification.getHeader("content-disposition").split(";")) {
//				if(cd.trim().startsWith("filename")) {
//					String filename = cd.substring(cd.indexOf('=')+1).trim().replace("/","");
//					return filename.substring(filename.lastIndexOf('\\')+1, filename.length() - 1);
//				}
//			}
//			return null;
//		}

//		public String moveFileToUploads(Part justification){
//			for(String cd : justification.getHeader("content-disposition").split(";")) {
//				if(cd.trim().startsWith("filename")) {
//					// Getting imported file
//					String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\\","/");
//					filename = filename.substring(1, filename.length()-1);
//					File file = new File(filename);
//					
//					// Getting imported file specifications
//					String extension = file.getName().substring(file.getName().indexOf(".") + 1);
//					String fileName = file.getName().substring(0, file.getName().indexOf("."));
//					
//					// Getting the new file
//					URL fileUrl = getClass().getResource("/uploads/");
//					String newPath = fileUrl.getPath().substring(1) + fileName + "_" + (new Date()).getTime() + "." + extension;
//					File newFile = new File(newPath);
//					
//					// Read from the imported file and write it in the new files
//					try {
//						
//						FileInputStream inStream = new FileInputStream(file);
//						FileOutputStream outStream = new FileOutputStream(newFile);
//			        	
//			    	    byte[] buffer = new byte[1024];
//			    		
//			    	    int length;
//			    	    //copy the file content in bytes 
//			    	    while ((length = inStream.read(buffer)) > 0){
//			    	  
//			    	    	outStream.write(buffer, 0, length);
//			    	 
//			    	    }
//			    	 
//			    	    inStream.close();
//			    	    outStream.close();
//			    	    
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//					
//					// Return the new file path
//					return newPath;
//				}
//			}
//			return null;
//		}

	
}
