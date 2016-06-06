package org.huntsboro.data.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "rental_owners")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentalOwners.findAll", query = "SELECT r FROM RentalOwners r"),
    @NamedQuery(name = "RentalOwners.findById", query = "SELECT r FROM RentalOwners r WHERE r.id = :id"),
    @NamedQuery(name = "RentalOwners.findByFirstName", query = "SELECT r FROM RentalOwners r WHERE r.firstName = :firstName"),
    @NamedQuery(name = "RentalOwners.findByLastName", query = "SELECT r FROM RentalOwners r WHERE r.lastName = :lastName"),
    @NamedQuery(name = "RentalOwners.findByCompanyName", query = "SELECT r FROM RentalOwners r WHERE r.companyName = :companyName"),
    @NamedQuery(name = "RentalOwners.findByDateOfBirth", query = "SELECT r FROM RentalOwners r WHERE r.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "RentalOwners.findByPrimaryEmail", query = "SELECT r FROM RentalOwners r WHERE r.primaryEmail = :primaryEmail"),
    @NamedQuery(name = "RentalOwners.findBySecondaryEmail", query = "SELECT r FROM RentalOwners r WHERE r.secondaryEmail = :secondaryEmail"),
    @NamedQuery(name = "RentalOwners.findByPhone", query = "SELECT r FROM RentalOwners r WHERE r.phone = :phone"),
    @NamedQuery(name = "RentalOwners.findByCountry", query = "SELECT r FROM RentalOwners r WHERE r.country = :country"),
    @NamedQuery(name = "RentalOwners.findByState", query = "SELECT r FROM RentalOwners r WHERE r.state = :state"),
    @NamedQuery(name = "RentalOwners.findByCity", query = "SELECT r FROM RentalOwners r WHERE r.city = :city"),
    @NamedQuery(name = "RentalOwners.findByStreet", query = "SELECT r FROM RentalOwners r WHERE r.street = :street"),
    @NamedQuery(name = "RentalOwners.findByZip", query = "SELECT r FROM RentalOwners r WHERE r.zip = :zip"),
    @NamedQuery(name = "RentalOwners.findByComments", query = "SELECT r FROM RentalOwners r WHERE r.comments = :comments")})
public class RentalOwners implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "primary_email")
    private String primaryEmail;
    @Column(name = "secondary_email")
    private String secondaryEmail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "Zip")
    private String zip;
    @Column(name = "comments")
    private String comments;

    public RentalOwners() {
    }

    public RentalOwners(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        if (!(object instanceof RentalOwners)) {
            return false;
        }
        RentalOwners other = (RentalOwners) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.RentalOwners[ id=" + id + " ]";
    }
    
}
