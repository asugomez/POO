package modele;

import modele.Personne;

/**
 * @author Asuncion Gomez
 */
public class Technicien extends Personne {
    private String specialite;

    private final String SPECIALITE_DEFAULT= null;

    /**
     * Default constructor
     */
    public Technicien() {
        this.specialite=SPECIALITE_DEFAULT;
    }

    public Technicien(String specialite) {
        this.specialite = specialite;
    }

    public Technicien(String nom, String prenom) {
        super(nom, prenom);
    }

    public Technicien(Personne personne) {
        super(personne);
    }

    public Technicien(String nom, String prenom, String adresse, Voiture voiture, String specialite) {
        super(nom, prenom, adresse, voiture);
        this.specialite = specialite;
    }

    public Technicien(Personne personne, String specialite) {
        super(personne);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Technicien{" +
                "specialite='" + specialite + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean estCompatible(Voiture v) {
        return (v instanceof Camion);
    }

    /**
     * 
     */
    public void changer_specialite() {
        // TODO implement here
    }

}