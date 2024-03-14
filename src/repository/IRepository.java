package repository;

import domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    public void add(T o);
    public void remove(T o);
    public void update(T o);
    public void find(T o);
    public Collection<T> getAll();
}
