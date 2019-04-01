package dao;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;import entities.Absence;

public class AbsencesManagerDAO {
	@PersistenceContext(unitName="AbsencesManager")
	private EntityManager em;
	
	public AbsencesManagerDAO (EntityManager em){
		em = (EntityManager) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true)).getAttribute("entitymanager"); 	 
	}
	
	public void addAbsence(Absence absence){
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(absence);
		t.commit();
	}
	
}
