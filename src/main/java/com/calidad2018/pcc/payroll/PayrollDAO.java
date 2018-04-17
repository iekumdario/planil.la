package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.position.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PayrollDAO extends CrudRepository<Payroll, Long> {

    List<Payroll> findByState(boolean state);


    List<Payroll> findByStateAndStartDateBetween(Boolean state, Date start, Date end);

}
