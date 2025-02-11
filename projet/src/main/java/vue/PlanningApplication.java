package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.CalendrierDuMois;

import java.io.File;

public class PlanningApplication extends Application {

    public void start(Stage stage)   {

        HBox root = new HBoxRoot();

        Scene scene = new Scene(root, 1000, 500);
        File fichierCss = new File("css"+File.separator+"StyleVBoxCalendar.css");
        scene.getStylesheets().add(fichierCss.toURI().toString());
        stage.setScene (scene);
        stage.setTitle("Calendrier");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
