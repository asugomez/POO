package modele;

import modele.Moteur;

/**
 * @author Asuncion Gomez
 * Class Voiture
 */
public abstract class Voiture {
    private final int identifiant;
    private String marque;
    private Moteur moteur;
    private Personne conducteur;

    private static int dernierIdentifiant = 0;


    //private final int IDENTIFIANT;
    private final String DEFAULT_MARQUE= "BRM";

    /**
     * Default constructor
     */
    public Voiture() {
        dernierIdentifiant++;
        this.identifiant= dernierIdentifiant;
        this.marque= DEFAULT_MARQUE;
        this.moteur=new Moteur();
        this.conducteur=null;
    }
    /**
     * Constructor par donnée de la marque
     * @param marque
     */
    public Voiture(String marque){
        this();
        if(marque!= null)
            this.marque=marque;
    }
    /**
     * Constructor par donnees de la marque, de la puissance et de la carburant du moteur
     * @param marque
     * @param puissance
     * @param carburant
     */
    public Voiture(String marque, int puissance, char carburant){
        this(marque);
        Moteur new_moteur= new Moteur(puissance, carburant);
        this.setMoteur(new_moteur);
    }
    /**
     * Constructor par donnees de la marque et d’un moteur.
     * @param marque
     * @param moteur
     */
    public Voiture(String marque, Moteur moteur){
        this(marque);
        this.setMoteur(moteur);
        //on utilise pas: this.moteur =moteur;, que se passe-t-il ? Dans ce cas, nous avons deux références qui pointent
        //sur le même objet. Et donc dans la classe Voiture on a pas de contrôle total sur
        //le moteur, i.e. que le moteur passé en paramètre peut être modifié s
    }

    /**
     * Constructeur par donnees
     * @param marque
     * @param moteur
     * @param conducteur
     */
    public Voiture(String marque, Moteur moteur, Personne conducteur){
        this(marque,moteur);
        if(conducteur!= null){
            this.conducteur=conducteur;
        }
    }

    /**
     * Acceseur en lecture de l'attribut identifiant
     */
    public int getIdentifiant() {
        return identifiant;
    }
    /**
     * Accesseur en lecture de l'attribut marque
     */
    public String getMarque() {
        return marque;
    }
    /**
     * Acceseur en lecture de l'attribut moteur
     */
    public Moteur getMoteur() {
        Moteur moteur= new Moteur(this.moteur);
        return moteur;
    }
    public int getPuissanceMoteur(){
        return this.moteur.getPuissance();
    }
    public char getCarburantMoteur(){
        return this.moteur.getCarburant();

    }
    /**
     * Acceseur en lecture de l'attribut conducteur
     */
    public Personne getConducteur(){
        if(this.conducteur!= null){
            Personne conducteur;
            if(this.conducteur instanceof Pilote)
                conducteur= new Pilote(this.conducteur);

            else
                conducteur=new Technicien(this.conducteur);
            return conducteur;
        }
        return null;

    }

    /**
     * Acceseur en écriture de l'attribut moteur
     */
    public void setMoteur(Moteur moteur) {
        if(moteur!= null)
            this.moteur = moteur;
    }

    /**
     * Acceseur en écriture de l'attribut puissance du moteur
     */
    public void setPuissanceMoteur(int puissanceMoteur){
        this.moteur.setPuissance(puissanceMoteur);
    }

    @Override
    /*public String toString() {
        return "Voiture{" +
                "identifiant=" + identifiant +
                ", marque='" + marque + '\'' +
                ", moteur=" + moteur + '\'' +
                ", conducteur=" + conducteur + '\'' +
                '}';
    }*/

    public String toString() {
        String s =  String.format("n°%-4d\tmarque : %-12s",
                identifiant, marque) + "\tmoteur : " + moteur;
        if (conducteur != null)
            s += "\tconducteur = " +
                    conducteur.getPrenom() + " " + conducteur.getNom();
        return s;
    }
    /**
     * @return true si la voiture est disponible
     */
    public boolean estDisponible(){
        return this.conducteur==null;
    }

    /**
     * @return true si on peut affecter un conducteur a la voiture
     */
    public boolean affecterVoiture(Personne P){
        if(!this.estDisponible() || P ==null){
            return false;
        }
        this.conducteur=P;
        return true;
    }

    /**
     * @return true si la voiture avait un conducteur et on peut la restituer
      */
    public boolean restituerVoiture(){
        if(!this.estDisponible() && this.conducteur.estPieton()){
            this.conducteur=null;
            return true;
        }
        return false;

    }

}