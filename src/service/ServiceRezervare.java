package service;

import domain.Loc;
import domain.Rezervare;
import repository.Repository;
import repository.RepositoryRezervareBD;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceRezervare {
    private RepositoryRezervareBD repoRez;
    public ServiceRezervare(){
        this.repoRez = new RepositoryRezervareBD("C:\\Users\\Alexandra\\IdeaProjects\\demo\\src\\Terminal.db");
    }
    public void addRezervare(String id, ArrayList<Loc> locuri){
        Rezervare r = new Rezervare(id, locuri);
        repoRez.addElem(r);
    }

    public void removeRezervare(String id){
        repoRez.removeElem(id);
    }

    public void updateRezervare(String id1, String id2, ArrayList<Loc> locuri1, ArrayList<Loc> locuri2)
    {
        Rezervare r1 = new Rezervare(id1, locuri1);
        Rezervare r2 = new Rezervare(id2, locuri2);
        repoRez.updateElem(r1,r2);
    }
    public Collection<Rezervare> getAll()
    {
         return repoRez.getAll();
    }
    public Rezervare getById(String id){
        return repoRez.getById(id);
    }



}
