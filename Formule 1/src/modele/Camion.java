package modele;

import java.util.*;

/**
 * @author Asuncion Gomez
 */
public class Camion extends Voiture {
    private int tonMaximal;

    private final int TON_MAX= 600;
    private final int TON_DEFAULT= 0;

    /**
     * Constructeur par defaut
     */
    public Camion(){
        this.tonMaximal= TON_DEFAULT;
    }
    /**
     * Constructeur par donees de la ton maximal
     * @param tonMaximal
     */
    public Camion(int tonMaximal) {
        initializedTon(tonMaximal);
    }
    /**
     * Constructeur par donees de la marque et de la ton maximal
     * @param marque
     * @param tonMaximal
     */
    public Camion(String marque, int tonMaximal) {
        super(marque);
        initializedTon(tonMaximal);
    }
    /**
     * Constructeur par donees de la marque, puissance, carburant et de la ton maximal
     * @param marque
     * @param puissance
     * @param carburant
     * @param tonMaximal
     */
    public Camion(String marque, int puissance, char carburant, int tonMaximal) {
        super(marque, puissance, carburant);
        initializedTon(tonMaximal);
    }
    /**
     * Constructeur par donees de la marque, moteur, conducteur et de la ton maximal
     * @param marque
     * @param moteur
     * @param conducteur
     * @param tonMaximal
     */
    public Camion(String marque, Moteur moteur, Personne conducteur, int tonMaximal) {
        super(marque, moteur, conducteur);
        initializedTon(tonMaximal);
    }

    /**
     * Pour verifier que ton n'est pas null est < TON_MAX
     */
    public void initializedTon(int ton){
        if(ton<=TON_MAX){
            this.tonMaximal=ton;
        }
    }

    public int getTonMaximal() {
        return tonMaximal;
    }


    @Override
    public String toString() {
        return "modele.Camion{" +
                "tonMaximal=" + tonMaximal +
                "} " + super.toString();
    }
}