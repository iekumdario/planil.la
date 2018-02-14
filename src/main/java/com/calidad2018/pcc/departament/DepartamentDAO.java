package com.calidad2018.pcc.departament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentDAO extends CrudRepository<Department, Long> {
}
