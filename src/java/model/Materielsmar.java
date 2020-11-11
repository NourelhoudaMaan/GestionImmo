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
@Table(name = "materielsmar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materielsmar.findAll", query = "SELECT m FROM Materielsmar m")
    , @NamedQuery(name = "Materielsmar.findByIdM", query = "SELECT m FROM Materielsmar m WHERE m.idM = :idM")
    , @NamedQuery(name = "Materielsmar.findByArticleM", query = "SELECT m FROM Materielsmar m WHERE m.articleM = :articleM")
    , @NamedQuery(name = "Materielsmar.findByCompteM", query = "SELECT m FROM Materielsmar m WHERE m.compteM = :compteM")
    , @NamedQuery(name = "Materielsmar.findByDesiniationM", query = "SELECT m FROM Materielsmar m WHERE m.desiniationM = :desiniationM")
    , @NamedQuery(name = "Materielsmar.findByCodeM", query = "SELECT m FROM Materielsmar m WHERE m.codeM = :codeM")
    , @NamedQuery(name = "Materielsmar.findByNumSerieM", query = "SELECT m FROM Materielsmar m WHERE m.numSerieM = :numSerieM")
    , @NamedQuery(name = "Materielsmar.findByMarckM", query = "SELECT m FROM Materielsmar m WHERE m.marckM = :marckM")
    , @NamedQuery(name = "Materielsmar.findByDepartM", query = "SELECT m FROM Materielsmar m WHERE m.departM = :departM")
    , @NamedQuery(name = "Materielsmar.findByServiceM", query = "SELECT m FROM Materielsmar m WHERE m.serviceM = :serviceM")
    , @NamedQuery(name = "Materielsmar.findByEtatM", query = "SELECT m FROM Materielsmar m WHERE m.etatM = :etatM")
    , @NamedQuery(name = "Materielsmar.findByBultaLivraison", query = "SELECT m FROM Materielsmar m WHERE m.bultaLivraison = :bultaLivraison")
    , @NamedQuery(name = "Materielsmar.findByBultatSortieM", query = "SELECT m FROM Materielsmar m WHERE m.bultatSortieM = :bultatSortieM")
    , @NamedQuery(name = "Materielsmar.findByEmployeM", query = "SELECT m FROM Materielsmar m WHERE m.employeM = :employeM")
    , @NamedQuery(name = "Materielsmar.findByDateAq", query = "SELECT m FROM Materielsmar m WHERE m.dateAq = :dateAq")
    , @NamedQuery(name = "Materielsmar.findByValeurAq", query = "SELECT m FROM Materielsmar m WHERE m.valeurAq = :valeurAq")
    , @NamedQuery(name = "Materielsmar.findByFournisseurM", query = "SELECT m FROM Materielsmar m WHERE m.fournisseurM = :fournisseurM")
    , @NamedQuery(name = "Materielsmar.findByDirectionM", query = "SELECT m FROM Materielsmar m WHERE m.directionM = :directionM")
    , @NamedQuery(name = "Materielsmar.findBySiteM", query = "SELECT m FROM Materielsmar m WHERE m.siteM = :siteM")
    , @NamedQuery(name = "Materielsmar.findByBureauM", query = "SELECT m FROM Materielsmar m WHERE m.bureauM = :bureauM")
    , @NamedQuery(name = "Materielsmar.findByVarStock", query = "SELECT m FROM Materielsmar m WHERE m.varStock = :varStock")
    , @NamedQuery(name = "Materielsmar.findByTypeM", query = "SELECT m FROM Materielsmar m WHERE m.typeM = :typeM")})
public class Materielsmar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_M")
    private Integer idM;
    @Size(max = 50)
    @Column(name = "article_M")
    private String articleM;
    @Size(max = 50)
    @Column(name = "compte_M")
    private String compteM;
    @Size(max = 50)
    @Column(name = "desiniation_M")
    private String desiniationM;
    @Column(name = "code_M")
    private Integer codeM;
    @Column(name = "numSerie_M")
    private Integer numSerieM;
    @Size(max = 50)
    @Column(name = "marck_M")
    private String marckM;
    @Size(max = 50)
    @Column(name = "depart_M")
    private String departM;
    @Size(max = 50)
    @Column(name = "service_M")
    private String serviceM;
    @Size(max = 50)
    @Column(name = "etat_M")
    private String etatM;
    @Size(max = 50)
    @Column(name = "bultaLivraison")
    private String bultaLivraison;
    @Size(max = 50)
    @Column(name = "bultatSortie_M")
    private String bultatSortieM;
    @Size(max = 50)
    @Column(name = "employe_M")
    private String employeM;
    @Column(name = "dateAq")
    @Temporal(TemporalType.DATE)
    private Date dateAq;
    @Size(max = 50)
    @Column(name = "valeurAq")
    private String valeurAq;
    @Size(max = 50)
    @Column(name = "fournisseur_M")
    private String fournisseurM;
    @Size(max = 50)
    @Column(name = "direction_M")
    private String directionM;
    @Size(max = 50)
    @Column(name = "site_M")
    private String siteM;
    @Column(name = "bureau_M")
    private Integer bureauM;
    @Size(max = 50)
    @Column(name = "varStock")
    private String varStock;
    @Size(max = 50)
    @Column(name = "type_M")
    private String typeM;

    public Materielsmar() {
    }

    public Materielsmar(Integer idM) {
        this.idM = idM;
    }

    public Integer getIdM() {
        return idM;
    }

    public void setIdM(Integer idM) {
        this.idM = idM;
    }

    public String getArticleM() {
        return articleM;
    }

    public void setArticleM(String articleM) {
        this.articleM = articleM;
    }

    public String getCompteM() {
        return compteM;
    }

    public void setCompteM(String compteM) {
        this.compteM = compteM;
    }

    public String getDesiniationM() {
        return desiniationM;
    }

    public void setDesiniationM(String desiniationM) {
        this.desiniationM = desiniationM;
    }

    public Integer getCodeM() {
        return codeM;
    }

    public void setCodeM(Integer codeM) {
        this.codeM = codeM;
    }

    public Integer getNumSerieM() {
        return numSerieM;
    }

    public void setNumSerieM(Integer numSerieM) {
        this.numSerieM = numSerieM;
    }

    public String getMarckM() {
        return marckM;
    }

    public void setMarckM(String marckM) {
        this.marckM = marckM;
    }

    public String getDepartM() {
        return departM;
    }

    public void setDepartM(String departM) {
        this.departM = departM;
    }

    public String getServiceM() {
        return serviceM;
    }

    public void setServiceM(String serviceM) {
        this.serviceM = serviceM;
    }

    public String getEtatM() {
        return etatM;
    }

    public void setEtatM(String etatM) {
        this.etatM = etatM;
    }

    public String getBultaLivraison() {
        return bultaLivraison;
    }

    public void setBultaLivraison(String bultaLivraison) {
        this.bultaLivraison = bultaLivraison;
    }

    public String getBultatSortieM() {
        return bultatSortieM;
    }

    public void setBultatSortieM(String bultatSortieM) {
        this.bultatSortieM = bultatSortieM;
    }

    public String getEmployeM() {
        return employeM;
    }

    public void setEmployeM(String employeM) {
        this.employeM = employeM;
    }

    public Date getDateAq() {
        return dateAq;
    }

    public void setDateAq(Date dateAq) {
        this.dateAq = dateAq;
    }

    public String getValeurAq() {
        return valeurAq;
    }

    public void setValeurAq(String valeurAq) {
        this.valeurAq = valeurAq;
    }

    public String getFournisseurM() {
        return fournisseurM;
    }

    public void setFournisseurM(String fournisseurM) {
        this.fournisseurM = fournisseurM;
    }

    public String getDirectionM() {
        return directionM;
    }

    public void setDirectionM(String directionM) {
        this.directionM = directionM;
    }

    public String getSiteM() {
        return siteM;
    }

    public void setSiteM(String siteM) {
        this.siteM = siteM;
    }

    public Integer getBureauM() {
        return bureauM;
    }

    public void setBureauM(Integer bureauM) {
        this.bureauM = bureauM;
    }

    public String getVarStock() {
        return varStock;
    }

    public void setVarStock(String varStock) {
        this.varStock = varStock;
    }

    public String getTypeM() {
        return typeM;
    }

    public void setTypeM(String typeM) {
        this.typeM = typeM;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idM != null ? idM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materielsmar)) {
            return false;
        }
        Materielsmar other = (Materielsmar) object;
        if ((this.idM == null && other.idM != null) || (this.idM != null && !this.idM.equals(other.idM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Materielsmar[ idM=" + idM + " ]";
    }
    
}
