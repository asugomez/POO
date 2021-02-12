// ------------------------------------------------------------------------- //
//	Méthode toString() de la classe Moteur
// ------------------------------------------------------------------------- //

    @Override
    public String toString() {
        return  String.format("%4d ch, ", puissance) +  
                ((carburant == DIESEL) ? "DIESEL" : "ESSENCE");
    }


//	Méthode toString() de la classe Voiture

    @Override
    public String toString() {
        String s =  String.format("n°%-4d\tmarque : %-12s", 
                        immatriculation, marque) + "\tmoteur : " + moteur;
        if (personne != null)
            s += "\tconducteur = " + 
                    personne.getPrenom() + " " + personne.getNom();
        return s;
    }   

// ------------------------------------------------------------------------- //
//	Méthode toString() de la classe Personne
// ------------------------------------------------------------------------- //

    @Override
    public String toString() {
        String s = String.format("n°%d\t%-9s\t%-10s\t%-20s", 
                identifiant, prenom, nom.toUpperCase(), adresse);
        if (estPieton())
            return s + "Piéton";
        return s + "\n    Voiture " + this.voiture;
    }


// ------------------------------------------------------------------------- //
//	Fonction principale de test de la classe Moteur
// ------------------------------------------------------------------------- //

    public static void main(String[] args) {
        int anciennePuissance;
        
        /* ----------------------------------------------------------------- */
        /*  Création du moteur 1                                             */
        Moteur m1 = new Moteur();        
        /*Affichage des valeurs des attributs du moteur créé                 */
        System.out.println("Création du moteur m1 : " + m1);               
        /* Modification de la puissance du moteur m1                         */
        anciennePuissance = m1.getPuissance();
        System.out.print("Puissance initiale du moteur m1 = " +
                String.format("%4d ch. Passage à 1000 ch ... ", 
                        anciennePuissance));
        m1.setPuissance(1000);
        if (m1.getPuissance() != anciennePuissance)
            System.out.println("effectué.");
        else
            System.out.println("non réalisé");
        System.out.println("Nouvelle puissance du moteur m1 = " + 
                m1.getPuissance()+ " ch");
        
        System.out.println();

        /* ----------------------------------------------------------------- */
        /*  Création du moteur 2                                             */
        Moteur m2 = new Moteur(1200, ESSENCE);
        /*Affichage des valeurs des attributs du moteur créé                 */
        System.out.println("Création du moteur m2 : " + m2);
        /* Modification de la puissance du moteur m2                         */
        anciennePuissance = m2.getPuissance();
        System.out.print("Puissance initiale du moteur m2 = " +
                String.format("%4d  ch. Passage à 200 ch ... ", 
                        m2.getPuissance()));
        m2.setPuissance(200);
        if (m2.getPuissance() != anciennePuissance)
            System.out.println("effectué.");
        else
            System.out.println("non réalisé.");
        System.out.println("Nouvelle puissance du moteur m2 = " + 
                m2.getPuissance()+ " ch");
        
        System.out.println("");
        
        /* ----------------------------------------------------------------- */
        /*  Création du moteur 3                                             */
        Moteur m3 = new Moteur(1400, DIESEL);
        /*  Affichage des valeurs des attributs du moteur créé               */
        System.out.println("Création du moteur m3 : " + m3);
 
        /* ----------------------------------------------------------------- */
        /*  Création du moteur 4                                             */
        System.out.println("Demande de création du moteur 4 de 1400 ch "+
                "au nitrométhane");
        
        // Demande de création du moteur 4 de 1400 ch au nitrométhane
        Moteur m4 = new Moteur(1400, 'N');
        /* Affichage des valeurs des attributs du moteur créé               */
        System.out.println("Création du moteur m4 : " + m4);

        System.out.println("");
        
        /* ----------------------------------------------------------------- */
        /*  Création du moteur 5                                             */
        System.out.println("Demande de création d'un moteur diesel de 200 ch");
        //  Demande de création d'un moteur diesel de 200 ch
        Moteur m5 = new Moteur( 200, DIESEL);
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création effective du moteur m5 : " + m5);
        
        System.out.println();
        /* ----------------------------------------------------------------- */
        /*  Création d'un moteur essence de 800 ch : m6                      */
        Moteur m6 = new Moteur(800, ESSENCE);
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création du moteur m6 : " + m6);
        /* ----------------------------------------------------------------- */
        /*  Création d'un nouveau moteur ayant les mêmes                     */
        /*  caractériques que le moteur précédent                            */
        Moteur m7 = new Moteur(m6);
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création du moteur m7 : " + m7);
        if (m6 == m7)
            System.out.println("Ces deux moteurs sont identiques");
        else
            System.out.println("Ces deux moteurs sont différents");
        /* Augmentation de 100 ch de la puissance du moteur 7                */
        m7.setPuissance(m7.getPuissance() + 100);
        /* Affichage des valeurs des attributs du moteur 7                   */
        System.out.println("Après augmentation de 100 ch " +
                "de la puissance du moteur m7 : " + m7);
        /* Réduction de 50 ch de la puissance du moteur 7                    */
        m7.setPuissance(m7.getPuissance() - 50);
        /* Affichage des valeurs des attributs du moteur 7                   */
        System.out.println("Après réduction de 50 ch " + 
                "de la puissance du moteur m7 : " + m7);
        /* Réduction de moitié de la puissance du moteur 7                   */
        m6.setPuissance(m7.getPuissance() / 2);
        /* Affichage des valeurs des attributs du moteur 7                   */
        System.out.println("Après réduction de moitié " +
                "de la puissance du moteur m7 : " + m7);
        
        
        /* Pour montrer que la modification de puisssance de m7 
         * n'a pas affecté celle de m6 ; ce sont bient deux objets (2 moteurs)
         * distincts : m7 n'est pas une référence à m6
         */
        System.out.println("Caractéristiques du moteur m6 : " + m6); 
        
        System.out.println("");
        /* ----------------------------------------------------------------- */
        /*  Création d'un nouveau moteur standard : m8                       */
        Moteur m8 = new Moteur();
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création du moteur m8 : " + m8);
        /* ----------------------------------------------------------------- */
        /*  Création d'un nouveau moteur ayant les mêmes caractériques que   */
        /*  le moteur précédent                                              */
        Moteur m9 = new Moteur(m8);
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création du moteur m9 : " + m9);
        if (m8.equals(m9))
            System.out.println("Les moteurs m8 et m9 sont identiques");
        else
            System.out.println("Les moteurs m8 et m9 sont différents");
        m9.setPuissance((m9.getPuissance() * 110) / 100);
        System.out.print("Après augmentation de 10% de la puissance de m9, ");
        if (m8.equals(m9))
            System.out.println("m8 et m9 sont toujours identiques");
        else
            System.out.println("m8 et m9 sont maintenant différents");
         

        System.out.println("");
        /* ----------------------------------------------------------------- */
        /*  Création d'un nouveau moteur standard : m10                      */
        Moteur m10 = new Moteur();
        /* Affichage des valeurs des attributs du moteur créé                */
        System.out.println("Création du moteur m10 : " + m10);
        /* ----------------------------------------------------------------- */
        /*  Déclaraion du moteur m11                                         */
        Moteur m11 = m10;
        /* Affichage des valeurs des attributs du moteur précédent           */
        System.out.println("Déclaration du moteur m11 : " + m11);
        if (m10.equals(m11))
            System.out.println("Les moteurs m10 et m11 sont identiques");
        else
            System.out.println("Les moteurs m10 et m11 sont différents");
        
        m11.setPuissance((m11.getPuissance() * 110) / 100);
        System.out.print("Après augmentation de 10% de la puissance de m11, ");
        if (m10.equals(m11))
            System.out.println("m10 et m11 sont toujours identiques");
        else
            System.out.println("m10 et m11 sont maintenant différents");
        
    }

// ------------------------------------------------------------------------- //
//	Fonction principale de la classe Voiture
// ------------------------------------------------------------------------- //

   public static void main(String[] args) {
        
        //  Création d'un type de moteur
        Moteur  m1 = new Moteur(800, 'E');
        //  Création d'une première voiture
        Voiture v1 = new Voiture("Ferrari", m1);
        //  Affichage de la voiture créée
        System.out.println("v1 : " + v1);
        //  Augmentation de la puissance des moteurs de type m1
        System.out.println("Puissance +15% des moteurs m1");
        m1.setPuissance(m1.getPuissance() * 115 / 100); 
        System.out.println("Nouvelles caractérisques du moteur m1 : " + m1);
        //  Cette modification s'applique-t-elle au moteur déjà installé
        //  dans la voiture v1 ?
        System.out.println("v1 : " + v1);
        
        System.out.println();

        //  Création d'un type de moteur standard
        Moteur  m2 = new Moteur(null);
        //  Création d'une voiture avec un moteur standard
        Voiture v2 = new Voiture("Maserati", m2);
        //  Affichage de la voiture créée
        System.out.println("v2 : " + v2);    
        //  Les techniciens sont parvenus à augmenter de 25% de la 
        //  puissance (du moteur) de la  voiture v2.
        System.out.println("Puissance +25% du moteur de v2");
        v2.setPuissance(v2.getPuissanceMoteur() * (100 + 25) / 100);
        System.out.println("v2 : " + v2);    
        System.out.println("Véhicule 2 :");
        System.out.println("\tImmatriculation : " + v2.getIdentifiant());
        System.out.println("\tMarque ........ : " + v2.getMarque());
        System.out.println("\tPuissance ..... : " + v2.getPuissance()+ " ch");
        System.out.println("\tCarburant ..... : " + v2.getCarburant());
      
        System.out.println();
        
        //  Création d'un nouveau moteur standard
        Moteur  m3 = new Moteur(null);
        //  Création d'une nouvelle voiture équipée d'un moteur standard
        Voiture v3 = new Voiture(null, m3);
        System.out.println("v3 : " + v3);
        //  Changement du moteur de la voiture 3 : passage au diesel
        System.out.println("Changement du moteur du véhicule 3");
        v3.setMoteur(1000, 'D');
        System.out.println("v3 : " + v3);
    }


// ------------------------------------------------------------------------- //
//	Fonction principale de la classe Personne
// ------------------------------------------------------------------------- //

    public static void main(String[] args) {
        Personne [] pers = new Personne[4];
        pers[0] = new Personne();
        pers[1] = new Personne("Gosling");
        pers[2] = new Personne("Stroustrup", "Bjarne");
        pers[3] = new Personne("Van Rossum", "Guido", 
                                "Haarlem, NEDERLAND");
        pers[1].setPrenom("James");
        pers[1].setAdresse("Calgary, CANADA");
        pers[2].setAdresse("Aarhus, DANEMARK");         
        
        Voiture [] v = new Voiture [3];
        v[0] = new Voiture("Porsche", 550, 'E');
        v[1] = new Voiture("McLaren", 777, 'E');
        System.out.println("Affectation de la voiture " +
                v[0].getIdentifiant() + " à la personne " +
                pers[0].getIdentification() + " ... " +
                (pers[0].affecter(v[0]) ? "effectuée" : "échouée"));
        System.out.println("Affectation de la voiture " +
                v[1].getIdentifiant() + " à la personne " +
                pers[2].getIdentification() + " ... " +
                (pers[2].affecter(v[1]) ? "effectuée" : "échouée"));
        System.out.println("Affectation de la voiture " +
                v[0].getIdentifiant() + " à la personne " +
                pers[3].getIdentification() + " ... " +
                (pers[3].affecter(v[0]) ? "effectuée" : "échouée"));        
        for (Personne p : pers) 
            System.out.println("Personne " + p);
        
    
        v[2] = new Voiture();              
        System.out.println("Affectation de la voiture " +
                v[2].getIdentifiant() + " à la personne " +
                pers[0].getIdentification() + " ... " +
                (pers[0].affecter(v[2]) ? "effectuée" : "échouée"));
        System.out.println("Affectation de la voiture " +
                v[2].getIdentifiant() + " à la personne " +
                pers[3].getIdentification() + " ... " +
                (pers[3].affecter(v[2]) ? "effectuée" : "échouée"));

        for (Personne p : pers) {
            System.out.println("Personne " + p);
        }

        System.out.println("Suppression de l'affectation de la voiture au "
                + "conducteur " + pers[1].getIdentification() + " ... " +
                (pers[1].restituer() ? "effectuée" : "échouée"));
        System.out.println("Suppression de l'affectation de la voiture au "
                + "conducteur " + pers[3].getIdentification() + " ... " +
                (pers[3].restituer() ? "effectuée" : "échouée"));
        System.out.println("Suppression de l'affectation de la voiture au "
                + "conducteur " + pers[3].getIdentification() + " ... " +
                (pers[3].restituer() ? "effectuée" : "échouée"));
        for (Voiture w : v)
            System.out.println("Voiture " + w);


        for (Personne p : pers) {
            System.out.println("Personne " + p);
        }        
    }
// ------------------------------------------------------------------------- //

