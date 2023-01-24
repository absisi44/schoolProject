package com.schoolProject.schoolProject.service.Impl;

import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptAvailableQuantityException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.TicketStation;
import com.schoolProject.schoolProject.repository.TicketStationRepository;
import com.schoolProject.schoolProject.service.StockService;
import com.schoolProject.schoolProject.service.TicketStationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TicketStationServiceImpl implements TicketStationService {

    public static final String THE_AVAILABLE_QUANTITY_IS = "The Available Quantity Is: ";
    public static final String FUELTIcKETS_NOT_FOUND_BY_INCOME_ID = "Fuel Ticket not Found by Fuel Ticket Id:";
    private final TicketStationRepository ticketStationRepository;
    private final StockService stockService;

    @Autowired
    public TicketStationServiceImpl(TicketStationRepository ticketStationRepository, StockService stockService) {
        this.ticketStationRepository = ticketStationRepository;
        this.stockService = stockService;
    }

    @Override
    public List<TicketStation> listTicketStation() {return ticketStationRepository.findAll();}

    @Override
    public TicketStation findByTicketStationId(String ticketId) {return ticketStationRepository.findByTicketId(ticketId);}

    @Override
    public TicketStation addNewTicketStation(TicketStation ticketStation) throws FuelTicketFDeptAvailableQuantityException {
        double stock_q = stockService.findQuantity(ticketStation.getCompanies(),ticketStation.getFuel(),
                ticketStation.getDiscriminations());
        TicketStation newticketStation= new TicketStation();
        if (stock_q < ticketStation.getQuantity()){
            throw new FuelTicketFDeptAvailableQuantityException(THE_AVAILABLE_QUANTITY_IS +stock_q);
        } else {
            double total_q = stock_q-ticketStation.getQuantity();
            newticketStation.setTicketId(generateTicketIs(ticketStation.getTicketId()));
            newticketStation.setTicketDate(ticketStation.getTicketDate());
            newticketStation.setQuantity(ticketStation.getQuantity());
            newticketStation.setSreviceHow(ticketStation.getSreviceHow());
            newticketStation.setVehicleNo(ticketStation.getVehicleNo());
            newticketStation.setCompanies(ticketStation.getCompanies());
            newticketStation.setFuel(ticketStation.getFuel());
            newticketStation.setDiscriminations(ticketStation.getDiscriminations());
            ticketStationRepository.save(newticketStation);
            stockService.updateQuantity(ticketStation.getCompanies(),ticketStation.getFuel(),
                    ticketStation.getDiscriminations(),total_q);
        }
        return newticketStation;
    }



    @Override
    public TicketStation updateTicketStation(String ticketId, TicketStation ticketDetailes) throws FuelTicketFDeptNotFoundException {

        TicketStation updateTicket=findByTicketStationId(ticketId);
        if (updateTicket ==null){

            throw new FuelTicketFDeptNotFoundException(FUELTIcKETS_NOT_FOUND_BY_INCOME_ID+ticketId);
        }

        updateTicket.setQuantity(ticketDetailes.getQuantity());
        updateTicket.setSreviceHow(ticketDetailes.getSreviceHow());
        updateTicket.setVehicleNo(ticketDetailes.getVehicleNo());
        updateTicket.setCompanies(ticketDetailes.getCompanies());
        updateTicket.setFuel(ticketDetailes.getFuel());
        updateTicket.setDiscriminations(ticketDetailes.getDiscriminations());
        ticketStationRepository.save(updateTicket);
        return updateTicket;
    }

    @Override
    public void deleteTicketStation(Long id) {ticketStationRepository.deleteById(id);}

    private String generateTicketIs(String ticketId) {return "TS-"+ LocalDate.now().getYear()+ RandomStringUtils.randomNumeric(3);}
}

