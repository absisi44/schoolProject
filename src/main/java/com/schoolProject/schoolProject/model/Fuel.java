package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String fuelId;
    private String fuelName;

    public Fuel(){}

    public Fuel(Long id, String fuelId, String fuelName) {
        this.id = id;
        this.fuelId = fuelId;
        this.fuelName = fuelName;
    }

    @Override
    public String toString() {
        return "Fuel{" + "fuelId='" + fuelId + '\'' + ", fuelName='" + fuelName + '\'' + '}';}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getFuelId() {return fuelId;}

    public void setFuelId(String fuelId) {this.fuelId = fuelId;}

    public String getFuelName() {return fuelName;}

    public void setFuelName(String fuelName) {this.fuelName = fuelName;}
}
