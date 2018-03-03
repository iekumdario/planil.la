package com.calidad2018.pcc.employee;

import java.util.List;

public interface EmployeeService<T> {
    public Iterable<T> findAll();
    public T findById(Long id);
    public void save(T entity);
    public void delete(Long id);
    public List<T> findByContractType(String type);
}
