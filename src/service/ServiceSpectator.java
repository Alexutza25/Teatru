package service;

import domain.Spectator;
import repository.Repository;
import repository.RepositorySpectatorBD;

import java.util.Collection;

public class ServiceSpectator {
    private Repository<Spectator> repoSpec;
    public ServiceSpectator(){
        this.repoSpec = new RepositorySpectatorBD("C:\\Users\\Alexandra\\IdeaProjects\\demo\\src\\Terminal.db");
    }

    public void addSpectator(String id, String nume, String prenume, String parola, boolean admin)
    {
       Spectator s = new Spectator(id, nume, prenume, parola,admin);
       repoSpec.addElem(s);
    }

    public void removeSpectator(String id)
    {
        repoSpec.removeElem(id);
    }

    public void updateSpectator(String id1, String nume1,String prenume1, String parola1, boolean admin1, String nume2, String prenume2, String parola2, boolean admin2)
    {
        Spectator s1 = new Spectator(id1,nume1, prenume1, parola1, admin1);
        Spectator s2 = new Spectator(id1, nume2, prenume2, parola2, admin2);
        repoSpec.updateElem(s1,s2);
    }
    public Collection<Spectator> getAllS()
    {
        return repoSpec.getAll();
    }

    public Spectator getById(String id){
        return repoSpec.getById(id);
    }

    public boolean findById(String id) {return repoSpec.findById(id); }

    public Spectator logare(String id, String parola){
        Spectator s = repoSpec.getById(id);
        if(s.getParola().equals(parola))
            return s;
        return null;
    }

}
