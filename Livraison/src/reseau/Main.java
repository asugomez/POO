package reseau;

import mesExceptions.ErrQuantite;

import java.util.*;

public class Main {

    public static void print1(String phrase){
        System.out.println(phrase);
    }

    public static void testPoint(){
        // Le premier point créé porte l'identifiant 0
        Point [] p = new Point [5];

        p[0] = new Point ();
        p[1] = new Point (  -3,    7);
        p[2] = new Point (-1.6, -2.5);
        p[3] = new Point ( 3.1, -9.5);
        p[4] = new Point (  -3,    7);

        p[0].setAbscisse(1.1);
        p[1].setOrdonnee(-p[2].getOrdonnee());
        p[2].setAbscisse( p[4].getOrdonnee());

        for (Point pt : p)
            System.out.println(pt);
    }

    public static void testRoute(){
        Random rnd = new Random((1 << 16) - 1);
        Point [] p = new Point [5];

        p[0] = new Point (5, 12);
        p[1] = new Point (-3.14, 2.718);
        p[2] = new Point (-1.6, -2.5);
        p[3] = new Point (3.1, -9.5);
        p[4] = new Point (5, -2.5);

        Route route[] = new Route [8];
        for (int i = 0; i < route.length; i++){
            route[i] = new Route (p[rnd.nextInt(p.length)], p[rnd.nextInt(p.length)]);
        }

        for (Route r : route)
            System.out.println(r);
    }
    public static void testClient2(){
        int count=0;
        try{
            Client c1= new Client(10,10,5);
            count++;
            Client c2= new Client(10,10,5);
            count++;
            Client c3= new Client(10,10,-5);
            count++;
            Client c4= new Client(10,10,5);
            count++;
            Client c5= new Client(10,10,5);
            count++;
        }catch (ErrQuantite ex){
            System.out.println("count: "+count);
        }finally {
            count=5;
        }
        System.out.println("count "+ count);
    }

    public static void testDepot(){

        Depot d = new Depot(0,0);
        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 10);
            Client c3 = new Client(-5, -5, 10);
            Client c4 = new Client(5, -5, 10);
            Set<Point> mesClients = new HashSet<>();
            mesClients.add(c1);
            mesClients.add(c2);
            mesClients.add(c3);
            mesClients.add(c4);
            d.ajouterRoutes(mesClients);
            System.out.println(d.toString());
        }catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
            System.exit(-1);
        }
        System.out.println(d);
    }

    public static void testClient(){
        try {
            Client c1 = new Client(5, 5, 10);
            Client c2 = new Client(-5, 5, 10);
            Client c3 = new Client(-5, -5, 10);
            Client c4 = new Client(5, -5, 10);
            Set<Point> mesClients = new HashSet<>();
            mesClients.add(c2);
            mesClients.add(c3);
            mesClients.add(c4);
            c1.ajouterRoutes(mesClients);
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);
            System.out.println(c4);
        }catch (ErrQuantite ex){
            System.out.println("Erreur: quantité negative");
        }
    }

    public static void testException(){
        Point p1 =  new Point(0,5);
        Point p2 = new Point(1,2);
        Point p3 = new Point(-2,-5);
        Set<Point> pointes = new HashSet<>();
        pointes.add(p2);
        pointes.add(p3);
        try{
            Client c1 = new Client(5, 5, 10);
            System.out.println("Creation ok");
            System.out.println(c1);
            Client c2 = new Client(5,-5, 0);
            System.out.println("Creation ok");
            System.out.println(c2);
            Client c3 = new Client(5,-5, -2);
            System.out.println("Creation ok");
            System.out.println(c3);
        } catch (ErrQuantite ex) {
            System.err.println(ex.toString());
        }
    }
    public static void testpru(){
        List<String> maListe = new LinkedList<String>();
        Set<String> monensemblre = new HashSet<String >();
        maListe.add("hola");
        maListe.add("hola");
        monensemblre.add("hola");
        monensemblre.add("hola");
        System.out.println(maListe);
        System.out.println(monensemblre);

    }
    public static void main(String[] args){
        //testRoute();
        //testPoint();
        //testDepot();
        //testClient2();
        testpru();
        //testException();

    }
}
