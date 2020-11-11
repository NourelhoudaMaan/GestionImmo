package controller;

import model.InventaireMateriel;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
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

@Named("inventaireMaterielController")
@SessionScoped
public class InventaireMaterielController implements Serializable {

    @EJB
    private controller.InventaireMaterielFacade ejbFacade;
    private List<InventaireMateriel> items = null;
    private InventaireMateriel selected;

    public InventaireMaterielController() {
    }

    public InventaireMateriel getSelected() {
        return selected;
    }

    public void setSelected(InventaireMateriel selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InventaireMaterielFacade getFacade() {
        return ejbFacade;
    }

    public InventaireMateriel prepareCreate() {
        selected = new InventaireMateriel();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleInventaire").getString("InventaireMaterielCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleInventaire").getString("InventaireMaterielUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleInventaire").getString("InventaireMaterielDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<InventaireMateriel> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleInventaire").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleInventaire").getString("PersistenceErrorOccured"));
            }
        }
    }

    public InventaireMateriel getInventaireMateriel(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<InventaireMateriel> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<InventaireMateriel> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = InventaireMateriel.class)
    public static class InventaireMaterielControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventaireMaterielController controller = (InventaireMaterielController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventaireMaterielController");
            return controller.getInventaireMateriel(getKey(value));
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
            if (object instanceof InventaireMateriel) {
                InventaireMateriel o = (InventaireMateriel) object;
                return getStringKey(o.getIdIM());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InventaireMateriel.class.getName()});
                return null;
            }
        }

    }
    
    private List<InventaireMateriel> filteredMaterielList;

    public List<InventaireMateriel> getFilteredMaterielList() {
        return filteredMaterielList;
    }

    public void setFilteredMaterielList(List<InventaireMateriel> filteredMaterielList) {
        this.filteredMaterielList = filteredMaterielList;
    }
}
