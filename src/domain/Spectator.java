package domain;

public class Spectator extends Entity{
    private String adresa, nume, prenume, parola;
    private boolean admin;
    public Spectator(String adresa, String nume, String prenume, String parola, boolean admin) {
        super(adresa);
        this.nume= nume;
        this.prenume = prenume;
        this.parola = parola;
        this.admin = admin;
    }
    public String getNume(){
        return this.nume;
    }

    public String getPrenume()
    {
        return this.prenume;
    }

    public String getParola(){
        return this.parola;
    }

    public boolean getAdmin(){return this.admin; }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setAdmin(boolean admin) { this.admin = admin; }
}
