/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
class SessionUtils {
    static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false) ;
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest() ;
    }
    
    public String getUserGrade(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false) ;
        return session.getAttribute("grade").toString();
    }
    
    public String getUserId(){
        HttpSession session = getSession() ;
        if(session !=null){
            return (String)session.getAttribute("useri=Id").toString() ;
        }else{
            return null ;
        }
    }
}
