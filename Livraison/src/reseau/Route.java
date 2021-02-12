package reseau;

import java.util.Objects;

public class Route {
    private Point origine;
    private Point destination;
    private double distance;

    public Route(){
        this.origine = new Point();
        this.destination = new Point();
        distance = 0;
    }
    public Route(Point p1, Point p2) {
        this();
        if(p1!=null)
            this.origine = p1;
        if(p2!=null)
            this.destination = p2;
        this.distance = setDistance();
    }

    public Point getOrigine() {
        return origine;
    }

    public Point getDestination() {
        return destination;
    }

    public boolean isPointHere(Point p){
        if(p!=null){
            return p==getOrigine() || p==getDestination();
        }
        return false;
    }

    public double getDistance() {
        this.setDistance();
        return distance;
    }

    private double setDistance(){
        double x = Math.pow(Math.abs(origine.getAbscisse() - destination.getAbscisse()),2);
        double y = Math.pow(Math.abs(origine.getOrdonnee() - destination.getOrdonnee()),2);
        return (Math.pow(x+y, 0.5));
    }

    @Override
    public String toString() {
        return  "Du " + origine + " au " + destination +
                ", la distance vaut " +
                String.format("%.2f", getDistance()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return this.origine == route.origine && this.destination == route.destination;
    }

    @Override
    public int hashCode() {
        return origine.hashCode() + destination.hashCode();
    }
}
