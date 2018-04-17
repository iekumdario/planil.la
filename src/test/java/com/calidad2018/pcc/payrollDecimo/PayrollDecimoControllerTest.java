package com.calidad2018.pcc.payrollDecimo;

import com.calidad2018.pcc.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class PayrollDecimoControllerTest {

    PayrollDecimoController payrollDecimoController;

    @Before
    public void setUp() throws Exception {

        payrollDecimoController = new PayrollDecimoController();
    }

    @Test
    public void getDates() {

        Map<String,Date> dateMap = payrollDecimoController.getDates(Constants.PRIMER_DECIMO);

        System.out.println(dateMap);

    }

    @Test
    public void getDatesSecond() {

        Map<String,Date> dateMap = payrollDecimoController.getDates(Constants.SEGUNDO_DECIMO);

        System.out.println(dateMap);

    }

    @Test
    public void getDatesThird() {

        Map<String,Date> dateMap = payrollDecimoController.getDates(Constants.TERCER_DECIMO);

        System.out.println(dateMap);

    }

    @Test
    public void period() {

        Calendar calendar = Calendar.getInstance();


        String period = payrollDecimoController.checkPeriod();

        System.out.println(period);

    }
}