package beans;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.AbsencesManagerDAO;
import entities.Absence;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private AbsencesManagerDAO dao;
	private String id;
	private String title;
	private List<Absence> absencesListe;
	
	@PostConstruct
	public void init(){
		dao = new AbsencesManagerDAO();
		absencesListe = dao.getAllAbsences();
	}
	
	public void add(ActionEvent event) {
		Absence absence = new Absence();
		absence.setTitle(title);
		dao.addAbsence(absence);
		absencesListe = dao.getAllAbsences();
	}
	
	public void modify(ActionEvent event) {
		Absence absence = new Absence();
		absence.setId(Integer.parseInt(id));
		absence.setTitle(title);
		dao.modifyAbsence(absence);
		absencesListe = dao.getAllAbsences();
	}
	
	public void select(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> param = context.getExternalContext().getRequestParameterMap();
		this.id = param.get("id");
		this.title = param.get("title");
	}
	
	public void remove(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> param = context.getExternalContext().getRequestParameterMap();
		dao.deleteAbsence(Integer.parseInt(param.get("id")));
		absencesListe = dao.getAllAbsences();
	}
	
	public void print(ActionEvent event){
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("d:/hello.pdf"));
			document.open();
			
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[]{ 160, 120 });
			table.setLockedWidth(true);
			for(Absence absence : absencesListe){
				PdfPCell cell1 = new PdfPCell(new Phrase("Absence N°" + absence.getId()));
				cell1.setFixedHeight(10);
		        cell1.setBorder(Rectangle.BOX);
		        PdfPCell cell2 = new PdfPCell(new Phrase(absence.getTitle()));
				cell2.setFixedHeight(30);
		        cell2.setBorder(Rectangle.BOX);
		        table.addCell(cell1);
		        table.addCell(cell2);
			}
	        document.add(table);
	        document.close();
			System.out.println("document printed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Absence> getAbsencesListe() {
		return absencesListe;
	}

	public void setAbsencesListe(List<Absence> absencesListe) {
		this.absencesListe = absencesListe;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
