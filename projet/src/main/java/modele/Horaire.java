package modele;


public class Horaire {
    private int chHeure;
    private int chQuartHeure;

    /**Construit un objet de la classe Horaire en affectant les paramètres parHeure et parQuartHeure aux champs
     * chHeure et chQuartHeure
     *
     * @param parHeure le paramètre à affecter au champ chHeure
     * @param parQuartHeure le paramètre à affecter au champ chQuartHeure
     */
    public Horaire (int parHeure, int parQuartHeure) {
        chHeure = parHeure;
        chQuartHeure = parQuartHeure;
    }

    /**Teste si l'horaire est valide. C'est à dire si les minutes sont un quart d'heure, si l'heure est entre 0 et 23
     * compris et si le quart d'heure est entre 0 et 59 compris.
     *
     * @return false si l'horaire n'est pas valide et true si il l'est
     */
    public boolean estValide() {
        if (chQuartHeure % 15 != 0)
            return false;
        if (chHeure < 0 || chHeure > 23)
            return false;
        if (chQuartHeure < 0 || chQuartHeure > 59)
            return false;
        return true;
    }

    /**Renvoie la conversion de l'horaire en minutes
     *
     * @return la conversion de l'horaire en minutes
     */
    public int toMinutes () {
        return chHeure*60 + chQuartHeure;
    }

    /**Accesseur qui renvoie le champ de l'heure de l'objet de la classe Horaire
     *
     * @return chHeure le champ de l'heure
     */
    public int getHeure () {
        return chHeure;
    }

    /**Accesseur qui renvoie le champ du quart d'heure (minutes) de l'objet de la classe Horaire
     *
     * @return chQuartHeure le champ du quart d'heure
     */
    public int getQuartHeure () {
        return chQuartHeure;
    }

    /**Modifieur qui change la valeur du champ chHeure pour le paramètre parHeure
     *
     * @param parHeure la nouvelle heure à affecter
     */
    public void setHeure (int parHeure) {
        chHeure = parHeure;
    }

    /**Modifieur qui change la valeur du champ chQuartHeure pour le paramètre parQuartHeure
     *
     * @param parQuartHeure le nouveau quart d'heure à affecter
     */
    public void setQuartHeure (int parQuartHeure) {
        chQuartHeure = parQuartHeure;
    }

    /**toString affiche l'horaire
     *
     * @return une chaine de caractère contenant l'horaire
     */
    public String toString () {
        return chHeure + "h" + chQuartHeure;
    }
}
