package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-18T13:28:49.791+0100")
@StaticMetamodel(Absence.class)
public class Absence_ {
	public static volatile SingularAttribute<Absence, Integer> id;
	public static volatile SingularAttribute<Absence, Seance> seance;
	public static volatile SingularAttribute<Absence, Etudiant> etudiant;
	public static volatile SingularAttribute<Absence, String> justification;
	public static volatile SingularAttribute<Absence, Character> remarque;
	public static volatile SingularAttribute<Absence, DemandeAbsence> demandeAbsence;
	public static volatile SingularAttribute<Absence, Integer> idSeance;
}
