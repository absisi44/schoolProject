package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String incomeId;
    private LocalDate incomeDate;
    private LocalDate shippingDate;
    private String invoiceNo;
    private String truckNo;
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
    @OneToOne(targetEntity = Locales.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_loc_id",referencedColumnName = "id")
    private Locales locales;
    private String status;

    public Income(){}
    public Income(Long id, String incomeId, LocalDate incomeDate, LocalDate shippingDate, String tnvoiceNo, String truckNo, double quntity,
                  Fuel fuel, Companies companies, Discriminations discriminations, Locales locales, String status) {
        this.id = id;
        this.incomeId = incomeId;
        this.incomeDate = incomeDate;
        this.shippingDate = shippingDate;
        this.invoiceNo = tnvoiceNo;
        this.truckNo = truckNo;
        this.quantity = quntity;
        this.fuel = fuel;
        this.companies = companies;
        this.discriminations = discriminations;
        this.locales = locales;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
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

    public Locales getLocales() {
        return locales;
    }

    public void setLocales(Locales locales) {
        this.locales = locales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PrePersist
    private void Oncreate() {incomeDate = LocalDate.now();}
}
