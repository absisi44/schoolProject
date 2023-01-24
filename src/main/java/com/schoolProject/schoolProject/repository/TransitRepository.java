package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface TransitRepository extends JpaRepository<Transit,Long> {

    Transit findByTransitId(String transitId);
    Transit findByIncomeDate(LocalDate incomeDate);

    Transit findByTruckNo(String truckNo);

}
