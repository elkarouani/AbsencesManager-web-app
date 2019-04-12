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
import entities.Store;

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
	
	public List<Etudiant> getEtudiant(){
		try {
			return em.createQuery("SELECT E From Etudiant E", Etudiant.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Seance> getSeance(){
		try {
			return em.createQuery("SELECT S From Etudiant S", Seance.class).getResultList();
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
	
	public boolean addFile(File file){
		try {
			EntityTransaction t = em.getTransaction();
			t.begin();
			Store store = new Store();
			
			// initialize buffered reader  
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuffer fileContentStr = new StringBuffer("");
			byte[] fileContent = null;
	        // read lines of file
			while ((line = reader.readLine()) != null) {
	        //append line to string buffer
				fileContentStr.append(line).append("\n");
			}
	                // convert string to byte array
			fileContent = fileContentStr.toString().trim().getBytes();
			
			store.setData(fileContent);
			em.persist(store);
			t.commit();
			System.out.println("well added");
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
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
