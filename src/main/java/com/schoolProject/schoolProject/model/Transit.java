package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String transitId;
    private String truckNo;
    private LocalDate incomeDate;
    private double quantity;

    @OneToOne(targetEntity = Fuel.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_fu_id",referencedColumnName = "id")
    private Fuel fuel;

    @OneToOne(targetEntity = Companies.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_co_id",referencedColumnName = "id")

    private Companies companies;
    private String shepmetDirection;
    private String agentName;

    public Transit(){}

    public Transit(Long id, String transitId, String truckNo, LocalDate incomeDate, double quntity, Fuel fuel, Companies companies, String shepmetDirction, String agentName) {
        this.id = id;
        this.transitId = transitId;
        this.truckNo = truckNo;
        this.incomeDate = incomeDate;
        this.quantity = quntity;
        this.fuel = fuel;
        this.companies = companies;
        this.shepmetDirection = shepmetDirction;
        this.agentName = agentName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransitId() {
        return transitId;
    }

    public void setTransitId(String transitId) {
        this.transitId = transitId;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    public String getShepmetDirection() {
        return shepmetDirection;
    }

    public void setShepmetDirection(String shepmetDirection) {
        this.shepmetDirection = shepmetDirection;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @PrePersist
    private void Oncreate() {incomeDate = LocalDate.now();}

}
