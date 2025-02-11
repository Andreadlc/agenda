package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import modele.PlanningCollections;

public class HBoxRoot extends HBox {

    private static PlanningCollections planning;
    private static Controleur controleur;
    private static VBoxCalendrier calendrierPane;
    private static GridPaneFormulaireReservation reservationPane;
    private static VBoxAffichagePlanning affichagePlanning;


    public HBoxRoot (){

        planning = new PlanningCollections();
        controleur = new Controleur();
        calendrierPane = new VBoxCalendrier();
        reservationPane = new GridPaneFormulaireReservation();
        affichagePlanning = new VBoxAffichagePlanning();
        this.getChildren().add(calendrierPane);
        this.getChildren().add(reservationPane);
        this.getChildren().add(affichagePlanning);
    }

    public static PlanningCollections getPlanning() {
        return planning;
    }

    public static Controleur getControleur() {
        return controleur;
    }

    public static GridPaneFormulaireReservation getReservationPane() {
        return reservationPane;
    }

    public static VBoxAffichagePlanning getAffichagePlanning() {
        return affichagePlanning;
    }
}
