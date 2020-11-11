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
@Table(name = "inventaire_materiel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventaireMateriel.findAll", query = "SELECT i FROM InventaireMateriel i")
    , @NamedQuery(name = "InventaireMateriel.findByIdIM", query = "SELECT i FROM InventaireMateriel i WHERE i.idIM = :idIM")
    , @NamedQuery(name = "InventaireMateriel.findByCodeIM", query = "SELECT i FROM InventaireMateriel i WHERE i.codeIM = :codeIM")
    , @NamedQuery(name = "InventaireMateriel.findByMarqueIM", query = "SELECT i FROM InventaireMateriel i WHERE i.marqueIM = :marqueIM")
    , @NamedQuery(name = "InventaireMateriel.findByTypeIM", query = "SELECT i FROM InventaireMateriel i WHERE i.typeIM = :typeIM")
    , @NamedQuery(name = "InventaireMateriel.findByDirectionIM", query = "SELECT i FROM InventaireMateriel i WHERE i.directionIM = :directionIM")
    , @NamedQuery(name = "InventaireMateriel.findByDepartementIM", query = "SELECT i FROM InventaireMateriel i WHERE i.departementIM = :departementIM")
    , @NamedQuery(name = "InventaireMateriel.findByServiceIM", query = "SELECT i FROM InventaireMateriel i WHERE i.serviceIM = :serviceIM")
    , @NamedQuery(name = "InventaireMateriel.findByEtatIM", query = "SELECT i FROM InventaireMateriel i WHERE i.etatIM = :etatIM")
    , @NamedQuery(name = "InventaireMateriel.findByNumDeplacementIM", query = "SELECT i FROM InventaireMateriel i WHERE i.numDeplacementIM = :numDeplacementIM")
    , @NamedQuery(name = "InventaireMateriel.findByDateDeplacementIM", query = "SELECT i FROM InventaireMateriel i WHERE i.dateDeplacementIM = :dateDeplacementIM")
    , @NamedQuery(name = "InventaireMateriel.findByDirectionDeplcmIM", query = "SELECT i FROM InventaireMateriel i WHERE i.directionDeplcmIM = :directionDeplcmIM")
    , @NamedQuery(name = "InventaireMateriel.findByBlocDeplcm", query = "SELECT i FROM InventaireMateriel i WHERE i.blocDeplcm = :blocDeplcm")
    , @NamedQuery(name = "InventaireMateriel.findByEtageDeplcmIM", query = "SELECT i FROM InventaireMateriel i WHERE i.etageDeplcmIM = :etageDeplcmIM")
    , @NamedQuery(name = "InventaireMateriel.findByNbrIM", query = "SELECT i FROM InventaireMateriel i WHERE i.nbrIM = :nbrIM")
    , @NamedQuery(name = "InventaireMateriel.findByEquipeAIM", query = "SELECT i FROM InventaireMateriel i WHERE i.equipeAIM = :equipeAIM")
    , @NamedQuery(name = "InventaireMateriel.findByEquipeBIM", query = "SELECT i FROM InventaireMateriel i WHERE i.equipeBIM = :equipeBIM")
    , @NamedQuery(name = "InventaireMateriel.findByEquipeCIM", query = "SELECT i FROM InventaireMateriel i WHERE i.equipeCIM = :equipeCIM")})
public class InventaireMateriel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_IM")
    private Integer idIM;
    @Column(name = "code_IM")
    private Integer codeIM;
    @Size(max = 20)
    @Column(name = "marque_IM")
    private String marqueIM;
    @Size(max = 20)
    @Column(name = "type_IM")
    private String typeIM;
    @Size(max = 20)
    @Column(name = "direction_IM")
    private String directionIM;
    @Size(max = 20)
    @Column(name = "departement_IM")
    private String departementIM;
    @Size(max = 20)
    @Column(name = "service_IM")
    private String serviceIM;
    @Size(max = 20)
    @Column(name = "etat_IM")
    private String etatIM;
    @Column(name = "numDeplacement_IM")
    private Integer numDeplacementIM;
    @Column(name = "dateDeplacement_IM")
    @Temporal(TemporalType.DATE)
    private Date dateDeplacementIM;
    @Size(max = 20)
    @Column(name = "directionDeplcm_IM")
    private String directionDeplcmIM;
    @Size(max = 20)
    @Column(name = "blocDeplcm")
    private String blocDeplcm;
    @Size(max = 20)
    @Column(name = "etageDeplcm_IM")
    private String etageDeplcmIM;
    @Column(name = "nbr_IM")
    private Integer nbrIM;
    @Size(max = 20)
    @Column(name = "equipeA_IM")
    private String equipeAIM;
    @Size(max = 20)
    @Column(name = "equipeB_IM")
    private String equipeBIM;
    @Size(max = 20)
    @Column(name = "equipeC_IM")
    private String equipeCIM;

    public InventaireMateriel() {
    }

    public InventaireMateriel(Integer idIM) {
        this.idIM = idIM;
    }

    public Integer getIdIM() {
        return idIM;
    }

    public void setIdIM(Integer idIM) {
        this.idIM = idIM;
    }

    public Integer getCodeIM() {
        return codeIM;
    }

    public void setCodeIM(Integer codeIM) {
        this.codeIM = codeIM;
    }

    public String getMarqueIM() {
        return marqueIM;
    }

    public void setMarqueIM(String marqueIM) {
        this.marqueIM = marqueIM;
    }

    public String getTypeIM() {
        return typeIM;
    }

    public void setTypeIM(String typeIM) {
        this.typeIM = typeIM;
    }

    public String getDirectionIM() {
        return directionIM;
    }

    public void setDirectionIM(String directionIM) {
        this.directionIM = directionIM;
    }

    public String getDepartementIM() {
        return departementIM;
    }

    public void setDepartementIM(String departementIM) {
        this.departementIM = departementIM;
    }

    public String getServiceIM() {
        return serviceIM;
    }

    public void setServiceIM(String serviceIM) {
        this.serviceIM = serviceIM;
    }

    public String getEtatIM() {
        return etatIM;
    }

    public void setEtatIM(String etatIM) {
        this.etatIM = etatIM;
    }

    public Integer getNumDeplacementIM() {
        return numDeplacementIM;
    }

    public void setNumDeplacementIM(Integer numDeplacementIM) {
        this.numDeplacementIM = numDeplacementIM;
    }

    public Date getDateDeplacementIM() {
        return dateDeplacementIM;
    }

    public void setDateDeplacementIM(Date dateDeplacementIM) {
        this.dateDeplacementIM = dateDeplacementIM;
    }

    public String getDirectionDeplcmIM() {
        return directionDeplcmIM;
    }

    public void setDirectionDeplcmIM(String directionDeplcmIM) {
        this.directionDeplcmIM = directionDeplcmIM;
    }

    public String getBlocDeplcm() {
        return blocDeplcm;
    }

    public void setBlocDeplcm(String blocDeplcm) {
        this.blocDeplcm = blocDeplcm;
    }

    public String getEtageDeplcmIM() {
        return etageDeplcmIM;
    }

    public void setEtageDeplcmIM(String etageDeplcmIM) {
        this.etageDeplcmIM = etageDeplcmIM;
    }

    public Integer getNbrIM() {
        return nbrIM;
    }

    public void setNbrIM(Integer nbrIM) {
        this.nbrIM = nbrIM;
    }

    public String getEquipeAIM() {
        return equipeAIM;
    }

    public void setEquipeAIM(String equipeAIM) {
        this.equipeAIM = equipeAIM;
    }

    public String getEquipeBIM() {
        return equipeBIM;
    }

    public void setEquipeBIM(String equipeBIM) {
        this.equipeBIM = equipeBIM;
    }

    public String getEquipeCIM() {
        return equipeCIM;
    }

    public void setEquipeCIM(String equipeCIM) {
        this.equipeCIM = equipeCIM;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIM != null ? idIM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventaireMateriel)) {
            return false;
        }
        InventaireMateriel other = (InventaireMateriel) object;
        if ((this.idIM == null && other.idIM != null) || (this.idIM != null && !this.idIM.equals(other.idIM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.InventaireMateriel[ idIM=" + idIM + " ]";
    }
    
}
