package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TicketStation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String ticketId;
    private LocalDate ticketDate;
    private double quantity;
    private String vehicleNo;
    private String sreviceHow;
    @OneToOne(targetEntity = Fuel.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_fu_id",referencedColumnName = "id")
    private Fuel fuel;
    @OneToOne(targetEntity = Companies.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_co_id",referencedColumnName = "id")
    private Companies companies;
    @OneToOne(targetEntity = Discriminations.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_disc_id",referencedColumnName = "id")
    private Discriminations discriminations;

    public TicketStation(){}
    public TicketStation(Long id, String ticketId, LocalDate iicketDate, double quantity, String vehicleNo, String sreviceHow,
                         Fuel fuel, Companies companies, Discriminations discriminations) {
        this.id = id;
        this.ticketId = ticketId;
        this.ticketDate = iicketDate;
        this.quantity = quantity;
        this.vehicleNo = vehicleNo;
        this.sreviceHow = sreviceHow;
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getSreviceHow() {
        return sreviceHow;
    }

    public void setSreviceHow(String sreviceHow) {
        this.sreviceHow = sreviceHow;
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

