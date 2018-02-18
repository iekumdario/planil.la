package com.calidad2018.pcc.position;

import com.calidad2018.pcc.core.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionServiceImpl implements EntityService<Position> {

    @Autowired
    private PositionDAO dao;

    @Override
    @Transactional
    public Iterable<Position> findAll(){
        return this.dao.findAll();
    }
    @Override
    @Transactional
    public Position findById(Long id){
        return this.dao.findOne(id);
    }
    @Override
    @Transactional
    public void save(Position position){
        this.dao.save(position);
    }
    @Override
    @Transactional
    public void delete(Long id){
        this.dao.delete(id);
    }
}