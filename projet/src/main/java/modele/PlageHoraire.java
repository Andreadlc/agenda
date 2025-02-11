package modele;



public class PlageHoraire implements Comparable <PlageHoraire>{
    private final static int DUREE_MINIMUM = 60;
    private Horaire chHoraireDebut;
    private Horaire chHoraireFin;

    /**Construit un objet de la classe PlageHoraire en affectant les paramètres parHoraireDebut et parHoraireFin
     * aux champs chHoraireDebut et chHoraireFin
     *
     * @param parHoraireDebut le paramètre à affecter au champ chHoraireDebut
     * @param parHoraireFin le paramètre à affecter au champ chHoraireFin
     */
    public PlageHoraire (Horaire parHoraireDebut, Horaire parHoraireFin) throws ExceptionPlanning{
        if (parHoraireFin.toMinutes() - parHoraireDebut.toMinutes() < DUREE_MINIMUM)
            throw new ExceptionPlanning(3);

        chHoraireDebut = parHoraireDebut;
        chHoraireFin = parHoraireFin;
    }

    /**Teste si la plage horaire est valide. C'est à dire si les horaires de début et de fin sont valide, si l'horaire
     * de fin est bien après l'horaire de début et si la durée est inférieur à la durée minimum.
     *
     * @return false si la plage horaire n'est pas valide et true si elle l'est
     */
    public boolean estValide () {
        if (!chHoraireDebut.estValide() || !chHoraireFin.estValide())
            return false;
        if (chHoraireFin.toMinutes() < chHoraireDebut.toMinutes())
            return false;
        if (chHoraireFin.toMinutes() - chHoraireDebut.toMinutes() < DUREE_MINIMUM)
            return false;
        return true;
    }

    /**Renvoie la durée entre l'horaire de début et l'horaire de fin
     *
     * @return un horaire représentant la durée entre les horaires de début et fin
     */
    public Horaire duree() {
        return new Horaire (chHoraireFin.getHeure() - chHoraireDebut.getHeure(),
                chHoraireFin.getQuartHeure() - chHoraireDebut.getQuartHeure());
    }

    /**compareTo compare la plage horaire this avec parpH.
     *
     * @param parpH une plage horaire
     * @return un négatif si this précède parpH, un positif si parpH précède this et un nul sinon(égalité, chevauchement,
     * inclusion)
     */
    public int compareTo (PlageHoraire parpH) {
        if (parpH.chHoraireDebut.toMinutes() >= chHoraireFin.toMinutes())
            return -1;
        else if (parpH.chHoraireFin.toMinutes() <= chHoraireDebut.toMinutes())
            return 1;
        else
            return 0;
    }

    /**toString affiche la plage horaire
     *
     * @return une chaine de caractère contenant la plage horaire
     */
    public String toString() {
        Horaire duree = duree();
        return chHoraireDebut + " - " + chHoraireFin + ", duree : " + duree.getHeure() + "h" + duree.getQuartHeure() + "min";
    }
}
