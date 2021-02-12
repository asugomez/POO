package atelier;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static atelier.Tache.DATE_DEBUT_DEFAUT;
import static atelier.Tache.DATE_LIMITE_DEFAUT;
import static atelier.Tache.DATE_INFINIE;

public class Main {
    public static void testCompare() {
        Tache t1 = new Tache(100);
        Tache t2 = new Tache(200);
        Tache t3 = new Tache(100);
        Tache t4 = new Tache(t2);
        Tache t5 = new Tache(t2, true);

        System.out.print(t1.compareTo(t1) + ", ");
        System.out.print(t2.compareTo(t1) + ", ");
        System.out.print(t3.compareTo(t1) + ", ");
        System.out.print(t3.compareTo(t2) + ", ");
        System.out.print(t4.compareTo(t2) + ", ");
        System.out.println(t2.compareTo(t5));
    }

    public static void testEquals() {
        Tache t1 = new Tache(100);
        Tache t2 = new Tache(200);
        Tache t3 = new Tache(100);
        Tache t4 = new Tache(t2);
        Tache t5 = new Tache(t2, true);

        System.out.print(t1.equals(t1)+ ", ");
        System.out.print(t1.equals(t2)+ ", ");
        System.out.print(t1.equals(t3)+ ", ");
        System.out.print(t2.equals(t3)+ ", ");
        System.out.print(t2.equals(t4)+ ", ");
        System.out.println(t2.equals(t5));
    }

    public static void testHashCode() {
        Tache t1 = new Tache(100);
        Tache t2 = new Tache(200);
        Tache t3 = new Tache(100);
        Tache t4 = new Tache(t2);
        Tache t5 = new Tache(t2, true);

        System.out.print("(" + t1.getId() + ", " + t1.hashCode() + "), ");
        System.out.print("(" + t2.getId() + ", " + t2.hashCode() + "), ");
        System.out.print("(" + t3.getId() + ", " + t3.hashCode() + "), ");
        System.out.print("(" + t4.getId() + ", " + t4.hashCode() + "), ");
        System.out.println("(" + t5.getId() + ", " + t5.hashCode() + ")");
    }

    public static void testMachine(){
        Tache t1 = new Tache(150, 300, 2.5);
        Tache t2 = new Tache(140, 400, 1.5);
        Tache t3 = new Tache( 50, 200, 2.5);
        Tache t4 = new Tache( 85, 200, 1.0);
        Tache t5 = new Tache( 75, 160, 0.5);
        Tache t6 = new Tache( 80, 500, 1.5);

        Machine m = new Machine();
        m.addTache(t1);
        m.addTache(t2);
        m.addTache(t3);
        m.addTache(t4);
        m.addTache(t5);
        m.addTache(t6);

        System.out.println(m);
    }

    public static void testAtelier(){
        Atelier a = new Atelier(2);

        List<Tache> listeTache = new LinkedList<Tache>();

        listeTache.add(new Tache(150, 300, 2.5));
        listeTache.add(new Tache(140, 400, 1.5));
        listeTache.add(new Tache( 50, 200, 2.5));
        listeTache.add(new Tache( 85, 200, 1.0));
        listeTache.add(new Tache( 75, 160, 0.5));
        listeTache.add(new Tache( 80, 500, 1.5));

        a.ordonnancerTaches(listeTache);

        System.out.println(a);

        System.out.println(a.getPenaliteTotal());
        System.out.println(a.getListeMachines().get(0).getPenaliteTotal());
    }

    public static void testTache(){
        Tache [] task = new Tache [6];
        task[0] = new Tache();
        task[1] = new Tache(65);                    // durée = 65mn, pas de date limite
        task[2] = new Tache(50, 75, 1.75);          // durée = 100mn, fin avant 75, 1.75€/mn de retard
        task[3] = new Tache(60, DATE_INFINIE, 2.25);// durée = 60mn, pas de date limite, 2.25€/mn de retard
        task[4] = new Tache(task[2]);               // comme la tâche 2
        task[5] = new Tache(70, 30, -5.15);

        for (Tache t : task)
            System.out.println("Tâche " + t);

        System.out.println();
        System.out.print("La tâche " + task[0].getId()
                + " débute à la date " + 25 + "\t ... modification ");
        if (! task[0].setDateDebut(25))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[1].getId()
                + " débute à la date " + 90 + "\t ... modification ");
        if (! task[1].setDateDebut(90))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[2].getId()
                + " débute à la date " + 70 + "\t ... modification ");
        if (! task[2].setDateDebut(70))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[3].getId()
                + " débute à la date " + 25 + "\t ... modification ");
        if (! task[3].setDateDebut(25))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[4].getId()
                + " débute à la date " + DATE_DEBUT_DEFAUT + "\t ... modification ");
        if (! task[4].setDateDebut(DATE_DEBUT_DEFAUT))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[4].getId()
                + " débute à la date " + 65 + "\t ... modification ");
        if (! task[4].setDateDebut(65))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.print("La tâche " + task[0].getId()
                + " débute à la date " + 15 + "\t ... modification ");
        if (! task[0].setDateDebut(65))
            System.out.println("annulée.");
        else
            System.out.println("effectuée.");

        System.out.println();
        for (Tache t : task)
            System.out.println("Tâche " + t);
    }

    public static void main(String[] args){
        testCompare();
        //------------ MACHINE ---------------------//
        //testMachine();

        //-----------------TACHE ---------------------//
        //testTache();
        // -------------- ATELIER ----------------- //
        //testAtelier();


    }
}
