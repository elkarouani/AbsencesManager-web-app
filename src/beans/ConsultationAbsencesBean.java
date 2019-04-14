package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import dao.AbsencesManagerDAO;
import entities.Absence;
import entities.Etudiant;
import entities.Module;
import entities.Seance;

@ManagedBean(name="consultation")
@SessionScoped
public class ConsultationAbsencesBean {
	private AbsencesManagerDAO dao; 

	private String student_name;
	private List<Absence> absences;
	private boolean studentNotFound;
	private boolean displayTable;
	private int count;
	
	private HtmlInputText date;
	private HtmlInputText enseignant;
	private HtmlSelectOneMenu seance;
	private HtmlSelectOneMenu remarque;
	
	private List<SelectItem> modules;
	
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		count = 0;
		modules = new ArrayList<SelectItem>();
		
	}
	
	public void findStudent(ActionEvent event){
		if (dao.findEtudiantByNom(student_name) == null) {
			studentNotFound = true;
		} else {
			studentNotFound = false;
			displayTable = true;
			Etudiant etudiant = dao.findEtudiantByNom(student_name); 
			absences = dao.getAbsencesByEtudiant(etudiant);
			for(@SuppressWarnings("unused") Absence absence : absences){count++;}
			for(Module module : dao.getAllModules()){
				modules.add(new SelectItem(module.getLibelle(), module.getLibelle()));
			}
		}
	}
	
	public void modifyAbsence(ActionEvent event){
		System.out.println(date.getValue().toString());
		System.out.println(seance.getValue().toString());
		System.out.println(remarque.getValue().toString());
	}
	
	public void populate(ActionEvent event){
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

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<SelectItem> getModules() {
		return modules;
	}

	public void setModules(List<SelectItem> modules) {
		this.modules = modules;
	}

	public boolean isDisplayTable() {
		return displayTable;
	}

	public void setDisplayTable(boolean displayTable) {
		this.displayTable = displayTable;
	}

	public HtmlInputText getDate() {
		return date;
	}

	public void setDate(HtmlInputText date) {
		this.date = date;
	}

	public HtmlSelectOneMenu getSeance() {
		return seance;
	}

	public void setSeance(HtmlSelectOneMenu seance) {
		this.seance = seance;
	}

	public HtmlSelectOneMenu getRemarque() {
		return remarque;
	}

	public void setRemarque(HtmlSelectOneMenu remarque) {
		this.remarque = remarque;
	}

	public HtmlInputText getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(HtmlInputText enseignant) {
		this.enseignant = enseignant;
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
//				PdfPCell cell1 = new PdfPCell(new Phrase("Absence N�" + absence.getId()));
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
