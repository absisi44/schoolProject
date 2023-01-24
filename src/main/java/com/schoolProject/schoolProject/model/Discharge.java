package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String dischargeId;
    private LocalDate dischargeDate;
    private String comManName;
    private String empName;
    @OneToOne(targetEntity = Income.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_in_id", referencedColumnName = "id")
    private Income income;

    public Discharge() {
    }

    public Discharge(Long id, String dischargeId, LocalDate dischargeDate, String comManName, String empName, Income income) {
        this.id = id;
        this.dischargeId = dischargeId;
        this.dischargeDate = dischargeDate;
        this.comManName = comManName;
        this.empName = empName;
        this.income = income;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(String dischargeId) {
        this.dischargeId = dischargeId;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getComManName() {
        return comManName;
    }

    public void setComManName(String comManName) {
        this.comManName = comManName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    @PrePersist
    private void Oncreate() {dischargeDate = LocalDate.now();}
}
