package mesExceptions;

public class ErrQuantite extends Exception {
    private int quantite;

    public ErrQuantite(int quantite) {
        this.quantite = quantite;
    }

    public ErrQuantite(String message, int quantite) {
        super(message);
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }


    @Override
    public String toString() {
        return "ErrQuantite{" +
                "quantite=" + quantite +
                "} " + super.toString();
    }
}
