package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import entities.Absence;
import entities.Etudiant;
import entities.Module;
import entities.Seance;

public class AbsencesManagerDAO {
	@PersistenceContext(unitName="AbsencesManager")
	private EntityManager em;
	
	public AbsencesManagerDAO (){
		em = (EntityManager) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true)).getAttribute("entitymanager");
		System.out.println("entity manager initialized");
	}
	
	public Etudiant findEtudiantByNom(String nom){
		try {
			Etudiant etudiant = em.createQuery("SELECT e FROM Etudiant e WHERE e.nom = :nom", Etudiant.class).setParameter("nom", nom).getSingleResult();
			if (etudiant == null) {
				return null;
			}else{
				return etudiant;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Module> getAllModules(){
		try {
			return em.createQuery("SELECT m From Module m", Module.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Absence> getAbsencesByEtudiant(Etudiant etudiant){
		try {
			return em.createQuery("SELECT a From Absence a WHERE a.etudiant.id = :id", Absence.class).setParameter("id", etudiant.getId()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Absence getAbsenceById(String id){
		try {
			return em.createQuery("SELECT a From Absence a WHERE a.id = :id order by a.seance.date_horaire", Absence.class).setParameter("id", Integer.parseInt(id)).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Etudiant> getEtudiant(){
		try {
			return em.createQuery("SELECT E From Etudiant E", Etudiant.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Seance> getAllSeances(){
		try {
			return em.createQuery("SELECT s From Seance s", Seance.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Seance getSeanceById(String id){
		try {
			return em.createQuery("SELECT s From Seance s WHERE s.id = :id", Seance.class).setParameter("id", Integer.parseInt(id)).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String addAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(absence);
			t.commit();
			return "well added";
		} catch (Exception e) {
		 // TODO: handle exception
			e.printStackTrace();
			return "error";
		}
	}
	
	public String saveAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.merge(absence);
//			em.createQuery("update Absence a set a.idAbsence = :idAbsence where id = :id").setParameter("idAbsence", absence.getIdSeance()).setParameter("id", absence.getId());
			t.commit();
			return "well modified";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
	}
	
	public Etudiant findEtudiant(long id_etudiant){
		try {
			return em.find(Etudiant.class, id_etudiant);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String removeAbsence(Absence absence){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(absence);
			t.commit();
			return "well deleted";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
	}
}
