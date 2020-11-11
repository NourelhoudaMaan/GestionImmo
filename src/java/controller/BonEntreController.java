package controller;

import model.BonEntre;
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

@Named("bonEntreController")
@SessionScoped
public class BonEntreController implements Serializable {

    @EJB
    private controller.BonEntreFacade ejbFacade;
    private List<BonEntre> items = null;
    private BonEntre selected;

    public BonEntreController() {
    }

    public BonEntre getSelected() {
        return selected;
    }

    public void setSelected(BonEntre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BonEntreFacade getFacade() {
        return ejbFacade;
    }

    public BonEntre prepareCreate() {
        selected = new BonEntre();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleBonEntre").getString("BonEntreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleBonEntre").getString("BonEntreUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleBonEntre").getString("BonEntreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BonEntre> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleBonEntre").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleBonEntre").getString("PersistenceErrorOccured"));
            }
        }
    }

    public BonEntre getBonEntre(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<BonEntre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BonEntre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BonEntre.class)
    public static class BonEntreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BonEntreController controller = (BonEntreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bonEntreController");
            return controller.getBonEntre(getKey(value));
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
            if (object instanceof BonEntre) {
                BonEntre o = (BonEntre) object;
                return getStringKey(o.getNumBE());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BonEntre.class.getName()});
                return null;
            }
        }

    }
    
    public List<BonEntre> listMGX(){
        ArrayList<BonEntre> liste = new ArrayList() ;
        List<BonEntre> listBon = ejbFacade.findAll();
        for(BonEntre bon : listBon){
            if(bon.getTypeBE().equals("mgx")){
                liste.add(bon);
            }
        }
        return liste ;
    }
    
    public List<BonEntre> listMTL(){
        ArrayList<BonEntre> liste = new ArrayList() ;
        List<BonEntre> listBon = ejbFacade.findAll();
        for(BonEntre bon : listBon){
            if(bon.getTypeBE().equals("mtl")){
                liste.add(bon);
            }
        }
        return liste ;
    }
    
    private List<BonEntre> filteredBonlList;

    public List<BonEntre> getFilteredBonlList() {
        return filteredBonlList;
    }

    public void setFilteredBonlList(List<BonEntre> filteredBonlList) {
        this.filteredBonlList = filteredBonlList;
    }


}
