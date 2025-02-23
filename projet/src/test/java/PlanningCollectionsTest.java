import static org.junit.jupiter.api.Assertions.*;
import modele.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TreeSet;

class PlanningCollectionsTest {
    private PlanningCollections planning;

    /**
     * Initialise le planning avant chaque test.
     */
    @BeforeEach
    void setUp() {
        planning = new PlanningCollections();
    }

    /**
     * Teste l'ajout d'une réservation et vérifie qu'elle est bien stockée dans le planning.
     *
     * - Crée une réservation avec une date et une plage horaire.
     * - Ajoute la réservation au planning.
     * - Vérifie que la réservation est bien présente dans les réservations du planning.
     */
    @Test
    void testAjoutReservation() {
        try {
            Reservation res1 = new Reservation(
                    new DateCalendrier(1, 1, 2021),
                    new PlageHoraire(new Horaire(8, 0), new Horaire(9, 0)),
                    "Cours1",
                    "Niveau1"
            );
            planning.ajout(res1);

            // Vérifier que la réservation est bien ajoutée
            assertTrue(planning.getReservations(res1.getDate()).contains(res1));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }

    /**
     * Teste l'ajout d'une réservation en doublon et vérifie que l'exception est bien levée.
     *
     * - Crée une réservation avec une date et une plage horaire.
     * - Ajoute la réservation une première fois (réussite attendue).
     * - Tente d'ajouter la même réservation une deuxième fois (doit lever une exception).
     * - Vérifie que l'exception levée a bien le code d'erreur correspondant à un doublon.
     */
    @Test
    void testAjoutReservationDoublon() {
        try {
            Horaire horaireDebut = new Horaire(9, 0);
            Horaire horaireFin = new Horaire(10, 0);

            Reservation res1 = new Reservation(
                    new DateCalendrier(10, 2, 2024),
                    new PlageHoraire(horaireDebut, horaireFin),
                    "Réunion",
                    "Niveau 1"
            );
            planning.ajout(res1);

            // Tentative d'ajout du doublon
            ExceptionPlanning exception = assertThrows(ExceptionPlanning.class, () -> {
                planning.ajout(res1);
            });

            // Vérifie que le code d'erreur correspond à un doublon
            assertEquals(2, exception.getCodeErreur(), "Le code erreur doit être 2 pour un doublon.");

        } catch (ExceptionPlanning e) {
            fail("Le premier ajout ne devrait pas lever d'exception.");
        }
    }

    /**
     * Teste la récupération des réservations à une date spécifique.
     *
     * - Crée deux réservations avec la même date mais des horaires différents.
     * - Ajoute ces réservations au planning.
     * - Vérifie que les deux réservations sont bien récupérées via la méthode getReservations(date).
     */
    @Test
    void testGetReservationsParDate() {
        try {
            Horaire horaireDebut1 = new Horaire(9, 0);
            Horaire horaireFin1 = new Horaire(10, 0);
            Reservation res1 = new Reservation(
                    new DateCalendrier(5, 3, 2024),
                    new PlageHoraire(horaireDebut1, horaireFin1),
                    "Cours Java",
                    "Niveau 1"
            );

            Horaire horaireDebut2 = new Horaire(10, 30);
            Horaire horaireFin2 = new Horaire(11, 30);
            Reservation res2 = new Reservation(
                    new DateCalendrier(5, 3, 2024),
                    new PlageHoraire(horaireDebut2, horaireFin2),
                    "Réunion Projet",
                    "Niveau 1"
            );

            planning.ajout(res1);
            planning.ajout(res2);

            TreeSet<Reservation> reservations = planning.getReservations(new DateCalendrier(5, 3, 2024));

            assertNotNull(reservations);
            assertEquals(2, reservations.size());
            assertTrue(reservations.contains(res1));
            assertTrue(reservations.contains(res2));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }

    /**
     * Teste la récupération des réservations par intitulé.
     *
     * - Crée trois réservations dont deux contiennent "Java" dans l'intitulé.
     * - Ajoute ces réservations au planning.
     * - Vérifie que la méthode getReservations(intitule) retourne bien les réservations contenant "Java".
     */
    @Test
    void testGetReservationsParIntitule() {
        try {
            Horaire horaireDebut1 = new Horaire(9, 0);
            Horaire horaireFin1 = new Horaire(10, 0);
            Reservation res1 = new Reservation(
                    new DateCalendrier(12, 4, 2024),
                    new PlageHoraire(horaireDebut1, horaireFin1),
                    "Cours Java",
                    "Niveau 1"
            );

            Horaire horaireDebut2 = new Horaire(10, 0);
            Horaire horaireFin2 = new Horaire(11, 0);
            Reservation res2 = new Reservation(
                    new DateCalendrier(15, 4, 2024),
                    new PlageHoraire(horaireDebut2, horaireFin2),
                    "Projet Java",
                    "Niveau 2"
            );

            Horaire horaireDebut3 = new Horaire(14, 0);
            Horaire horaireFin3 = new Horaire(15, 0);
            Reservation res3 = new Reservation(
                    new DateCalendrier(18, 4, 2024),
                    new PlageHoraire(horaireDebut3, horaireFin3),
                    "Réunion équipe",
                    "Niveau 1"
            );

            planning.ajout(res1);
            planning.ajout(res2);
            planning.ajout(res3);

            TreeSet<Reservation> reservations = planning.getReservations("Java");

            assertNotNull(reservations);
            assertEquals(2, reservations.size());
            assertTrue(reservations.contains(res1));
            assertTrue(reservations.contains(res2));
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }

    /**
     * Teste la récupération des réservations par numéro de semaine.
     *
     * - Crée deux réservations dans la même semaine.
     * - Ajoute ces réservations au planning.
     * - Vérifie que la méthode getReservations(semaine) retourne bien les réservations de cette semaine.
     */
    @Test
    void testGetReservationsParSemaine() {
        try {
            Horaire horaireDebut1 = new Horaire(9, 0);
            Horaire horaireFin1 = new Horaire(10, 0);
            Reservation res1 = new Reservation(
                    new DateCalendrier(1, 1, 2024),
                    new PlageHoraire(horaireDebut1, horaireFin1),
                    "Meeting",
                    "Niveau 1"
            );

            Horaire horaireDebut2 = new Horaire(10, 0);
            Horaire horaireFin2 = new Horaire(11, 0);
            Reservation res2 = new Reservation(
                    new DateCalendrier(23, 1, 2024),
                    new PlageHoraire(horaireDebut2, horaireFin2),
                    "Conférence",
                    "Niveau 1"
            );

            planning.ajout(res1);
            planning.ajout(res2);

            TreeSet<Reservation> reservations = planning.getReservations(1);

            assertNotNull(reservations);
            assertEquals(1, reservations.size());
        } catch (ExceptionPlanning e) {
            fail("L'ajout ne devrait pas lever d'exception.");
        }
    }
}
