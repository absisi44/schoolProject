package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

    Income findByIncomeId(String incomeId);
    Income findByTruckNo(String truckNo);

    @Query(" FROM Income S WHERE S.status =?1")
    Income findByStatus(String status);
}
