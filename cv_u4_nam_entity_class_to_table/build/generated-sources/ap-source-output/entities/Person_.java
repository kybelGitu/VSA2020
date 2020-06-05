package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-23T13:44:45")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, Date> born;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Float> weight;
    public static volatile SingularAttribute<Person, Long> id;

}