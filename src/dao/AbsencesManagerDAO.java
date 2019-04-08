package dao;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import entities.Etudiant;

public class AbsencesManagerDAO {
	@PersistenceContext(unitName="AbsencesManager")
	private EntityManager em;
	
	public AbsencesManagerDAO (){
		em = (EntityManager) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true)).getAttribute("entitymanager");
	}
	
	public List<Etudiant> getAllEtudiants(){
		try {
			return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public String addAbsence(Absence absence){
//		try {
//			EntityTransaction t = em.getTransaction();
//			t.begin();
//			em.persist(absence);
//			t.commit();
//			return "well added";
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
//	public String modifyAbsence(Absence absence){
//		try {
//			EntityTransaction t = em.getTransaction();
//			t.begin();
//			em.merge(absence);
//			t.commit();
//			return "well modified";
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
//	public String deleteAbsence(int id_absence){
//		try {
//			EntityTransaction t = em.getTransaction();
//			t.begin();
//			em.remove(em.find(Absence.class, id_absence));
//			t.commit();
//			return "well deleted";
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
//	public List<Absence> getAllAbsences(){
//		try {
//			return em.createQuery("SELECT a FROM Absence a", Absence.class).getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
}
