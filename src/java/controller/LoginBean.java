/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author HP
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

     @EJB
    private UserFacade userFacade;
    
    private static final long serialVersionUID = 5443351151396868724L ;
    private String grade ;
    private String password ;
    private String msg ;
    
    public LoginBean() {
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String LoginCompte() {
        int trouve = -1 ;
        List<User> listeUser = userFacade.findAll();
        FacesContext context = FacesContext.getCurrentInstance() ;
        
        for(User user : listeUser){
            if(grade.equals("admin") && user.getPassword().equals(password)){
                trouve = 1;
            }else{
                if(grade.equals("employe") && user.getPassword().equals(password)){
                    trouve = 2;
                }else{
                    if(grade.equals("service mgx") && user.getPassword().equals(password)){
                        trouve = 3;
                    }else{
                        if(grade.equals("service mtl") && user.getPassword().equals(password)){
                            trouve = 4;
                        }else{
                            if(grade.equals("cadre logistique") && user.getPassword().equals(password)){
                                trouve = 5;
                            }else{
                                if(grade.equals("cadre patrimoine") && user.getPassword().equals(password)){
                                    trouve = 6;
                                }else{
                                    if(grade.equals("gestionnaire MGX") && user.getPassword().equals(password)){
                                        trouve =  7;
                                    }else{
                                        if(grade.equals("commision admin") && user.getPassword().equals(password)){
                                            trouve = 8;
                                        }else{
                                            if(grade.equals("commisaire prisseur") && user.getPassword().equals(password)){
                                                trouve = 9;
                                            }else{
                                                if(grade.equals("inventaire") && user.getPassword().equals(password)){
                                                    trouve = 10;
                                                }else{
                                                    if(grade.equals("gestionnaire MTL") && user.getPassword().equals(password)){
                                                        trouve =  11;
                                                    }else{
                                                        if(grade.equals("fournisseur") && user.getPassword().equals(password)){
                                                            trouve =  12;
                                                        }else{
                                                            if(user.getGradeU().equals(grade) && !user.getPassword().equals(password)){
                                                                trouve = 13;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
               }
            }
        }
        String log = "" ;
        HttpSession session = SessionUtils.getSession() ;
        session.setAttribute("grade", grade);
        switch(trouve){
            case 1 : 
                log = "admin";
                break ;
            case 2 :
                log = "employe" ;
                break ;
            case 3 :
                log = "ServiceMGX" ;
                break ;
            case 4 :
                log = "ServiceMTL" ;
                break ;
            case 5 :
                log = "logistique" ;
                break ;
            case 6 :
                log = "patrimpoine" ;
                break ;
            case 7 :
                log = "gestionnaireStockMGX" ;
                break ;
            case 8 :
                log = "commisionAdmin" ;
                break ;
            case 9 :
                log = "commisairePrisseur" ;
                break ;
            case 10 :
                log = "Inventaire" ;
                break ;
            case 11 :
                log = "gestionnaireStockMTL" ;
                break ;
            case 12 :
                log = "fournisseur" ;
                break ;
            case 13 :
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mot de passe est incorrecte","SVP entrez un mot de passe valide"));
                log = "index" ;
                break ;
            default : 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Le compte inexiste",null));
                log = "index" ;
        }
        return log ;
    }
    
    public String logout(){
        HttpSession session = SessionUtils.getSession() ;
        session.invalidate();
        return "index" ;
    }
    
}
