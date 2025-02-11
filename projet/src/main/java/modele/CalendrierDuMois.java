package modele;

import java.util.Collection;
import java.util.TreeSet;

/** La classe CalendrierDuMois représente le calendrier d'un mois d'une année spécifique.*/
public class CalendrierDuMois {
    /**le champ mois représente un mois de l'année*/
    private int mois;
    /**le champ année représente l'année*/
    private int annee;
    /**le champ treeSetDate représente une collection d'objet de la classe DateCalendrier qui représentera la liste de toutes les
     * dates du mois de de l'année*/
    private Collection <DateCalendrier> treeSetDate;

    /**Constructeur de la classe CalendrierDuMois utilisant un mois et une année sous forme de int comme paramètres.
     *
     * @param mois un entier représentant le mois
     * @param annee un entier représentant l'année
     */
    public CalendrierDuMois ( int mois, int annee) {
        this.mois = mois;
        this.annee = annee;
        treeSetDate = new TreeSet <> ();
        DateCalendrier date = new DateCalendrier (1,mois,annee);
        int indiceJour = date.getJourSemaine() ;
        for (int x = indiceJour ; x!=0 ; x--) {
            treeSetDate.add(date);
            date = date.dateDeLaVeille();
        }
        date = new DateCalendrier (2,mois,annee);
        indiceJour = indiceJour % 7 ;
        while (date.getMois () == mois) {
            while(indiceJour<7) {
                treeSetDate.add(date);
                date = date.dateDuLendemain();
                indiceJour++ ;
            }
            indiceJour=0;
        }
    }

    /** Accesseur du champ treeSetDate
     *
     * @return treeSetDate
     */
    public Collection <DateCalendrier> getDates() {
        return treeSetDate;
    }

    /** Accesseur du champ mois
     *
     * @return mois
     */
    public int getMois () {
        return mois;
    }

    /** Accesseur du champ annee
     *
     * @return annee
     */
    public int getAnnee() {
        return annee;
    }

    /** toString envoie une chaine de caractères composée de la taille de treeSetDate ainsi que toutes les dates qui le
     * compose
     * @return une chaîne de caractères
     */
    public String toString () {
        return treeSetDate.size() + " " + treeSetDate.toString();
    }


}
