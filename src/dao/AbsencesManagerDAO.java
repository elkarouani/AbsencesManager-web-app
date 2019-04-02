package dao;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;


import entities.Absence;

public class AbsencesManagerDAO {
	@PersistenceContext(unitName="AbsencesManager")
	private EntityManager em;
	
	public AbsencesManagerDAO (){
		em = (EntityManager) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true)).getAttribute("entitymanager");
	}
	
	public void addAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(absence);
			t.commit();
			System.out.println("well added");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void modifyAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.merge(absence);
			t.commit();
			System.out.println("well modified");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public List<Absence> getAllAbsences(){
		try {
			return em.createQuery("SELECT a FROM Absence a", Absence.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
