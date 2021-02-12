package planification;

import mesExceptions.ErrQuantite;
import planning.Planning;
import reseau.Client;
import reseau.Depot;
import reseau.Point;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Routage {
    private Depot depot;
    //private TreeSet<Client> mesClients;
    private Set<Client> mesClients;
    private Planning planning;
    private int capaciteCamion;

    public Routage(){
        this.depot = new Depot(0,0);
        // new TreeSet<>(new Comparator(){ @Override public int compare(Client o1, Client o2) { return o1.getId() - o2.getId(); } });
        this.mesClients = new TreeSet<>(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getIdentifiant() - o2.getIdentifiant();
            }
        });
        //mesClients = new HashSet<>();
        planning = new Planning();

    }

    public Routage(int capaciteCamion) {
        this();
        planning = new Planning(capaciteCamion);
        if(capaciteCamion>=0)
            this.capaciteCamion = capaciteCamion;
    }

    public Routage(Depot depot, int capaciteCamion) {
        this();
        if(depot!=null)
            this.depot = depot;
        this.capaciteCamion= capaciteCamion;
    }

    public Depot getDepot() {
        Depot depot = new Depot(this.depot);
        return depot;
    }

    public Set<Client> getMesClients() {
        Set<Client> mesClients = new TreeSet<>(new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getIdentifiant() - o2.getIdentifiant();
            }
        });
        for(Client c: this.mesClients){
            Client client = new Client(c);
            mesClients.add(client);
        }
        return mesClients;
    }

    public Planning getPlanning() {
        Planning planning = new Planning(this.planning);
        return planning;
    }

    public int getCapaciteCamion() {
        return capaciteCamion;
    }

    @Override
    public String toString() {
        return "Routage{" +
                "depot=" + depot +
                ", mesClients=" + mesClients +
                ", planning=" + planning +
                ", capaciteCamion=" + capaciteCamion +
                '}';
    }

    /**
     * initialise les routes qui partent du dépôt et de chaque client
     */
    private void initialiserRoutes(){
        if(mesClients!=null){
            //connecter les points
            //Set<Point> mesClientsEnPoints = new HashSet<>();
            TreeSet<Point> mesClientsEnPoints = new TreeSet<>(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.getIdentifiant() - o2.getIdentifiant();
                }
            });
            mesClientsEnPoints.addAll(mesClients);
            for(Client clientOrigine: mesClients){
                clientOrigine.ajouterRoutes(mesClientsEnPoints);
            }
        }

    }

    /**
     * doit faire appel à la méthode de pla- nification basique de la classe Planning
     */
    public void planificationBasique(){
        this.planning.planificationBasique(this.getDepot(), this.getMesClients());
    }

    public void planificationMin(){ this.planning.planificationMinTournee(this.getDepot(),this.getMesClients());}

    public void creationClientTest1(){
        try {
            Client c0 = new Client(-99.7497, 12.7171, 4);
            Client c1 = new Client(61.7481, 17.0019, 10);
            Client c2 = new Client(-29.9417, 79.1925, 17);
            Client c3 = new Client(49.321, -65.1784, 18);
            Client c4 = new Client(42.1003, 2.70699, 7);
            Client c5 = new Client(-97.0031, -81.7194, 8);
            Client c6 = new Client(-70.5374, -66.8203, 20);
            Client c7 = new Client(-10.8615, -76.1834, 1);
            Client c8 = new Client(-98.2177, -24.424, 11);
            Client c9 = new Client(14.2369, 20.3528, 13);
            Client c10 = new Client(0,25,7);
            Client c11=new Client(30,0,17);
            Client c12 = new Client(40, 37, 15);
            mesClients.clear();
            mesClients.add(c0);
            mesClients.add(c1);
            mesClients.add(c2);
            mesClients.add(c3);
            mesClients.add(c4);
            mesClients.add(c5);
            mesClients.add(c6);
            mesClients.add(c7);
            mesClients.add(c8);
            mesClients.add(c9);
            mesClients.add(c10);
            mesClients.add(c11);
            mesClients.add(c12);
        } catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
            System.exit(-1);
        }
    }

    public static void test() {
        int capacite = 40;
        Routage r1 = new Routage(capacite);
        r1.creationClientTest1();
        r1.initialiserRoutes();
        r1.planificationBasique();
        int nbTournee = r1.planning.getEnsTournee().size();

        System.out.println("nº tournee " +nbTournee);
        System.out.println("distance "+r1.planning.getDistanceTotal());
        System.out.println("nb caisses " + r1.planning.getNbTotalCaisses());
    }

    public static void testMin(){
        int capacite = 50;
        Routage r1 = new Routage(capacite);
        r1.creationClientTest1();
        r1.initialiserRoutes();
        r1.planificationMin();

        int nbTournee = r1.planning.getEnsTournee().size();
        /*for(int i=0;i<nbTournee;i++){
            System.out.println("nb Tournee nº "+ i);
            System.out.println(r1.planning.getEnsTournee().get(i));
        }*/
        System.out.println("nº tournee " +nbTournee);
        System.out.println("distance "+r1.planning.getDistanceTotal());
        System.out.println("nb caisses " + r1.planning.getNbTotalCaisses());

    }

    /**
     * Cette méthode initialise un objet de type Print- Writer avec le constructeur
     * par donnée du nom du fichier et puis elle écrit le planning complet dans le fichier texte.
     */
    public void ecriturePlanning(String fichierSortie){
        PrintWriter ecriture;
        try{
            ecriture = new PrintWriter(fichierSortie);
            ecriture.println(this.toString());
            ecriture.close();
        } catch (IOException ex) {
            System.out.println("Erreur fichier ecriture");
        }
    }

    public void traitementLigne(String line){
        if(line!=null) {
            String[] traitement = line.split("\t");
            switch (traitement.length) {
                case 1:
                    this.capaciteCamion = (Integer.parseInt(traitement[0]));
                    break;
                case 2:
                    this.depot = new Depot(Double.parseDouble(traitement[0]), Double.parseDouble(traitement[1]));
                    break;
                default:
                    try {
                        Client c = new Client(Double.parseDouble(traitement[0])
                                , Double.parseDouble(traitement[1])
                                , Integer.parseInt(traitement[2]));
                        this.mesClients.add(c);
                        break;
                    } catch (ErrQuantite ex) {
                        System.out.println(ex.getMessage());
                    }
            }
        }
    }

    public void lectureClient(String fichierEntree){
        FileReader f;
        BufferedReader br;
        try{
            f = new FileReader(fichierEntree);
            br = new BufferedReader(f);
            String ligne = br.readLine();
            while(ligne != null) {
                traitementLigne(ligne);
                ligne=br.readLine();
            }
            br.close();
            f.close();
        } catch (FileNotFoundException ex){
            System.out.println("Erreur fichier n'existe pas");
        } catch (IOException ex){
            System.out.println("Erreur fichier lecture");
        }
    }

    public static void testLecture(String nomFichier){
        Routage r1= new Routage();
        r1.lectureClient(nomFichier);
        r1.initialiserRoutes();
        r1.planificationBasique();
        String desktop= System.getProperty("user.home") + "/Desktop/S6-B/POO/Chap3/Livraison/solutions/";
        r1.ecriturePlanning(desktop+"sol_"+nomFichier);
    }
}
