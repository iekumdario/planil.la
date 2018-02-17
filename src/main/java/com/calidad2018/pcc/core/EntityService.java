package com.calidad2018.pcc.core;

public interface EntityService<T> {
    public Iterable<T> findAll();
    public T findById(Long id);
    public void save(T entity);
    public void delete(Long id);
}
