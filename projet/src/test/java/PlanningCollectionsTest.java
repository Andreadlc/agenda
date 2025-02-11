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
        try {
            // Crée deux réservations pour la même date, mais avec des horaires différents
            Horaire horaireDebut1 = new Horaire(9, 0);  // 9h00
            Horaire horaireFin1 = new Horaire(10, 0);   // 10h00
            Reservation res1 = new Reservation(
                    new DateCalendrier(5, 3, 2024),
                    new PlageHoraire(horaireDebut1, horaireFin1),
                    "Cours Java",
                    "Niveau 1"
            );

            Horaire horaireDebut2 = new Horaire(10, 30);  // 10h30
            Horaire horaireFin2 = new Horaire(11, 30);   // 11h30
            Reservation res2 = new Reservation(
                    new DateCalendrier(5, 3, 2024),
                    new PlageHoraire(horaireDebut2, horaireFin2),
                    "Réunion Projet",
                    "Niveau 1"
            );

            planning.ajout(res1);
            planning.ajout(res2);

            // Récupère les réservations pour la date 5 mars 2024
            TreeSet<Reservation> reservations = planning.getReservations(new DateCalendrier(5, 3, 2024));

            assertNotNull(reservations);
            assertEquals(2, reservations.size()); // Vérifie qu'on a bien 2 réservations pour cette date
            assertTrue(reservations.contains(res1));
            assertTrue(reservations.contains(res2));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }


    @Test
    void testGetReservationsParIntitule() {
        try {
            // Crée trois réservations avec des intitulés différents
            Horaire horaireDebut1 = new Horaire(9, 0);  // 9h00
            Horaire horaireFin1 = new Horaire(10, 0);   // 10h00
            Reservation res1 = new Reservation(
                    new DateCalendrier(12, 4, 2024),
                    new PlageHoraire(horaireDebut1, horaireFin1),
                    "Cours Java",
                    "Niveau 1"
            );

            Horaire horaireDebut2 = new Horaire(10, 0);  // 10h00
            Horaire horaireFin2 = new Horaire(11, 0);   // 11h00
            Reservation res2 = new Reservation(
                    new DateCalendrier(15, 4, 2024),
                    new PlageHoraire(horaireDebut2, horaireFin2),
                    "Projet Java",
                    "Niveau 2"
            );

            Horaire horaireDebut3 = new Horaire(14, 0);  // 14h00
            Horaire horaireFin3 = new Horaire(15, 0);   // 15h00
            Reservation res3 = new Reservation(
                    new DateCalendrier(18, 4, 2024),
                    new PlageHoraire(horaireDebut3, horaireFin3),
                    "Réunion équipe",
                    "Niveau 1"
            );

            planning.ajout(res1);
            planning.ajout(res2);
            planning.ajout(res3);

            // Récupère les réservations contenant "Java"
            TreeSet<Reservation> reservations = planning.getReservations("Java");

            assertNotNull(reservations);
            assertEquals(2, reservations.size()); // Vérifie qu'on a bien 2 réservations contenant "Java"
            assertTrue(reservations.contains(res1));
            assertTrue(reservations.contains(res2));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }


    @Test
    void testGetReservationsParSemaine() {
        // À compléter après
    }
}
