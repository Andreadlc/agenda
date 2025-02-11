package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class VBoxCalendrier extends VBox implements ConstantesCalendrier {
    Label labelMoisCalendrier;

    public VBoxCalendrier (){
        super(10);

        //date du jour
        modele.DateCalendrier today = new DateCalendrier();

        //étiquette du mois
        labelMoisCalendrier = new Label(MOIS[today.getMois()-1] + " " + today.getAnnee());

        //boutons pour se déplacer de mois en mois
        HBox boiteBoutons = new HBox();
        this.getChildren().add(labelMoisCalendrier);
        Button boutonNext = new Button(">");
        Button boutonLast = new Button("<");
        Button boutonFirstMonth = new Button("<<");
        Button boutonLastMonth = new Button(">>");
        boiteBoutons.getChildren().add(boutonFirstMonth);
        boiteBoutons.getChildren().add(boutonLast);
        boiteBoutons.getChildren().add(boutonNext);
        boiteBoutons.getChildren().add(boutonLastMonth);

        //on empile un conteneur TilePane par mois
        StackPane stackPaneMois = new StackPane();

        //on ne peut sélectionner qu'un bouton à la fois(ToggleGroup)
        ToggleGroup buttonGroup = new ToggleGroup();
        for (int numMois = 1 ; numMois <= 12 ; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());

            //un conteneur TilePane par mois
            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(7);

            //nombre de lignes pour les date + 1 pour les noms des jours
            tilePane.setPrefRows(monthCalendar.getDates().size() / 7 + 1);

            //couleur de fond pour TilePane
            tilePane.setId("opaque");

            //créé et ajoute les étiquettes de la première ligne
            for (String jourAb : JOURS_SEMAINE_ABR) {
                Label labelJour = new Label(jourAb);
                tilePane.getChildren().add(labelJour);
            }

            for (DateCalendrier date : monthCalendar.getDates()) {

                //un numéro de jour par bouton
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));

                //on associe le bouton au groupe
                boutonDate.setToggleGroup(buttonGroup);
                tilePane.getChildren().add(boutonDate);

                //on associe la date au bouton date
                boutonDate.setUserData(date);
                //boutonDate.setOnAction(new EventHandler<ActionEvent>() {
                //    @Override
                //    public void handle(ActionEvent actionEvent) {
                //        System.out.println(boutonDate.getUserData());
                //    }
                //});
                boutonDate.setOnAction(HBoxRoot.getControleur());
                if (date.getMois() != monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                }
                if (date.isToday()) {
                    boutonDate.setId("today");
                }
            }
            tilePane.setAccessibleText(MOIS[numMois-1]);
            stackPaneMois.getChildren().add(tilePane);
        }

        //on replace le mois courant devant par défaut
        List<Node> liste = stackPaneMois.getChildren();
        int dernierIndice = liste.size()-1;
        while (!ConstantesCalendrier.MOIS[today.getMois()-1].equals(liste.get(dernierIndice).getAccessibleText())){
            liste.get(0).toFront();
        }

        //bouton pour passer au mois suivant
        boutonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(0).toFront();
                labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + today.getAnnee());
            }
        });

        //bouton pour passer au mois précédent
        boutonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(dernierIndice).toBack();
                labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + today.getAnnee());
            }
        });

        //bouton pour passer au premier mois de l'année
        boutonFirstMonth.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!labelMoisCalendrier.getText().equals("janvier " + today.getAnnee())) {
                    liste.get(0).toFront();
                    labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + today.getAnnee());
                }
            }
        }));

        //bouton pour passer au dernier mois de l'année
        boutonLastMonth.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!labelMoisCalendrier.getText().equals("décembre " + today.getAnnee())) {
                    liste.get(dernierIndice).toBack();
                    labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + today.getAnnee());
                }
            }
        }));

        this.getChildren().add(stackPaneMois);
        this.getChildren().add(boiteBoutons);
    }
}
