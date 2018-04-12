package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PayrollServiceImpl implements EntityService<Payroll> {

    @Autowired
    private PayrollDAO dao;

    @Override
    @Transactional
    public Iterable<Payroll> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Payroll findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Payroll position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }

    public List<Payroll> findByState(boolean state){

        return dao.findByState(state);
    }
}