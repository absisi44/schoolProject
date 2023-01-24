package com.schoolProject.schoolProject.model;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ExitPermit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String exitpermitId;
    private LocalDate permitdate;
    private String reciptNo;
    private String checkBankNo;
    private double fees;
    private String empName;
    @OneToOne(targetEntity = Income.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_in_id",referencedColumnName = "id")
    private Income income;

    public ExitPermit(){}

    public ExitPermit(Long id, String exitpermitID, LocalDate permitdate, String reciptNo, String checkBankNo, double fees, String empName, Income income) {
        this.id = id;
        this.exitpermitId = exitpermitID;
        this.permitdate = permitdate;
        this.reciptNo = reciptNo;
        this.checkBankNo = checkBankNo;
        this.fees = fees;
        this.empName = empName;
        this.income = income;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getExitpermitId() {return exitpermitId;}

    public void setExitpermitId(String exitpermitId) {this.exitpermitId = exitpermitId;}

    public LocalDate getPermitdate() {return permitdate;}

    public void setPermitdate(LocalDate permitdate) {this.permitdate = permitdate;}

    public String getReciptNo() {return reciptNo;}

    public void setReciptNo(String reciptNo) {this.reciptNo = reciptNo;}

    public String getCheckBankNo() {return checkBankNo;}

    public void setCheckBankNo(String checkBankNo) {this.checkBankNo = checkBankNo;}

    public double getFees() {return fees;}

    public void setFees(double fees) {this.fees = fees;}

    public String getEmpName() {return empName;}

    public void setEmpName(String empName) {this.empName = empName;}

    public Income getIncome() {return income;}

    public void setIncome(Income income) {this.income = income;}

    @PrePersist
    private void Oncreate() {permitdate = LocalDate.now();}
}
