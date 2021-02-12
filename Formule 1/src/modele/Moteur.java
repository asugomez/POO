package modele;

import java.util.Objects;

/**
 * @author Asuncion Gomez
 * Class Moteur
 */
public class Moteur {
    private int puissance;
    private char carburant;

    private static int PUISSANCE_MIN= 600;
    public static char ESSENCE= 'E';
    public static char DIESEL= 'D';

    /**
     * Default constructor
     */
    public Moteur(){
        this.carburant= ESSENCE;
        this.puissance=PUISSANCE_MIN;
    }
    /**
     * Constructreur par données
     * @param carburant
     * @param puissance
     */
    public Moteur(int puissance, char carburant) {
        this();
        if(carburant==ESSENCE || carburant==DIESEL)
            this.carburant = carburant;

        if(puissance>=PUISSANCE_MIN)
            this.puissance = puissance;
    }
    /**
     * Constructeur par copie
     */
    public Moteur(Moteur moteur2){
        this();
        if(moteur2 != null) {
            this.puissance = moteur2.puissance;
            this.carburant = moteur2.carburant;
        }
    }

    /**
     * Acceseur en lecture de l'attribut carburant
     */
    public char getCarburant() {
        return carburant;
    }

    /**
     * Acceseur en lecture de l'attribut puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * Acceseur en écriture de l'attribut puissance
     * @param puissance
     */
    public void setPuissance(int puissance) {
        if(puissance>=PUISSANCE_MIN)
            this.puissance = puissance;
    }

    @Override
    /**
     * méthode toString
     * @return une chaîne de caractères avec les informations sur le moteur
     */
    /*public String toString() {
        return "Moteur{" +
                "carburant=" + carburant +
                ", puissance=" + puissance +
                '}';
    }*/
    public String toString() {
        return  String.format("%4d ch, ", puissance) +
                ((carburant == DIESEL) ? "DIESEL" : "ESSENCE");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Moteur)) return false;
        Moteur moteur = (Moteur) o;
        return puissance == moteur.puissance &&
                carburant == moteur.carburant;
    }


    /**
     * 
     */
    public void val_puissance() {
        // TODO implement here
    }

}