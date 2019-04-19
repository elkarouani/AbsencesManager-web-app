package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-19T14:18:16.263+0200")
@StaticMetamodel(Seance.class)
public class Seance_ {
	public static volatile SingularAttribute<Seance, Integer> id;
	public static volatile SingularAttribute<Seance, Module> module;
	public static volatile SingularAttribute<Seance, Date> date_horaire;
	public static volatile SingularAttribute<Seance, Enseignant> enseignant;
	public static volatile SingularAttribute<Seance, Salle> salle;
}
