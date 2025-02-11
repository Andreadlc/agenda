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
