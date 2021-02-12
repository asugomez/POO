package reseau;

public class Depot extends Point {

    public Depot(){
        super();
    }
    public Depot(double abscisse, double ordonnee){
        super(abscisse,ordonnee);
    }

    public Depot(Point p) {
        super(p);
    }

    @Override
    public String toString() {
        return this.getIdentifiant() + ", situé au " + super.toString();
    }
}
