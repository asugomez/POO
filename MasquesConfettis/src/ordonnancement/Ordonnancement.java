package ordonnancement;

import atelier.Atelier;
import atelier.Tache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.*;

public class Ordonnancement {
    private List<Tache> ensTaches; //l’ensemble des tâches à ordonnancer
    private int nbMachines;
    private Atelier atelier;

    private static final int NUM_DEFAUT_MACHINES = 1;
    private static final int NUM_MAX_MACHINES=100;

    /**
     * Constructeur par defaut
     */
    public Ordonnancement() {
        ensTaches = new LinkedList<>();
        nbMachines=NUM_DEFAUT_MACHINES;
        atelier = new Atelier(nbMachines);
    }

    /**
     * Constructeur par donnees
     */
    public Ordonnancement(int nbMachines) {
        this();
        if(nbMachines>0 && nbMachines<NUM_MAX_MACHINES) {
            this.nbMachines = nbMachines;
            atelier = new Atelier(nbMachines);
        }
    }
    /**
     * Constructeur par donnees
     */
    public Ordonnancement(int nbMachines, List<Tache> ensTaches) {
        this(nbMachines);
        if(ensTaches!=null)
            this.ensTaches = ensTaches;
    }

    /**
     * Constructeur par copie
     */
    public Ordonnancement(Ordonnancement ordonnancement) {
        this();
        if(ordonnancement!=null) {
            List<Tache> ensTachesCopie = new LinkedList<>(ordonnancement.ensTaches);
            ensTaches = ensTachesCopie;
            nbMachines = ordonnancement.nbMachines;
            Atelier atelierCopie = new Atelier(ordonnancement.atelier);
            atelier = atelierCopie;
        }
    }

    public List<Tache> getEnsTaches() {
        return ensTaches;
    }

    public int getNbMachines() {
        return nbMachines;
    }

    public Atelier getAtelier() {
        Atelier atelier = new Atelier(this.atelier);
        return atelier;
    }

    public void setNbMachines(int nbMachines) {
        if(nbMachines>0 && nbMachines<NUM_MAX_MACHINES) {
            this.nbMachines = nbMachines;
            atelier = new Atelier(nbMachines);
        }
    }

    @Override
    public String toString() {
        return "Ordonnancement{" +
                "ensTaches=" + ensTaches +
                ", nbMachines=" + nbMachines +
                ", atelier=" + atelier +
                '}';
    }

    /**
     * fonction qui permet de mettre à jour l’attribut atelier avec nbMachines machines
     */
    private void resetAtelier(){
        atelier = new Atelier(atelier.getNbMachines());
        atelier.miseAJourCriteres();
    }
    /**
     * copie une liste de taches
     */
    private List<Tache> copierList(List<Tache> taches){
        List<Tache> listTacheCopie = new LinkedList<>();
        if(taches!=null){
            for(Tache t: taches){
                Tache tacheCopie= new Tache(t);
                listTacheCopie.add(tacheCopie);
            }
        }
        return listTacheCopie;
    }
    /**
     * réalise une copie de la liste des tâches en paramètres, puis ordonnance ces tâches sur l’atelier.
     */
    private void ordonnancerCopieTaches(List<Tache> taches){
        resetAtelier();
        List<Tache> listTacheCopie = new LinkedList<>();
        listTacheCopie= copierList(taches);
        atelier.ordonnancerTaches(listTacheCopie);
    }
    /**
     * remet à jour l’atelier, puis y ordonnance les tâches avec le même modus operandi
     * que celui proposé par Monsieur Sorcier.
     */
    public void ordonnancerMonsieurSorcier(){
        ordonnancerCopieTaches(ensTaches);
    }

    /**
     * Methode croissante
     */
    public void ordonnancerCroissante(){
        sort(ensTaches);
        ordonnancerCopieTaches(ensTaches);
    }

    /**
     * Methode decroissante
     */
    public void ordonnancerDecroissante(){
        sort(ensTaches);
        reverse(ensTaches);
        ordonnancerCopieTaches(ensTaches);
    }

    /**
     * Methode aleatoire
     */
    public void ordonnancerAleatoire(){
        List<Tache> tachesOpti = copierList(ensTaches);
        Ordonnancement ordoCopie = new Ordonnancement(nbMachines, ensTaches);
        //double penaliteAtelierCopie= ordoCopie.atelier.getPenaliteTotal();
        //double penaliteAtelier= atelier.getPenaliteTotal();
        int REP= 100000;//nro repetition
        for(int i=0;i<REP;i++){
            shuffle(ensTaches);
            ordoCopie.ordonnancerCopieTaches(ensTaches);
            int dateDispoCopie = ordoCopie.atelier.getTempsTotalExec();
            int dateDispo = atelier.getTempsTotalExec();
            if(dateDispoCopie<dateDispo){
                tachesOpti = copierList(ensTaches);
            }
        }
        ordonnancerCopieTaches(tachesOpti);
    }

    /**
     * Methode decroissante mais avec mon style
     */
    public void ordonnancerAsuGomez(){
        resetAtelier();
        List<Tache> listTacheCopie = copierList(ensTaches);
        atelier.ordonnancerOpti1(listTacheCopie);
    }

    private List<Tache> listeTacheDefaut(int nbTaches){
        List<Tache> newList = new LinkedList<>();
        for(int i=0;i<nbTaches;i++){
            Tache t= new Tache();
            newList.add(t);
        }
        return newList;
    }

    /**
     * Methode avec l'algorithme NEH
     */
    public void ordonnancerNEH(){
        resetAtelier();
        List<Tache> taches= copierList(ensTaches);
        List<Tache> tachesSol = listeTacheDefaut(ensTaches.size());
        List<Tache> tachesOriginal = listeTacheDefaut(ensTaches.size());
        Ordonnancement ordoSol = new Ordonnancement(nbMachines, tachesSol);
        System.out.println("nb taaaach: "+ tachesSol.size());

        //mettre la premiere tache dans la premiere machine
        if(ensTaches!=null) {
            atelier.addTache(ensTaches.get(0),0);
            ordoSol.atelier.addTache(taches.get(0), 0);
            atelier.miseAJourCriteres();
            ordoSol.atelier.miseAJourCriteres();
        }

        for(int k =0;k<nbMachines;k++){
            for(int i=1;i<ensTaches.size();i++){
                atelier.addTache(ensTaches.get(i),k);
                int nbTachesMachineK=ordoSol.atelier.getListeMachines().get(k).getListeTaches().size();
                for(int j = 0 ;j<ensTaches.size();j++){
                    System.out.println(ordoSol.atelier.addTache(taches.get(i),k,j));
                    ordoSol.atelier.miseAJourCriteres();
                    atelier.miseAJourCriteres();
                    int tempsDureSol = ordoSol.atelier.getTempsTotalExec();
                    int tempsDureOriginal = this.atelier.getTempsTotalExec();
                    if(tempsDureSol<tempsDureOriginal) {
                        resetAtelier();
                        this.atelier = ordoSol.atelier;
                        atelier.miseAJourCriteres();
                    }

                }
            }
        }

    }

    /**
     * affiche les critères (temps de production, pénalités) de l’atelier
     * Si le paramètre verbose vaut true, alors on afiche également l’atelier
     */
    public void afficher(boolean verbose){
        System.out.println("Temps de production: " + atelier.getTempsTotalExec());
        System.out.println("Penalites: " +atelier.getPenaliteTotal());
        if(verbose)
            System.out.println("Atelier: "+atelier);
    }

    public static void testQuestion3() {
        List<Tache> taches = new ArrayList<>();
        taches.add(new Tache(100));
        taches.add(new Tache(150));

        Ordonnancement ordo = new Ordonnancement(1, taches);
        ordo.ordonnancerCopieTaches(taches);

        System.out.println("Tâches initiales :");    // Ne doivent pas avoir changé
        System.out.println(taches.get(0));
        System.out.println(taches.get(1));
        System.out.println("Tâches ordonnancées :"); // Les dates de début sont mises à jour
        System.out.println(ordo.atelier);
    }
    public static void main(String[] args){
        testQuestion3();
    }


}
