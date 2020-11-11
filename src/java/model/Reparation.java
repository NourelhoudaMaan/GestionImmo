/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "reparation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparation.findAll", query = "SELECT r FROM Reparation r")
    , @NamedQuery(name = "Reparation.findByIdR", query = "SELECT r FROM Reparation r WHERE r.idR = :idR")
    , @NamedQuery(name = "Reparation.findByCodeMR", query = "SELECT r FROM Reparation r WHERE r.codeMR = :codeMR")
    , @NamedQuery(name = "Reparation.findByMarckR", query = "SELECT r FROM Reparation r WHERE r.marckR = :marckR")
    , @NamedQuery(name = "Reparation.findByCategorieMR", query = "SELECT r FROM Reparation r WHERE r.categorieMR = :categorieMR")
    , @NamedQuery(name = "Reparation.findByTypeMR", query = "SELECT r FROM Reparation r WHERE r.typeMR = :typeMR")
    , @NamedQuery(name = "Reparation.findByEtatMR", query = "SELECT r FROM Reparation r WHERE r.etatMR = :etatMR")
    , @NamedQuery(name = "Reparation.findByRaparationR", query = "SELECT r FROM Reparation r WHERE r.raparationR = :raparationR")
    , @NamedQuery(name = "Reparation.findByDateDebutR", query = "SELECT r FROM Reparation r WHERE r.dateDebutR = :dateDebutR")
    , @NamedQuery(name = "Reparation.findByDateFinR", query = "SELECT r FROM Reparation r WHERE r.dateFinR = :dateFinR")})
public class Reparation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_R")
    private Integer idR;
    @Column(name = "codeM_R")
    private Integer codeMR;
    @Size(max = 50)
    @Column(name = "marck_R")
    private String marckR;
    @Size(max = 50)
    @Column(name = "categorieM_R")
    private String categorieMR;
    @Size(max = 50)
    @Column(name = "typeM_R")
    private String typeMR;
    @Size(max = 50)
    @Column(name = "etatM_R")
    private String etatMR;
    @Size(max = 50)
    @Column(name = "raparation_R")
    private String raparationR;
    @Column(name = "dateDebut_R")
    @Temporal(TemporalType.DATE)
    private Date dateDebutR;
    @Column(name = "dateFin_R")
    @Temporal(TemporalType.DATE)
    private Date dateFinR;

    public Reparation() {
    }

    public Reparation(Integer idR) {
        this.idR = idR;
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public Integer getCodeMR() {
        return codeMR;
    }

    public void setCodeMR(Integer codeMR) {
        this.codeMR = codeMR;
    }

    public String getMarckR() {
        return marckR;
    }

    public void setMarckR(String marckR) {
        this.marckR = marckR;
    }

    public String getCategorieMR() {
        return categorieMR;
    }

    public void setCategorieMR(String categorieMR) {
        this.categorieMR = categorieMR;
    }

    public String getTypeMR() {
        return typeMR;
    }

    public void setTypeMR(String typeMR) {
        this.typeMR = typeMR;
    }

    public String getEtatMR() {
        return etatMR;
    }

    public void setEtatMR(String etatMR) {
        this.etatMR = etatMR;
    }

    public String getRaparationR() {
        return raparationR;
    }

    public void setRaparationR(String raparationR) {
        this.raparationR = raparationR;
    }

    public Date getDateDebutR() {
        return dateDebutR;
    }

    public void setDateDebutR(Date dateDebutR) {
        this.dateDebutR = dateDebutR;
    }

    public Date getDateFinR() {
        return dateFinR;
    }

    public void setDateFinR(Date dateFinR) {
        this.dateFinR = dateFinR;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idR != null ? idR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparation)) {
            return false;
        }
        Reparation other = (Reparation) object;
        if ((this.idR == null && other.idR != null) || (this.idR != null && !this.idR.equals(other.idR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reparation[ idR=" + idR + " ]";
    }
    
}
