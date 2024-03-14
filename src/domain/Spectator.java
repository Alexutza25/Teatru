package domain;

public class Spectator extends Entity{
    private String nume,adresa,numarTelefon;

    public Spectator(int idSpectator, String nume, String adresa, String numarTelefon)
    {
        super(idSpectator);
        this.nume = nume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
    }

    public String getNume(){
        return this.nume;
    }
    public String getAdresa(){
        return this.adresa;
    }

    public String getNumarTelefon(){
        return this.numarTelefon;
    }
}
