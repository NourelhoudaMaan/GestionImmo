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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "bon_entre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonEntre.findAll", query = "SELECT b FROM BonEntre b")
    , @NamedQuery(name = "BonEntre.findByNumBE", query = "SELECT b FROM BonEntre b WHERE b.numBE = :numBE")
    , @NamedQuery(name = "BonEntre.findByNumFacteur", query = "SELECT b FROM BonEntre b WHERE b.numFacteur = :numFacteur")
    , @NamedQuery(name = "BonEntre.findByDateFacteur", query = "SELECT b FROM BonEntre b WHERE b.dateFacteur = :dateFacteur")
    , @NamedQuery(name = "BonEntre.findByFournBE", query = "SELECT b FROM BonEntre b WHERE b.fournBE = :fournBE")
    , @NamedQuery(name = "BonEntre.findByDateBE", query = "SELECT b FROM BonEntre b WHERE b.dateBE = :dateBE")
    , @NamedQuery(name = "BonEntre.findByCategorieMBE", query = "SELECT b FROM BonEntre b WHERE b.categorieMBE = :categorieMBE")
    , @NamedQuery(name = "BonEntre.findByMarqueMBE", query = "SELECT b FROM BonEntre b WHERE b.marqueMBE = :marqueMBE")
    , @NamedQuery(name = "BonEntre.findByTypeBE", query = "SELECT b FROM BonEntre b WHERE b.typeBE = :typeBE")})
public class BonEntre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_BE")
    private Integer numBE;
    @Column(name = "num_Facteur")
    private Integer numFacteur;
    @Column(name = "date_Facteur")
    @Temporal(TemporalType.DATE)
    private Date dateFacteur;
    @Size(max = 50)
    @Column(name = "fourn_BE")
    private String fournBE;
    @Column(name = "date_BE")
    @Temporal(TemporalType.DATE)
    private Date dateBE;
    @Size(max = 50)
    @Column(name = "categorieM_BE")
    private String categorieMBE;
    @Size(max = 50)
    @Column(name = "marqueM_BE")
    private String marqueMBE;
    @Size(max = 50)
    @Column(name = "type_BE")
    private String typeBE;

    public BonEntre() {
    }

    public BonEntre(Integer numBE) {
        this.numBE = numBE;
    }

    public Integer getNumBE() {
        return numBE;
    }

    public void setNumBE(Integer numBE) {
        this.numBE = numBE;
    }

    public Integer getNumFacteur() {
        return numFacteur;
    }

    public void setNumFacteur(Integer numFacteur) {
        this.numFacteur = numFacteur;
    }

    public Date getDateFacteur() {
        return dateFacteur;
    }

    public void setDateFacteur(Date dateFacteur) {
        this.dateFacteur = dateFacteur;
    }

    public String getFournBE() {
        return fournBE;
    }

    public void setFournBE(String fournBE) {
        this.fournBE = fournBE;
    }

    public Date getDateBE() {
        return dateBE;
    }

    public void setDateBE(Date dateBE) {
        this.dateBE = dateBE;
    }

    public String getCategorieMBE() {
        return categorieMBE;
    }

    public void setCategorieMBE(String categorieMBE) {
        this.categorieMBE = categorieMBE;
    }

    public String getMarqueMBE() {
        return marqueMBE;
    }

    public void setMarqueMBE(String marqueMBE) {
        this.marqueMBE = marqueMBE;
    }

    public String getTypeBE() {
        return typeBE;
    }

    public void setTypeBE(String typeBE) {
        this.typeBE = typeBE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numBE != null ? numBE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonEntre)) {
            return false;
        }
        BonEntre other = (BonEntre) object;
        if ((this.numBE == null && other.numBE != null) || (this.numBE != null && !this.numBE.equals(other.numBE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BonEntre[ numBE=" + numBE + " ]";
    }
    
}
