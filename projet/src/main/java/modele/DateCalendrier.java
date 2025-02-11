package modele;

import java.util.Calendar;

public class DateCalendrier extends Date implements ConstantesCalendrier, Comparable <Date>{
    private int chJourSemaine ; //1:lundi...

    /**Constructeur de la classe DateCalendrier qui construit la date du jour
     *
     */
    public DateCalendrier () {
        Calendar today = Calendar.getInstance();

        chAnnee = today.get (Calendar.YEAR);

        // Calendar.MONTH retourne 0 pour janvier, 1 pour février...
        chMois = today.get (Calendar.MONTH) + 1;

        chJour = today.get (Calendar.DAY_OF_MONTH);

        //Calendar.DAY_OF_WEEK retourne 1 pour dimanche, 2 pour lundi...
        int dayOfWeek = today.get (Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1)
            chJourSemaine = 7;
        else chJourSemaine = dayOfWeek - 1;
    }

    /**Constructeur de la classe DateCalendrier qui construit la date parJour/parMois/parAnnee
     *
     */
    public DateCalendrier(int parJour, int parMois, int parAnnee){
        super(parJour, parMois, parAnnee);
        Calendar day = Calendar.getInstance();
        day.set(parAnnee, parMois-1, parJour);
        int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1)
            chJourSemaine = 7;
        else chJourSemaine = dayOfWeek - 1;
    }

    /**Renvoie le jour de la semaine
     */
    public int getJourSemaine() {
        return chJourSemaine;
    }

    /**Renvoie l'Année'
     */
    public int getAnnee() {
        return chAnnee;
    }

    /**Renvoie le mois
     */
    public int getMois() {
        return chMois;
    }

    /**renvoie le jour
     */
    public int getJour(){
        return chJour;
    }

    public boolean isToday() {
        if (this.compareTo(new DateCalendrier()) == 0) {
            return true;
        }
        return false;
    }

    /**Renvoie le numéro de la semaine de la date chJour/chMois/chAnnee
     */
    public int getWeekOfYear () {
        Calendar date = Calendar.getInstance();
        date.set(chAnnee, chMois - 1, chJour);
        return date.get(Calendar.WEEK_OF_YEAR);
    }

    /**dateDuLendemain renvoie la date du lendemain de this
     *
     */
    public DateCalendrier dateDuLendemain () {
        Date dateLendemain = super.dateDuLendemain();
        return new DateCalendrier(dateLendemain.chJour, dateLendemain.chMois, dateLendemain.chAnnee);
    }

    /**dateDeLaVeille renvoie la date de la veille de this
     *
     */
    public DateCalendrier dateDeLaVeille () {
        Date dateVeille = super.dateDeLaVeille();
        return new DateCalendrier(dateVeille.chJour, dateVeille.chMois, dateVeille.chAnnee);
    }

    public String toString () {
        return JOURS_SEMAINE[chJourSemaine-1] + " " + chJour + " " + MOIS[chMois-1] + " " + chAnnee;
    }
}
