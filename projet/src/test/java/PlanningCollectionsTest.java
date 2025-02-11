import static org.junit.jupiter.api.Assertions.*;

import modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TreeSet;

class PlanningCollectionsTest {
    private PlanningCollections planning;

    @BeforeEach
    void setUp() {
        planning = new PlanningCollections();
    }

    @Test
    void testAjoutReservation() {
        try {
            Reservation res1 = new Reservation(new DateCalendrier(1, 1, 2021), new PlageHoraire(new Horaire(8, 0), new Horaire(9, 0)), "Cours1", "Niveau1");
            planning.ajout(res1);

            // Vérifier que la réservation est bien ajoutée
            assertTrue(planning.getReservations(res1.getDate()).contains(res1));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }

    @Test
    void testAjoutReservationDoublon() {
        try {
            // Crée des objets Horaire pour l'heure de début et l'heure de fin
            Horaire horaireDebut = new Horaire(9, 0);  // 9h00
            Horaire horaireFin = new Horaire(10, 0);   // 10h00

            // Crée la première réservation avec tous les paramètres nécessaires
            Reservation res1 = new Reservation(
                    new DateCalendrier(10, 2, 2024),
                    new PlageHoraire(horaireDebut, horaireFin),  // Utilisation des objets Horaire dans PlageHoraire
                    "Réunion",
                    "Niveau 1"
            );
            planning.ajout(res1);  // Ajout de la première réservation

            // Essaye d'ajouter la même réservation (doit lever une exception)
            ExceptionPlanning exception = assertThrows(ExceptionPlanning.class, () -> {
                planning.ajout(res1);  // Tentative d'ajout du doublon
            });

            // Vérifie que le code d'erreur est bien celui du doublon (code 2)
            assertEquals(2, exception.getCodeErreur(), "Le code erreur doit être 2 pour un doublon.");

        } catch (ExceptionPlanning e) {
            // Si une exception est levée à ce stade, c'est une erreur
            fail("Le premier ajout ne devrait pas lever d'exception.");
        }
    }


    @Test
    void testGetReservationsParDate() {
        // À compléter après
    }

    @Test
    void testGetReservationsParIntitule() {
        // À compléter après
    }

    @Test
    void testGetReservationsParSemaine() {
        // À compléter après
    }
}
