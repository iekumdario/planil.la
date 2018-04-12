package com.calidad2018.pcc.payroll;

import com.calidad2018.pcc.contract.Contract;
import com.calidad2018.pcc.core.BaseEntity;
import com.calidad2018.pcc.department.Department;
import com.calidad2018.pcc.employee.Employee;
import com.calidad2018.pcc.payroll.PayrollEmploy.PayrollEmployee;
import com.calidad2018.pcc.position.Position;
import com.calidad2018.pcc.utils.Gender;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Entity(name = "payroll")
public class Payroll {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date payDate;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PayrollEmployee> employees;

    private boolean state;


    public Payroll() {
        super();
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Set<PayrollEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<PayrollEmployee> employees) {
        this.employees = employees;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
