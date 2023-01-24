package com.schoolProject.schoolProject.controller;

import com.schoolProject.schoolProject.exception.ExceptionHandling;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptExistException;
import com.schoolProject.schoolProject.exception.model.FuelTicketFDeptNotFoundException;
import com.schoolProject.schoolProject.model.FuelTicketFDept;
import com.schoolProject.schoolProject.model.HttpResponse;
import com.schoolProject.schoolProject.service.FuelTicketFDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path ={"/oilapp/v1/FuelTicketD/"} )
public class FuelTicketFDeptController extends ExceptionHandling {

    public static final String FUEL_TICKET_DELETE_SUCCESSFULLY = "Fuel Ticket Delete Successfully";
    private final FuelTicketFDeptService fDeptService;

    @Autowired
    public FuelTicketFDeptController(FuelTicketFDeptService fDeptService) {
        this.fDeptService = fDeptService;
    }

    @GetMapping("/lis-fuelTicket")
    public ResponseEntity<List<FuelTicketFDept>> getlistFuelTicketFDept(){
        List<FuelTicketFDept> fDeptList = fDeptService.listFuelTicketFDept();
        return new ResponseEntity<>(fDeptList,OK);
    }

    @GetMapping("/find-fuelTicket/{fueltiketfId}")
    public ResponseEntity<FuelTicketFDept> findByFuelTicket(@PathVariable("fueltiketfId") String fueltiketfId){
        FuelTicketFDept fDeptList = fDeptService.findByFueltiketfId(fueltiketfId);
        return new ResponseEntity<>(fDeptList,OK);
    }


    @PostMapping("/add-fuelTicket")
    public ResponseEntity<FuelTicketFDept> addtFuelTicketFDept(@RequestBody FuelTicketFDept fDeptList) throws FuelTicketFDeptExistException {
        FuelTicketFDept NewFuelTicketFDept = fDeptService.addNewFuelTickD(fDeptList);
        return new ResponseEntity<>(NewFuelTicketFDept,OK);
    }

    @PutMapping("/update-fuelTicket/{fueltiketfId}")
    public ResponseEntity<FuelTicketFDept> updaFuelTicketFDept(@PathVariable("fueltiketfId") String fueltiketfId,@RequestBody FuelTicketFDept fDeptListDetails) throws FuelTicketFDeptNotFoundException {
        FuelTicketFDept updateFuelTicketFDept= fDeptService.updateFuelTickD(fueltiketfId,fDeptListDetails);
        return new ResponseEntity<>(updateFuelTicketFDept,OK);

    }

    @PutMapping("/update-Ticketstatus/{fueltiketfId}")
    public ResponseEntity<FuelTicketFDept> updaFuelTicketStatus(@PathVariable("fueltiketfId") String fueltiketfId) throws FuelTicketFDeptNotFoundException {
        FuelTicketFDept updatestatus= fDeptService.updateFuelTickDsStatus(fueltiketfId);
        return new ResponseEntity<>(updatestatus,OK);

    }

    @DeleteMapping("/delete-fuelTicket/{id}")
    public ResponseEntity<HttpResponse> deletefuelTicket(@PathVariable("id") Long id){
        fDeptService.deleteFuelTickD(id);
        return response(HttpStatus.NO_CONTENT, FUEL_TICKET_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        HttpResponse body = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new ResponseEntity<HttpResponse>(body, status);
    }

}
