package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-12T19:33:28.496+0100")
@StaticMetamodel(Etudiant.class)
public class Etudiant_ {
	public static volatile SingularAttribute<Etudiant, Long> id;
	public static volatile SingularAttribute<Etudiant, String> nom;
	public static volatile SingularAttribute<Etudiant, String> prenom;
	public static volatile SingularAttribute<Etudiant, String> telephone;
	public static volatile SingularAttribute<Etudiant, Date> date_naissance;
	public static volatile SingularAttribute<Etudiant, String> email;
	public static volatile SingularAttribute<Etudiant, String> numero_inscription;
}
