package controller;

import model.Materiel;
import controller.util.JsfUtil;
import controller.util.PaginationHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import model.Reparation;

@Named("materielController")
@SessionScoped
public class MaterielController implements Serializable {

    @EJB
    private ReparationFacade reparationFacade;

    private Materiel current;
    private DataModel items = null;
    @EJB
    private controller.MaterielFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MaterielController() {
    }

    public Materiel getSelected() {
        if (current == null) {
            current = new Materiel();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MaterielFacade getFacade() {
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
        current = (Materiel) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Materiel();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle4").getString("MaterielCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle4").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Materiel) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle4").getString("MaterielUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle4").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Materiel) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle4").getString("MaterielDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle4").getString("PersistenceErrorOccured"));
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

    public Materiel getMateriel(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Materiel.class)
    public static class MaterielControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterielController controller = (MaterielController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materielController");
            return controller.getMateriel(getKey(value));
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
            if (object instanceof Materiel) {
                Materiel o = (Materiel) object;
                return getStringKey(o.getIdM());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Materiel.class.getName());
            }
        }

    }

    public List<Materiel> listeDemandeMTL(){
        ArrayList<Materiel> liste = new ArrayList();
        List<Materiel> listeTest = ejbFacade.findAll();
        for(Materiel materiel : listeTest){
            if(materiel.getTypeM().equals("mtl")){
                liste.add(materiel);
            }
        }
        return liste ;
    }
    
    public List<Materiel> listeDemandeMGX(){
        ArrayList<Materiel> liste = new ArrayList();
        List<Materiel> listeTest = ejbFacade.findAll();
        for(Materiel materiel : listeTest){
            if(materiel.getTypeM().equals("mgx")){
                liste.add(materiel);
            }
        }
        return liste ;
    }
    
    private List<Materiel> filteredMaterielList;

    public List<Materiel> getFilteredMaterielList() {
        return filteredMaterielList;
    }

    public void setFilteredMaterielList(List<Materiel> filteredMaterielList) {
        this.filteredMaterielList = filteredMaterielList;
    }
    
    
     public ArrayList<Materiel> EvaluerMaterielMGX(){
        ArrayList<Materiel> evaluer = new ArrayList();
        List<Reparation> liste = reparationFacade.findAll() ;
        List<Materiel> listeMateriel = listeDemandeMGX() ;
        for(Materiel materiel : listeMateriel){
            int trouve = 0;
            for(Reparation repa : liste){
                if(materiel.getCodeM().equals(repa.getCodeMR()) && repa.getTypeMR().equals("mgx")){
                   trouve = 1;
                }
            }
            if(trouve == 0){
                evaluer.add(materiel);
            }
        }
        return evaluer ;
    }
}
