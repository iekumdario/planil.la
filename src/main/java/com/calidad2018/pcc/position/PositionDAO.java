package com.calidad2018.pcc.position;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDAO extends CrudRepository<Position, Long> {
}
