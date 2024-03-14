package domain;

public class Teatru extends Entity{
    protected int idTeatru;
    private Sala configuratieSala;

    public Teatru(int idTeatru, Sala configuratieSala)
    {
        super(idTeatru);
        this.configuratieSala = configuratieSala;
    }

    public int getIdTeatru()
    {
        return this.idTeatru;
    }

    public Sala getConfiguratieSala()
    {
        return this.configuratieSala;
    }
}
