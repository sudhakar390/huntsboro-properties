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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "applications_and_leases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationsAndLeases.findAll", query = "SELECT a FROM ApplicationsAndLeases a"),
    @NamedQuery(name = "ApplicationsAndLeases.findById", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.id = :id"),
    @NamedQuery(name = "ApplicationsAndLeases.findByTenants", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.tenants = :tenants"),
    @NamedQuery(name = "ApplicationsAndLeases.findByStatus", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.status = :status"),
    @NamedQuery(name = "ApplicationsAndLeases.findByProperty", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.property = :property"),
    @NamedQuery(name = "ApplicationsAndLeases.findByUnit", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.unit = :unit"),
    @NamedQuery(name = "ApplicationsAndLeases.findByType", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.type = :type"),
    @NamedQuery(name = "ApplicationsAndLeases.findByTotalNumOccupants", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.totalNumOccupants = :totalNumOccupants"),
    @NamedQuery(name = "ApplicationsAndLeases.findByStartDate", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "ApplicationsAndLeases.findByEndDate", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "ApplicationsAndLeases.findByRecurringChargesFrequency", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.recurringChargesFrequency = :recurringChargesFrequency"),
    @NamedQuery(name = "ApplicationsAndLeases.findByNextDueDate", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.nextDueDate = :nextDueDate"),
    @NamedQuery(name = "ApplicationsAndLeases.findByRent", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.rent = :rent"),
    @NamedQuery(name = "ApplicationsAndLeases.findBySecurityDeposit", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.securityDeposit = :securityDeposit"),
    @NamedQuery(name = "ApplicationsAndLeases.findBySecurityDepositDate", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.securityDepositDate = :securityDepositDate"),
    @NamedQuery(name = "ApplicationsAndLeases.findByEmergencyContact", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.emergencyContact = :emergencyContact"),
    @NamedQuery(name = "ApplicationsAndLeases.findByCoSignerDetails", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.coSignerDetails = :coSignerDetails"),
    @NamedQuery(name = "ApplicationsAndLeases.findByNotes", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.notes = :notes"),
   // @NamedQuery(name = "ApplicationsAndLeases.findByPropertyAndUnit", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.property = :property AND a.unit =:unit"),
    @NamedQuery(name = "ApplicationsAndLeases.findByAgreement", query = "SELECT a FROM ApplicationsAndLeases a WHERE a.agreement = :agreement")})


public class ApplicationsAndLeases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tenants")
    private Integer tenants;
    @Column(name = "status")
    private String status;
    @Column(name = "property")
    private Integer property;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "type")
    private String type;
    @Column(name = "total_num_occupants")
    private Integer totalNumOccupants;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_Date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "recurring_charges_frequency")
    private String recurringChargesFrequency;
    @Column(name = "next_due_date")
    @Temporal(TemporalType.DATE)
    private Date nextDueDate;
    @Column(name = "rent")
    private Integer rent;
    @Column(name = "security_deposit")
    private Integer securityDeposit;
    @Column(name = "security_deposit_date")
    @Temporal(TemporalType.DATE)
    private Date securityDepositDate;
    @Column(name = "emergency_contact")
    private String emergencyContact;
    @Column(name = "co_signer_details")
    private String coSignerDetails;
    @Column(name = "notes")
    private String notes;
    @Column(name = "agreement")
    private String agreement;

    public ApplicationsAndLeases() {
    }

    public ApplicationsAndLeases(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenants() {
        return tenants;
    }

    public void setTenants(Integer tenants) {
        this.tenants = tenants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotalNumOccupants() {
        return totalNumOccupants;
    }

    public void setTotalNumOccupants(Integer totalNumOccupants) {
        this.totalNumOccupants = totalNumOccupants;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRecurringChargesFrequency() {
        return recurringChargesFrequency;
    }

    public void setRecurringChargesFrequency(String recurringChargesFrequency) {
        this.recurringChargesFrequency = recurringChargesFrequency;
    }

    public Date getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(Date nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Integer securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Date getSecurityDepositDate() {
        return securityDepositDate;
    }

    public void setSecurityDepositDate(Date securityDepositDate) {
        this.securityDepositDate = securityDepositDate;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getCoSignerDetails() {
        return coSignerDetails;
    }

    public void setCoSignerDetails(String coSignerDetails) {
        this.coSignerDetails = coSignerDetails;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
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
        if (!(object instanceof ApplicationsAndLeases)) {
            return false;
        }
        ApplicationsAndLeases other = (ApplicationsAndLeases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.ApplicationsAndLeases[ id=" + id + " ]";
    }
    
}
