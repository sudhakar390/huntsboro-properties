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

/**
 *
 * @author pinjasur
 */
@Entity
@Table(name = "references")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "References.findAll", query = "SELECT r FROM References r"),
    @NamedQuery(name = "References.findById", query = "SELECT r FROM References r WHERE r.id = :id"),
    @NamedQuery(name = "References.findByReferenceName", query = "SELECT r FROM References r WHERE r.referenceName = :referenceName"),
    @NamedQuery(name = "References.findByPhone", query = "SELECT r FROM References r WHERE r.phone = :phone")})

public class References implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "reference_name")
    private String referenceName;
    @Column(name = "phone")
    private String phone;
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    @ManyToOne
    private ApplicantsAndTenants tenant;

    public References() {
    }

    public References(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        if (!(object instanceof References)) {
            return false;
        }
        References other = (References) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.services.References[ id=" + id + " ]";
    }
    
}
