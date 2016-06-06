package org.huntsboro.data.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Properties.findAll", query = "SELECT p FROM Properties p"),
    @NamedQuery(name = "Properties.findById", query = "SELECT p FROM Properties p WHERE p.id = :id"),
    @NamedQuery(name = "Properties.findByPropertyName", query = "SELECT p FROM Properties p WHERE p.propertyName = :propertyName"),
    @NamedQuery(name = "Properties.findByPropertyType", query = "SELECT p FROM Properties p WHERE p.propertyType = :propertyType"),
    @NamedQuery(name = "Properties.findByNumUnits", query = "SELECT p FROM Properties p WHERE p.numUnits = :numUnits"),
    @NamedQuery(name = "Properties.findByOwner", query = "SELECT p FROM Properties p WHERE p.owner = :owner"),
    @NamedQuery(name = "Properties.findByOperatingAccount", query = "SELECT p FROM Properties p WHERE p.operatingAccount = :operatingAccount"),
    @NamedQuery(name = "Properties.findByLeaseTerm", query = "SELECT p FROM Properties p WHERE p.leaseTerm = :leaseTerm"),
    @NamedQuery(name = "Properties.findByCountry", query = "SELECT p FROM Properties p WHERE p.country = :country"),
    @NamedQuery(name = "Properties.findByState", query = "SELECT p FROM Properties p WHERE p.state = :state"),
    @NamedQuery(name = "Properties.findByStreet", query = "SELECT p FROM Properties p WHERE p.street = :street"),
    @NamedQuery(name = "Properties.findByCity", query = "SELECT p FROM Properties p WHERE p.city = :city"),
    @NamedQuery(name = "Properties.findByZip", query = "SELECT p FROM Properties p WHERE p.zip = :zip")})
public class Properties implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "property_name")
    private String propertyName;
    @Column(name = "property_type")
    private String propertyType;
    @Column(name = "num_units")
    private Integer numUnits;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "owner")
    private String owner;
    @Column(name = "operating_account")
    private String operatingAccount;
    @Column(name = "lease_term")
    private String leaseTerm;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "Street")
    private String street;
    @Column(name = "City")
    private String city;
    @Column(name = "Zip")
    private String zip;
    @OneToMany(mappedBy = "property")
    private Collection<Units> unitsCollection;
    @OneToMany(mappedBy = "property")
    private Collection<MaintenanceRequests> maintenanceRequestsCollection;
    @Column(name = "amenities")
    private String amenities;
    
    
    
    public Properties() {
    }

    public Properties(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(Integer numUnits) {
        this.numUnits = numUnits;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOperatingAccount() {
        return operatingAccount;
    }

    public void setOperatingAccount(String operatingAccount) {
        this.operatingAccount = operatingAccount;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @XmlTransient
    public Collection<Units> getUnitsCollection() {
        return unitsCollection;
    }

    public void setUnitsCollection(Collection<Units> unitsCollection) {
        this.unitsCollection = unitsCollection;
    }

    @XmlTransient
    public Collection<MaintenanceRequests> getMaintenanceRequestsCollection() {
		return maintenanceRequestsCollection;
	}

	public void setMaintenanceRequestsCollection(Collection<MaintenanceRequests> maintenanceRequestsCollection) {
		this.maintenanceRequestsCollection = maintenanceRequestsCollection;
	}
    
	
	
	
    public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
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
        if (!(object instanceof Properties)) {
            return false;
        }
        Properties other = (Properties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.Properties[ id=" + id + " ]";
    }
    
}
