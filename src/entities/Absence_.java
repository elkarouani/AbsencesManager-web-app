package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-19T09:52:51.217+0100")
@StaticMetamodel(Absence.class)
public class Absence_ {
	public static volatile SingularAttribute<Absence, Integer> id;
	public static volatile SingularAttribute<Absence, Integer> idSeance;
	public static volatile SingularAttribute<Absence, Seance> seance;
	public static volatile SingularAttribute<Absence, Etudiant> etudiant;
	public static volatile SingularAttribute<Absence, String> justification;
	public static volatile SingularAttribute<Absence, Character> remarque;
	public static volatile SingularAttribute<Absence, DemandeAbsence> demandeAbsence;
}
