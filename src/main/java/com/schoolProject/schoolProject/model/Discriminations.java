package com.schoolProject.schoolProject.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discriminations {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String dicrId;
    private String discrName;

    public Discriminations(){}

    public Discriminations(Long id, String dicrId, String discrName) {
        this.id = id;
        this.dicrId = dicrId;
        this.discrName = discrName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicrId() {
        return dicrId;
    }

    public void setDicrId(String dicrId) {
        this.dicrId = dicrId;
    }

    public String getDiscrName() {
        return discrName;
    }

    public void setDiscrName(String discrName) {
        this.discrName = discrName;
    }


}
