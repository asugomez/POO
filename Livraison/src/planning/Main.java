package planning;

import mesExceptions.ErrQuantite;
import reseau.Client;
import reseau.Depot;
import reseau.Point;

import java.util.HashSet;
import java.util.Set;

//import static planification.Routage.test;

public class Main {
    public static void testTournee(){
        Depot d1 = new Depot(0, 0);
        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 10);
            Client c3 = new Client(-5, -5, 10);
            Client c4 = new Client(5, -5, 10);
            Set<Point> ensPoint = new HashSet<>();
            ensPoint.add(c1);
            ensPoint.add(c2);
            ensPoint.add(c3);
            ensPoint.add(c4);
            d1.ajouterRoutes(ensPoint);
            ensPoint.add(d1);
            c1.ajouterRoutes(ensPoint);
            c2.ajouterRoutes(ensPoint);
            c3.ajouterRoutes(ensPoint);
            c4.ajouterRoutes(ensPoint);
            Set<Client> ensClient = new HashSet<>();
            ensClient.add(c1);
            ensClient.add(c2);
            ensClient.add(c3);
            ensClient.add(c4);
            Tournee t = new Tournee(d1, 50);
            t.ajouterClient(ensClient);
            System.out.println(t);
        }catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
            System.exit(-1);
        }
    }

    public static void testAjouteDesClients(){
        Depot d1 = new Depot(0, 0);
        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 10);
            Client c3 = new Client(-5, -5, 10);
            Client c4 = new Client(5, -5, 10);
            Set<Point> ensPoint = new HashSet<>();
            ensPoint.add(c1);
            ensPoint.add(c2);
            ensPoint.add(c3);
            ensPoint.add(c4);
            d1.ajouterRoutes(ensPoint);
            ensPoint.add(d1);
            c1.ajouterRoutes(ensPoint);
            c2.ajouterRoutes(ensPoint);
            c3.ajouterRoutes(ensPoint);
            c4.ajouterRoutes(ensPoint);
            Set<Client> ensClient = new HashSet<>();
            ensClient.add(c1);
            ensClient.add(c2);
            ensClient.add(c3);
            ensClient.add(c4);
        } catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
            System.exit(-1);
        }
        Tournee t = new Tournee(d1, 50);
        //System.out.println(t.ajouterClient(ensClient));
        System.out.println(t);
        System.out.println(t.getDernierClient());
        System.out.println(t.getDernierClient().getDistance(new Client(2,3)));
    }

    public static void testPlanning(){
        Planning planning = new Planning(100);
        Depot d1 = new Depot(0, 0);
        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 15);
            Client c3 = new Client(-5, -5, 5);
            Client c4 = new Client(5, -5, 8);
            Set<Point> ensPoint = new HashSet<>();
            ensPoint.add(c1);
            ensPoint.add(c2);
            ensPoint.add(c3);
            ensPoint.add(c4);
            d1.ajouterRoutes(ensPoint);
            ensPoint.add(d1);
            System.out.println(ensPoint);
            c1.ajouterRoutes(ensPoint);
            c2.ajouterRoutes(ensPoint);
            c3.ajouterRoutes(ensPoint);
            c4.ajouterRoutes(ensPoint);
            Set<Client> ensClient = new HashSet<>();
            ensClient.add(c1);
            ensClient.add(c2);
            ensClient.add(c3);
            ensClient.add(c4);
            planning.planificationBasique(d1, ensClient);
        } catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
            System.exit(-1);
        }
    }



    public static void main(String []  args){
        //testTournee();
        //testAjouteDesClients();
        //testPlanning();

    }
}
