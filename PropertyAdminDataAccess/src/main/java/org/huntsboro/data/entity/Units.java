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
@Table(name = "units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Units.findAll", query = "SELECT u FROM Units u"),
    @NamedQuery(name = "Units.findById", query = "SELECT u FROM Units u WHERE u.id = :id"),
    @NamedQuery(name = "Units.findByUnitNumber", query = "SELECT u FROM Units u WHERE u.unitNumber = :unitNumber"),
    @NamedQuery(name = "Units.findByStatus", query = "SELECT u FROM Units u WHERE u.status = :status"),
    @NamedQuery(name = "Units.findBySize", query = "SELECT u FROM Units u WHERE u.size = :size"),
    @NamedQuery(name = "Units.findByCountry", query = "SELECT u FROM Units u WHERE u.country = :country"),
    @NamedQuery(name = "Units.findByStreet", query = "SELECT u FROM Units u WHERE u.street = :street"),
    @NamedQuery(name = "Units.findByCity", query = "SELECT u FROM Units u WHERE u.city = :city"),
    @NamedQuery(name = "Units.findByZip", query = "SELECT u FROM Units u WHERE u.zip = :zip"),
    @NamedQuery(name = "Units.findByState", query = "SELECT u FROM Units u WHERE u.state = :state"),
    @NamedQuery(name = "Units.findByRooms", query = "SELECT u FROM Units u WHERE u.rooms = :rooms"),
    @NamedQuery(name = "Units.findByBathroom", query = "SELECT u FROM Units u WHERE u.bathroom = :bathroom"),
    @NamedQuery(name = "Units.findByFeatures", query = "SELECT u FROM Units u WHERE u.features = :features"),
    @NamedQuery(name = "Units.findByMarketRent", query = "SELECT u FROM Units u WHERE u.marketRent = :marketRent"),
    @NamedQuery(name = "Units.findByRentalAmount", query = "SELECT u FROM Units u WHERE u.rentalAmount = :rentalAmount"),
    @NamedQuery(name = "Units.findByDepositAmount", query = "SELECT u FROM Units u WHERE u.depositAmount = :depositAmount"),
    @NamedQuery(name = "Units.findByDescription", query = "SELECT u FROM Units u WHERE u.description = :description")})
public class Units implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "unit_number")
    private Integer unitNumber;
    @Column(name = "photo")
    private String photo;
    @Column(name = "status")
    private String status;
    @Column(name = "size")
    private String size;
    @Column(name = "country")
    private String country;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "Zip")
    private String zip;
    @Column(name = "state")
    private String state;
    @Column(name = "rooms")
    private Integer rooms;
    @Column(name = "bathroom")
    private Integer bathroom;
    @Column(name = "features")
    private String features;
    @Column(name = "market_rent")
    private Integer marketRent;
    @Column(name = "rental_amount")
    private Integer rentalAmount;
    @Column(name = "deposit_amount")
    private Integer depositAmount;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "property", referencedColumnName = "id")
    @ManyToOne
    private Properties property;

    
    public Units() {
    }

    public Units(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Integer getMarketRent() {
        return marketRent;
    }

    public void setMarketRent(Integer marketRent) {
        this.marketRent = marketRent;
    }

    public Integer getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(Integer rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    public String getPropertyName() {
        return property.getPropertyName();
    }
    
    public String getAmenities() {
        return property.getAmenities();
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
        if (!(object instanceof Units)) {
            return false;
        }
        Units other = (Units) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.Units[ id=" + id + " ]";
    }
    
}
