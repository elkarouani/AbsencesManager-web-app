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
	
	{
		dao = new AbsencesManagerDAO();
	}
	
	@PostConstruct
	public void init(){
		
	}
	
	public void findStudent(ActionEvent event){
		List<Etudiant> etudiants = dao.getAllEtudiants();
		for(Etudiant etudiant : etudiants){
			if (etudiant.getNom().indexOf(student_name) != -1) {
				System.out.println("founded");
			} else {
				System.out.println("not founded");
			}
		}
	}
	
	public void read(ActionEvent event){
		List<Seance> seances = dao.getAllSeances();
		for(Seance seance: seances){
			System.out.println(seance.getId());
			System.out.println(seance.getDate_horaire());
			System.out.println(seance.getEnseignant().getPrenom());
			System.out.println(seance.getModule().getLibelle());
			System.out.println(seance.getSalle().getLibelle());
		}
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
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
