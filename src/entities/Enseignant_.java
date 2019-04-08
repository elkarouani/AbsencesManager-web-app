package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-08T18:19:47.331+0100")
@StaticMetamodel(Enseignant.class)
public class Enseignant_ {
	public static volatile SingularAttribute<Enseignant, Long> id;
	public static volatile SingularAttribute<Enseignant, String> nom;
	public static volatile SingularAttribute<Enseignant, String> prenom;
	public static volatile SingularAttribute<Enseignant, Boolean> is_chef;
	public static volatile SingularAttribute<Enseignant, String> telephone;
	public static volatile SingularAttribute<Enseignant, String> email;
	public static volatile SingularAttribute<Enseignant, Module> module;
}
