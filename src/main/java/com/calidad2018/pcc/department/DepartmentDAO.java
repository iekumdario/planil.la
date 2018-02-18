package com.calidad2018.pcc.department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends CrudRepository<Department, Long> {
}
