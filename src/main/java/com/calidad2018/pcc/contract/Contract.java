package com.calidad2018.pcc.contract;

import com.calidad2018.pcc.contractType.ContractType;
import com.calidad2018.pcc.employee.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Contract {

    @ManyToOne
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @OneToOne
    private Employee employee;

    @NotNull
    private double baseSalary;

    @NotNull
    private double hourlyRate = 0;

    @NotNull
    private int weeklyHours = 40;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Contract() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }


    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
        this.hourlyRate = baseSalary / (weeklyHours*4); //we get the hours for the whole month to get the hourly rate
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employees) {
        this.employee = employees;
    }

}