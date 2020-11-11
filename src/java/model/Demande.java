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
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findByIdD", query = "SELECT d FROM Demande d WHERE d.idD = :idD")
    , @NamedQuery(name = "Demande.findByNom", query = "SELECT d FROM Demande d WHERE d.nom = :nom")
    , @NamedQuery(name = "Demande.findByPr\u00e9nom", query = "SELECT d FROM Demande d WHERE d.pr\u00e9nom = :pr\u00e9nom")
    , @NamedQuery(name = "Demande.findByStuctureD", query = "SELECT d FROM Demande d WHERE d.stuctureD = :stuctureD")
    , @NamedQuery(name = "Demande.findByDateD", query = "SELECT d FROM Demande d WHERE d.dateD = :dateD")
    , @NamedQuery(name = "Demande.findByRefferenceD", query = "SELECT d FROM Demande d WHERE d.refferenceD = :refferenceD")
    , @NamedQuery(name = "Demande.findByMaterielD", query = "SELECT d FROM Demande d WHERE d.materielD = :materielD")
    , @NamedQuery(name = "Demande.findByTypeD", query = "SELECT d FROM Demande d WHERE d.typeD = :typeD")
    , @NamedQuery(name = "Demande.findByQteD", query = "SELECT d FROM Demande d WHERE d.qteD = :qteD")
    , @NamedQuery(name = "Demande.findByDureeD", query = "SELECT d FROM Demande d WHERE d.dureeD = :dureeD")
    , @NamedQuery(name = "Demande.findByProjetEffectD", query = "SELECT d FROM Demande d WHERE d.projetEffectD = :projetEffectD")
    , @NamedQuery(name = "Demande.findByNatureTrvD", query = "SELECT d FROM Demande d WHERE d.natureTrvD = :natureTrvD")
    , @NamedQuery(name = "Demande.findByVisaOpD", query = "SELECT d FROM Demande d WHERE d.visaOpD = :visaOpD")
    , @NamedQuery(name = "Demande.findByVisaAccD", query = "SELECT d FROM Demande d WHERE d.visaAccD = :visaAccD")
    , @NamedQuery(name = "Demande.findByAccordD", query = "SELECT d FROM Demande d WHERE d.accordD = :accordD")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_D")
    private Integer idD;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @Size(max = 50)
    @Column(name = "pr\u00e9nom")
    private String prénom;
    @Size(max = 50)
    @Column(name = "stucture_D")
    private String stuctureD;
    @Column(name = "date_D")
    @Temporal(TemporalType.DATE)
    private Date dateD;
    @Size(max = 50)
    @Column(name = "refference_D")
    private String refferenceD;
    @Size(max = 50)
    @Column(name = "materiel_D")
    private String materielD;
    @Size(max = 50)
    @Column(name = "type_D")
    private String typeD;
    @Size(max = 50)
    @Column(name = "qte_D")
    private String qteD;
    @Size(max = 50)
    @Column(name = "duree_D")
    private String dureeD;
    @Size(max = 50)
    @Column(name = "projetEffect_D")
    private String projetEffectD;
    @Size(max = 50)
    @Column(name = "natureTrv_D")
    private String natureTrvD;
    @Size(max = 50)
    @Column(name = "visaOp_D")
    private String visaOpD;
    @Size(max = 50)
    @Column(name = "visaAcc_D")
    private String visaAccD;
    @Size(max = 50)
    @Column(name = "accord_D")
    private String accordD;

    public Demande() {
    }

    public Demande(Integer idD) {
        this.idD = idD;
    }

    public Integer getIdD() {
        return idD;
    }

    public void setIdD(Integer idD) {
        this.idD = idD;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getStuctureD() {
        return stuctureD;
    }

    public void setStuctureD(String stuctureD) {
        this.stuctureD = stuctureD;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public String getRefferenceD() {
        return refferenceD;
    }

    public void setRefferenceD(String refferenceD) {
        this.refferenceD = refferenceD;
    }

    public String getMaterielD() {
        return materielD;
    }

    public void setMaterielD(String materielD) {
        this.materielD = materielD;
    }

    public String getTypeD() {
        return typeD;
    }

    public void setTypeD(String typeD) {
        this.typeD = typeD;
    }

    public String getQteD() {
        return qteD;
    }

    public void setQteD(String qteD) {
        this.qteD = qteD;
    }

    public String getDureeD() {
        return dureeD;
    }

    public void setDureeD(String dureeD) {
        this.dureeD = dureeD;
    }

    public String getProjetEffectD() {
        return projetEffectD;
    }

    public void setProjetEffectD(String projetEffectD) {
        this.projetEffectD = projetEffectD;
    }

    public String getNatureTrvD() {
        return natureTrvD;
    }

    public void setNatureTrvD(String natureTrvD) {
        this.natureTrvD = natureTrvD;
    }

    public String getVisaOpD() {
        return visaOpD;
    }

    public void setVisaOpD(String visaOpD) {
        this.visaOpD = visaOpD;
    }

    public String getVisaAccD() {
        return visaAccD;
    }

    public void setVisaAccD(String visaAccD) {
        this.visaAccD = visaAccD;
    }

    public String getAccordD() {
        return accordD;
    }

    public void setAccordD(String accordD) {
        this.accordD = accordD;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idD != null ? idD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idD == null && other.idD != null) || (this.idD != null && !this.idD.equals(other.idD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Demande[ idD=" + idD + " ]";
    }
    
}
