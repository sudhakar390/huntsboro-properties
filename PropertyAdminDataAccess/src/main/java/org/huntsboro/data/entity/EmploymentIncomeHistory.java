/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huntsboro.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "employment_income_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmploymentIncomeHistory.findAll", query = "SELECT e FROM EmploymentIncomeHistory e"),
    @NamedQuery(name = "EmploymentIncomeHistory.findById", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.id = :id"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByEmployerName", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.employerName = :employerName"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByCity", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.city = :city"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByEmployerPhone", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.employerPhone = :employerPhone"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByEmployedFrom", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.employedFrom = :employedFrom"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByEmployedTill", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.employedTill = :employedTill"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByOccupation", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.occupation = :occupation"),
    @NamedQuery(name = "EmploymentIncomeHistory.findByNotes", query = "SELECT e FROM EmploymentIncomeHistory e WHERE e.notes = :notes")})
public class EmploymentIncomeHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "employer_name")
    private String employerName;
    @Column(name = "city")
    private String city;
    @Column(name = "employer_phone")
    private String employerPhone;
    @Column(name = "employed_from")
    @Temporal(TemporalType.DATE)
    private Date employedFrom;
    @Column(name = "employed_till")
    @Temporal(TemporalType.DATE)
    private Date employedTill;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    @ManyToOne
    private ApplicantsAndTenants tenant;

    public EmploymentIncomeHistory() {
    }

    public EmploymentIncomeHistory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public Date getEmployedFrom() {
        return employedFrom;
    }

    public void setEmployedFrom(Date employedFrom) {
        this.employedFrom = employedFrom;
    }

    public Date getEmployedTill() {
        return employedTill;
    }

    public void setEmployedTill(Date employedTill) {
        this.employedTill = employedTill;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ApplicantsAndTenants getTenant() {
        return tenant;
    }

    public void setTenant(ApplicantsAndTenants tenant) {
        this.tenant = tenant;
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
        if (!(object instanceof EmploymentIncomeHistory)) {
            return false;
        }
        EmploymentIncomeHistory other = (EmploymentIncomeHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.EmploymentIncomeHistory[ id=" + id + " ]";
    }
    
}
