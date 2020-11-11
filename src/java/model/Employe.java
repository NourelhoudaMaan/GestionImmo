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
@Table(name = "employe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")
    , @NamedQuery(name = "Employe.findByMatriculeE", query = "SELECT e FROM Employe e WHERE e.matriculeE = :matriculeE")
    , @NamedQuery(name = "Employe.findByNomE", query = "SELECT e FROM Employe e WHERE e.nomE = :nomE")
    , @NamedQuery(name = "Employe.findByPrenomE", query = "SELECT e FROM Employe e WHERE e.prenomE = :prenomE")
    , @NamedQuery(name = "Employe.findByEmailE", query = "SELECT e FROM Employe e WHERE e.emailE = :emailE")})
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricule_E")
    private Integer matriculeE;
    @Size(max = 50)
    @Column(name = "nom_E")
    private String nomE;
    @Size(max = 50)
    @Column(name = "prenom_E")
    private String prenomE;
    @Size(max = 50)
    @Column(name = "email_E")
    private String emailE;

    public Employe() {
    }

    public Employe(Integer matriculeE) {
        this.matriculeE = matriculeE;
    }

    public Integer getMatriculeE() {
        return matriculeE;
    }

    public void setMatriculeE(Integer matriculeE) {
        this.matriculeE = matriculeE;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public String getEmailE() {
        return emailE;
    }

    public void setEmailE(String emailE) {
        this.emailE = emailE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculeE != null ? matriculeE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.matriculeE == null && other.matriculeE != null) || (this.matriculeE != null && !this.matriculeE.equals(other.matriculeE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Employe[ matriculeE=" + matriculeE + " ]";
    }
    
}
