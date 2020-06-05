/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import cv3swingapp.Person;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@Entity
@Table(name = "PERSON_SWINGAPP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonSwingapp.findAll", query = "SELECT p FROM PersonSwingapp p"),
    @NamedQuery(name = "PersonSwingapp.findById", query = "SELECT p FROM PersonSwingapp p WHERE p.id = :id"),
    @NamedQuery(name = "PersonSwingapp.findByBirthyear", query = "SELECT p FROM PersonSwingapp p WHERE p.birthyear = :birthyear"),
    @NamedQuery(name = "PersonSwingapp.findByEmployed", query = "SELECT p FROM PersonSwingapp p WHERE p.employed = :employed"),
    @NamedQuery(name = "PersonSwingapp.findByFirstname", query = "SELECT p FROM PersonSwingapp p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "PersonSwingapp.findByLastname", query = "SELECT p FROM PersonSwingapp p WHERE p.lastname = :lastname"),
    @NamedQuery(name = "PersonSwingapp.findByValues", query = "SELECT p FROM PersonSwingapp p WHERE p.lastname = :lastname and  p.firstname = :firstname and p.birthyear = :birthyear")})
public class PersonSwingapp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "BIRTHYEAR")
    private Integer birthyear;
    @Column(name = "EMPLOYED")
    private boolean employed;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;

    public PersonSwingapp() {
    }

    public PersonSwingapp(Long id) {
        this.id = id;
    }

    public PersonSwingapp(Person person) {
        this.birthyear = person.getBirthYear();
        this.employed = person.isEmployed();
        this.firstname = person.getFirstName();
        this.lastname = person.getLastName();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear = birthyear;
    }

    public boolean getEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonSwingapp)) {
            return false;
        }
        PersonSwingapp other = (PersonSwingapp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cv3swingapp.PersonSwingapp[ id=" + id + " ]";
    }
    
}
