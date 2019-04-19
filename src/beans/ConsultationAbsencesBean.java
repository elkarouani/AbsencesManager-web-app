package beans;

import java.io.FileOutputStream;
import java.net.URL;
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
import java.awt.Graphics;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

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
//		int id_seance = Integer.parseInt(seanceInput.getValue().toString());
		String remarque = remarqueInput.getValue().toString();
//		absence.setIdSeance(id_seance);
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
	
	public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
	
//	Impression de fichier pdf
	public void print(ActionEvent event){
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("d:/hello.pdf"));
			document.open();
			
			URL fileUrl = getClass().getResource("/index.png");
			String newPath = fileUrl.getPath();
			Image img = Image.getInstance(newPath);
			img.scalePercent(140 * 92 / 300);
			document.add(img);

			document.add( Chunk.NEWLINE );
			Font font1 = new Font(Font.FontFamily.HELVETICA  , 18, Font.BOLD);
			Font font2 = new Font(Font.FontFamily.TIMES_ROMAN  , 12, Font.BOLD);
			
			Paragraph title = new Paragraph("Relevé d'Absences", font1);
			title.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(title);
	        document.add( Chunk.NEWLINE );
	        
	        Etudiant etudiant = dao.findEtudiantByNom(student_name);
	        
	        PdfPTable Line = new PdfPTable(2);
	        
	        Line.setWidthPercentage(100);
	        
	        Paragraph left = new Paragraph("Code : ", font2);
	        left.add(" "+etudiant.getNumero_inscription());
	        PdfPCell cell11 = new PdfPCell(left);
	        cell11.setPadding(0);
	        cell11.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	        cell11.setBorder(PdfPCell.NO_BORDER);
	        
	        Line.addCell(cell11);
	        
	        Paragraph right = new Paragraph("Niveau/Filiére : ", font2);
	        right.add(" LP-TPW");
	        PdfPCell cell13 = new PdfPCell(right);
	        cell13.setPadding(0);
	        cell13.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	        cell13.setBorder(PdfPCell.NO_BORDER);
	        
	        Line.addCell(cell13);
	        
	        document.add(Line);
	        document.add( Chunk.NEWLINE );
	        
	        Paragraph line1 = new Paragraph("Nom et Prénom : ", font2);
	        line1.add(" "+etudiant.getNom() + " " + etudiant.getPrenom());
	        document.add(line1);
	        document.add( Chunk.NEWLINE );
	        document.add( Chunk.NEWLINE );
	        
	        int nbrAbsencesJustifie = 0;
	        int nbrAbsencesNonJustifie = 0;
	        
	        for(Absence absence : absences){
	        	if(absence.getJustification().equals("oui")){nbrAbsencesJustifie++;}
	        	if(absence.getJustification().equals("non")){nbrAbsencesNonJustifie++;}
	        }
	        
	        Paragraph line2 = new Paragraph("Nombre Total d'absence non justifié : ", font2);
	        line2.add(" "+nbrAbsencesNonJustifie);
	        document.add(line2);
	        document.add( Chunk.NEWLINE );
	        Paragraph line3 = new Paragraph("Nombre Total d'absence justifié : ", font2);
	        line3.add(" "+nbrAbsencesJustifie);
	        document.add(line3);
	        document.add( Chunk.NEWLINE );
	        
			PdfPTable table = new PdfPTable(5);
			table.setTotalWidth(new float[]{ 100, 100, 100, 100, 100 });
			table.setLockedWidth(true);
			absences = dao.getAbsencesByEtudiant(etudiant);
			
			PdfPCell cell1 = new PdfPCell(new Phrase("Date Absence", font2));
			cell1.setFixedHeight(10);
	        cell1.setBorder(Rectangle.BOX);
	        cell1.setBackgroundColor(new BaseColor(175, 181, 188));
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        PdfPCell cell2 = new PdfPCell(new Phrase("Horaire", font2));
			cell2.setFixedHeight(30);
	        cell2.setBorder(Rectangle.BOX);
	        cell2.setBackgroundColor(new BaseColor(175, 181, 188));
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        PdfPCell cell3 = new PdfPCell(new Phrase("Matiére", font2));
			cell3.setFixedHeight(30);
	        cell3.setBorder(Rectangle.BOX);
	        cell3.setBackgroundColor(new BaseColor(175, 181, 188));
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        PdfPCell cell4 = new PdfPCell(new Phrase("Professeur", font2));
			cell4.setFixedHeight(30);
	        cell4.setBorder(Rectangle.BOX);
	        cell4.setBackgroundColor(new BaseColor(175, 181, 188));
	        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        PdfPCell cell5 = new PdfPCell(new Phrase("Justifactions", font2));
			cell5.setFixedHeight(30);
	        cell5.setBorder(Rectangle.BOX);
	        cell5.setBackgroundColor(new BaseColor(175, 181, 188));
	        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	        table.addCell(cell4);
	        table.addCell(cell5);
			
	        for(Absence absence : absences){
				PdfPCell newCell1 = new PdfPCell(new Phrase(absence.getSeance().getDate_horaire().getDate() + "/" + (absence.getSeance().getDate_horaire().getMonth() + 1) + "/" + (absence.getSeance().getDate_horaire().getYear() + 1900)));
				newCell1.setFixedHeight(10);
				newCell1.setBorder(Rectangle.BOX);
				newCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				newCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        PdfPCell newCell2 = new PdfPCell(new Phrase(absence.getSeance().getDate_horaire().getHours() + "/" + absence.getSeance().getDate_horaire().getMinutes()));
		        newCell2.setFixedHeight(30);
		        newCell2.setBorder(Rectangle.BOX);
		        newCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        newCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        PdfPCell newCell3 = new PdfPCell(new Phrase(absence.getSeance().getModule().getLibelle()));
		        newCell3.setFixedHeight(30);
		        newCell3.setBorder(Rectangle.BOX);
		        newCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        newCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        PdfPCell newCell4 = new PdfPCell(new Phrase(absence.getSeance().getEnseignant().getNom() + " " + absence.getSeance().getEnseignant().getPrenom()));
		        newCell4.setFixedHeight(30);
		        newCell4.setBorder(Rectangle.BOX);
		        newCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        newCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        PdfPCell newCell5 = new PdfPCell(new Phrase((absence.getJustification().equals("non")) ? "non justifiée" : "justifiée"));
		        newCell5.setFixedHeight(30);
		        newCell5.setBorder(Rectangle.BOX);
		        newCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        newCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        
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
