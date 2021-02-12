package planning;

import reseau.Client;
import reseau.Depot;
import reseau.Point;

import java.util.*;

public class Tournee {
    private Depot depot;
    //private Set<Client> clientsLivres;
    private LinkedList<Client> clientsLivres;
    private double distanceParcourue;
    private int nbCaissesALivrer;
    private int capaciteMax;
    private int identifiant;

    public static int dernierIdentifiant = 0;

    public static final int INFINI = Integer.MAX_VALUE;

    public Tournee() {
        this.depot = new Depot();
        this.capaciteMax = INFINI;
        this.clientsLivres = new LinkedList<>();
        //this.clientsLivres = new HashSet<>();
        this.nbCaissesALivrer=0;
        this.identifiant = dernierIdentifiant++;
    }

    public Tournee(Depot depot, int capaciteMax) {
        this();
        if(depot!=null)
            this.depot = depot;
        if(capaciteMax >= 0)
            this.capaciteMax = capaciteMax;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public Tournee(Tournee t){
        this();
        if(t!=null){
            Depot depotCopie= t.getDepot();
            this.depot = depotCopie;
            //Set<Client> clientsLivresCopie = copierClientSet();
            LinkedList<Client> clientsLivresCopie = copierClientLinkedin();
            this.clientsLivres=clientsLivresCopie;
            this.distanceParcourue= t.distanceParcourue;
            this.nbCaissesALivrer = t.nbCaissesALivrer;
            this.capaciteMax = t.capaciteMax;
        }
    }

    public Depot getDepot() {
        Depot depot = new Depot(this.depot);
        return depot;
    }

    private Set<Client> copierClientSet(){
        Set<Client> clientsLivresCopie = new HashSet<>();
        for(Client c: this.clientsLivres) {
            Client clientCopie = new Client(c);
            clientsLivresCopie.add(clientCopie);
        }
        return clientsLivresCopie;
    }

    private LinkedList<Client> copierClientLinkedin(){
        LinkedList<Client> clientsLivresCopie = new LinkedList<>();
        for(Client c: this.clientsLivres){
            Client clientCopie = new Client(c);
            clientsLivresCopie.add(c);
        }
        return clientsLivresCopie;
    }


   /* public Set<Client> getClientsLivres() {
        Set<Client> clientsLivres = copierClientSet();
        return clientsLivres;
    }*/

    public List<Client> getClientsLivres(){
        List<Client> clientsLivres = copierClientLinkedin();
        return clientsLivres;
    }

    public void vider(){
        this.distanceParcourue=0;
        this.nbCaissesALivrer=0;
        this.clientsLivres = new LinkedList<>();
        //this.clientsLivres = new HashSet<>();
    }

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    public int getNbCaissesALivrer() {
        return nbCaissesALivrer;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setDepot(Depot depot) {
        if(depot!=null)
            this.depot = depot;
    }

    @Override
    public String toString() {
        return "Tournee{" +
                "depot=" + depot +
                ", clientsLivres=" + clientsLivres +
                ", distanceParcourue=" + distanceParcourue +
                ", nbCaissesALivrer=" + nbCaissesALivrer +
                ", capaciteMax=" + capaciteMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournee)) return false;
        Tournee tournee = (Tournee) o;
        return tournee.distanceParcourue == distanceParcourue &&
                nbCaissesALivrer == tournee.nbCaissesALivrer &&
                capaciteMax == tournee.capaciteMax &&
                Objects.equals(depot, tournee.depot) &&
                Objects.equals(clientsLivres, tournee.clientsLivres);
    }

    @Override
    public int hashCode() {
        return clientsLivres.hashCode() + depot.hashCode();
    }

    /**
     * retourne le dernier client livre
     */
    public Client getDernierClient(){
        if(clientsLivres.size()>0){
            Client dernierClient = new Client();
            for(Client c: clientsLivres){
                dernierClient=c;
            }
            return dernierClient;
        }
        return null;
    }

    /**
     * ajoute le client dans la collection des clients livrés
     * on suppose que ce client est ajouté à la fin de la tournée, avant de retourner au dépô
     */
   /* public boolean ajouterClient(Client client){
        if(client!=null){
            if((client.getNbCaisses() + this.nbCaissesALivrer) <= this.capaciteMax){
                //liste non vide
                //
                if(!this.clientsLivres.isEmpty()) {
                    Client dernierClient = this.clientsLivres.getLast();
                    this.distanceParcourue -= dernierClient.getDistance(depot);
                    this.distanceParcourue += dernierClient.getDistance(client);
                    this.distanceParcourue += client.getDistance(depot);
                }
                // liste vide --> du depot au client et viceversa
                else
                    distanceParcourue = client.getDistance(depot) + depot.getDistance(client);
                this.
                clientsLivres.add(client);
                this.nbCaissesALivrer+=client.getNbCaisses();
                return true;
            }
            return false;
        }
        return false;
    }*/


    /*public boolean ajouterClient(Client c) {
        if (c == null) return false;
        if (c.getNbCaisses() + this.nbCaissesALivrer > this.capaciteMax) {
            return false;
        } else {
            Client dernierClient = this.clientsLivres.getLast();
            this.distanceParcourue -= dernierClient.getDistance(depot);
            this.distanceParcourue += dernierClient.getDistance(c);
            this.distanceParcourue += c.getDistance(depot);
        }
        if (clientsLivres.isEmpty()) {
            distanceParcourue = depot.getDistance(c) + c.getDistance(depot);
        }
        nbCaissesALivrer += c.getNbCaisses();
        return clientsLivres.add(c);
    }*/



    public boolean ajouterClient(Client c) {
        if (c == null) return false;
        if (c.getNbCaisses() + this.nbCaissesALivrer > this.capaciteMax) {
            return false;
        }
        if (clientsLivres.isEmpty()) {
            distanceParcourue = depot.getDistance(c) + c.getDistance(depot);
        }else {
            Client dernierClient = clientsLivres.getLast(); //getDernierClient();
            distanceParcourue -= dernierClient.getDistance(depot);
            distanceParcourue += dernierClient.getDistance(c);
            distanceParcourue += c.getDistance(depot);
        }

        nbCaissesALivrer += c.getNbCaisses();
        return clientsLivres.add(c);
    }

    public boolean ajouterClient(Set<Client> ensembleClient){
        boolean reussit = false;
        if(ensembleClient!=null){
            reussit = true;
            for(Client c: ensembleClient){
                boolean ajouteDeClient = ajouterClient(c);
                if(ajouteDeClient==false){
                    reussit=false;
                }
            }
        }
        return reussit;
    }
}
