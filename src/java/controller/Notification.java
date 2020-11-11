/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Notification implements Serializable {

    private static final long serialVersionUID = 5443351151396868724L ;
    private String sender ;
    private String msgObject ;
    private String destination ;
    private String msg ;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsgObject() {
        return msgObject;
    }

    public void setMsgObject(String msgObject) {
        this.msgObject = msgObject;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Notification() {
    }
    
    public void sendNotification(){
        List listeMsg = null ;
        switch(destination){
            case "Employe":
            break ;
            
            case "Service MGX":
            break ;
            
            case "Service MTL":
            break ;
            
            case "Gestionnaire de stock MGX":
                listeMsg = gestionnaireMGXNotification();
            break ;
            
            case "Gestionnaire de stock MTL":
                
            break ;
            
            case "Commision admin":
            break ;
            
            case "Commisaire priseur":
            break ;
            
            case "Cadre patremoin":
            break ;
            
            case "Cadre logistique":
            break ;
            
            case "Chef d'inventaire":
            break ;
        }
    }
    
    public List gestionnaireMGXNotification(){
        ArrayList<String> listeMsg = new ArrayList() ;
        
            listeMsg.add(sender);
            listeMsg.add(msgObject);
            listeMsg.add(msg);
            return listeMsg;
    }
    
}
