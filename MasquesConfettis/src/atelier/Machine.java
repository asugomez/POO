package atelier;

import java.util.LinkedList;
import java.util.List;

public class Machine {
    private int dateDisponibilite; //la date où on peut commencer une autre tache
    private double penaliteTotal;
    private List<Tache> listeTaches;


    private static double PENALITE_TOTAL_DEFAUT=0;

    public Machine() {
        this.dateDisponibilite=0;
        this.penaliteTotal=PENALITE_TOTAL_DEFAUT;
        this.listeTaches=new LinkedList<>();
    }

    public int getDateDisponibilite() {
        return dateDisponibilite;
    }

    public double getPenaliteTotal() {
        return penaliteTotal;
    }

    public LinkedList<Tache> getListeTaches() {
        LinkedList<Tache> listeTaches = new LinkedList<>(this.listeTaches);
        return listeTaches;
    }

    @Override
    public String toString() {
        String s = "Cette machine est disponible à la date " +
                dateDisponibilite +
                ", pénalité totale = " +
                String.format("%.1f €", penaliteTotal) + "\n";
        String detail = "\n";
        int i = 1;
        for (Tache t : this.listeTaches)
            detail += String.format("%2d) \t", i++) + t + "\n";
        return s + detail;
    }

    /**
     * Fonction qui ajoute des taches a la machine
     * @return true si on a reussi
     */
    public boolean addTache(Tache t){
        if(t!= null && t.setDateDebut(dateDisponibilite)) {
            listeTaches.add(t);
            this.dateDisponibilite=t.getDateDebut()+t.getTempsProduction();
            penaliteTotal = penaliteTotal +  t.getCout();
            return true;
        }
        return false;

    }
    public boolean shift(int i, int j){
        return true;
        //IMPLEMENTER
    }

    /**
     * c'est bcp plus complique pour modifier la penalite, blablabla
     */
    public boolean addTache(int index, Tache t){
        if(t!= null && t.setDateDebut(dateDisponibilite)){
            listeTaches= new LinkedList<>();
            List<Tache> tachesNew = new LinkedList<>();
            tachesNew.add(index,t);
            System.out.println("listaaaaaa  :"+tachesNew);
            for(Tache t1: tachesNew){
                listeTaches.add(t1);
                this.dateDisponibilite=t1.getDateDebut()+t1.getTempsProduction();
                penaliteTotal = penaliteTotal +  t1.getCout();
            }
            return true;
        }
        return false;
    }


    public boolean removeTache(Tache t){
        if(t!=null){
            listeTaches.remove(t);
            this.dateDisponibilite=dateDisponibilite - t.getTempsProduction();
            penaliteTotal = penaliteTotal-t.getCout();
            return true;
        }
        return false;
    }
}
