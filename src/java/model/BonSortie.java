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
@Table(name = "bon_sortie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonSortie.findAll", query = "SELECT b FROM BonSortie b")
    , @NamedQuery(name = "BonSortie.findByNumBS", query = "SELECT b FROM BonSortie b WHERE b.numBS = :numBS")
    , @NamedQuery(name = "BonSortie.findByDateBS", query = "SELECT b FROM BonSortie b WHERE b.dateBS = :dateBS")
    , @NamedQuery(name = "BonSortie.findByEmployeBS", query = "SELECT b FROM BonSortie b WHERE b.employeBS = :employeBS")
    , @NamedQuery(name = "BonSortie.findByDepartBS", query = "SELECT b FROM BonSortie b WHERE b.departBS = :departBS")
    , @NamedQuery(name = "BonSortie.findByServiceBS", query = "SELECT b FROM BonSortie b WHERE b.serviceBS = :serviceBS")
    , @NamedQuery(name = "BonSortie.findByBlocBS", query = "SELECT b FROM BonSortie b WHERE b.blocBS = :blocBS")
    , @NamedQuery(name = "BonSortie.findByBureauBS", query = "SELECT b FROM BonSortie b WHERE b.bureauBS = :bureauBS")
    , @NamedQuery(name = "BonSortie.findByCodeMBE", query = "SELECT b FROM BonSortie b WHERE b.codeMBE = :codeMBE")
    , @NamedQuery(name = "BonSortie.findByTypeBE", query = "SELECT b FROM BonSortie b WHERE b.typeBE = :typeBE")})
public class BonSortie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_BS")
    private Integer numBS;
    @Column(name = "date_BS")
    @Temporal(TemporalType.DATE)
    private Date dateBS;
    @Size(max = 50)
    @Column(name = "employe_BS")
    private String employeBS;
    @Size(max = 50)
    @Column(name = "depart_BS")
    private String departBS;
    @Size(max = 50)
    @Column(name = "service_BS")
    private String serviceBS;
    @Size(max = 50)
    @Column(name = "bloc_BS")
    private String blocBS;
    @Column(name = "bureau_BS")
    private Integer bureauBS;
    @Column(name = "codeM_BE")
    private Integer codeMBE;
    @Size(max = 50)
    @Column(name = "type_BE")
    private String typeBE;

    public BonSortie() {
    }

    public BonSortie(Integer numBS) {
        this.numBS = numBS;
    }

    public Integer getNumBS() {
        return numBS;
    }

    public void setNumBS(Integer numBS) {
        this.numBS = numBS;
    }

    public Date getDateBS() {
        return dateBS;
    }

    public void setDateBS(Date dateBS) {
        this.dateBS = dateBS;
    }

    public String getEmployeBS() {
        return employeBS;
    }

    public void setEmployeBS(String employeBS) {
        this.employeBS = employeBS;
    }

    public String getDepartBS() {
        return departBS;
    }

    public void setDepartBS(String departBS) {
        this.departBS = departBS;
    }

    public String getServiceBS() {
        return serviceBS;
    }

    public void setServiceBS(String serviceBS) {
        this.serviceBS = serviceBS;
    }

    public String getBlocBS() {
        return blocBS;
    }

    public void setBlocBS(String blocBS) {
        this.blocBS = blocBS;
    }

    public Integer getBureauBS() {
        return bureauBS;
    }

    public void setBureauBS(Integer bureauBS) {
        this.bureauBS = bureauBS;
    }

    public Integer getCodeMBE() {
        return codeMBE;
    }

    public void setCodeMBE(Integer codeMBE) {
        this.codeMBE = codeMBE;
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
        hash += (numBS != null ? numBS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonSortie)) {
            return false;
        }
        BonSortie other = (BonSortie) object;
        if ((this.numBS == null && other.numBS != null) || (this.numBS != null && !this.numBS.equals(other.numBS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BonSortie[ numBS=" + numBS + " ]";
    }
    
}
