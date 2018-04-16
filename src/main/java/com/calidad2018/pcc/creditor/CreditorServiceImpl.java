package com.calidad2018.pcc.creditor;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditorServiceImpl implements EntityService<Creditor> {

    @Autowired
    private CreditorDAO dao;

    @Override
    @Transactional
    public Iterable<Creditor> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Creditor findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Creditor creditor){
        this.dao.save(creditor);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}