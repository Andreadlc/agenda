package modele;

import java.util.Scanner;

/** Représente une date chJour/chMois/chAnnee */
public class Date{
    /** Un enter représentant un jour */
    protected int chJour;
    /** Un entier représentant un mois */
    protected int chMois;
    /** Un entier représentant une année */
    protected int chAnnee;

    /** Constructeur de la classe Date
     *
     * @param parJour un enter représentant un jour
     * @param parMois un entier représentant un mois
     * @param parAnnee un entier représentant une année
     */
    public Date(int parJour, int parMois, int parAnnee){
        chJour = parJour;
        chMois = parMois;
        chAnnee = parAnnee;
    }

    /** Constructeur test vide
     *
     */
    public Date(){
    }

    /** Constructeur de la classe Date qui créé la date 01/01/chAnnée
     *
     * @param parAnnee un entier représentant une année
     */
    public Date(int parAnnee){
        chJour = 1;
        chMois = 1;
        chAnnee = parAnnee;
    }

    /** Lit les dates à l'aide de la classe Scanner */
    public static Date lireDate(){
        Scanner scanner = new Scanner(System.in);
        int jour = scanner.nextInt();
        int mois = scanner.nextInt();
        int annee = scanner.nextInt();
        return new Date(jour, mois, annee);
    }

    /** Vérifie si this est valide. C'est à dire si l'année est 1583 ou après, si le mois est entre 1 et 12 compris
     * et si le jour est bien compris entre 1 et le dernier jour du mois compris
     *
     * @return true si this est valide et false si elle est fausse
     */
    public boolean estValide(){
        if (chAnnee >= 1583){
            if (chMois > 0 && chMois < 13){
                if (chJour > 0 && chJour <= dernierJourDuMois(chMois, chAnnee))
                    return true;
            }
        }
        return false;
    }

    /** Vérifie si parAnnee est une année bissextile. C'est à dire si elle est soit divisible par 4 mais pas par 100
     * ou alors si elle divisible par 400
     *
     * @param parAnnee un entier représentant une année
     * @return true si chAnnee est bissextile et false sinon
     */
    private static boolean estBissextile(int parAnnee){
        return ((parAnnee%4 == 0 && parAnnee%100 != 0) || parAnnee%400 == 0);
    }


    private static int dernierJourDuMois(int parMois, int parAnnee){
        switch (parMois){
            case 2 : if (Date.estBissextile(parAnnee))
                        return 29;
                    return 28;
            case 4 : case 6 : case 9 : case 11 : return 30;
            default : return 31;
        }
    }

    /** compareTo compare this avec parDate
     *
     * @param parDate une date
     * @return 0 si this et parDate sont égaux, un négatif si this < parDate ou
     *          un positif si this > parDate
     */
    public int compareTo (Date parDate) {
        if (this.chAnnee < parDate.chAnnee)
            return -1;
        if (this.chAnnee > parDate.chAnnee)
            return 1;

        if (this.chMois < parDate.chMois)
            return -1;
        if (this.chMois > parDate.chMois)
            return 1;

        if (this.chJour < parDate.chJour)
            return -1;
        if (this.chJour > parDate.chJour)
            return 1;

        return 0;
    }

    /**dateDuLendemain renvoie la date du lendemain de this
     *
     * @return la date du lendemain
     */
    public Date dateDuLendemain () {
        if (this.chJour + 1 <= dernierJourDuMois(this.chMois, this.chAnnee))
            return new Date(chJour + 1, this.chMois, this.chAnnee);
        else {
            if (this.chMois + 1 <= 12)
                return new Date(1, this.chMois + 1, this.chAnnee);
            else
                return new Date(this.chAnnee + 1);
        }
    }

    /**dateDeLaVeille renvoie la date de la veille de this
     *
     * @return la date de la veille
     */
    public Date dateDeLaVeille () {
        if (this.chJour - 1 >= 1)
            return new Date(chJour - 1, this.chMois, this.chAnnee);
        else {
            if (this.chMois - 1 >= 1)
                return new Date(dernierJourDuMois(this.chMois-1, this.chAnnee), this.chMois - 1, this.chAnnee);
            else
                return new Date(31, 12, this.chAnnee - 1);
        }
    }


    public String toString(){
        if (estValide())
            return chJour + "/" + chMois + "/" + chAnnee;
        else
            return "date invalide";
    }


}
