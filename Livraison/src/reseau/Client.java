package reseau;

import mesExceptions.ErrQuantite;

public class Client extends Point {

    private int nbCaisses;

    public Client(){
        super();
        this.nbCaisses=0;
    }
    public Client(int nbCaisses) throws ErrQuantite {
        super();
        if(nbCaisses<0) {
            ErrQuantite err = new ErrQuantite("Le nombre de caisses doit être plus grand que 0", nbCaisses);
            System.out.format("ERREUR: client %d", this.getIdentifiant());
            System.err.println(err.getMessage());
            throw err;
        }else
            this.nbCaisses = nbCaisses;
    }

    public Client(double abscisse, double ordonnee) {
        super(abscisse, ordonnee);
    }

    /**
     * @throws ErrQuantite si le nombre des caisses est negatif
     */
    public Client(double abscisse, double ordonnee, int nbCaisses) throws ErrQuantite {
        super(abscisse, ordonnee);
        if(nbCaisses<0) {
            ErrQuantite err = new ErrQuantite("Le nombre de caisses doit être plus grand que 0", nbCaisses);
            System.err.println(err.getMessage());
            throw err;
        }else
            this.nbCaisses = nbCaisses;
    }


    public Client(Client c) {
        super(c);
        this.nbCaisses = c.getNbCaisses();
    }

    public int getNbCaisses() {
        return nbCaisses;
    }

    public void setNbCaisses(int nbCaisses) {
        if(nbCaisses>0)
            this.nbCaisses = nbCaisses;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tNombre de caisses demandées : " +
                this.nbCaisses;
    }


}
