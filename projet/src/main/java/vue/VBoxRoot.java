package vue;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class VBoxRoot extends VBox implements ConstantesCalendrier{
   //Label LabelDateAujourdhui;
   //Label LabelDateLendemain;
   Label labelMoisCalendrier;
   Label labelDatesCalendrier;


   public VBoxRoot (){
      super(10);

      modele.DateCalendrier dateJour = new DateCalendrier();
      //LabelDateAujourdhui = new Label(dateJour.toString());
      //modele.DateCalendrier dateDemain = new DateCalendrier().dateDuLendemain();
      //LabelDateLendemain = new Label(dateDemain.toString());

      //this.getChildren().add(LabelDateAujourdhui);
      //this.getChildren().add(LabelDateLendemain);


      int currentMois = dateJour.getMois()-1;
      int currentAnnee = dateJour.getAnnee();
      labelMoisCalendrier = new Label(MOIS[currentMois] + " " + currentAnnee);
      HBox boiteBoutons = new HBox();
      //HBox boiteBoutonsBis = new HBox();
      this.getChildren().add(labelMoisCalendrier);
      Button boutonNext = new Button(">");
      Button boutonLast = new Button("<");
      //Button boutonJan = new Button("<<");
      //Button boutonDec = new Button(">>");

      boiteBoutons.getChildren().add(boutonLast);
      boiteBoutons.getChildren().add(boutonNext);
      //boiteBoutonsBis.getChildren().add(boutonJan);
      this.getChildren().add(boiteBoutons);
      //this.getChildren().add(boiteBoutonsBis);

      StackPane stackPaneMois = new StackPane();
      for (int i = 1 ; i <= 12 ; i++) {
         CalendrierDuMois parCal = new CalendrierDuMois(i,dateJour.getAnnee());
         int mois = parCal.getMois()-1;

         ScrollPane scrollPaneDates = new ScrollPane();
         VBox boiteDate = new VBox(5);
         VBox.setMargin(scrollPaneDates, new Insets(14));

         Collection <DateCalendrier> dates = parCal.getDates();
         Iterator <DateCalendrier> iterator = dates.iterator();
         while (iterator.hasNext()) {
            DateCalendrier date = iterator.next();
            labelDatesCalendrier = new Label(date.toString());
            if (date.compareTo(dateJour) == 0) {
               labelDatesCalendrier.setId("today");
            }
            boiteDate.getChildren().add(labelDatesCalendrier);
         }

         scrollPaneDates.setAccessibleText(ConstantesCalendrier.MOIS[mois]);
         scrollPaneDates.setContent(boiteDate);

         stackPaneMois.getChildren().add(scrollPaneDates);
      }

      List <Node> liste = stackPaneMois.getChildren();
      int dernierIndice = liste.size()-1;
      while (!ConstantesCalendrier.MOIS[dateJour.getMois()-1].equals(liste.get(dernierIndice).getAccessibleText())){
         liste.get(0).toFront();
      }

      boutonNext.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            System.out.println("bouton next");
            liste.get(0).toFront();
            labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + currentAnnee);
         }
      });

      boutonLast.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
            System.out.println("bouton last");
            liste.get(dernierIndice).toBack();
            labelMoisCalendrier.setText(liste.get(dernierIndice).getAccessibleText() + " " + currentAnnee);
         }
      });

      //boutonJan.setOnAction(new EventHandler<ActionEvent>() {
      //   @Override
      //   public void handle(ActionEvent actionEvent) {
      //      System.out.println("bouton Jan");
      //      while (!liste.get(dernierIndice).getAccessibleText().equals("Janvier")) {
      //         liste.get(0).toFront();
      //      }
      //      labelMoisCalendrier.setText("janvier" + " " + currentAnnee);
      //   }
      //});

      this.getChildren().add(stackPaneMois);
   }
}
