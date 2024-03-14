package domain;

import java.util.ArrayList;

public class Sala extends Entity{
    private ArrayList<Loc> loc;

    public Sala(int idSala, ArrayList<Loc> loc) {
        super(idSala);
        this.loc = loc;
    }

    public ArrayList<Loc> getLoc()
    {
        return this.loc;
    }

}
