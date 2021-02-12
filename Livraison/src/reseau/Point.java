package reseau;

import java.util.*;

public class Point {
    private double abscisse;
    private double ordonnee;
    private int identifiant;
    private  Map<Point, Route> mesRoutes;
    //private List<Route> mesRoutes;

    public static int dernierIdentifiant = 0;

    public static final double INFINI = Double.POSITIVE_INFINITY;

    public Point(){
        abscisse=0.0;
        ordonnee=0.0;
        this.identifiant = dernierIdentifiant++;
        //this.mesRoutes = new LinkedList<>();
        this.mesRoutes = new HashMap<>();
    }

    public Point(double abscisse, double ordonnee) {
        this();
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    public Point(Point p){
        this();
        if(p!=null){
            this.abscisse=p.getAbscisse();
            this.ordonnee=p.getOrdonnee();
            //List<Route> mesRoutesCopie = new LinkedList<>(p.mesRoutes);
            Map<Point, Route> mesRoutesCopie = new HashMap<>(p.mesRoutes);
            this.mesRoutes=mesRoutesCopie;
            this.identifiant = p.identifiant;
        }
    }

    public void setAbscisse(double abscisse) {
        this.abscisse = abscisse;
    }

    public void setOrdonnee(double ordonnee) {
        this.ordonnee = ordonnee;
    }

    public double getAbscisse() {
        return abscisse;
    }

    public double getOrdonnee() {
        return ordonnee;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    /*@Override
    public String toString() {
        String s = "point n°" + getIdentifiant() +
                String.format(" (%+5.1f, %+5.1f)", getAbscisse(), getOrdonnee());
        return s;
    }*/

    //2eme version

    @Override
    public String toString() {
        String s;

        if (this instanceof Depot)
            s = "dépôt";
        else
            s = "client";
        s += " n°" + identifiant + String.format(" (%+5.1f, %+5.1f)", abscisse, ordonnee);

        if (mesRoutes.isEmpty()) return s;
        if (this instanceof Depot)
            s += "\nListe des clients connectés au dépôt n°" + this.getIdentifiant();
        else
            s += "\nListe des clients connectés au client n°" + this.identifiant;

        for (Route r : mesRoutes.values()) {
            s += "\n\t";
            Point p = r.getDestination();
            if (p instanceof Depot)
                s += "dépôt";
            else
                s += "client";
            s += " n°" + p.getIdentifiant() + ", distant de "
                    + String.format("%6.3f", r.getDistance());
        }
        return s;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return (this.getOrdonnee() == point.getOrdonnee() && this.getAbscisse() == point.getAbscisse() && this.getIdentifiant() == point.getIdentifiant());
    }

    @Override
    public int hashCode() {
        return this.identifiant;
    }

    /**
     * prend en paramètre un ensemble de points.
     * Ces points sont tous les points joignables depuis le point courant (this).
     * Cette méthode doit donc remplir la structure de données
     */
    public void ajouterRoutes(Set<Point> mesDestinations){
        if(mesDestinations!=null){
            for(Point p: mesDestinations) {
                Route r1 = new Route(this, p);
                this.mesRoutes.put(p, r1);
            }
        }

    }

    /*public void ajouterRoutes(List<Point> mesDestinations){
        if(mesDestinations!=null){
            for(Point p: mesDestinations){
                Route r1 = new Route(this,p);
                this.mesRoutes.add(r1);
            }
        }
    }*/

    /**
     * renvoie le numero des routes
     */
    public int getNbRoutes(){
        return mesRoutes.size();
    }

    /**
     * renvoie la distance entre le point (this) et le point p
     */
    /*public double getDistance(Point p){
        if(p==null) return INFINI;
        Route r= mesRoutes.get(p);
        if(r==null) return INFINI;
        return r.getDistance();
    }*/
    public double getDistance (Point p){
        if(p!= null){
            Set<Point> destination = new HashSet<>();
            destination.add(p);
            this.ajouterRoutes(destination);
            Route r= mesRoutes.get(p);
            if(r != null) {
                return r.getDistance();
            }
        }
        return INFINI;
    }

    /*public double getDistance(Point p){
        if(p==null) return INFINI;
        for(Route r: mesRoutes){
            if(r.getDestination().equals(p)){
                return r.getDistance();
            }
        }
        return INFINI;
    }*/
}
