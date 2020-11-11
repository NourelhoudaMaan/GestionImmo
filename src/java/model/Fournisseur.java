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
@Table(name = "fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f")
    , @NamedQuery(name = "Fournisseur.findByIdF", query = "SELECT f FROM Fournisseur f WHERE f.idF = :idF")
    , @NamedQuery(name = "Fournisseur.findByNomF", query = "SELECT f FROM Fournisseur f WHERE f.nomF = :nomF")
    , @NamedQuery(name = "Fournisseur.findByPrenomF", query = "SELECT f FROM Fournisseur f WHERE f.prenomF = :prenomF")
    , @NamedQuery(name = "Fournisseur.findByTeleF", query = "SELECT f FROM Fournisseur f WHERE f.teleF = :teleF")
    , @NamedQuery(name = "Fournisseur.findByFaxF", query = "SELECT f FROM Fournisseur f WHERE f.faxF = :faxF")})
public class Fournisseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_F")
    private Integer idF;
    @Size(max = 20)
    @Column(name = "nom_F")
    private String nomF;
    @Size(max = 20)
    @Column(name = "prenom_F")
    private String prenomF;
    @Column(name = "tele_F")
    private Integer teleF;
    @Column(name = "fax_F")
    private Integer faxF;

    public Fournisseur() {
    }

    public Fournisseur(Integer idF) {
        this.idF = idF;
    }

    public Integer getIdF() {
        return idF;
    }

    public void setIdF(Integer idF) {
        this.idF = idF;
    }

    public String getNomF() {
        return nomF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public String getPrenomF() {
        return prenomF;
    }

    public void setPrenomF(String prenomF) {
        this.prenomF = prenomF;
    }

    public Integer getTeleF() {
        return teleF;
    }

    public void setTeleF(Integer teleF) {
        this.teleF = teleF;
    }

    public Integer getFaxF() {
        return faxF;
    }

    public void setFaxF(Integer faxF) {
        this.faxF = faxF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idF != null ? idF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.idF == null && other.idF != null) || (this.idF != null && !this.idF.equals(other.idF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Fournisseur[ idF=" + idF + " ]";
    }
    
}
