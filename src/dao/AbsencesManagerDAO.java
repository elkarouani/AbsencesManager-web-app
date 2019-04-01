package dao;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import entities.Absence;

public class AbsencesManagerDAO {
	@PersistenceContext(unitName="AbsencesManager")
	private static EntityManager em;
	
	public AbsencesManagerDAO (EntityManager em){
//		em = (EntityManager) ((HttpSession) FacesContext.getCurrentInstance()
//				.getExternalContext().getSession(true)).getAttribute("entitymanager");
		
		
	}
	
	public static void addAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(absence);
			t.commit();
			System.out.println("well added");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("there is an error : " + e.getMessage() );
		}
		
		
	}
	
}
