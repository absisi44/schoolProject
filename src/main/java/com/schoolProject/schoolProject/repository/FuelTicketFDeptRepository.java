package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.FuelTicketFDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTicketFDeptRepository extends JpaRepository<FuelTicketFDept,Long> {

    FuelTicketFDept findByFueltiketfId(String fueltiketfId);

    @Query("SELECT F FROM FuelTicketFDept F WHERE F.companies.id =?1 and F.fuel.id=?1 and F.discriminations.id=?1")
    FuelTicketFDept findFuelTicketFDept(Long companies_id, Long fuel_id, Long discriminations_id);

}

