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
@Table(name = "maintenance_requests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenanceRequests.findAll", query = "SELECT u FROM MaintenanceRequests u"),
    @NamedQuery(name = "MaintenanceRequests.findById", query = "SELECT u FROM MaintenanceRequests u WHERE u.requestId = :id")})

public class MaintenanceRequests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id")
    private Integer requestId;
    @Column(name = "issue_description")
    private String issueDescription;
    @Column(name = "maintenance_location")
    private String maintenanceLocation;
    @Column(name = "entry_allowed")
    private String entryAllowed;

    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @ManyToOne
    private Properties property;
    @Column(name="unit_number")
    private Integer unit;

    @Column(name="requested_by")
    private String requestedBy;
    
    @Column(name="cell_phone")
    private String cellPhone;
    
    @Column(name="work_phone")
    private String workPhone;
    
    @Column(name="emergency_indicator")
    private String emergencyIndicator;
    
    @Column(name="email")
    private String email;
    
    
    public MaintenanceRequests() {
    }

    public MaintenanceRequests(Integer id) {
        this.requestId = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer id) {
        this.requestId = id;
    }


    public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}


	public String getEntryAllowed() {
		return entryAllowed;
	}

	public void setEntryAllowed(String entryAllowed) {
		this.entryAllowed = entryAllowed;
	}

	

	public String getMaintenanceLocation() {
		return maintenanceLocation;
	}

	public void setMaintenanceLocation(String maintenanceLocation) {
		this.maintenanceLocation = maintenanceLocation;
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

	public String getEmergencyIndicator() {
		return emergencyIndicator;
	}

	public void setEmergencyIndicator(String emergencyIndicator) {
		this.emergencyIndicator = emergencyIndicator;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaintenanceRequests)) {
            return false;
        }
        MaintenanceRequests other = (MaintenanceRequests) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MaintenanceRequests[ requestId=" + requestId + " ]";
    }
    
}
