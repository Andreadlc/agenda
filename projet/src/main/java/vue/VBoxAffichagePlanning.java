package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.DateCalendrier;
import modele.Reservation;

import java.util.TreeMap;
import java.util.TreeSet;

public class VBoxAffichagePlanning extends VBox {
    Label numSemaine;
    TableView <Reservation> tableDesReservations;
    TreeSet<Reservation> reservationsDeLaSemaine;


    public VBoxAffichagePlanning () {
        int semaine = new DateCalendrier().getWeekOfYear();
        numSemaine = new Label("Semaine " + semaine);
        this.getChildren().add(numSemaine);

        reservationsDeLaSemaine = HBoxRoot.getPlanning().getReservations(semaine);
        tableDesReservations = new TableView<>();
        tableDesReservations.setPrefSize(525, 450);

        this.getChildren().add(tableDesReservations);

        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableDesReservations.getColumns().add(dateColumn);
        dateColumn.setPrefWidth(150);
        dateColumn.setResizable(false);

        TableColumn<Reservation, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        tableDesReservations.getColumns().add(coursColumn);
        coursColumn.setPrefWidth(100);
        coursColumn.setResizable(false);

        TableColumn<Reservation, String> niveauColumn = new TableColumn<>("Niveau");
        niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        tableDesReservations.getColumns().add(niveauColumn);
        niveauColumn.setPrefWidth(100);
        niveauColumn.setResizable(false);


        TableColumn<Reservation, String> horaireColumn = new TableColumn<>("Plage Horaire");
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("plageHoraire"));
        tableDesReservations.getColumns().add(horaireColumn);
        horaireColumn.setPrefWidth(175);
        horaireColumn.setResizable(false);
    }

    public void update (DateCalendrier parDate) {
        numSemaine.setText("Semaine " + parDate.getWeekOfYear());
        tableDesReservations.getItems().clear();
        reservationsDeLaSemaine = HBoxRoot.getPlanning().getReservations(parDate.getWeekOfYear());
        if (reservationsDeLaSemaine != null) {
            for (Reservation reservation : reservationsDeLaSemaine) {
                tableDesReservations.getItems().add(reservation);
            }
        }
    }

    public void setNumSemaine (String parNumSemaine) {
        numSemaine.setText(parNumSemaine);
    }
}
