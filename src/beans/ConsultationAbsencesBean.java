package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import dao.AbsencesManagerDAO;
import entities.Enseignant;
import entities.Etudiant;
import entities.Module;
import entities.Salle;
import entities.Seance;

@ManagedBean(name="consultation")
@RequestScoped
public class ConsultationAbsencesBean {
	private AbsencesManagerDAO dao; 

	private String student_name;
	private boolean studentNotFound;
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		studentNotFound = false;
	}
	
	public void findStudent(ActionEvent event){
		Etudiant etudiant = new Etudiant();
		etudiant.setNom(student_name);
		if (!dao.findEtudiantByNom(etudiant)) {
			studentNotFound = true;
		} else {
			
		}
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public boolean isStudentNotFound() {
		return studentNotFound;
	}

	public void setStudentNotFound(boolean studentNotFound) {
		this.studentNotFound = studentNotFound;
	}
	
//	public void print(ActionEvent event){
//		try {
//			Document document = new Document();
//			PdfWriter.getInstance(document, new FileOutputStream("d:/hello.pdf"));
//			document.open();
//			
//			PdfPTable table = new PdfPTable(2);
//			table.setTotalWidth(new float[]{ 160, 120 });
//			table.setLockedWidth(true);
//			for(Absence absence : absencesListe){
//				PdfPCell cell1 = new PdfPCell(new Phrase("Absence N°" + absence.getId()));
//				cell1.setFixedHeight(10);
//		        cell1.setBorder(Rectangle.BOX);
//		        PdfPCell cell2 = new PdfPCell(new Phrase(absence.getTitle()));
//				cell2.setFixedHeight(30);
//		        cell2.setBorder(Rectangle.BOX);
//		        table.addCell(cell1);
//		        table.addCell(cell2);
//			}
//	        document.add(table);
//	        document.close();
//	        FacesContext.getCurrentInstance().addMessage(title, new FacesMessage("document printed"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
