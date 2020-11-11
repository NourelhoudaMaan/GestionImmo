/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "direction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direction.findAll", query = "SELECT d FROM Direction d")
    , @NamedQuery(name = "Direction.findByCodeDr", query = "SELECT d FROM Direction d WHERE d.codeDr = :codeDr")
    , @NamedQuery(name = "Direction.findByLibilleDr", query = "SELECT d FROM Direction d WHERE d.libilleDr = :libilleDr")
    , @NamedQuery(name = "Direction.findByType", query = "SELECT d FROM Direction d WHERE d.type = :type")})
public class Direction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_Dr")
    private Integer codeDr;
    @Size(max = 20)
    @Column(name = "libille_Dr")
    private String libilleDr;
    @Size(max = 20)
    @Column(name = "type")
    private String type;

    public Direction() {
    }

    public Direction(Integer codeDr) {
        this.codeDr = codeDr;
    }

    public Integer getCodeDr() {
        return codeDr;
    }

    public void setCodeDr(Integer codeDr) {
        this.codeDr = codeDr;
    }

    public String getLibilleDr() {
        return libilleDr;
    }

    public void setLibilleDr(String libilleDr) {
        this.libilleDr = libilleDr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeDr != null ? codeDr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direction)) {
            return false;
        }
        Direction other = (Direction) object;
        if ((this.codeDr == null && other.codeDr != null) || (this.codeDr != null && !this.codeDr.equals(other.codeDr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Direction[ codeDr=" + codeDr + " ]";
    }
    
}
