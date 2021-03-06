package com.calidad2018.pcc.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeDAO extends PagingAndSortingRepository<Employee, Long> {

    @Query("select p from Employee p where p.contract.contractType.description=:#{#description}")
    Page<Employee> findByContractContractType(@Param("description") String description, Pageable pageable);

    @Query("select p from Employee p where p.contract.contractType.description=:#{#description}")
    List<Employee> findByContractContractType(@Param("description") String description);

    @Query("select p from Employee p where p.contract.startDate <= :starDay")
    List<Employee> findByStarDateAfterPeriod(@Param("starDay") Date starDay);


}
