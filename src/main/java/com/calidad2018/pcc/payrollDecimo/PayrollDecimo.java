package com.calidad2018.pcc.payrollDecimo;

import com.calidad2018.pcc.payroll.PayrollEmployee.PayrollEmployee;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Entity(name = "payrolldecimo")
public class PayrollDecimo {


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

    @OneToMany(mappedBy = "payrollDecimo", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PayrollEmployee> employees;

    private boolean state;


    public PayrollDecimo() {
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

    @Override
    public String toString() {
        return "PayrollDecimo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", payDate=" + payDate +
                ", employees=" + employees.size() +
                ", state=" + state +
                '}';
    }
}
