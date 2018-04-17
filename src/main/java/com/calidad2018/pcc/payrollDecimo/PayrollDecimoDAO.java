package com.calidad2018.pcc.payrollDecimo;

import com.calidad2018.pcc.payroll.Payroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PayrollDecimoDAO extends CrudRepository<PayrollDecimo, Long> {

    List<PayrollDecimo> findByState(boolean state);


    List<PayrollDecimo> findByStateAndStartDateBetween(Boolean state, Date start, Date end);

}
