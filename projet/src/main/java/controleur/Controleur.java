package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.*;
import vue.GridPaneFormulaireReservation;
import vue.HBoxRoot;
import vue.VBoxAffichagePlanning;

public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        PlanningCollections planning = HBoxRoot.getPlanning();
        GridPaneFormulaireReservation reservationPane = HBoxRoot.getReservationPane();
        VBoxAffichagePlanning affichagePlanning = HBoxRoot.getAffichagePlanning();
        // la source de event est un ToggleButton du calendrier
        if (event.getSource() instanceof ToggleButton) {
// à compléter question 5
            DateCalendrier date = (DateCalendrier) ((ToggleButton) event.getSource()).getUserData();
            HBoxRoot.getReservationPane().update(date);
        }
// la source de event est le bouton "Enregistrer" du formulaire de réservation
        if (event.getSource() instanceof Button) {
// à compléter question 6
            try {
                DateCalendrier date = reservationPane.getDate();
                String intitule = reservationPane.getTextFieldCours().getText();
                String niveau = reservationPane.getNiveau();
                //int heureDebut = Integer.parseInt(reservationPane.getComboHeureDebut().getValue());
                //int minuteDebut = Integer.parseInt(reservationPane.getComboMinuteDebut().getValue());
                //int heureFin = Integer.parseInt(reservationPane.getComboHeureFin().getValue());
                //int minuteFin = Integer.parseInt(reservationPane.getComboMinuteFin().getValue());

                //Horaire horaireDebut = new Horaire(heureDebut, minuteDebut);
                //Horaire horaireFin = new Horaire(heureFin, minuteFin);
                Horaire horaireDebut = reservationPane.getHoraireDebut();
                Horaire horaireFin = reservationPane.getHoraireFin();

                PlageHoraire plageHoraire = new PlageHoraire(horaireDebut, horaireFin);

                Reservation reservation = new Reservation(date, plageHoraire, intitule, niveau);

                planning.ajout(reservation);
                System.out.println(planning);

                affichagePlanning.update(date);
            }
            catch (ExceptionPlanning parExceptionPlan){
                System.out.println(ConstantesErreur.ERREURS_PLANNING[parExceptionPlan.getCodeErreur()]);
            }
        }
    }
}
