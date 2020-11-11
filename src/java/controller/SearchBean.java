/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Materiel;

/**
 *
 * @author HP
 */
public class SearchBean {

   @EJB
    private MaterielFacade materielFacade;
    
    private String searchInputMateriel ;

    public String getSearchInputMateriel() {
        return searchInputMateriel;
    }

    public void setSearchInputMateriel(String searchInputMateriel) {
        this.searchInputMateriel = searchInputMateriel;
    }
    
    public SearchBean() {
    }
    
    public List<Materiel> resultSearchMateriel(){
        ArrayList<Materiel> resultMateriel = new ArrayList() ;
        List<Materiel> materielListe = this.materielFacade.findAll() ;
        for(Materiel materiel : materielListe){
            if(materiel.getCodeM().equals(searchInputMateriel)){
                resultMateriel.add(materiel) ;
            }else{
                if(materiel.getMarckM().equals(searchInputMateriel)){
                    resultMateriel.add(materiel) ;
                }else{
                    if(materiel.getEmployeM().equals(searchInputMateriel)){
                        resultMateriel.add(materiel) ;
                    }else{
                        if(materiel.getServiceM().equals(searchInputMateriel)){
                            resultMateriel.add(materiel) ;
                        }else{
                            if(materiel.getDepartM().equals(searchInputMateriel)){
                                resultMateriel.add(materiel) ;
                            }else{
                                if(materiel.getDirectionM().equals(searchInputMateriel)){
                                    resultMateriel.add(materiel) ;
                                }
                            }
                        }
                    }
                }
            }
            
        }
        return resultMateriel ;
    }
    
    public String ResultPage(){
        List<Materiel> result = resultSearchMateriel() ;
        if(result == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aucun matériel trouvé",null));
            return "gestionnaireStock" ;
        }else{
            return "ResultSearch.xhtml" ;
        }
    }
    
}
