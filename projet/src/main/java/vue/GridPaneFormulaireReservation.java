package vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import modele.ConstantesCalendrier;
import modele.DateCalendrier;
import modele.Horaire;
import modele.PlageHoraire;

import java.util.Calendar;

public class GridPaneFormulaireReservation extends GridPane implements ConstantesCalendrier {

    Label labelDate;
    DateCalendrier date;
    TextField textFieldCours;
    ToggleGroup radioGroup;
    ComboBox<String> comboHeureDebut;
    ComboBox<String> comboMinuteDebut;
    ComboBox<String> comboHeureFin;
    ComboBox<String> comboMinuteFin;


    public GridPaneFormulaireReservation () {
        //this.setGridLinesVisible(true);

        this.setPadding(new Insets(10));
        this.setHgap(9);
        this.setVgap(14);
        int ligne = 0;

        //date
        date = new DateCalendrier();
        labelDate = new Label(date.toString());
        labelDate.setId("date");
        GridPane.setHalignment(labelDate, HPos.CENTER);
        //son séparateur
        Separator separateurDate = new Separator();
        separateurDate.setPadding(new Insets(10, 0, 5, 0));

        //cours
        Label labelCours = new Label("_cours");
        labelCours.setMnemonicParsing(true);
        labelCours.setId("cours");
        //sa zone de texte
        textFieldCours = new TextField();
        Platform.runLater(() -> textFieldCours.requestFocus());
        labelCours.setLabelFor(textFieldCours);

        //niveau
        Label labelNiveau = new Label("niveau");
        labelNiveau.setId("niveau");
        //ses boutons radios

        TilePane levelBox = new TilePane();
        levelBox.setPrefColumns(2);
        levelBox.setPrefRows(2);
        radioGroup = new ToggleGroup();
        RadioButton boutonDebutant = new RadioButton("_débutant");
        boutonDebutant.setUserData("débutant");
        boutonDebutant.setToggleGroup(radioGroup);
        RadioButton boutonMoyen = new RadioButton("_moyen");
        boutonMoyen.setUserData("moyen");
        boutonMoyen.setToggleGroup(radioGroup);
        RadioButton boutonAvance = new RadioButton("a_vance");
        boutonAvance.setUserData("avance");
        boutonAvance.setToggleGroup(radioGroup);
        RadioButton boutonExpert = new RadioButton("e_xpert");
        boutonExpert.setUserData("expert");
        boutonExpert.setToggleGroup(radioGroup);
        levelBox.getChildren().add(boutonDebutant);
        levelBox.getChildren().add(boutonMoyen);
        levelBox.getChildren().add(boutonAvance);
        levelBox.getChildren().add(boutonExpert);

        boutonDebutant.setSelected(true);

        //horaires
        Label labelHoraire = new Label("horaire");
        labelHoraire.setId("horaire");
        Label labelHoraire2 = new Label("de");
        labelHoraire2.setId("horaire");
        Label labelHoraire3 = new Label("h");
        labelHoraire3.setId("horaire");
        Label labelHoraire4 = new Label("mn");
        labelHoraire4.setId("horaire");
        Label labelHoraire5 = new Label("à");
        labelHoraire5.setId("horaire");
        Label labelHoraire6 = new Label("h");
        labelHoraire6.setId("horaire");
        Label labelHoraire7 = new Label("mn");
        labelHoraire7.setId("horaire");
        //ses ComboBox
        comboHeureDebut = peupleComboBox(HEURES);
        comboHeureDebut.setValue(HEURES[0]);
        comboMinuteDebut = peupleComboBox(MINUTES);
        comboMinuteDebut.setValue(MINUTES[0]);
        comboHeureFin = peupleComboBox(HEURES);
        comboHeureFin.setValue(HEURES[0]);
        comboMinuteFin = peupleComboBox(MINUTES);
        comboMinuteFin.setValue(MINUTES[0]);

        //leur séparateur
        Separator separateurHoraire = new Separator();
        separateurHoraire.setPadding(new Insets(10, 0, 5, 0));

        //Boutons
        HBox boiteBoutons = new HBox();
        Button boutonAnnuler = new Button("_Annuler");
        boutonAnnuler.setMnemonicParsing(true);
        Button boutonEnregistrer = new Button("_Enregistrer");
        boutonEnregistrer.setMnemonicParsing(true);
        boiteBoutons.getChildren().add(boutonAnnuler);
        boiteBoutons.getChildren().add(boutonEnregistrer);
        boutonAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textFieldCours.clear();
            }
        });
        boutonEnregistrer.setOnAction(HBoxRoot.getControleur());

        //ajouts 3 ou 5 paramètres
        // compos. / col. / lig.
        //compos. / col. / lig. / colSpan / rowSpan
        add(labelDate, 0, ligne++, 6, 1);
        add(separateurDate, 0, ligne++, 6, 1);

        add(labelCours, 0, ligne);
        add(textFieldCours, 1, ligne++, 5, 1);

        add(labelNiveau, 0, ligne);
        add(levelBox, 1, ligne++, 4, 2);

        add(labelHoraire, 0, ligne);
        add(labelHoraire2, 1, ligne);
        add(comboHeureDebut, 2, ligne);
        add(labelHoraire3, 3, ligne);
        add(comboMinuteDebut, 4, ligne);
        add(labelHoraire4, 5, ligne++);

        add(labelHoraire5, 1, ligne);
        add(comboHeureFin, 2, ligne);
        add(labelHoraire6, 3, ligne);
        add(comboMinuteFin, 4, ligne);
        add(labelHoraire7, 5, ligne++);
        add(separateurHoraire, 0, ligne++, 6, 1);

        add(boiteBoutons, 2, ligne, 4, 1);

    }

    private ComboBox<String> peupleComboBox(String [] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        for (String string : strings){
            comboBox.getItems().add(string);
        }
        return comboBox;
    }

    public void update (DateCalendrier parDate) {
        date = parDate;
        labelDate.setText(date.toString());
//        HBoxRoot.getAffichagePlanning().setNumSemaine("Semaine " + parDate.getWeekOfYear());
        HBoxRoot.getAffichagePlanning().update(parDate);
    }

    public DateCalendrier getDate() {
        return date;
    }

    public TextField getTextFieldCours() {
        return textFieldCours;
    }

    //public ComboBox<String> getComboHeureDebut() {
    //    return comboHeureDebut;
    //}

    //public ComboBox<String> getComboMinuteDebut() {
    //    return comboMinuteDebut;
    //}

    public Horaire getHoraireDebut() {
        return new Horaire(comboHeureDebut.getSelectionModel().getSelectedIndex()+8,
                comboMinuteDebut.getSelectionModel().getSelectedIndex()*15);
    }

    public Horaire getHoraireFin() {
        return new Horaire(comboHeureFin.getSelectionModel().getSelectedIndex()+8,
                comboMinuteFin.getSelectionModel().getSelectedIndex()*15);
    }

    //public ComboBox<String> getComboHeureFin() {
    //    return comboHeureFin;
    //}

    //public ComboBox<String> getComboMinuteFin() {
    //    return comboMinuteFin;
    //}

    public String getNiveau() {
        return radioGroup.getSelectedToggle().getUserData().toString();
    }
}
