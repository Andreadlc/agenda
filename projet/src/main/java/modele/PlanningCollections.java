package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlanningCollections {
    private ArrayList <Reservation> chListReservations;
    private TreeSet <Reservation> chSetReservations;
    private TreeMap <Integer, TreeSet <Reservation>> chMapReservation;

    public PlanningCollections () {
        chListReservations = new ArrayList <> ();
        chSetReservations = new TreeSet <> ();
        chMapReservation = new TreeMap <> ();
    }

    /** Ajoute parReservation à chListReservation, chSetReservation et chMapReservation si parReservation est compatible
     */
    public void ajout (Reservation parReservation) throws ExceptionPlanning{

        if (!parReservation.estValide())
            throw new ExceptionPlanning(0);

        Iterator <Reservation> iteratorList = chListReservations.iterator();
        while (iteratorList.hasNext()) {
            Reservation nextList = iteratorList.next();
            if (nextList.compareTo(parReservation) == 0) {
                throw new ExceptionPlanning(2);
            }
        }
        chListReservations.add(parReservation);

        chSetReservations.add(parReservation);

        int parJour = parReservation.getDate().chJour;
        int parMois = parReservation.getDate().chMois;
        int parAnnee = parReservation.getDate().chAnnee;
        DateCalendrier parDate = new DateCalendrier(parJour, parMois, parAnnee);
        if (chMapReservation.containsKey(parDate.getWeekOfYear())) {
            TreeSet <Reservation> chReservations = chMapReservation.get(parDate.getWeekOfYear());
            chReservations.add(parReservation);
        }
        else {
            TreeSet <Reservation> parReservations = new TreeSet<>();
            parReservations.add(parReservation);
            chMapReservation.put(parDate.getWeekOfYear(),parReservations);
        }
    }

    /** Retourne l'ensemble des réservations de chSetReservations à la date parDate ou null si il n'y en a aucune.
    */
    public TreeSet <Reservation> getReservations (DateCalendrier parDate) {
        TreeSet <Reservation> parSetReservations = new TreeSet <> ();
        Iterator <Reservation> iterateur = chSetReservations.iterator();
        while (iterateur.hasNext()) {
            Reservation res = iterateur.next();
            if (res.getDate().compareTo(parDate) == 0)
                parSetReservations.add(res);
            }
        if (parSetReservations.size() == 0)
            parSetReservations = null;
        return parSetReservations;
    }

    /** Retourne l'ensemble des réservations de chSetReservations ayant la chaîne parString dans leur intitulé
     *  ou null si il n'y en a aucune.
     */
    public TreeSet <Reservation> getReservations (String parString) {
        TreeSet <Reservation> parSetReservations = new TreeSet <> ();
        Iterator <Reservation> iterateur = chSetReservations.iterator();
        while (iterateur.hasNext()) {
            Reservation res = iterateur.next();
            if (res.getIntitule().contains(parString))
                parSetReservations.add(res);
        }
        if (parSetReservations.size() == 0)
            parSetReservations = null;
        return parSetReservations;
    }

    /** Retourne l'ensemble des réservations de chSetReservations à la semaine parInt
     *  ou null si il n'y en a aucune.
     */
    public TreeSet <Reservation> getReservations (int parInt) {
        TreeSet <Reservation> parSetReservations = new TreeSet <> ();
        Iterator <Reservation> iterateur = chSetReservations.iterator();
        while (iterateur.hasNext()) {
            Reservation res = iterateur.next();
            if (res.getDate().getWeekOfYear() == parInt)
                parSetReservations.add(res);
        }
        if (parSetReservations.size() == 0)
            parSetReservations = null;
        return parSetReservations;
    }

    public String toString () {
        //return chListReservations.size() + " " + chListReservations + "\n" + "\n" + chSetReservations.size() + " " + chSetReservations
        //        + "\n" + "\n" + chMapReservation.size() + " " + chMapReservation;
        return chSetReservations.size() + " " + chSetReservations;
    }
}
