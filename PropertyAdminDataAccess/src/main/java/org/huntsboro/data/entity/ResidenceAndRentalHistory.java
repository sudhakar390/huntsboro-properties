/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huntsboro.data.entity;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "residence_and_rental_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResidenceAndRentalHistory.findAll", query = "SELECT r FROM ResidenceAndRentalHistory r"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findById", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.id = :id"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByAddress", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.address = :address"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByLandlordOrManagerName", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.landlordOrManagerName = :landlordOrManagerName"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByLandlordOrManagerPhone", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.landlordOrManagerPhone = :landlordOrManagerPhone"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByMonthlyRent", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.monthlyRent = :monthlyRent"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByDurationOfResidencyFrom", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.durationOfResidencyFrom = :durationOfResidencyFrom"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByTo", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.to = :to"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByReasonForLeaving", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.reasonForLeaving = :reasonForLeaving"),
    @NamedQuery(name = "ResidenceAndRentalHistory.findByNotes", query = "SELECT r FROM ResidenceAndRentalHistory r WHERE r.notes = :notes")})
public class ResidenceAndRentalHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "address")
    private String address;
    @Column(name = "landlord_or_manager_name")
    private String landlordOrManagerName;
    @Column(name = "landlord_or_manager_phone")
    private String landlordOrManagerPhone;
    @Column(name = "monthly_rent")
    private String monthlyRent;
    @Column(name = "duration_of_residency_from")
    private String durationOfResidencyFrom;
    @Column(name = "to")
    private String to;
    @Column(name = "reason_for_leaving")
    private String reasonForLeaving;
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    @ManyToOne
    private ApplicantsAndTenants tenant;

    public ResidenceAndRentalHistory() {
    }

    public ResidenceAndRentalHistory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandlordOrManagerName() {
        return landlordOrManagerName;
    }

    public void setLandlordOrManagerName(String landlordOrManagerName) {
        this.landlordOrManagerName = landlordOrManagerName;
    }

    public String getLandlordOrManagerPhone() {
        return landlordOrManagerPhone;
    }

    public void setLandlordOrManagerPhone(String landlordOrManagerPhone) {
        this.landlordOrManagerPhone = landlordOrManagerPhone;
    }

    public String getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(String monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getDurationOfResidencyFrom() {
        return durationOfResidencyFrom;
    }

    public void setDurationOfResidencyFrom(String durationOfResidencyFrom) {
        this.durationOfResidencyFrom = durationOfResidencyFrom;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
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
        if (!(object instanceof ResidenceAndRentalHistory)) {
            return false;
        }
        ResidenceAndRentalHistory other = (ResidenceAndRentalHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.ResidenceAndRentalHistory[ id=" + id + " ]";
    }
    
}
