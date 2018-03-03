package com.calidad2018.pcc.employee;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService<Employee> {

    @Autowired
    private EmployeeDAO dao;

    @Override
    @Transactional
    public Iterable<Employee> findAll(){
        return this.dao.findAll();
    }

    @Override
    @Transactional
    public Employee findById(Long id){
        return this.dao.findOne(id);
    }

    @Override
    @Transactional
    public void save(Employee employee){
        this.dao.save(employee);
    }

    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }


    public Page<Employee> findByContract(String type, org.springframework.data.domain.Pageable pageable) {

        return dao.findByContractContratType(type,pageable);

    }

    @Override
    public List<Employee> findByContractType(String type) {

        return dao.findByContractContratType(type);

    }
}