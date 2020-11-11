package controller;

import model.BonSortie;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("bonSortieController")
@SessionScoped
public class BonSortieController implements Serializable {

    @EJB
    private controller.BonSortieFacade ejbFacade;
    private List<BonSortie> items = null;
    private BonSortie selected;

    public BonSortieController() {
    }

    public BonSortie getSelected() {
        return selected;
    }

    public void setSelected(BonSortie selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BonSortieFacade getFacade() {
        return ejbFacade;
    }

    public BonSortie prepareCreate() {
        selected = new BonSortie();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleBon").getString("BonSortieCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleBon").getString("BonSortieUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleBon").getString("BonSortieDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BonSortie> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleBon").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleBon").getString("PersistenceErrorOccured"));
            }
        }
    }

    public BonSortie getBonSortie(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<BonSortie> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BonSortie> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BonSortie.class)
    public static class BonSortieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BonSortieController controller = (BonSortieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bonSortieController");
            return controller.getBonSortie(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BonSortie) {
                BonSortie o = (BonSortie) object;
                return getStringKey(o.getNumBS());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BonSortie.class.getName()});
                return null;
            }
        }

    }
    
    public List<BonSortie> listMGX(){
        ArrayList<BonSortie> liste = new ArrayList() ;
        List<BonSortie> listBon = ejbFacade.findAll();
        for(BonSortie bon : listBon){
            if(bon.getTypeBE().equals("mgx")){
                liste.add(bon);
            }
        }
        return liste ;
    }
    
    public List<BonSortie> listMTL(){
        ArrayList<BonSortie> liste = new ArrayList() ;
        List<BonSortie> listBon = ejbFacade.findAll();
        for(BonSortie bon : listBon){
            if(bon.getTypeBE().equals("mtl")){
                liste.add(bon);
            }
        }
        return liste ;
    }
    
    private List<BonSortie> filteredBonlList;

    public List<BonSortie> getFilteredBonlList() {
        return filteredBonlList;
    }

    public void setFilteredBonlList(List<BonSortie> filteredBonlList) {
        this.filteredBonlList = filteredBonlList;
    }

    

}
