package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-30T18:06:04.683+0100")
@StaticMetamodel(Seance.class)
public class Seance_ {
	public static volatile SingularAttribute<Seance, Long> id;
	public static volatile SingularAttribute<Seance, Module> module;
	public static volatile SingularAttribute<Seance, Date> date_horaire;
	public static volatile SingularAttribute<Seance, Enseignant> enseignant;
	public static volatile SingularAttribute<Seance, Salle> salle;
}
