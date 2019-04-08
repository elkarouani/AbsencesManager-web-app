package beans;

//import java.io.FileOutputStream;
//import java.util.List;
//import java.util.Map;

import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;

import dao.AbsencesManagerDAO;
//import entities.Absence;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private AbsencesManagerDAO dao;
	
	@PostConstruct
	public void init(){
		
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
