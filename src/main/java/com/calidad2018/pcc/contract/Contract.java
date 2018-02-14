package com.calidad2018.pcc.contract;

import com.calidad2018.pcc.contracttype.ContractType;
import com.calidad2018.pcc.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Contract {

    @ManyToOne
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    private double baseSalary = 0;

    private double hourlyRate = 0;

    private int weeklyHours = 0;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Contract() {

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}