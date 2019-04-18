package beans;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
	private char filteredRemarque;
	
	private HtmlInputText dateInput;
//	private UISelectItems enseignantInput;
	private HtmlInputHidden seanceInput;
	private HtmlSelectOneMenu remarqueInput;
	
	private List<SelectItem> seances;
	
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		count = 0;
		seances = new ArrayList<SelectItem>();
	}
	
//	Generation de tables des absences liées au étudiant demandé
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
	
//	Filtrer la table par les remarques (A, E, R, P)
	public void filterByRemarque(ActionEvent event){
		Etudiant etudiant = dao.findEtudiantByNom(student_name); 
//		absences = dao.getAbsencesByEtudiant(etudiant);
		absences.clear();
		seances.clear();
		for(Absence absence : dao.getAbsencesByEtudiant(etudiant)){
			if(absence.getRemarque() == filteredRemarque){
				System.out.println(absence.getRemarque());
				absences.add(absence);
			}
		}
		for(Absence absence : absences){count++;}
		for(Seance seance : dao.getAllSeances()){
			String seanceString = seance.getModule().getLibelle() + "-" + (seance.getDate_horaire().getDate()) + "/" + (seance.getDate_horaire().getMonth() + 1) + "/" + (seance.getDate_horaire().getYear() + 1900);
			seances.add(new SelectItem(seance.getId(), seanceString));
		}
	}
	
//	Mettre une modification sur une absence
	public void modifyAbsence(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		Absence absence = dao.getAbsenceById(params.get("id"));
		int id_seance = Integer.parseInt(seanceInput.getValue().toString());
		String remarque = remarqueInput.getValue().toString();
		absence.setIdSeance(id_seance);
		absence.setRemarque(remarque.charAt(0));
		System.out.println(dao.saveAbsence(absence));
		seances = new ArrayList<SelectItem>();
		count = 0;
		chargeTable();
	}
	
//	Supprimer une absence
	public void deleteAbsence(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		Absence absence = dao.getAbsenceById(params.get("id"));
		System.out.println(dao.removeAbsence(absence));
		count = 0;
		chargeTable();
	}

//	rechargement de table des absences 
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
	
	public HtmlInputHidden getSeanceInput() {
		return seanceInput;
	}

	public void setSeanceInput(HtmlInputHidden seanceInput) {
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

	public char getFilteredRemarque() {
		return filteredRemarque;
	}

	public void setFilteredRemarque(char filteredRemarque) {
		this.filteredRemarque = filteredRemarque;
	}
	
//	Impression de fichier pdf
	public void print(ActionEvent event){
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("d:/hello.pdf"));
			document.open();
			
			String imageFile = "assets/img/index.png"; 
//			ImageData data = ImageDataFactory.create(imageFile);
			
			PdfPTable table = new PdfPTable(5);
			table.setTotalWidth(new float[]{ 80, 80, 80, 80, 80 });
			table.setLockedWidth(true);
			Etudiant etudiant = dao.findEtudiantByNom(student_name); 
			absences = dao.getAbsencesByEtudiant(etudiant);
			PdfPCell cell1 = new PdfPCell(new Phrase("Date Absence"));
			cell1.setFixedHeight(10);
	        cell1.setBorder(Rectangle.BOX);
	        PdfPCell cell2 = new PdfPCell(new Phrase("Horaire"));
			cell2.setFixedHeight(30);
	        cell2.setBorder(Rectangle.BOX);
	        PdfPCell cell3 = new PdfPCell(new Phrase("Matiére"));
			cell3.setFixedHeight(30);
	        cell3.setBorder(Rectangle.BOX);
	        PdfPCell cell4 = new PdfPCell(new Phrase("Professeur"));
			cell4.setFixedHeight(30);
	        cell4.setBorder(Rectangle.BOX);
	        PdfPCell cell5 = new PdfPCell(new Phrase("Justifactions"));
			cell5.setFixedHeight(30);
	        cell5.setBorder(Rectangle.BOX);
			for(Absence absence : absences){
				PdfPCell newCell1 = new PdfPCell(new Phrase(absence.getSeance().getDate_horaire().getDate() + "/" + (absence.getSeance().getDate_horaire().getMonth() + 1) + "/" + (absence.getSeance().getDate_horaire().getYear() + 1900)));
				newCell1.setFixedHeight(10);
				newCell1.setBorder(Rectangle.BOX);
		        PdfPCell newCell2 = new PdfPCell(new Phrase(absence.getSeance().getDate_horaire().getHours() + "/" + absence.getSeance().getDate_horaire().getMinutes()));
		        newCell2.setFixedHeight(30);
		        newCell2.setBorder(Rectangle.BOX);
		        PdfPCell newCell3 = new PdfPCell(new Phrase(absence.getSeance().getModule().getLibelle()));
		        newCell3.setFixedHeight(30);
		        newCell3.setBorder(Rectangle.BOX);
		        PdfPCell newCell4 = new PdfPCell(new Phrase(absence.getSeance().getEnseignant().getNom() + " " + absence.getSeance().getEnseignant().getPrenom()));
		        newCell4.setFixedHeight(30);
		        newCell4.setBorder(Rectangle.BOX);
		        PdfPCell newCell5 = new PdfPCell(new Phrase((absence.getJustification() == "non") ? "non justifiée" : "justifiée"));
		        newCell5.setFixedHeight(30);
		        newCell5.setBorder(Rectangle.BOX);
		        table.addCell(newCell1);
		        table.addCell(newCell2);
		        table.addCell(newCell3);
		        table.addCell(newCell4);
		        table.addCell(newCell5);
			}

	        document.add(table);
	        document.close();
	        System.out.println("well printed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
