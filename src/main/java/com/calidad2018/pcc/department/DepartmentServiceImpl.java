package com.calidad2018.pcc.department;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements EntityService<Department> {

    @Autowired
    private DepartmentDAO dao;

    @Override
    @Transactional
    public Iterable<Department> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Department findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Department department){
        this.dao.save(department);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}