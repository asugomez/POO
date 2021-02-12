package modele;

public class main {
   	
    public static void main(String[] args) {

        //--------------------- Moteur  ------------------------------//

        System.out.println("------------ Premiere partie: moteur ----------------");
        //par defaut
        Moteur moteur1 = new Moteur();
        //par copie
        Moteur moteur2 = new Moteur(moteur1);
        //par donnees
        Moteur moteur3 = new Moteur(700, 'E');
        //ERREUR: F
        Moteur moteur4 = new Moteur(800,'F');
        //ERREUR: puissance min
        Moteur moteur5 = new Moteur(200, 'D');
        System.out.println("Moteur 1 avant: " + moteur1.toString());
        moteur1.setPuissance(800);
        System.out.println("Moteur 1 apres (P=800): " + moteur1.toString());
        moteur1.setPuissance(200);

        System.out.println("Moteur 1 apres (P=200): " + moteur1.toString());

        System.out.println(moteur2.toString());
        System.out.println(moteur3.toString());
        System.out.println(moteur4.toString());
        System.out.println(moteur5.toString());

        //--------------------- Voiture ------------------------------//

        /*System.out.println("------------ Deuxieme partie: voiture ----------------");

        Moteur m1 = new Moteur(null);
        Moteur m2 = new Moteur(1000,'E');

        Voiture v1 = new Voiture(null, m1);
        Voiture v2 = new Voiture("Ferrari", m2);
        Voiture v3 = new Voiture("Ferrari", m2);
        Voiture v12 = new Voiture();
        Voiture v13 = new Voiture();
        Voiture v14 = new Voiture();
        Voiture v15 = new Voiture();

        System.out.println("v1 : " + v1.toString());
        System.out.println("v2 : " + v2.toString());
        System.out.println("v3 : " + v3.toString());
        m2.setPuissance(1200);
        System.out.println("v2 : " + v2.toString());
        System.out.println("v3 : " + v3.toString());
        System.out.println("v12 : " + v12.toString());
        System.out.println("v13 : " + v13.toString());*/


        //--------------------- Personne ------------------------------//

        /*System.out.println("------------ Troiseme partie: personne ----------------");

        Personne p1 = new Personne();
        Personne p2 = new Personne();
        Personne p3 = new Personne("Vachette", "Alban", "Marseille", null);
        //Personne p4= new Personne(1,2,3,4);
        Personne p4 = new Personne();
        Personne p5 = new Personne(null, null, null, null);
        Personne p6 = new Personne("Gomez", "Humberto", "Chili", v2);
        System.out.println("p1: " + p1.toString());
        System.out.println("p2: " + p2.toString());
        System.out.println("p3: " + p3.toString());
        //System.out.println("p4: "+ p4.toString());
        System.out.println("p5: " + p5.toString());
        System.out.println("p6: " + p6.toString());*/
        /**
         * Changer l'adresse
         */
        /*
        p3.setAdresse("Jamaica");
        System.out.println("p3 new: " + p3.toString());*/
        /**
         * affectation
         * 1. la personne a deja une voiture
         * 2. la personne est une pieton
         * 3. la voiture est deja utilise pour qqn
         * 4. voiture null
         */
        /*
        System.out.println("affectaion p6--> v13 (false): " + p6.affecter(v13));
        System.out.println("affectaion p1--> v3 (true): " + p1.affecter(v3));
        System.out.println("affectaion p2--> v3 (FALSE): " + p2.affecter(v3));
        System.out.println("affectation p2 --> null (FALSE) " + p2.affecter(null));
        */
        /**
         * 1. restituer une personne qui avait une voiture --> true
         * 2. Affecter cette persone avec une voiture utilisee --> false
         * 3. affecter avec une voiture disponible  --> true
         * 4. personne pas disponible
         * 5. restituer sans voiture
         */
        /*
        System.out.println("restituer p1: (true) " + p1.restituer());
        System.out.println("p1 --> v2: (FAlse) " + p1.affecter(v2));
        System.out.println("p6 v: " + p6.getVoiture());

        System.out.println("affecter p1 : (true) " + p1.affecter(v13));
        System.out.println("affecter p1 --> v14 (false): " + p1.affecter(v14));

        System.out.println("restituer p4 (false) personne sans voiture: " + p4.restituer());*/

        //--------------------- Formule1    ------------------------------//

        Personne p =new Pilote();
        Personne tech= new Technicien();
        Voiture v0 = new Camion();
        System.out.println(" ");
        Personne p2 = new Pilote("jean", "jacques", "lens");
        System.out.println(p2);
        Voiture v1 = new Formule1();
        Voiture v2 = new Camion();
        v2.setMoteur(moteur3);
        boolean succes= false;


        Ecurie ecurie= new Ecurie();
        succes=ecurie.personneEst(p);
        System.out.println(succes);//false
        succes= ecurie.embaucher(p);
        System.out.println(succes);//true
        succes=ecurie.personneEst(p);
        System.out.println(succes);//true
        succes=ecurie.restituer(p.getIdentification());
        System.out.println(succes);//false
        succes=ecurie.affecter(p.getIdentification(), v1.getIdentifiant());
        System.out.println(succes);//false
        succes=ecurie.acheter(v0);
        System.out.println(succes);//true
        succes= ecurie.acheter(v1);
        System.out.println(succes);//true
        succes=ecurie.affecter(p.getIdentification(), v1.getIdentifiant());
        System.out.println(succes);//true


    }
}