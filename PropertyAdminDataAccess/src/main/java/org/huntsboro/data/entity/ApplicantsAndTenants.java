/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huntsboro.data.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "applicants_and_tenants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicantsAndTenants.findAll", query = "SELECT a FROM ApplicantsAndTenants a"),
    @NamedQuery(name = "ApplicantsAndTenants.findById", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.id = :id"),
    @NamedQuery(name = "ApplicantsAndTenants.findByLastName", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "ApplicantsAndTenants.findByFirstName", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "ApplicantsAndTenants.findByEmail", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.email = :email"),
    @NamedQuery(name = "ApplicantsAndTenants.findByCellPhone", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.cellPhone = :phone"),
    @NamedQuery(name = "ApplicantsAndTenants.findByBirthDate", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.birthDate = :birthDate"),
    @NamedQuery(name = "ApplicantsAndTenants.findByDriverLicenseNumber", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.driverLicenseNumber = :driverLicenseNumber"),
    @NamedQuery(name = "ApplicantsAndTenants.findByDriverLicenseState", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.driverLicenseState = :driverLicenseState"),
    @NamedQuery(name = "ApplicantsAndTenants.findByRequestedLeaseTerm", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.requestedLeaseTerm = :requestedLeaseTerm"),
    @NamedQuery(name = "ApplicantsAndTenants.findByMonthlyGrossPay", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.monthlyGrossPay = :monthlyGrossPay"),
    @NamedQuery(name = "ApplicantsAndTenants.findByAdditionalIncome", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.additionalIncome = :additionalIncome"),
    @NamedQuery(name = "ApplicantsAndTenants.findByAssets", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.assets = :assets"),
    @NamedQuery(name = "ApplicantsAndTenants.findByStatus", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.status = :status"),
    @NamedQuery(name = "ApplicantsAndTenants.findByNotes", query = "SELECT a FROM ApplicantsAndTenants a WHERE a.notes = :notes")})

public class ApplicantsAndTenants implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email")
    private String email;    // this is unique and will be the username for the resident portal
    @Column(name = "cell_phone")
    private String cellPhone;
    @Column(name = "work_phone")
    private String workPhone;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;
    @Column(name = "driver_license_state")
    private String driverLicenseState;
    @Column(name = "requested_lease_term")
    private String requestedLeaseTerm;
    @Column(name = "monthly_gross_pay")
    private String monthlyGrossPay;
    @Column(name = "additional_income")
    private String additionalIncome;
    @Column(name = "assets")
    private String assets;
    @Column(name = "status")
    private String status;
    @Column(name = "notes")
    private String notes;
    @OneToMany(mappedBy = "tenant")
    private Collection<References> referencesCollection;
    @OneToMany(mappedBy = "tenant")
    private Collection<EmploymentIncomeHistory> employmentIncomeHistoryCollection;
    @OneToMany(mappedBy = "tenant")
    private Collection<ResidenceAndRentalHistory> residenceAndRentalHistoryCollection;

    
    @Column(name = "tenant_password")
    private String password;
    
    public ApplicantsAndTenants() {
    }

	public ApplicantsAndTenants(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverLicenseState() {
        return driverLicenseState;
    }

    public void setDriverLicenseState(String driverLicenseState) {
        this.driverLicenseState = driverLicenseState;
    }

    public String getRequestedLeaseTerm() {
        return requestedLeaseTerm;
    }

    public void setRequestedLeaseTerm(String requestedLeaseTerm) {
        this.requestedLeaseTerm = requestedLeaseTerm;
    }

    public String getMonthlyGrossPay() {
        return monthlyGrossPay;
    }

    public void setMonthlyGrossPay(String monthlyGrossPay) {
        this.monthlyGrossPay = monthlyGrossPay;
    }

    public String getAdditionalIncome() {
        return additionalIncome;
    }

    public void setAdditionalIncome(String additionalIncome) {
        this.additionalIncome = additionalIncome;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public Collection<References> getReferencesCollection() {
        return referencesCollection;
    }

    public void setReferencesCollection(Collection<References> referencesCollection) {
        this.referencesCollection = referencesCollection;
    }

    @XmlTransient
    public Collection<EmploymentIncomeHistory> getEmploymentIncomeHistoryCollection() {
        return employmentIncomeHistoryCollection;
    }

    public void setEmploymentIncomeHistoryCollection(Collection<EmploymentIncomeHistory> employmentIncomeHistoryCollection) {
        this.employmentIncomeHistoryCollection = employmentIncomeHistoryCollection;
    }

    @XmlTransient
    public Collection<ResidenceAndRentalHistory> getResidenceAndRentalHistoryCollection() {
        return residenceAndRentalHistoryCollection;
    }

    public void setResidenceAndRentalHistoryCollection(Collection<ResidenceAndRentalHistory> residenceAndRentalHistoryCollection) {
        this.residenceAndRentalHistoryCollection = residenceAndRentalHistoryCollection;
    }


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
        if (!(object instanceof ApplicantsAndTenants)) {
            return false;
        }
        ApplicantsAndTenants other = (ApplicantsAndTenants) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.ApplicantsAndTenants[ id=" + id + " ]";
    }
    
}
