package controller;

import model.Reparation;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import model.Materiel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@Named("reparationController")
@SessionScoped
public class ReparationController implements Serializable {


    @EJB
    private controller.ReparationFacade ejbFacade;
    private List<Reparation> items = null;
    private Reparation selected;

    public ReparationController() {
    }

    public Reparation getSelected() {
        return selected;
    }

    public void setSelected(Reparation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReparationFacade getFacade() {
        return ejbFacade;
    }

    public Reparation prepareCreate() {
        selected = new Reparation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleReparation").getString("ReparationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleReparation").getString("ReparationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleReparation").getString("ReparationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reparation> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleReparation").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleReparation").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Reparation getReparation(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Reparation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reparation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reparation.class)
    public static class ReparationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReparationController controller = (ReparationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reparationController");
            return controller.getReparation(getKey(value));
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
            if (object instanceof Reparation) {
                Reparation o = (Reparation) object;
                return getStringKey(o.getIdR());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reparation.class.getName()});
                return null;
            }
        }

    }
    
    private List<Reparation> filteredMaterielList;

    public List<Reparation> getFilteredMaterielList() {
        return filteredMaterielList;
    }

    public void setFilteredMaterielList(List<Reparation> filteredMaterielList) {
        this.filteredMaterielList = filteredMaterielList;
    }

    
    public PieChartModel GrapheModel(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ejbFacade.findAll() ;
        for(Reparation repar : liste){
            String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
        }
        model.setTitle("Les marques réparées");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public List<Reparation> ListMGX(){
        ArrayList<Reparation> listeR = new ArrayList() ;
        List<Reparation> liste = ejbFacade.findAll() ;
        for(Reparation r : liste){
            if(r.getTypeMR().equals("mgx")){
                listeR.add(r) ;
            }
        }
        return listeR ;
    }
    public PieChartModel GrapheModelMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
        }
        model.setTitle("Les marques réparées");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheMarckPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
        }
        model.setTitle("Les marques tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheMarckReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
                int compteur = 0 ;
                for(Reparation reparation : liste){
                    if(reparation.getMarckR().equals(marque)){ compteur++;}
                }
                model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les marques réparées");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheMarckRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR();
                int compteur = 0 ;
                for(Reparation reparation : liste){
                    if(reparation.getMarckR().equals(marque)){ compteur++;}
                }
                model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les marques impossible à réparer");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GraphePCPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("pc")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les pc tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GraphePCReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("pc") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les pc réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    int total = 0 ;
    public PieChartModel GraphePCRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            int compteur = 0 ;
            if(repar.getCategorieMR().equals("pc") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR(); 
            compteur++; total++;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++; 
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les pc impossible à réparer ");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GraphePCEnpannePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("pc") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les pc en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheClavierPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("clavier")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les clavies tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheClavierReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("clavier") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les clavies réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheClavierRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("clavie") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les clavies impossible à réparer");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheClavieEnpannePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("clavie") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les clavies en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    
    public PieChartModel GrapheUCPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("unité centrale")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les unités centrales tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheUCReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("unité centrale") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les clavies réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheUCRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("unité centrale") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les unités centrales impossible à réparer");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheUCEnpannePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("unité centrale") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les unités centrales en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public BarChartModel GrapheBarMGX(){
        BarChartModel model = new BarChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            String marque = repar.getMarckR();
            ChartSeries r = new ChartSeries() ;
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            r.setLabel(marque);
            r.set(marque, compteur);
            model.addSeries(r);
        }
        model.setTitle("Les marques tombent en panne");
        model.setLegendPosition("e");
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Marque");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Fois");
        yAxis.setMin(0);
        yAxis.setMax(10);
        return model ;
    }
    
    public PieChartModel GrapheEcranPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("ecran")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les ecrans tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheEcranReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("ecran") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les ecran réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheEcranRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            int compteur = 0 ;
            if(repar.getCategorieMR().equals("ecran") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR(); 
            compteur++; total++;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++; 
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les ecran impossible à réparer ");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheEcranEnpannePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("ecran") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les ecran en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheSourisPieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("souris")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les souris tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheSourisReparePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("souris") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les souris réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheSourisRefusePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            int compteur = 0 ;
            if(repar.getCategorieMR().equals("souris") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR(); 
            compteur++; total++;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++; 
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les souris impossible à réparer ");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheSourisEnpannePieMGX(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMGX() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("souris") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les souris en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public List<Reparation> ListMTL(){
        ArrayList<Reparation> listeR = new ArrayList() ;
        List<Reparation> liste = ejbFacade.findAll() ;
        for(Reparation r : liste){
            if(r.getTypeMR().equals("mtl")){
                listeR.add(r) ;
            }
        }
        return listeR ;
    }
    
    public PieChartModel GrapheMarckPieMTL(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMTL() ;
        for(Reparation repar : liste){
            String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
        }
        model.setTitle("Les marques tombent en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheVoiturePieMTL(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMTL() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("voiture")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les voiture réparées");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheVoitureReparePieMTL(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMTL() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("voiture") && repar.getEtatMR().equals("réparer")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les voitures réparés");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheVoituresRefusePieMTL(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMTL() ;
        for(Reparation repar : liste){
            int compteur = 0 ;
            if(repar.getCategorieMR().equals("voiture") && repar.getEtatMR().equals("réfuser")){
                String marque = repar.getMarckR(); 
            compteur++; total++;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++; 
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les voitures impossible à réparer ");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }
    
    public PieChartModel GrapheVoitureEnpannePieMTL(){
        PieChartModel model = new PieChartModel() ;
        List<Reparation> liste = ListMTL() ;
        for(Reparation repar : liste){
            if(repar.getCategorieMR().equals("voiture") && repar.getEtatMR().equals("en panne")){
                String marque = repar.getMarckR();
            int compteur = 0 ;
            for(Reparation reparation : liste){
                if(reparation.getMarckR().equals(marque)){
                    compteur++;
                }
            }
            model.set(marque, compteur);
            }
            
        }
        model.setTitle("Les voitures en cours en panne");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        return model ;
    }

    
   
}
