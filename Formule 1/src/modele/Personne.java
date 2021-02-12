package modele;

import java.util.*;

/**
 * @author Asuncion Gomez
 */
public abstract class Personne {
    private int identification;
    private String nom;
    private String prenom;
    private String adresse;
    private Voiture voiture;

    private static int dernierIdentification = 0;

    private final String SANS_NOM= "Doe";
    private final String SANS_PRENOM= "John";
    private final String DEFAULT_ADRESSE= "Peta, OUCHNOK";



    /**
     * Default constructor
     */
    public Personne() {
        dernierIdentification++;
        this.identification=dernierIdentification;
        this.nom=SANS_NOM;
        this.prenom=SANS_PRENOM;
        this.adresse=DEFAULT_ADRESSE;
        this.voiture=null;
    }
    public Personne(String nom){
        this();
        if(nom!=null)
            this.nom=nom;
    }
    public Personne(String nom, String prenom){
        this(nom);
        if(prenom !=null)
            this.prenom=prenom;

    }
    public Personne(String nom, String prenom, String adresse){
        this(nom, prenom);
        if(adresse!=null){
            this.adresse=adresse;
        }
    }

    /**
     * Constructor par donees
     * @param nom
     * @param prenom
     * @param adresse
     * @param voiture
     */
    public Personne(String nom, String prenom, String adresse, Voiture voiture){
        this();
        if(nom!=null)
            this.nom=nom;
        if(prenom!=null)
            this.prenom=prenom;
        if(adresse!=null)
            this.adresse=adresse;
        if(voiture!= null) {
            this.voiture = voiture;
            this.voiture.affecterVoiture(this);
        }
    }
    /**
     * Constructeur par copie
     * @param personne
     */
    public Personne(Personne personne){
        this(personne.getNom(), personne.getPrenom(), personne.getAdresse(), personne.getVoiture());
    }
    /**
     * Acceseur en lecture de l'attribut identification
     * @return identification
     */
    public int getIdentification() {
        return identification;
    }
    /**
     * Acceseur en lecture de l'attribut nom
     * @return nom
     */
    public String getNom() {
        String nom = new String(this.nom);
        return nom;
    }
    /**
     * Acceseur en lecture de l'attribut prenom
     * @return prenom
     */
    public String getPrenom() {
        String prenom= new String(this.prenom);
        return prenom;
    }
    /**
     * Acceseur en lecture de l'attribut adresse
     * @return adresse
     */
    public String getAdresse() {
        String adresse = new String(this.adresse);
        return adresse;
    }
    /**
     * Acceseur en lecture de l'attribut voiture
     * @return voiture
     */
    public Voiture getVoiture() {
        Voiture car= this.voiture;
        return car;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Acceseur en écriture de l'attribut adresse
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString(){
        String s= String.format("nº%d\t%-10s\t%-10s\t%-24s", identification, prenom, nom.toUpperCase(), adresse);
        if (estPieton())
            return s + "Piéton";
        return s + "\n  Voiture "+ this.voiture;
    }

    /**
     * indique si la personne est a pied ou non
     * @return true si est pieton
     */
    public boolean estPieton(){
        return this.voiture==null;
    }

    /**
     * l’affectation de la voiture v à la personne.
     * @param V
     * @return boolean si l’affectation a pu etre effectuee correctement ou non.
     */
    public boolean affecter(Voiture V){
        //la voiture n'existe pas ou  la voiture n'est pas disponible ou la personne n'est pas un pieton
        if(V == null || !V.estDisponible() || !this.estPieton())
            return false;
        //si ne sont pas compatibles
        if(!this.estCompatible(V))
            return false;
        else{
            this.voiture= V;
            return this.voiture.affecterVoiture(this);
        }
    }

    /**
     * @return true si on oeut restituer la voiture
     */
    public boolean restituer() {
        if(!this.estPieton()){
            Voiture vehicule = this.voiture;//new Voiture(this.voiture);
            this.voiture=null;
            return vehicule.restituerVoiture();
        }
        return false;
    }

    /**
     * Pour verifier que les techniciens conduisent les camions
     * et les pilotes les formule1
     * @param v voiture
     * @return true si sont compatibles
     */
    public boolean estCompatible(Voiture v){
        return false;
    }
    /**
     * @param Car V
     *          hola
     *           hola ""
     * @return Voiture v
     *
     */

}