package modele;


public class Reservation implements Comparable <Reservation>{
    private DateCalendrier chDate;
    private PlageHoraire chPlageHoraire;
    private String chIntitule;
    private String chNiveau;

    /**Construit un objet de la classe Reservation en affectant les paramètres parDate, parPlageHoraire et parIntitule
     * aux champs chDate, chPlageHoraire et chIntitule
     *
     * @param parDate le paramètre à affecter au champ chDate
     * @param parPlageHoraire le paramètre à affecter au champ chPlageHoraire
     * @param parIntitule le paramètre à affecter au champ chIntitule
     */
    public Reservation (DateCalendrier parDate, PlageHoraire parPlageHoraire, String parIntitule, String parNiveau) {
        chDate = parDate;
        chPlageHoraire = parPlageHoraire;
        chIntitule = parIntitule;
        chNiveau = parNiveau;
    }

    /**Renvoie true si chDate et chPlageHoraire sont valide.
     *
     */
    public boolean estValide () {
        if (chDate.estValide() && chPlageHoraire.estValide())
            return true;
        return false;
    }

    /**compareTo compare this avec le paramètre parReservation
     *
     * @param parReservation une réservation possédant les champs chDate, chPlageHoraire et chIntitule
     * @return un négatif si this précède parReservation, un positif si parReservation précède this et un nul sinon
     */
    public int compareTo (Reservation parReservation) {
        if (chDate.compareTo(parReservation.chDate) < 0)
            return -1;
        if (chDate.compareTo(parReservation.chDate) > 0)
            return 1;

        if (chPlageHoraire.compareTo(parReservation.chPlageHoraire) < 0)
            return -1;
        if (chPlageHoraire.compareTo(parReservation.chPlageHoraire) > 0)
            return 1;

        return 0;

    }

    /**Accesseur de chDate
     *
     */
    public DateCalendrier getDate() {
        return chDate;
    }

    /**Accesseur de chPlageHoraire
     *
     */
    public PlageHoraire getPlageHoraire() {
        return chPlageHoraire;
    }

    /**Accesseur de chIntitule
     *
     */
    public String getIntitule() {
        return chIntitule;
    }

    /**Accesseur de chNiveau
     *
     */
    public String getNiveau(){
        return chNiveau;
    }

    /**Affiche l'objet de la classe Reservation sous la forme chIntitule, chDate, chPlageHoraire.
     *
     * @return une chaîne de caractères de la forme chIntitule, chDate, chPlageHoraire
     */
    public String toString() {
        return chIntitule + " " + chNiveau + ", "+ chDate + ", " + chPlageHoraire;
    }
}
