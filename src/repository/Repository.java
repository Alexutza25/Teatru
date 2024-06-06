package repository;

import domain.Entity;

import java.util.Collection;

public class Repository<T extends Entity> extends AbstractRepository<T>{
    public int getSize(){
        return elems.size();
    }

    public T getAt(int poz)
    {
        return elems.get(poz);
    }

    public boolean findById(String id){
        for(T elem:elems)
        {
            if(elem.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public T getById(String id){
        for(T elem: elems){
            if(elem.getId().equals(id)){
                return elem;
            }
        }
        return null;
    }

    public void removeElem(String id){
      if(findById(id)) {
          elems.remove(getById(id));
      }
    }

    public void addElem(T elem){
        if(!findById(elem.getId())){
            elems.add(elem);
        }
    }

    public void updateElem(T elem1, T elem2)
    {
        if(findById(elem1.getId())){
            removeElem(elem1.getId());
            addElem(elem2);
        }
    }

    public Collection<T> getAll(){
        return this.elems;
    }
}
