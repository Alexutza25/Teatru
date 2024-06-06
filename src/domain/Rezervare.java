package domain;

import java.util.ArrayList;

public class Rezervare extends Entity{
    private String id;
    private ArrayList<Loc> locuri;
    public Rezervare(String id, ArrayList<Loc> locuri)
    {
        super(id);
        this.locuri = locuri;
    }
    public double getPret()
    {
        double s = 0;
        for(Loc l:locuri)
        {
            s+=l.getPret();
        }
        return s;
    }

    public ArrayList<Loc> getLocuri() {
        return locuri;
    }

    public void setLocuri(ArrayList<Loc> locuri) {
        this.locuri = locuri;
    }
}
