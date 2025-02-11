package modele;

public class ExceptionPlanning extends Exception{
    private int chCodeErreur;

    /**Construit un objet de la classe ExceptionPlanning avec pour champ chCodeErreur un entier parCodeErreur
     */
    public ExceptionPlanning(int parCodeErreur){
        super();
        chCodeErreur = parCodeErreur;
    }

    /**Accesseur au champ chCodeErreur
     *
     */
    public int getCodeErreur (){
        return chCodeErreur;
    }
}
