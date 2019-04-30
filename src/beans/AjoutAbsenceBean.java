package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import dao.AbsencesManagerDAO;
import entities.Absence;
import entities.Etudiant;
import entities.Module;
import entities.Seance;

@ManagedBean(name="ajoutv2")
@ApplicationScoped
public class AjoutAbsenceBean {
	
	public AbsencesManagerDAO dao;
	
	public long seance_id;
	public String remarque;
	public boolean show_table;
	public boolean no_students_exists;
	
	public List<SelectItem> seances;
	public List<Etudiant> etudiants;
	public Map<Etudiant, String> notifications;

	@PostConstruct
	public void init(){
		show_table = false;
		no_students_exists = false;
		dao = new AbsencesManagerDAO();
		seances = new ArrayList<SelectItem>();
		
		seances.add(new SelectItem(0, "-----"));
		for(Seance seance : dao.getAllSeances()){
			String libelle = seance.getModule().getLibelle() + " : " + seance.getDate_horaire().getDate() + " / " + (seance.getDate_horaire().getMonth() + 1) + " / " + (seance.getDate_horaire().getYear() + 1900);
			seances.add(new SelectItem(seance.getId(), libelle));
		}
	}
	
	public void apply(ActionEvent event){
		show_table = true;
		etudiants = new ArrayList<Etudiant>();
		notifications = new HashMap<Etudiant, String>();
		
		for(Etudiant etudiant : dao.getAllEtudiants()){
			boolean etudiantHaveAbsence = false;
			for(Absence absence : dao.getAllAbsences()){
				if(absence.getEtudiant().equals(etudiant) && absence.getSeance().equals(dao.getSeanceById(seance_id))){
					etudiantHaveAbsence = true;
				}
			}
			if(etudiantHaveAbsence == false){
				no_students_exists = false;
				etudiants.add(etudiant);
				List<Module> modulesHadAbsencesForStudent = getModulesHadAbsencesForStudent(etudiant);
				if(modulesHadAbsencesForStudent.size() != 0){
					if(modulesHadAbsencesForStudent.size() == dao.getAllModules().size()){
						notifications.put(etudiant, "Cet étudiant a trois absences dans tous les modules");
					} else {
						String notification = "Cet étudiant passe à trois absences dans les modules : ";
						for(Module module : modulesHadAbsencesForStudent){
							notification += module.getLibelle() + ", ";
						}
						notifications.put(etudiant, notification.substring(0, notification.length() - 2));
					}
				}
			}
		}
		
		if(etudiants.size() == 0){
			show_table = false;
			no_students_exists = true;
		}
	}
	
	public List<Module> getModulesHadAbsencesForStudent(Etudiant etudiant){
		List<Module> modulesHadAbsencesForStudent = new ArrayList<Module>();
		for(Module module : dao.getAllModules()){
			int countNbrAbsenceForSingleModule = 0;
			for(Absence absence : dao.getAllAbsences()){
				if(etudiant.equals(absence.getEtudiant()) && 
				module.equals(absence.getSeance().getModule())){
					countNbrAbsenceForSingleModule++;
				}
			}
			if(countNbrAbsenceForSingleModule >= 3){
				modulesHadAbsencesForStudent.add(module);
			}
		}
		return modulesHadAbsencesForStudent;
	}
	
	public void addAbsence(ActionEvent event){
		System.out.println("i am here");
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		
		Etudiant selectedEtudiant = null;
		for(Etudiant etudiant : etudiants){
			if(etudiant.getId() == new Long(params.get("etudiant_id"))){
				selectedEtudiant = etudiant;
			}
		}
		Seance selectedSeance = null;
		for(Seance seance : dao.getAllSeances()){
			if(seance.getId() == seance_id){
				selectedSeance = seance;
			}
		}
		char remarque = this.remarque.charAt(0);
		String justification = (remarque == "E".charAt(0)) ? "oui" : "non";
		
		Absence absence = new Absence();
		absence.setEtudiant(selectedEtudiant);
		absence.setJustification(justification);
		absence.setRemarque(remarque);
		absence.setSeance(selectedSeance);
		
		System.out.println(dao.addAbsence(absence));
		show_table = false;
		seance_id = 0;
	}
	
	public List<SelectItem> getSeances() {
		return seances;
	}

	public void setSeances(List<SelectItem> seances) {
		this.seances = seances;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public long getSeance_id() {
		return seance_id;
	}

	public void setSeance_id(long seance_id) {
		this.seance_id = seance_id;
	}

	public boolean isShow_table() {
		return show_table;
	}

	public void setShow_table(boolean show_table) {
		this.show_table = show_table;
	}

	public boolean isNo_students_exists() {
		return no_students_exists;
	}

	public void setNo_students_exists(boolean no_students_exists) {
		this.no_students_exists = no_students_exists;
	}

	public Map<Etudiant, String> getNotifications() {
		return notifications;
	}

	public void setNotifications(Map<Etudiant, String> notifications) {
		this.notifications = notifications;
	}
}
