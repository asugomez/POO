package atelier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.reverse;
import static java.util.Collections.shuffle;


public class Atelier {
    private int tempsTotalExec;
    private double penaliteTotal;
    private int nbMachines;
    private List<Machine> listeMachines;

    private static final int NUM_MAX_MACHINES=100;
    private static final int NUM_DEFAUT_MACHINES=1;

    public Atelier() {
        this.nbMachines=NUM_DEFAUT_MACHINES;
        this.listeMachines= new ArrayList<>();
        tempsTotalExec=0;
        penaliteTotal=0;
    }

    public Atelier(int nbMachines) {
        this();
        this.setNbMachines(nbMachines);
        for(int i=0;i<this.nbMachines;i++){
            Machine m=new Machine();
            this.listeMachines.add(m);
        }
    }

    public Atelier(Atelier atelier){
        this();
        if(atelier!=null) {
            nbMachines = atelier.nbMachines;
            List<Machine> listeMachinesCopie = new ArrayList<>(atelier.listeMachines);
            listeMachines = listeMachinesCopie;
        }
    }

    public int getTempsTotalExec() {
        return tempsTotalExec;
    }

    public double getPenaliteTotal() {
        return penaliteTotal;
    }

    public int getNbMachines() {
        return nbMachines;
    }

    public ArrayList<Machine> getListeMachines() {
        ArrayList<Machine> listeMachines= new ArrayList<>(this.listeMachines);
        return listeMachines;
    }

    public void setNbMachines(int nbMachines) {
        if(nbMachines<= NUM_MAX_MACHINES)
            this.nbMachines = nbMachines;
    }

    @Override
    public String toString() {
        String s = "Cet atelier est composé de " + listeMachines.size()+
                " machine(s)\n";

        int i = 1;
        for (Machine m : listeMachines) {
            s += "\nMachine " + i + " : " + m ;
            i++;
        }
        s += "\nDurée totale d'exécution  = " + tempsTotalExec +
                "\nMontant total des pénalités = " +
                String.format("%.1f €", getPenaliteTotal());

        return s + "\n";
    }

    public void miseAJourCriteres(){
        if(listeMachines!=null) {
            for (Machine m : listeMachines) {
                this.penaliteTotal += m.getPenaliteTotal();
                int tempsMach=m.getDateDisponibilite();
                this.tempsTotalExec=Math.max(tempsTotalExec, tempsMach);
            }
        }
    }

    private Machine getMachine(int posMachine){
        return this.listeMachines.get(posMachine);
    }

    /**
     * ajoute la tâche t à la machine en position posMachine dans votre liste des machines
     */
    public boolean addTache(Tache t, int posMachine){
        if(posMachine<nbMachines)
            return getMachine(posMachine).addTache(t);
        return false;
    }

    /**
     * ajoute la tache t à la machine posMachine dans la position j
     */
    public boolean addTache(Tache t, int posMachine, int positionTache){
        int nbTaches= getMachine(posMachine).getListeTaches().size();
        System.out.println("nb taches: "+nbTaches);
        //le nro de taches cest moins

        if(posMachine<nbMachines){
            System.out.println("funcion add tache atelier");
            return getMachine(posMachine).addTache(positionTache, t);
        }
        return false;
    }

    private boolean removeTache(Tache t, int posMachine){
        if(posMachine<nbMachines)
            return getMachine(posMachine).removeTache(t);
        return false;
    }

    /**
     * renvoie un entier indiquant la position, dans la liste des machines, de la machine qui est disponible en premier
     */
    private int getPremiereMachineLibre(){
        int dispo = Integer.MAX_VALUE;
        int index=-1;
        for (int i =0;i<listeMachines.size();i++){
            if(getMachine(i).getDateDisponibilite() <dispo){
                dispo=getMachine(i).getDateDisponibilite();
                index=i;
            }
        }
        return index;
    }

    /**
     * Algorithme du Monsieur xxx
     */
    public void ordonnancerTaches(List<Tache> taches){
        if(taches!=null){
            addTache(taches.get(0),0);
            for(int i=1;i<taches.size();i++) {
                addTache(taches.get(i), getPremiereMachineLibre());
            }
        }
        this.miseAJourCriteres();

    }

    //--------------------- ALGO ASU ------------//

    private int getTachePlusGrande(List<Tache> taches){
        int temps=Integer.MIN_VALUE;
        int index=-1;
        for(int j=0;j< taches.size();j++){
            if(taches.get(j).getTempsProduction()>temps){
                temps=taches.get(j).getTempsProduction();
                index=j;
            }
        }
        return index;
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
     * Ordre la liste de tache selon ses temps d'execution (de la plus grande a la plus petit)
     */
    public List<Tache>  dureeList(List<Tache> taches){
        List<Tache> tacheOrdonnee= new LinkedList<>();
        List<Tache> taches1 = copierList(taches);
        int index;
        for(int i=0;i<taches.size();i++){
            index=  getTachePlusGrande(taches1);
            tacheOrdonnee.add(taches1.get(index));
            taches1.remove(getTachePlusGrande(taches1));
        }
        return tacheOrdonnee;
    }

    public void ordonnancerOpti1(List<Tache> taches){
        List<Tache> tachesNew = dureeList(taches);
        ordonnancerTaches(tachesNew);

    }
    public void ordonnancerNEH(List<Tache> taches){
        Atelier atCopie = new Atelier(this);
        List<Tache> tachesCopie = new LinkedList<>(taches);
        if(taches!=null){
            this.addTache(taches.get(0),0);
            atCopie.addTache(tachesCopie.get(0),0);
            double penalite1 = Integer.MAX_VALUE;
            double penalite2 = Integer.MAX_VALUE;


            for(int i=1;i<taches.size();i++){
                addTache(taches.get(i), getPremiereMachineLibre());
                miseAJourCriteres();
                penalite1=getPenaliteTotal();
                Machine m=atCopie.listeMachines.get(getPremiereMachineLibre());
                m.getListeTaches().add(i,taches.get(i));

            }
        }
    }

}
