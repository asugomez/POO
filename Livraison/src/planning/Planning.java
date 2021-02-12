package planning;

import reseau.Client;
import reseau.Depot;

import java.util.*;

import static planning.Tournee.INFINI;

public class Planning {
    //private List<Tournee> ensTournee;
    private TreeSet<Tournee> ensTournee;
    private int distanceTotal;
    private int nbTotalCaisses;
    private int capaciteCamions;

    public Planning(){
        //ensTournee= new ArrayList<>();
        //int compa = compare()
        //this.ensTournee = new TreeSet<>(new Comparator() {  });
        this.ensTournee= new TreeSet<>(new Comparator<Tournee>() {
            @Override
            public int compare(Tournee o1, Tournee o2) {
                return o1.getIdentifiant() - o2.getIdentifiant();
            }
        });
        distanceTotal=0;
        nbTotalCaisses=0;
        capaciteCamions = INFINI;
    }

    public Planning(int capaciteCamions) {
        this();
        /*int capaciteTotal = 0;
        for(Tournee t: ensTournee){
            capaciteTotal+=t.getCapaciteMax();
        }*/
        if(capaciteCamions>=0)
            this.capaciteCamions = capaciteCamions;
    }

    public Planning(Planning planning){
        if(planning!=null){
            TreeSet<Tournee> ensTourneeCopie = new TreeSet<>(planning.ensTournee);
            this.ensTournee = ensTourneeCopie;
            this.distanceTotal= planning.getDistanceTotal();
            this.nbTotalCaisses = planning.getNbTotalCaisses();
            this.capaciteCamions = planning.getCapaciteCamions();
        }
    }

    /*public List<Tournee> getEnsTournee() {
        List<Tournee> ensTournee = this.ensTournee;
        return ensTournee;
    }*/
    public Set<Tournee> getEnsTournee() {
        Set<Tournee> ensTournee = this.ensTournee;
        return ensTournee;
    }

    public int getDistanceTotal() {
        return distanceTotal;
    }

    public int getNbTotalCaisses() {
        return nbTotalCaisses;
    }

    public int getCapaciteCamions() {
        return capaciteCamions;
    }

    public void setCapaciteCamions(int capaciteCamions) {
        if(capaciteCamions>=0)
            this.capaciteCamions = capaciteCamions;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "ensTournee=" + ensTournee +
                ", distanceTotal=" + distanceTotal +
                ", nbTotalCaisses=" + nbTotalCaisses +
                ", capaciteCamions=" + capaciteCamions +
                '}';
    }

    /**
     * ajoute une tournée à l’ensemble des tournées.
     * Cette méthode mettra à jour les attributs pour la distance totale et le nombre total de caisses livrées.
     */
    private boolean ajouterTournee(Tournee t){
        if(t!=null && ((nbTotalCaisses+t.getNbCaissesALivrer()) <= capaciteCamions)){
            distanceTotal+=t.getDistanceParcourue();
            nbTotalCaisses+=t.getNbCaissesALivrer();
            capaciteCamions+=t.getCapaciteMax();
            return ensTournee.add(t);
        }
        return false;
    }



    /**
     * commence par créer une tournée vide
     * elle itère sur l’ensemble des clients et les ajoute à la tournée courante.
     * Lorsque l’ajout d’un client à la tournée courante n’est pas possible, alors la tournée courante est ajoutée au planning
     */
    public void planificationBasique(Depot depot, Set<Client> clients) {
        int capacite= 40;
        Tournee tourneeCourante = new Tournee(depot,capacite);
        //Set<Client> clientsCopie = new HashSet<>(clients);
        List<Client> clientsCopie = new LinkedList<>(clients);
        Tournee tourneeAjouter = new Tournee(depot,capacite);
        for (Client c : clientsCopie) {
            /**
             * on NE peut PAS ajouter la tournee ou le client
             * la capacité du planning doit être plus grande que les capacites d'ensemble de tournee
             */
            if(c.getNbCaisses() + tourneeCourante.getNbCaissesALivrer() <= this.capaciteCamions){
                if(!(c.getNbCaisses() + tourneeCourante.getNbCaissesALivrer() <= tourneeCourante.getCapaciteMax())){
                    tourneeAjouter = new Tournee(tourneeCourante);
                    this.ajouterTournee(tourneeAjouter);
                    tourneeCourante.vider();
                }
                tourneeCourante.ajouterClient(c);
            }
            else{
                tourneeAjouter = new Tournee(tourneeCourante);
                this.ajouterTournee(tourneeAjouter);
                tourneeCourante.vider();
            }
        }
        this.ajouterTournee(tourneeCourante);
        //System.out.println(this.ensTournee);
    }

    public void planificationMinTournee(Depot depot, Set<Client> clients){
        if(clients!=null) {
            int caises = 0;
            boolean reussit = false;
            for (Client c : clients) {
                for (Tournee t : ensTournee) {
                    if (t.getCapaciteMax() >= t.getNbCaissesALivrer() + c.getNbCaisses() ) {
                        reussit = t.ajouterClient(clients);
                    }
                }
            }
            if (reussit==false) {
                this.planificationBasique(depot, clients);
            }
        }

    }


}
