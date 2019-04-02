package beans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
	}
	
	public void modify(ActionEvent event) {
		Absence absence = new Absence();
		absence.setId(Integer.parseInt(id));
		absence.setTitle(title);
		dao.modifyAbsence(absence);
	}
	
	public void select(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> param = context.getExternalContext().getRequestParameterMap();
		this.id = param.get("id");
		this.title = param.get("title");
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
