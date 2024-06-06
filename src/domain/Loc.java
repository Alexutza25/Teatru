package domain;

public class Loc extends Entity{
    private String rand_numar;
    private double pret;
    private boolean stare; //true daca e liber,
    // false daca este ocupat

    public Loc(String rand_numar, double pret, boolean stare)
    {
        super(rand_numar);
        this.pret = pret;
        this.stare = stare;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean getStare() {
        return stare;
    }

    public void setStare(boolean stare) {
        this.stare = stare;
    }


}
