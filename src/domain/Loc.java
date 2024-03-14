package domain;

public class Loc extends Entity{

    private int rand,loja,numar;
    private double pret;
    private String stare;

    public Loc(int id, int rand, int loja, int numar, double pret, String stare)
    {
        super(id);
        this.rand = rand;
        this.loja = loja;
        this.numar = numar;
        this.pret = pret;
        this.stare = stare;
    }

    public int getRand()
    {
        return this.rand;
    }

    public int getLoja()
    {
        return this.loja;
    }

    public int getNumar()
    {
        return this.numar;
    }

    public double getPret()
    {
        return this.pret;
    }

    public String getStare()
    {
        return this.stare;
    }

}
