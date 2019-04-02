package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import dao.AbsencesManagerDAO;
import entities.Absence;

@ManagedBean(name="manager")
@RequestScoped
public class BeansManager {
	private String title;

	public void add(ActionEvent event) {
		Absence absence = new Absence();
		absence.setTitle(title);
		AbsencesManagerDAO dao = new AbsencesManagerDAO();
		dao.addAbsence(absence);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
