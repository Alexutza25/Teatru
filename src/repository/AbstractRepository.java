package repository;

import domain.Entity;

import java.util.ArrayList;

public abstract class AbstractRepository <T extends Entity>{
    protected ArrayList<T> elems = new ArrayList<>();
}
