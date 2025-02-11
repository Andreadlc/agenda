package modele;

/** Interface contenant les différentes erreurs pouvant être renvoyées par le planning */
public interface ConstantesErreur {
    /** Liste des erreurs affichable selon le code erreur */
    public final String [] ERREURS_PLANNING = {
            "Reservation invalide",
            "Planning complet",
            "Reservation incompatible",
            "Plage Horaire invalide"
    };
}
