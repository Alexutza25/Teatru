package service;

import domain.Loc;
import repository.Repository;
import repository.RepositoryLocBD;

import java.util.Collection;

public class ServiceLoc {
    private Repository<Loc> repoLoc;

    public ServiceLoc() {
        this.repoLoc = new RepositoryLocBD("C:\\Users\\Alexandra\\IdeaProjects\\demo\\src\\Terminal.db");
    }

    public void addLoc(String id, double pret, boolean stare) {
        Loc l = new Loc(id, pret, stare);
        repoLoc.addElem(l);
    }

    public void removeLoc(String id) {
        repoLoc.removeElem(id);
    }

    public void updateLoc(String id1, String id2, double pret1, boolean stare1, double pret2, boolean stare2) {
        Loc l1 = new Loc(id1, pret1, stare1);
        Loc l2 = new Loc(id2, pret2, stare2);
        repoLoc.updateElem(l1, l2);
    }

    public Collection<Loc> getAll()
    {
        return repoLoc.getAll();
    }
    public Loc getById(String id)
    {
        return repoLoc.getById(id);
    }

    public boolean findById(String id){ return repoLoc.findById(id); }
}
