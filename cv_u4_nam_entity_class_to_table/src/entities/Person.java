/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author vsa
 * 
 */
@Entity
//namapovanie triedy
@Table(name = "T_OSOBA")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
})
public class Person implements Serializable {
    
/////////////*************P R O P E R T I E S *************\\\\\\\\\\\\
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "meno")
    private String name;
// dy Person odstáňte property born.
//Spustite   mapovanie stlpca
//uloha 
// Z triedy Person odstáňte property born.
//Spustite program, mal by vypísať všetky údaje z tabuľky s výnimkou dátumu. 
    @Column(name = "narodena")
    @Temporal(javax.persistence.TemporalType.DATE)  
    private Date born;
//Uloha 6: Ak stĺpec VAHA nemá povolené nulové hodnoty, mal by vám program vyhodiť výnimku. Pozrite v logu akú.
    //, exception: SQLIntegrityConstraintViolationException: Column 'VAHA'  cannot accept a NULL value.
    
    @Column(name = "vaha",nullable = true)//pri zmene zmenit v persistence.xml - recreate
    private Float weight = 10f;//nicializujte váhu každej novej osoby (napr. pridajte vahu do metody addPerson(String meno, float vaha)) 
        // ja som to spravil takto
        //pridane @Transient
    @Transient
    private int age;


/////////////*************C O N S T R U C T O R S *************\\\\\\\\\\\\
    public Person(){
        super();
    }

    public Person(String name){
        this.name = name;
    } 
    
/////////////************* M E T H O D S  *************\\\\\\\\\\\\
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getBorn() {
        return born;
    }


    public void setBorn(Date born) {
        this.born = born;
    }
    

    public Float getWeight() {
        return weight;
    }


    public void setWeight(Float weight) {
        this.weight = weight;
    }


    public Long getId() {
        return id;
    }
    //Internal Exception: java.sql.SQLSyntaxErrorException: Column 'AGE' is either not in any table in the FROM list or appears within a join specification and is outside the scope of the join specification or appears in a HAVING clause and is not in the GROUP BY list. If this is a CREATE or ALTER TABLE  statement then 'AGE' is not a column in the target table.

    public void setId(Long id) {
        this.id = id;
    }
    
    // uloha : Do triedy Person pridajte property int age
//Spustite program - nastal problém. Nájdite v logu chybovú správu s popisom problému. 
    //Error Code: 30000
//Call: SELECT ID, AGE, narodena, meno, vaha FROM T_OSOBA
// Zly sql dotaz
//Query: ReadAllQuery(name="Person.findAll" referenceClass=Person sql="SELECT ID, AGE, narodena, meno, vaha FROM T_OSOBA")
//	at org.eclipse.persistence.internal.jpa.QueryImpl.getDetailedException(QueryImpl.java:378)
//	at org.eclipse.persistence.internal.jpa.QueryImpl.executeReadQuery(QueryImpl.java:260)
//	at org.eclipse.persistence.internal.jpa.QueryImpl.getResultList(QueryImpl.java:469)
//	at cv_u4_nam_entity_class_to_table.Cv_u4_nam_entity_class_to_table.showAllPersons(Cv_u4_nam_entity_class_to_table.java:44)
//	at cv_u4_nam_entity_class_to_table.Cv_u4_nam_entity_class_to_table.main(Cv_u4_nam_entity_class_to_table.java:27)

    /**
     * Get the value of age
     * function calculate age in years between current date and borndate 
     * 
     * @return the value of age
     */
    public int getAge() {
         // validate inputs ...   
         try{
            Date currentDate = new Date();
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
            int d1 = Integer.parseInt(formatter.format(this.born));                            
            int d2 = Integer.parseInt(formatter.format(currentDate));                          
            age = (d2 - d1) / 10000;//remove the last 4 number 
         }                       //from the result (MMdd)
           
         catch(NullPointerException e){
             age = -1;
         }                                           
        return age;  
    }

    /**
     * Set the value of age
     *
     * @param age new value of age
     */
    public void setAge(int age) {
        this.age = age;
    }

/////////////************* O T H E R S  *************\\\\\\\\\\\\
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        getAge();
        return "Person{" + "id=" + id + ", name=" + name + ", born=" + born + ", weight=" + weight + ", age=" + age + '}';
    }   
}
