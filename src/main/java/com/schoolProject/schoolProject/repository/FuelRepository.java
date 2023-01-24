package com.schoolProject.schoolProject.repository;

import com.schoolProject.schoolProject.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {

    Fuel findByFuelId(String fuelId);
    Fuel findByFuelName(String fuelName);

}
