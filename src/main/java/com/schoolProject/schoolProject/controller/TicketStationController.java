package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptAvailableQuantityException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.model.TicketStation;
import com.schoolProject.schoolProject.service.TicketStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/ticket/"} )
public class TicketStationController {

    public static final String TICKET_STATION_DELETE_SUCCESSFULLY = "Ticket Station Delete Successfully";
    private final TicketStationService ticketStationService;

    @Autowired
    public TicketStationController(TicketStationService ticketStationService) {
        this.ticketStationService = ticketStationService;
    }


    @GetMapping("/lis-ticket")
    public ResponseEntity<List<TicketStation>> getlistticket(){
        List<TicketStation> ticketStations = ticketStationService.listTicketStation();
        return new ResponseEntity<>(ticketStations,OK);
    }

    @GetMapping("/find-ticket/{ticketId}")
    public ResponseEntity<TicketStation> findByticket(@PathVariable("ticketId") String ticketId){
        TicketStation ticket = ticketStationService.findByTicketStationId(ticketId);
        return new ResponseEntity<>(ticket,OK);
    }

    @PostMapping("/add-ticket")
    public ResponseEntity<TicketStation> addtTicketStation(@RequestBody TicketStation ticketStation) throws FuelTicketFDeptAvailableQuantityException {
        TicketStation NewTicketStation = ticketStationService.addNewTicketStation(ticketStation);
        return new ResponseEntity<>(NewTicketStation,OK);
    }

    @PutMapping("/update-ticket/{ticketId}")
    public ResponseEntity<TicketStation> updateTicket(@PathVariable("ticketId") String ticketId, @RequestBody TicketStation ticketStation) throws FuelTicketFDeptNotFoundException {
        TicketStation updateTicketStation= ticketStationService.updateTicketStation(ticketId,ticketStation);
        return new ResponseEntity<>(updateTicketStation,OK);

    }

    @DeleteMapping("/delete-ticket/{id}")
    public ResponseEntity<HttpResponse> deleteticket(@PathVariable("id") Long id){
        ticketStationService.deleteTicketStation(id);
        return response(HttpStatus.NO_CONTENT, TICKET_STATION_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }


}

