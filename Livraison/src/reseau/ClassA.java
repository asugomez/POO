package reseau;

public class ClassA extends Point{
    private int p;
    private int x;

    public ClassA(){
        p= 0;
    }

    public boolean set(int p1){
        if(p1 ==0)
            return false;
        this.p=p1;
        return true;

    }
    public boolean setx(int p1){
        if(p1 ==0)
            return false;
        this.x=p1;
        return true;

    }

    @Override
    public String toString() {
        return "reseau.ClassA{" +
                "p=" + p +
                ", x=" + x +
                "} " + super.toString();
    }

    public static void main (String [] args){
        ClassA clase = new ClassA();
        ClassA c2  = clase;
        System.out.println(c2);
        if(c2.set(2))
            System.out.println(c2);
        boolean ho= c2.set(0);
        boolean jiji=c2.setx(3);

        if(c2.set(5) && c2.setx(3))
            System.out.println(c2);
        System.out.println(c2);

    }

        /*
        int i = 0;
        Set<Client> clients1 = new HashSet<>(clients);
        while (i < clients1.size()){
            Tournee tourneeCourante = new Tournee(depot, 15);
            boolean reussit = true;
            int counter= 0;
            for(Client c: clients1) {
                if (counter >= i)
                    reussit = tourneeCourante.ajouterClient(c);
                counter++;
                System.out.println("reu " + reussit);
            }

            System.out.println(tourneeCourante);
            if(reussit)
                i++;
            else{
                i++;
                System.out.println(this.ajouterTournee(tourneeCourante));
            }
        }
    }*/
}
