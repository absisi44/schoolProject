package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class FuelTicketFDept {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String fueltiketfId;
    private LocalDate fueltiketfDate;
    private double quantity;
    private String direcation;
    private String benficiary;

    @OneToOne(targetEntity = Fuel.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_fu_id",referencedColumnName = "id")
    private Fuel fuel;
    @OneToOne(targetEntity = Companies.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_co_id",referencedColumnName = "id")
    private Companies companies;
    @OneToOne(targetEntity = Discriminations.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_disc_id",referencedColumnName = "id")
    private Discriminations discriminations;

    private String status;

    public FuelTicketFDept(){}

    public FuelTicketFDept(Long id,  String fueltiketfId, LocalDate fueltiketfDate, double quantity, String direcation,
                           String benficiary, Fuel fuel, Companies companies, Discriminations discriminations, String status) {
        this.id = id;
        this.fueltiketfId = fueltiketfId;
        this.fueltiketfDate = fueltiketfDate;
        this.quantity = quantity;
        this.direcation = direcation;
        this.benficiary = benficiary;
        this.fuel = fuel;
        this.companies = companies;
        this.discriminations = discriminations;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFueltiketfId() {
        return fueltiketfId;
    }

    public void setFueltiketfId(String fueltiketfId) {
        this.fueltiketfId = fueltiketfId;
    }

    public LocalDate getFueltiketfDate() {
        return fueltiketfDate;
    }

    public void setFueltiketfDate(LocalDate fueltiketfDate) {
        this.fueltiketfDate = fueltiketfDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDirecation() {
        return direcation;
    }

    public void setDirecation(String direcation) {
        this.direcation = direcation;
    }

    public String getBenficiary() {
        return benficiary;
    }

    public void setBenficiary(String benficiary) {
        this.benficiary = benficiary;
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

    public Discriminations getDiscriminations() {
        return discriminations;
    }

    public void setDiscriminations(Discriminations discriminations) {
        this.discriminations = discriminations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrePersist
    private void Oncreate() {fueltiketfDate = LocalDate.now();}
}

