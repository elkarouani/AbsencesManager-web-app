package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-11T19:15:11.835+0200")
@StaticMetamodel(Absence.class)
public class Absence_ {
	public static volatile SingularAttribute<Absence, Long> id;
	public static volatile SingularAttribute<Absence, Etudiant> etudiant;
	public static volatile SingularAttribute<Absence, Seance> seance;
	public static volatile SingularAttribute<Absence, String> justification;
	public static volatile SingularAttribute<Absence, Character> remarque;
	public static volatile SingularAttribute<Absence, DemandeAbsence> demandeAbsence;
}
