package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import dao.AbsencesManagerDAO;
import entities.Absence;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private AbsencesManagerDAO dao;
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
}
