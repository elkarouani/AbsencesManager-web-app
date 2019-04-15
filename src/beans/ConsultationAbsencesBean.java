package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
	
	private HtmlInputText dateInput;
//	private UISelectItems enseignantInput;
	private UISelectItems seanceInput;
	private HtmlSelectOneMenu remarqueInput;
	
	private List<SelectItem> seances;
	
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		count = 0;
		seances = new ArrayList<SelectItem>();
		
	}
	
	public void findStudent(ActionEvent event){
		if (dao.findEtudiantByNom(student_name) == null) {
			studentNotFound = true;
		} else {
			studentNotFound = false;
			displayTable = true;
			Etudiant etudiant = dao.findEtudiantByNom(student_name); 
			absences = dao.getAbsencesByEtudiant(etudiant);
			for(Absence absence : absences){count++;}
			for(Seance seance : dao.getAllSeances()){
				String seanceString = seance.getModule().getLibelle() + "-" + (seance.getDate_horaire().getDate()) + "/" + (seance.getDate_horaire().getMonth() + 1) + "/" + (seance.getDate_horaire().getYear() + 1900);
				seances.add(new SelectItem(seance.getId(), seanceString));
			}
		}
	}
	
	public void modifyAbsence(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		Absence absence = dao.getAbsenceById(params.get("id"));
		System.out.println( ( (ArrayList<SelectItem>)seanceInput.getValue() ).get(0).getValue().toString() );
//		Seance seance = dao.getSeanceById(seanceInput.getValue().toString());
//		absence.setSeance(seance);
//		absence.setRemarque(remarqueInput.getValue().toString().charAt(0));
//		System.out.println(dao.saveAbsence(absence));
//		seances = new ArrayList<SelectItem>();
		chargeTable();
	}
	
	public void deleteAbsence(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		Absence absence = dao.getAbsenceById(params.get("id"));
		System.out.println(dao.removeAbsence(absence));
		chargeTable();
	}
	
	public void populate(ActionEvent event){
	}
	
	public void chargeTable(){
		Etudiant etudiant = dao.findEtudiantByNom(student_name);
		absences = dao.getAbsencesByEtudiant(etudiant);
		for(Absence absenceItem : absences){count++;}
		seances.add(new SelectItem(null, "-------"));
		for(Seance seance : dao.getAllSeances()){
			String seanceString = seance.getModule().getLibelle() + "-" + (seance.getDate_horaire().getDate()) + "/" + (seance.getDate_horaire().getMonth() + 1) + "/" + (seance.getDate_horaire().getYear() + 1900);
			seances.add(new SelectItem(seance.getId(), seanceString));
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

	public boolean isDisplayTable() {
		return displayTable;
	}

	public void setDisplayTable(boolean displayTable) {
		this.displayTable = displayTable;
	}
	
	public HtmlInputText getDateInput() {
		return dateInput;
	}

	public void setDateInput(HtmlInputText dateInput) {
		this.dateInput = dateInput;
	}

//	public HtmlInputText getEnseignantInput() {
//		return enseignantInput;
//	}
//
//	public void setEnseignantInput(HtmlInputText enseignantInput) {
//		this.enseignantInput = enseignantInput;
//	}
	
	public HtmlSelectOneMenu getRemarqueInput() {
		return remarqueInput;
	}
	
	public UISelectItems getSeanceInput() {
		return seanceInput;
	}

	public void setSeanceInput(UISelectItems seanceInput) {
		this.seanceInput = seanceInput;
	}

	public void setRemarqueInput(HtmlSelectOneMenu remarqueInput) {
		this.remarqueInput = remarqueInput;
	}

	public List<SelectItem> getSeances() {
		return seances;
	}

	public void setSeances(List<SelectItem> seances) {
		this.seances = seances;
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
