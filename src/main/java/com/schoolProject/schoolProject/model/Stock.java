package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String stockId;
    private double quantity;
    @OneToOne(targetEntity = Fuel.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_fu_id",referencedColumnName = "id")
    private Fuel fuel;
    @OneToOne(targetEntity = Companies.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_co_id",referencedColumnName = "id")
    private Companies companies;
    @OneToOne(targetEntity = Discriminations.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_disc_id",referencedColumnName = "id")
    private Discriminations discriminations;

    public Stock(){}

    public Stock(Long id, String stockId, double quntity, Fuel fuel, Companies companies, Discriminations discriminations) {
        this.id = id;
        this.stockId = stockId;
        this.quantity = quntity;
        this.fuel = fuel;
        this.companies = companies;
        this.discriminations = discriminations;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    public Discriminations getDiscriminations() {
        return discriminations;
    }

    public void setDiscriminations(Discriminations discriminations) {
        this.discriminations = discriminations;
    }
}