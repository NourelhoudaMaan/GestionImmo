package controller;

import model.Demande;
import controller.util.JsfUtil;
import controller.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("demandeController")
@SessionScoped
public class DemandeController implements Serializable {

    private Demande current;
    private DataModel items = null;
    @EJB
    private controller.DemandeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DemandeController() {
    }

    public Demande getSelected() {
        if (current == null) {
            current = new Demande();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DemandeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Demande) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Demande();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleDemande").getString("DemandeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleDemande").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Demande) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleDemande").getString("DemandeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleDemande").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Demande) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleDemande").getString("DemandeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleDemande").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Demande getDemande(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Demande.class)
    public static class DemandeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeController controller = (DemandeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeController");
            return controller.getDemande(getKey(value));
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
            if (object instanceof Demande) {
                Demande o = (Demande) object;
                return getStringKey(o.getIdD());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Demande.class.getName());
            }
        }

    }
    
    public List<Demande> listeDemandeMTL(){
        ArrayList<Demande> liste = new ArrayList();
        List<Demande> listeTest = ejbFacade.findAll();
        for(Demande materiel : listeTest){
            if(materiel.getTypeD().equals("mtl") || materiel.getTypeD().equals("MTL")){
                liste.add(materiel);
            }
        }
        return liste ;
    }
    
    public List<Demande> listeDemandeMGX(){
        ArrayList<Demande> liste = new ArrayList();
        List<Demande> listeTest = ejbFacade.findAll();
        for(Demande materiel : listeTest){
            if(materiel.getTypeD().equals("mgx") || materiel.getTypeD().equals("MGX")){
                liste.add(materiel);
            }
        }
        return liste ;
    }
    
    public List<Demande> LastDemandeMGX(){
        ArrayList<Demande> listeNotif = new ArrayList();
        List<Demande> liste = ejbFacade.findAll();
        for(Demande notif : liste){
            if(notif.getTypeD().equals("mgx") || notif.getTypeD().equals("MGX")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Demande> LastDemandeMTL(){
        ArrayList<Demande> listeNotif = new ArrayList();
        List<Demande> liste = ejbFacade.findAll();
        for(Demande notif : liste){
            if(notif.getTypeD().equals("mtl") || notif.getTypeD().equals("MTL")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    private List<Demande> filteredMaterielList;

    public List<Demande> getFilteredMaterielList() {
        return filteredMaterielList;
    }

    public void setFilteredMaterielList(List<Demande> filteredMaterielList) {
        this.filteredMaterielList = filteredMaterielList;
    }

}
