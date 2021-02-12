package modele;

import modele.Personne;

/**
 * @author Asuncion Gomez
 */
public class Pilote extends Personne {
    private int nombrePrix;
    private int nombreAbandon;

    private final int N_PRIX_DEFAUT= 0;
    private final int N_ABANDON_DEFAUT=0;

    /**
     * Default constructor
     */
    public Pilote(){
        this.nombrePrix=N_PRIX_DEFAUT;
        this.nombreAbandon=N_ABANDON_DEFAUT;
    }

    public Pilote(String nom) {
        super(nom);
        this.nombrePrix=N_PRIX_DEFAUT;
        this.nombreAbandon=N_ABANDON_DEFAUT;
    }

    public Pilote(String nom, String prenom) {
        super(nom, prenom);
        this.nombrePrix=N_PRIX_DEFAUT;
        this.nombreAbandon=N_ABANDON_DEFAUT;
    }

    public Pilote(Personne personne) {
        super(personne);
    }

    public Pilote(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        this.nombrePrix=N_PRIX_DEFAUT;
        this.nombreAbandon=N_ABANDON_DEFAUT;
    }

    /**
     * Constructor
     * @param nombrePrix 
     * @param nombreAbandon 
     */
    public Pilote(int nombrePrix, int nombreAbandon) {
        this.nombrePrix = nombrePrix;
        this.nombreAbandon = nombreAbandon;
    }
    /**
     * Constructor
     * @param personne
     * @param nombrePrix
     * @param nombreAbandon
     */

    public Pilote(Personne personne, int nombrePrix, int nombreAbandon) {
        super(personne);
        this.nombrePrix = nombrePrix;
        this.nombreAbandon = nombreAbandon;
    }
    /**
     * Constructor
     * @param nom 
     * @param prenom
     * @param adresse
     * @param voiture
     * @param nombrePrix
     * @param nombreAbandon
     */
    public Pilote(String nom, String prenom, String adresse, Voiture voiture, int nombrePrix, int nombreAbandon) {
        super(nom, prenom, adresse, voiture);
        this.nombrePrix = nombrePrix;
        this.nombreAbandon = nombreAbandon;
    }


    public int getNombrePrix() {
        return nombrePrix;
    }

    public int getNombreAbandon() {
        return nombreAbandon;
    }

    @Override
    public String toString() {
        return "Pilote{" +
                "nombrePrix=" + nombrePrix +
                ", nombreAbandon=" + nombreAbandon +
                "} " + super.toString();
    }

    @Override
    public boolean estCompatible(Voiture v) {
        return (v instanceof Formule1);
    }

    /**
     * 
     */
    public void gagner_prix() {
        // TODO implement here
    }

    /**
     * 
     */
    public void abandonnner() {
        // TODO implement here
    }

    /**
     * 
     */
    public void retirer_prix() {
        // TODO implement here
    }

}