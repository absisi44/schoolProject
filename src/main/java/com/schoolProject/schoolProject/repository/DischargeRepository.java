package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DischargeRepository extends JpaRepository<Discharge,Long> {

    Discharge findByDischargeId(String dischargeId);
    Discharge findByComManName(String comManName);
}