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
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByIdN", query = "SELECT n FROM Notification n WHERE n.idN = :idN")
    , @NamedQuery(name = "Notification.findBySenderN", query = "SELECT n FROM Notification n WHERE n.senderN = :senderN")
    , @NamedQuery(name = "Notification.findByReceiverN", query = "SELECT n FROM Notification n WHERE n.receiverN = :receiverN")
    , @NamedQuery(name = "Notification.findByObjectMsgN", query = "SELECT n FROM Notification n WHERE n.objectMsgN = :objectMsgN")
    , @NamedQuery(name = "Notification.findByBodyMsgN", query = "SELECT n FROM Notification n WHERE n.bodyMsgN = :bodyMsgN")
    , @NamedQuery(name = "Notification.findByDateN", query = "SELECT n FROM Notification n WHERE n.dateN = :dateN")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_N")
    private Integer idN;
    @Size(max = 50)
    @Column(name = "sender_N")
    private String senderN;
    @Size(max = 50)
    @Column(name = "receiver_N")
    private String receiverN;
    @Size(max = 50)
    @Column(name = "objectMsg_N")
    private String objectMsgN;
    @Size(max = 200)
    @Column(name = "bodyMsg_N")
    private String bodyMsgN;
    @Column(name = "date_N")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateN;

    public Notification() {
    }

    public Notification(Integer idN) {
        this.idN = idN;
    }

    public Integer getIdN() {
        return idN;
    }

    public void setIdN(Integer idN) {
        this.idN = idN;
    }

    public String getSenderN() {
        return senderN;
    }

    public void setSenderN(String senderN) {
        this.senderN = senderN;
    }

    public String getReceiverN() {
        return receiverN;
    }

    public void setReceiverN(String receiverN) {
        this.receiverN = receiverN;
    }

    public String getObjectMsgN() {
        return objectMsgN;
    }

    public void setObjectMsgN(String objectMsgN) {
        this.objectMsgN = objectMsgN;
    }

    public String getBodyMsgN() {
        return bodyMsgN;
    }

    public void setBodyMsgN(String bodyMsgN) {
        this.bodyMsgN = bodyMsgN;
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idN != null ? idN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.idN == null && other.idN != null) || (this.idN != null && !this.idN.equals(other.idN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Notification[ idN=" + idN + " ]";
    }
    
}
