package modele;

import java.util.*;

/**
 * @author Asuncion Gomez
 */
public class Formule1 extends Voiture {
    private String sponsor;

    public static final String SPONSOR_DEFAUT= "EC-Lille";
    /**
     * Constructeur par defaut
     */
    public Formule1(){
        super();
        //this.sponsor=null;
        this.sponsor=SPONSOR_DEFAUT;
    }
    /**
     * Constructor par donnée du sponsor
     * @param sponsor
     */
    public Formule1(String sponsor) {
        initializedSponsor(sponsor);
    }
    /**
     * Constructor par donnée de la marque et du sponsor
     * @param marque
     * @param sponsor
     */
    public Formule1(String marque, String sponsor) {
        super(marque);
        initializedSponsor(sponsor);
    }
    /**
     * Constructor par donnees de la marque, de la puissance, de la carburant du moteur et du sponsor
     * @param marque
     * @param puissance
     * @param carburant
     * @param sponsor
     */
    public Formule1(String marque, int puissance, char carburant, String sponsor) {
        super(marque, puissance, carburant);
        initializedSponsor(sponsor);
    }
    public void initializedSponsor(String sponsor){
        if(sponsor!=null)
            this.sponsor=sponsor;
    }

    public String getSponsor() {
        String sponsor= new String(this.sponsor);
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        if(sponsor!=null)
            this.sponsor = sponsor;
    }

    @Override
    public String toString() {
        return "Formule1{" +
                "sponsor='" + sponsor + '\'' +
                "} " + super.toString();
    }
}