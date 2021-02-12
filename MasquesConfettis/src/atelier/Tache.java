package atelier;

public class Tache implements Comparable<Tache>{
    private int dateDebut;
    /**
     * Attribut date fin --> seulement lecture
     */
    private int id;
    private int tempsProduction; //duration
    private int dateLimite;
    private double penalite;
    //private double cout;

    private static int dernierId=0;
    public static final int DATE_DEBUT_DEFAUT=-1;//no assigned
    public static final int DATE_FIN_DEFAUT=-1; //no asigned
    private static final int TEMPS_PRODUCTION_MIN= 50;
    public static final int DATE_LIMITE_DEFAUT=Integer.MAX_VALUE;
    public final static int DATE_INFINIE = Integer.MAX_VALUE;
    public static final double PENALITE_DEFAUT=0.;


    /**
     * Constructor par defaut
     */
    public Tache() {
        dernierId++;
        id=dernierId;
        dateDebut=DATE_DEBUT_DEFAUT;
        tempsProduction=TEMPS_PRODUCTION_MIN;
        dateLimite=DATE_LIMITE_DEFAUT;
        penalite=PENALITE_DEFAUT;
        //cout=COUT_DEFAUT;
    }

    /**
     * Constructor avec la tempsProduction de la tache
     */
    public Tache(int tempsProduction) {
        this();
        setTempsProduction(tempsProduction);
    }

    /**
     * Constructor avec la tempsProduction, la date de livraison et la penalite
     */
    public Tache(int tempsProduction, int dateLimite, double penalite) {
        this(tempsProduction);
        if(valideDateLimite(dateLimite))
            this.dateLimite = dateLimite;
        else
            this.dateLimite=2*tempsProduction;
        if(penalite>0)
            this.penalite=penalite;
    }

    /**
     * Constructor par copie
     */
    public Tache(Tache t){
        this();
        if(t!=null){
            tempsProduction=t.tempsProduction;
            dateDebut=t.dateDebut;
            dateLimite=t.dateLimite;
            penalite=t.penalite;
        }
    }

    public Tache(Tache t, boolean b){
        this(t);
        id=t.id;
    }


    public int getDateDebut() {
        return dateDebut;
    }


    public int getId() {
        return id;
    }

    public int getTempsProduction() {
        return tempsProduction;
    }

    public int getDateLimite() {
        return dateLimite;
    }

    public double getPenalite() {
        return penalite;
    }

    public double getCout() {
        if(dateDebut>=0)
            return penalite * Math.max(dateDebut+tempsProduction - dateLimite,0);
        else
            return 0;
    }


    /**
     * Pour initializer la tempsProduction
     */
    private void setTempsProduction(int tempsProduction){
        if(tempsProduction>=TEMPS_PRODUCTION_MIN){
            this.tempsProduction=tempsProduction;
        }
    }

    public boolean setDateDebut(int dateDebut) {
        if(dateDebut>=0 && this.dateDebut<0){
            this.dateDebut = dateDebut;

            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String s = "n°" + id + ", ";

        if (dateDebut == DATE_DEBUT_DEFAUT)
            s += "date de début inconnue, ";
        else
            s += String.format ("date de début : %3d, ", dateDebut);

        s += String.format("durée : %3d", tempsProduction);
        System.out.println();
        if (this.dateLimite < DATE_INFINIE) {
            s += String.format("\n\tà livrer avant la date %3d, " +
                            "pénalité de retard : %.2f €/mn, " +
                            "surcoût : %.2f €" ,
                    dateLimite, penalite,getCout());
        }
        else {
            s += ", pas de date limite";
        }

        return s;
    }

    /**
     * pour verifier que la date de livraison est au moins egale a 2 fois la date d'execution
     */
    private boolean valideDateLimite(int dateLimite){
        return dateLimite>= 2* tempsProduction;
    }

    @Override
    /**
     * Si int > 0 --> this est plus grande (son temps de production) que la tache o.
     * si int < 0 --> l'inverse
     * si int = 0 --> les deuxx taches ont le meme temps de production
     */
    public int compareTo(Tache t){
        int diff=this.tempsProduction - t.tempsProduction;
        //double diff=this.getPenalite() - t.getDateLimite();
        if(diff!=0)
            return (int)diff;
        else
            return (int) this.id - t.id;
    }

    @Override
    public int hashCode() {
        return 53*7+ id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!= obj.getClass())
            return false;
        return this.hashCode()==obj.hashCode();
    }
    public static void main(String[] args){
        Tache t1= new Tache(100);
        Tache t2= new Tache(100);
        Tache t3= t2;
        System.out.println(t1.compareTo(t2));
        System.out.println(t2.compareTo(t3));
        System.out.println(t1.equals(t3));
        System.out.println(t2.equals(t3));

    }

}
