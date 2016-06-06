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
@Table(name = "broadcast_messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BroadcastMessages.findAll", query = "SELECT u FROM BroadcastMessages u"),
    @NamedQuery(name = "BroadcastMessages.findById", query = "SELECT u FROM BroadcastMessages u WHERE u.msgId = :id")})

public class BroadcastMessages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer msgId;
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @ManyToOne
    private Properties property;
    @Column(name="unit_number")
    private Integer unit;

    @Column(name="created_by")
    private String createdBy;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    public BroadcastMessages() {
    }

    

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	

	public Properties getProperty() {
        return property;
    }

    public void setProperty(Properties property) {
        this.property = property;
    }

    
    
    
    public Integer getMsgId() {
		return msgId;
	}



	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getMessageText() {
		return messageText;
	}



	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	@Override
    public int hashCode() {
        int hash = 0;
        hash += (msgId != null ? msgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BroadcastMessages)) {
            return false;
        }
        BroadcastMessages other = (BroadcastMessages) object;
        if ((this.msgId == null && other.msgId != null) || (this.msgId != null && !this.msgId.equals(other.msgId))) {
            return false;
        }
        return true;
    }



	@Override
	public String toString() {
		return "BroadcastMessages [msgId=" + msgId + ", property=" + property + ", unit=" + unit + ", createdBy="
				+ createdBy + ", messageText=" + messageText + ", createdAt=" + createdAt + "]";
	}

   
}
