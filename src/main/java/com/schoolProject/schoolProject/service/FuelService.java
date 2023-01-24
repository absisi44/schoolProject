package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.FuelExistException;
import com.schoolProject.schoolProject.exception.model.FuelNotFoundException;
import com.schoolProject.schoolProject.model.Fuel;

import java.util.List;

public interface FuelService {
    List<Fuel> getFuels();
    Fuel findByFuelId(String fuelId);
    Fuel findByFuelName(String fuelName);
    Fuel addFuel(Fuel newFuel) throws FuelExistException;
    Fuel updateFuel(String fuelId, Fuel fuelDetails) throws FuelNotFoundException;
    void deleteFuel(Long id);
}
