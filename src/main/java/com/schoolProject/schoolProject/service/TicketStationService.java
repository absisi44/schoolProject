package com.schoolProject.schoolProject.service;

import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptAvailableQuantityException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.TicketStation;

import java.util.List;

public interface TicketStationService {

    List<TicketStation> listTicketStation();
    TicketStation findByTicketStationId(String ticketId);
    TicketStation addNewTicketStation(TicketStation ticketStation) throws FuelTicketFDeptAvailableQuantityException;
    TicketStation updateTicketStation(String ticketId,TicketStation ticketDetailes) throws FuelTicketFDeptNotFoundException;
    void deleteTicketStation (Long id);

}

