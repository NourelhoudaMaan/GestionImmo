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
@Table(name = "projet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projet.findAll", query = "SELECT p FROM Projet p")
    , @NamedQuery(name = "Projet.findByCodeP", query = "SELECT p FROM Projet p WHERE p.codeP = :codeP")
    , @NamedQuery(name = "Projet.findByLibilleP", query = "SELECT p FROM Projet p WHERE p.libilleP = :libilleP")
    , @NamedQuery(name = "Projet.findByDirectionP", query = "SELECT p FROM Projet p WHERE p.directionP = :directionP")})
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_P")
    private Integer codeP;
    @Size(max = 20)
    @Column(name = "libille_P")
    private String libilleP;
    @Size(max = 20)
    @Column(name = "direction_P")
    private String directionP;

    public Projet() {
    }

    public Projet(Integer codeP) {
        this.codeP = codeP;
    }

    public Integer getCodeP() {
        return codeP;
    }

    public void setCodeP(Integer codeP) {
        this.codeP = codeP;
    }

    public String getLibilleP() {
        return libilleP;
    }

    public void setLibilleP(String libilleP) {
        this.libilleP = libilleP;
    }

    public String getDirectionP() {
        return directionP;
    }

    public void setDirectionP(String directionP) {
        this.directionP = directionP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeP != null ? codeP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projet)) {
            return false;
        }
        Projet other = (Projet) object;
        if ((this.codeP == null && other.codeP != null) || (this.codeP != null && !this.codeP.equals(other.codeP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Projet[ codeP=" + codeP + " ]";
    }
    
}
