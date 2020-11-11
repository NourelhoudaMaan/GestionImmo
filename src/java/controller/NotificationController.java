package controller;

import model.Notification;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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

@Named("notificationController")
@SessionScoped
public class NotificationController implements Serializable {

    @EJB
    private controller.NotificationFacade ejbFacade;
    private List<Notification> items = null;
    private Notification selected;

    public NotificationController() {
    }

    public Notification getSelected() {
        return selected;
    }

    public void setSelected(Notification selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotificationFacade getFacade() {
        return ejbFacade;
    }

    public Notification prepareCreate() {
        selected = new Notification();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleNotification").getString("NotificationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleNotification").getString("NotificationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleNotification").getString("NotificationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Notification> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNotification").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleNotification").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Notification getNotification(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Notification> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Notification> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Notification.class)
    public static class NotificationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificationController controller = (NotificationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificationController");
            return controller.getNotification(getKey(value));
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
            if (object instanceof Notification) {
                Notification o = (Notification) object;
                return getStringKey(o.getIdN());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notification.class.getName()});
                return null;
            }
        }

    }
    
    
    public List<Notification> gestionMGXLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MGX")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> gestionMGXAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MGX")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> gestionMGXTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        LocalDateTime now = LocalDateTime.now();
        String d = now.toString();
        
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MGX")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> ListGestionnaireMGX(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MGX")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Gestionnaire de stock MGX")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
    
    public List<Notification> gestionMTLLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MTL")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> gestionMTLAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MTL")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> gestionMTLTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MTL")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> ListServiceMGX(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mgx")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Service mgx")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
     
     public List<Notification> serviceMGXLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mgx")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> serviceMGXAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mgx")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> ListServiceMTL(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mtl")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Service mtl")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
     
     public List<Notification> serviceMTLLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mtl")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> serviceMTLAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Service mtl")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }

    
    public List<Notification> ListGestionnaireMTL(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Gestionnaire de stock MTL")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Gestionnaire de stock MTL")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
    
    public List<Notification> ListPatremoin(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre patremoin")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Cadre patremoin")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
     
     public List<Notification> patrimoineLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre patremoin")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> patrimoineAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre patremoin")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> patrimoineTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre patremoin")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> ListLogistique(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre logistique")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Cadre logistique")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
    
    public List<Notification> logistiqueLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre logistique")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> logistiqueAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre logistique")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> logistiqueTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Cadre logistique")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> ListCommisionAdmin(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commision admin")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Commision admin")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
    
    public List<Notification> commisionAdminLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commision admin")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> commisionAdminAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commision admin")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> commisionAdminTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commision admin")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
     public List<Notification> ListPriseur(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commisaire priseur")){
                listeNotif.add(notif) ;
            }
            if(notif.getSenderN().equals("Commisaire priseur")){
                listeNotif.add(notif) ;
            }
        }
        
        return listeNotif ;
    }
    
    public List<Notification> PriseurLastReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commisaire priseur")){
                listeNotif.add(notif) ;
            }
        }
        int index = listeNotif.size();
        return listeNotif.subList(index-1, index) ;
    }
    
    public List<Notification> PriseurAllReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commisaire priseur")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
    
    public List<Notification> PriseurTodayReceiver(){
        ArrayList<Notification> listeNotif = new ArrayList();
        List<Notification> liste = ejbFacade.findAll();
        for(Notification notif : liste){
            if(notif.getReceiverN().equals("Commisaire prisseur")){
                listeNotif.add(notif) ;
            }
        }
        return listeNotif ;
    }
}
