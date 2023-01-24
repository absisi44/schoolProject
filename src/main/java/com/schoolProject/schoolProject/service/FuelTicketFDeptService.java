package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptExistException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.FuelTicketFDept;

import java.util.List;

public interface FuelTicketFDeptService {

    List<FuelTicketFDept> listFuelTicketFDept();
    FuelTicketFDept findByFueltiketfId(String fueltiketfId);
    FuelTicketFDept findFuelTicketFDept(Long companies_id, Long fuel_id, Long discriminations_id);
    FuelTicketFDept addNewFuelTickD(FuelTicketFDept fuelTicketFDept) throws FuelTicketFDeptExistException;
    FuelTicketFDept updateFuelTickD(String fueltiketfId,FuelTicketFDept fuelTicketFDept) throws FuelTicketFDeptNotFoundException;

    FuelTicketFDept updateFuelTickDsStatus(String fueltiketfId) throws FuelTicketFDeptNotFoundException;

    void deleteFuelTickD(Long id);
}
