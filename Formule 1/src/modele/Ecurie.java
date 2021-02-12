package modele;

import java.util.Arrays;

/**
 * @author Asuncion Gomez
 */
public class Ecurie {

    private String nom;
    private Personne[] equipe;
    private Voiture[] flotte;


    private static String NOM_DEFAUT = "Gomez Team";
    private static final int N_MAX_PERSONNES = 5;
    private static final int N_MAX_VOITURES= 5;
    private int nbPersonne;
    private int nbVoiture;

    public Ecurie(){
        this.nom= NOM_DEFAUT;
        equipe= new Personne[N_MAX_PERSONNES];
        flotte= new Voiture[N_MAX_VOITURES];
        nbPersonne=0;
        nbVoiture=0;
    }

    public Ecurie(String nom) {
        this();
        if(nom!= null)
            this.nom = nom;
    }

    public String getNom() {
        String nom= this.nom;
        return nom;
    }

    public void setNom(String nom) {
        if(nom!=null)
            this.nom = nom;
    }

    @Override
    public String toString() {
        return "modele.Ecurie{" +
                "nom='" + nom + '\'' +
                ", equipe=" + Arrays.toString(equipe) +
                ", flotte=" + Arrays.toString(flotte) +
                '}';
    }

    /**
     * Fonction pour vérifier la présence d’une personne dans son équipe
     * @param personne
     * @return true si la personne est dans l'ecurie
     */
    public boolean personneEst(Personne personne) {
        if(this.equipe!=null){
            for (Personne personne1 : equipe) {
                if (personne.equals(personne1))
                    return true;
            }
        }
        return false;
    }
    /**
     * Fonction pour vérifier la présence d’une voiture dans sa flotte
     * @param voiture
     * @return true si la voiture est dans l'ecurie
     */
    public boolean voitureEst(Voiture voiture){
        if(this.flotte!=null){
            for(Voiture voiture1: flotte){
                if(voiture.equals(voiture1))
                    return true;
            }
        }
        return false;
    }

    /**
     * Fonction pour embaucher une personne
     * @param P personne
     * @return true s'il est possible
     */
    public boolean embaucher(Personne P) {
        if(!this.personneEst(P) && nbPersonne<N_MAX_PERSONNES && this.equipe!= null){
            this.equipe[nbPersonne]= P;
            nbPersonne++;
            return true;
        }
        return false;
    }
    /**
     * Fonction pour acheter une voiture
     * @param V voiture
     * @return true s'il est possible
     */
    public boolean acheter(Voiture V) {
        if(!this.voitureEst(V) && nbVoiture<N_MAX_VOITURES && this.flotte!=null){
            this.flotte[nbVoiture]=V;
            nbVoiture++;
            return true;
        }
        return false;
    }
    /**
     * Fonction qui permet de localiser dans l’equipe la personne avec l’identifiant id si elle est preesente
     * @param id identification de la personne
     * @return Personne ou null
     */
    private Personne localiserPersonne(int id){
        if(this.equipe!=null){
            for(int i=0;i<nbPersonne;i++){
                if(equipe[i].getIdentification() == id)
                    return equipe[i];
            }
        }
        return null;
    }
    /**
     * Fonction qui permet de localiser dans la flotte la voiture avec l’identifiant id
     * @param immat identifient de la voiture
     * @return Voiture ou null
     */
    private Voiture localiserVoiture(int immat){
        if(this.flotte!=null){
            for(int i=0;i<this.nbVoiture;i++){
                if(flotte[i].getIdentifiant() == immat){
                    return flotte[i];
                }
            }
        }
        return null;
    }

    /**
     * l’affectation de la voiture v à la personne.
     * @param id identification personne
     * @param immat identifiant voiture
     * @return boolean si l’affectation a pu etre effectuee correctement ou non.
     */
    public boolean affecter(int id, int immat){
        if(localiserPersonne(id) != null && localiserVoiture(immat)!=null){
            boolean succes= false;
            succes = localiserPersonne(id).affecter(localiserVoiture(immat));
            return succes;
        }
        return false;
    }
    /**
     * @param id identification personne
     * @return true si on oeut restituer la voiture
     */
    public boolean restituer(int id){
        if(localiserPersonne(id)!=null)
            return localiserPersonne(id).restituer();
        return false;
    }
}