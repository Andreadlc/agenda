package modele;

/** Interface contenant toutes les constantes utilisées par le calendrier
 */
public interface ConstantesCalendrier {

    /** Tableau contenant les noms des jours de la semaine en français */
    final String [] JOURS_SEMAINE = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};

    /** Tableau contenant les noms des jours de la semaine abrégés en français */
    final String [] JOURS_SEMAINE_ABR = {"lu", "ma", "me", "je", "ve", "sa", "di"};

    /** Tableau contenant les noms des mois de l'année en français */
    final String [] MOIS = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre",
            "octobre", "novembre", "décembre"};

    /** Tableau contenant les heures utilisées pour les réservations du calendrier */
    final String [] HEURES = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};

    /** Tableau contenant les quart d'heures pour les réservations du calendrier */
    final String [] MINUTES = {"00", "15", "30", "45"};

}
